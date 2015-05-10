package com.appdirect.integration.oauth;

import lombok.extern.slf4j.Slf4j;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.HTTPConduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Anatoly Chernysh
 */
@Slf4j
@Component
public class OAuthInterceptor extends AbstractPhaseInterceptor<Message> {

    private final OAuthConsumer consumer;

    @Autowired
    public OAuthInterceptor(@Value("${appdirect.oauth.consumerKey}") String oauthConsumerKey,
                            @Value("${appdirect.oauth.consumerSecret}") String oauthConsumerSecret) {
        super(Phase.SEND);
        this.consumer = new DefaultOAuthConsumer(oauthConsumerKey, oauthConsumerSecret);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        log.info("Entering handleMessage");

        HttpURLConnection connect = (HttpURLConnection) message.get(HTTPConduit.KEY_HTTP_CONNECTION);
        if (connect == null) {
            return;
        }

        URL url = connect.getURL();
        if (url == null) {
            return;
        }
        log.info("Request: " + url);

        try {
            consumer.sign(connect);
        } catch (OAuthException e) {
            throw new Fault(e);
        }
    }
}