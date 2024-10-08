package com.example.transfer.service.impl;

import com.example.transfer.dao.AccountDao;
import com.example.transfer.exceptions.AccountNotFoundException;
import com.example.transfer.exceptions.InsufficientFundsException;
import com.example.transfer.exceptions.SameAccountException;
import com.example.transfer.model.Account;
import com.example.transfer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    @Override
    public void saveAccount(Account account) {
        accountDao.save(account);
    }

    @Override
    public void balanceAmounts(Account sourceAccount, Account targetAccount, double amount) throws Exception {
        //check the balance of source account
        if (sourceAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds in the source account");
        }

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        targetAccount.setBalance(targetAccount.getBalance() + amount);

        accountDao.save(sourceAccount);
        accountDao.save(targetAccount);
    }

    @Override
    public Map<String, Account> validateAndGetAccounts(String sourceAccountId, String targetAccountId) throws AccountNotFoundException, SameAccountException {
        if (Long.valueOf(sourceAccountId).equals(Long.valueOf(targetAccountId))) {
            throw new SameAccountException("Source Account Id must be different from target Account Id");
        }
        // Fetch source account
        Optional<Account> sourceAccount = accountDao.findById(Long.valueOf(sourceAccountId));
        if (sourceAccount.isEmpty()) {
            throw new AccountNotFoundException("Wrong Source Account Id: " + sourceAccountId);
        }
        // Fetch target account
        Optional<Account> targetAccount = accountDao.findById(Long.valueOf(targetAccountId));
        if (targetAccount.isEmpty()) {
            throw new AccountNotFoundException("Wrong Target Account Id: " + targetAccountId);
        }

        // Create a map to store accounts
        Map<String, Account> accounts = new HashMap<>();
        accounts.put("sourceAccount", sourceAccount.get());
        accounts.put("targetAccount", targetAccount.get());

        return accounts;
    }
}
