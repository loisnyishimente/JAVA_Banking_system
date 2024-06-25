package rw.ac.rca.spring_boot_template.services;

import rw.ac.rca.spring_boot_template.models.Transaction;
import java.math.BigDecimal;

public interface BankingService {
    Transaction deposit(Long customerId, BigDecimal amount);
    Transaction withdraw(Long customerId, BigDecimal amount);
    Transaction transfer(Long fromCustomerId, Long toCustomerId, BigDecimal amount);
}
