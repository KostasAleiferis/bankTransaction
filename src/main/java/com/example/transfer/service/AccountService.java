package com.example.transfer.service;

import com.example.transfer.exceptions.AccountNotFoundException;
import com.example.transfer.exceptions.SameAccountException;
import com.example.transfer.model.Account;

import java.util.Map;

public interface AccountService {

    void saveAccount(Account account);

    void balanceAmounts(Account sourceAccount, Account targetAccount, double amount) throws Exception;

    Map<String, Account> validateAndGetAccounts(String sourceAccountId, String targetAccountId) throws AccountNotFoundException, SameAccountException;
}
