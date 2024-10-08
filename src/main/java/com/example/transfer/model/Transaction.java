package com.example.transfer.model;

import com.example.transfer.enums.Currency;
import jakarta.persistence.*;
import lombok.ToString;

@ToString
@Entity
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID", nullable = false)
    private Long transactionId;
    @ManyToOne
    @JoinColumn(name = "SOURCE_ACCOUNT_ID", nullable = false)
    private Account sourceAccount;
    @ManyToOne
    @JoinColumn(name = "TARGET_ACCOUNT_ID", nullable = false)
    private Account targetAccount;
    @Column(name = "AMOUNT", nullable = false)
    private double amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "CURRENCY", nullable = false)
    private Currency currency;


    public Transaction(Account sourceAccount, Account targetAccount, double amount, Currency currency) {
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.amount = amount;
        this.currency = currency;
    }

    public Transaction() {

    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Account targetAccount) {
        this.targetAccount = targetAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }
}
