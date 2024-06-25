package rw.ac.rca.spring_boot_template.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import rw.ac.rca.spring_boot_template.enumerations.TransactionStatus;
import rw.ac.rca.spring_boot_template.enumerations.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private String account;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private TransactionType type;

    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDateTime;

    @Column(length = 255)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    private TransactionStatus status;

    @Column(nullable = false)
    private LocalDateTime transactionDateTime;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public LocalDateTime getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }
}
