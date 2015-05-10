package com.appdirect.integration.security;

import com.appdirect.dao.converters.CustomerConverter;
import com.appdirect.dao.repositories.entities.Customer;
import com.appdirect.dao.service.CustomerService;
import com.appdirect.dto.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Anatoly Chernysh
 */
@Slf4j
public class OpenIdUserDetailsServiceImpl implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

    @Autowired
    private CustomerService customerService;

    @Override
    @Transactional
    public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException {
        String openId = token.getIdentityUrl();

        Customer customer = customerService.getCustomerByOpenId(openId);
        if (customer == null) {
            throw new UsernameNotFoundException(openId);
        }

        CustomerConverter customerConverter = new CustomerConverter();
        CustomerDTO customerDTO = customerConverter.convert(customer);
        return new UserDetailsImpl(customerDTO);
    }
}
