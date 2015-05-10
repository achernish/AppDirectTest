package com.appdirect.config;

import com.appdirect.integration.security.OpenIdUserDetailsServiceImpl;
import org.openid4java.consumer.ConsumerException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.openid.AxFetchListFactory;
import org.springframework.security.openid.OpenID4JavaConsumer;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anatoly Chernysh
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public AuthenticationUserDetailsService<OpenIDAuthenticationToken> openIdUserDetailsService() {
        return new OpenIdUserDetailsServiceImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .anyRequest().permitAll().and().csrf().disable()
                .logout().logoutSuccessUrl("/exit").and()
                .openidLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/openid")
                .authenticationUserDetailsService(openIdUserDetailsService())
                .consumer(openIdConsumer());
    }

    @Bean
    public OpenID4JavaConsumer openIdConsumer() throws ConsumerException {
        OpenIDAttribute roleAttr = new OpenIDAttribute("roles", "https://www.appdirect.com/schema/user/roles");
        roleAttr.setCount(99);

        AxFetchListFactory attributesToFetchFactory = new AxFetchListFactory() {

            @Override
            public List<OpenIDAttribute> createAttributeList(String s) {
                List<OpenIDAttribute> attributes = new ArrayList<>();

                attributes.add(new OpenIDAttribute("userUuid", "https://www.appdirect.com/schema/user/uuid"));
                attributes.add(new OpenIDAttribute("email", "http://axschema.org/contact/email"));
                attributes.add(new OpenIDAttribute("firstName", "http://axschema.org/namePerson/first"));
                attributes.add(new OpenIDAttribute("lastName", "http://axschema.org/namePerson/last"));
                attributes.add(new OpenIDAttribute("country", "http://axschema.org/contact/country/home"));
                attributes.add(new OpenIDAttribute("language", "http://axschema.org/pref/language"));
                attributes.add(new OpenIDAttribute("companyUuid", "https://www.appdirect.com/schema/company/uuid"));
                attributes.add(new OpenIDAttribute("companyName", "http://axschema.org/company/name"));
                attributes.add(new OpenIDAttribute("title", "http://axschema.org/company/title"));
                attributes.add(roleAttr);

                return attributes;
            }
        };

        return new OpenID4JavaConsumer(attributesToFetchFactory);
    }
}