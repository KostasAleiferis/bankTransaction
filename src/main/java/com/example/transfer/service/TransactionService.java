package com.example.transfer.service;

import com.example.transfer.dto.MoneyTransferRequestDto;
import com.example.transfer.model.Transaction;

public interface TransactionService {

    Transaction transfer(MoneyTransferRequestDto moneyTransferRequestDto) throws Exception;

}
