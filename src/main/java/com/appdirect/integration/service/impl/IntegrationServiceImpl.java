package com.appdirect.integration.service.impl;

import com.appdirect.dao.repositories.entities.Customer;
import com.appdirect.dao.service.CustomerService;
import com.appdirect.integration.model.Event;
import com.appdirect.integration.model.SubscriptionCancelEvent;
import com.appdirect.integration.model.SubscriptionChangeEvent;
import com.appdirect.integration.model.SubscriptionOrderEvent;
import com.appdirect.integration.oauth.OAuthInterceptor;
import com.appdirect.integration.remote.service.AppDirectIntegrationAPI;
import com.appdirect.integration.remote.type.ErrorCode;
import com.appdirect.integration.remote.type.Result;
import com.appdirect.integration.service.IntegrationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author Anatoly Chernysh
 */
@Service
@Slf4j
public class IntegrationServiceImpl implements IntegrationService {

    @Value("${appdirect.baseurl}")
    private String appDirectBaseUrl;

    @Autowired
    private OAuthInterceptor oauthInterceptor;

    @Autowired
    private CustomerService customerService;

    private AppDirectIntegrationAPI getAppDirectIntegrationAPI() {
        AppDirectIntegrationAPI api = JAXRSClientFactory.create(appDirectBaseUrl, AppDirectIntegrationAPI.class);
        ClientConfiguration config = WebClient.getConfig(api);
        config.getOutInterceptors().add(oauthInterceptor);
        return api;
    }

    @Override
    @Transactional
    public Result handleEvent(String eventUrl) {
        String token = null;
        if (StringUtils.hasLength(eventUrl)) {
            token = eventUrl.substring(eventUrl.lastIndexOf('/') + 1);
        }

        AppDirectIntegrationAPI api = getAppDirectIntegrationAPI();

        String xmlEvent = api.readEvent(token);
        log.debug("XML event: " + xmlEvent);

        Event event = null;
        try {
            event = new Event(xmlEvent);
        } catch (Exception e) {
            log.error("Event not found or invalid: ", e);
        }

        switch (event.getEventType()) {
            case SUBSCRIPTION_ORDER:
                return processSubscriptionOrderEvent(event);

            case SUBSCRIPTION_CHANGE:
                return processSubscriptionChangeEvent(event);

            case SUBSCRIPTION_CANCEL:
                return processSubscriptionCancelEvent(event);

            case SUBSCRIPTION_NOTICE:
                return processSubscriptionNoticeEvent(event);

            default:
                return new Result(false, ErrorCode.UNKNOWN_ERROR, "Event type is not supported: " + String.valueOf(event.getEventType()));
        }
    }

    private Result processSubscriptionOrderEvent(Event event) {
        SubscriptionOrderEvent subscriptionOrderEvent = new SubscriptionOrderEvent(event.getEventXmlDocument());

        Customer existingCustomer = customerService.getCustomerByOpenId(subscriptionOrderEvent.getOpenId());
        if (existingCustomer != null) {
            return new Result(false, ErrorCode.USER_ALREADY_EXISTS, "Customer already exists.");
        }

        Customer customer = new Customer();
        customer.setOpenId(subscriptionOrderEvent.getOpenId());
        customer.setFirstName(subscriptionOrderEvent.getFirstName());
        customer.setLastName(subscriptionOrderEvent.getLastName());
        customer.setEmail(subscriptionOrderEvent.getEmail());
        customer.setEdition(subscriptionOrderEvent.getEdition());
        this.customerService.addCustomer(customer);

        Result result = new Result(true, "Customer successfully created.");
        result.setAccountIdentifier(String.valueOf(customer.getId()));
        return result;
    }

    private Result processSubscriptionChangeEvent(Event event) {
        SubscriptionChangeEvent subscriptionChangeEvent = new SubscriptionChangeEvent(event.getEventXmlDocument());

        Customer customer = this.customerService.getCustomerById(subscriptionChangeEvent.getId());
        if (customer == null) {
            return new Result(false, ErrorCode.ACCOUNT_NOT_FOUND, String.format("Could not find customer with identifier %d.",
                    subscriptionChangeEvent.getId()));
        }

        customer.setEdition(subscriptionChangeEvent.getEdition());
        this.customerService.updateCustomer(customer);

        return new Result(true, String.format("Customer with identifier %d has been updated.", customer.getId()));
    }

    private Result processSubscriptionCancelEvent(Event event) {
        SubscriptionCancelEvent subscriptionCancelEvent = new SubscriptionCancelEvent(event.getEventXmlDocument());

        Customer customer = this.customerService.getCustomerById(subscriptionCancelEvent.getId());
        if (customer == null) {
            return new Result(false, ErrorCode.ACCOUNT_NOT_FOUND, String.format("Could not find customer with identifier %d.",
                    subscriptionCancelEvent.getId()));
        }

        this.customerService.removeCustomer(subscriptionCancelEvent.getId());

        return new Result(true, String.format("Customer with identifier %s has been deleted.", customer.getId()));
    }

    private Result processSubscriptionNoticeEvent(Event event) {
        return new Result(true, "Dummy notice success.");
    }
}