package com.example.userService.services;

import com.example.userService.utils.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

/**
 * Service layer hidden all service logic
 *
 */
@Service
public class UserAccountsService {


    @Autowired
    public RestTemplate restTemplate;

    protected Logger logger = Logger.getLogger(UserAccountsService.class.getName());

    public Account validUser(String accountNumber) {
        logger.info("findByNumber() invoked: for " + accountNumber);
        try {
            return restTemplate.getForObject("http://accounts-service/accounts/{number}", Account.class, accountNumber);
        } catch (Exception e) {
            logger.severe(e.getClass() + ": " + e.getLocalizedMessage());
            return null;
        }
    }

}
