package com.example.transfer.configuration;

import com.example.transfer.enums.Currency;
import com.example.transfer.model.Account;
import com.example.transfer.service.AccountService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AccountConfig {

    private final AccountService accountService;
    private static final Logger logger = LoggerFactory.getLogger(AccountConfig.class);

    public AccountConfig(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostConstruct
    public void createAccount() {
        logger.info("Get in createAccount()");
        Account account1 = new Account(500, Currency.GBP, LocalDateTime.now());
        Account account2 = new Account(600, Currency.GBP, LocalDateTime.now());

        accountService.saveAccount(account1);
        accountService.saveAccount(account2);
        logger.info("2 Accounts created: \nAccount1: {}\nAccount2: {}", account1, account2);

    }
}
