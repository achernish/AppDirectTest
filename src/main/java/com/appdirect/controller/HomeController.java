package com.appdirect.controller;

import com.appdirect.dto.CustomerDTO;
import com.appdirect.integration.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Anatoly Chernysh
 */
@Controller
public class HomeController {

    @Value("${appdirect.oauth.consumerKey}")
    private String oauthConsumerKey;

    @Value("${appdirect.oauth.consumerSecret}")
    private String oauthConsumerSecret;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("oauthConsumerKey", oauthConsumerKey);
        model.addAttribute("oauthConsumerSecret", oauthConsumerSecret);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            UserDetailsImpl user = (UserDetailsImpl) principal;
            CustomerDTO customerDTO = user.getCustomerDTO();

            model.addAttribute("firstName", customerDTO.getFirstName());
            model.addAttribute("lastName", customerDTO.getLastName());
            model.addAttribute("openId", customerDTO.getOpenId());
            model.addAttribute("isLoggedIn", true);
        } else {
            model.addAttribute("isLoggedIn", false);
        }

        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {
        if (request.getParameterMap().containsKey("error")) {
            String message = "N/A";
            HttpSession session = request.getSession();
            if (session != null) {
                Object exception = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
                if (exception != null) {
                    message = exception.toString();
                }
            }
            model.addAttribute("error", "An error occurred during openID login. Message: " + message);
        }

        return "login";
    }

    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:/";
    }
}