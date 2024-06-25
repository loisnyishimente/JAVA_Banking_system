package rw.ac.rca.spring_boot_template.dtos.requests;

import java.math.BigDecimal;

public class TransactionRequest {
    private Long customerId;
    private BigDecimal amount;
    private Long destinationAccountId; // Used only for transfers

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

    public Long getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(Long destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }
}
