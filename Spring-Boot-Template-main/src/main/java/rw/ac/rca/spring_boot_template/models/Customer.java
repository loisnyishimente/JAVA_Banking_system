package rw.ac.rca.spring_boot_template.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "customers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"mobile"}),
        @UniqueConstraint(columnNames = {"account"})
})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstname;
    private String lastname;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "mobile", unique = true)
    private String mobile;
    @Transient
    private LocalDate dob; // This will not be persisted, just held temporarily
    private Date dateOfBirth; // Persisted as a Date
    private String account; // Account number field
    private BigDecimal balance;
    private LocalDateTime lastUpdatedDateTime;

    public Customer() {
        // Default constructor
    }

    public Customer(String firstname, String lastname, String email, String mobile, LocalDate dob, String account, BigDecimal balance) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.mobile = mobile;
        this.dob = dob; // Temporary holding
        this.dateOfBirth = java.sql.Date.valueOf(dob); // Convert LocalDate to java.sql.Date for persistence
        this.account = account;
        this.balance = balance;
        this.lastUpdatedDateTime = LocalDateTime.now(); // Initialize with current time
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(LocalDateTime lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }
}
