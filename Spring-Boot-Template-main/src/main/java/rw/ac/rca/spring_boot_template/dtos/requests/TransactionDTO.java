package rw.ac.rca.spring_boot_template.dtos.requests;

import rw.ac.rca.spring_boot_template.enumerations.TransactionType;

import java.math.BigDecimal;

public class TransactionDTO {
    private Long customerId;
    private BigDecimal amount;
    private TransactionType transactionType;
    private Long destinationAccountId; // Optional, used for transfers

    // Constructors
    public TransactionDTO() {
    }

    public TransactionDTO(Long customerId, BigDecimal amount, TransactionType transactionType, Long destinationAccountId) {
        this.customerId = customerId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.destinationAccountId = destinationAccountId;
    }

    // Getters and Setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Long getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(Long destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }
}
