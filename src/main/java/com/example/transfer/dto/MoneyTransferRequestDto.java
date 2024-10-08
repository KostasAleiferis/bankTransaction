package com.example.transfer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

public class MoneyTransferRequestDto {

    @JsonProperty("sourceAccountId")
    @NotBlank(message = "Source account ID cannot be null or blank")
    @NumberFormat
    private String sourceAccountId;

    @JsonProperty("targetAccountId")
    @NotBlank(message = "Target account ID cannot be null or blank")
    private String targetAccountId;

    @JsonProperty("amount")
    @DecimalMin(value = "0", message = "Amount must be a positive value")
    private double amount;

    @JsonProperty("currency")
    @NotBlank(message = "Currency cannot be null or null")
    private String currency;

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public String getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(String targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
