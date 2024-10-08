package com.example.transfer.service.impl;

import com.example.transfer.dao.TransactionDao;
import com.example.transfer.dto.MoneyTransferRequestDto;
import com.example.transfer.enums.Currency;
import com.example.transfer.exceptions.InvalidCurrencyException;
import com.example.transfer.model.Account;
import com.example.transfer.model.Transaction;
import com.example.transfer.service.AccountService;
import com.example.transfer.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    TransactionDao transactionDao;
    @Autowired
    AccountService accountService;

    public Transaction transfer(MoneyTransferRequestDto moneyTransferRequestDto) throws Exception {
        try {
            //Validate accountIds number format
            Long.valueOf(moneyTransferRequestDto.getSourceAccountId());
            Long.valueOf(moneyTransferRequestDto.getTargetAccountId());
            //Validate currency
            Currency currency = validateCurrency(moneyTransferRequestDto.getCurrency());

            Map<String, Account> accounts = accountService.validateAndGetAccounts(
                    moneyTransferRequestDto.getSourceAccountId(),
                    moneyTransferRequestDto.getTargetAccountId());

            Account sourceAccount = accounts.get("sourceAccount");
            Account targetAccount = accounts.get("targetAccount");
            accountService.balanceAmounts(sourceAccount, targetAccount, moneyTransferRequestDto.getAmount());

            Transaction transaction = new Transaction(sourceAccount, targetAccount,
                    moneyTransferRequestDto.getAmount(), currency);
            transactionDao.save(transaction);
            logger.info("Successful Transaction: {}", transaction);
            return transaction;
        } catch (NumberFormatException e) {
            throw e;
        }
    }

    private Currency validateCurrency(String currencyInput) throws InvalidCurrencyException {
        try {
            return Currency.valueOf(currencyInput);
        } catch (IllegalArgumentException e) {
            // Handle the invalid currency case
            throw new InvalidCurrencyException("Invalid currency. Please chosse one of these currencies: " + Arrays.toString(Currency.values()));
        }
    }
}
