package rw.ac.rca.spring_boot_template.services.serviceImpl;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.ac.rca.spring_boot_template.enumerations.TransactionStatus;
import rw.ac.rca.spring_boot_template.enumerations.TransactionType;
import rw.ac.rca.spring_boot_template.models.Customer;
import rw.ac.rca.spring_boot_template.models.Message;
import rw.ac.rca.spring_boot_template.models.Transaction;
import rw.ac.rca.spring_boot_template.repositories.ICustomerRepository;
import rw.ac.rca.spring_boot_template.repositories.IMessageRepository;
import rw.ac.rca.spring_boot_template.repositories.ITransactionRepository;
import rw.ac.rca.spring_boot_template.services.BankingService;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class BankingServiceImpl implements BankingService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private IMessageRepository messageRepository;

    @Autowired
    private EmailService emailService;

    @Override
    @Transactional
    public Transaction deposit(Long customerId, BigDecimal amount) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setBalance(customer.getBalance().add(amount));
        customer.setLastUpdatedDateTime(LocalDateTime.now());
        customerRepository.save(customer);

        Transaction transaction = new Transaction();
        transaction.setCustomer(customer);
        transaction.setAccount(customer.getAccount());
        transaction.setAmount(amount);
        transaction.setDescription("Deposit amount: " + amount);
        transaction.setTransactionDateTime(LocalDateTime.now());
        transaction.setType(TransactionType.SAVING); // Updated enum type to DEPOSIT
        transaction.setStatus(TransactionStatus.COMPLETED);

        transaction = transactionRepository.save(transaction);

        sendTransactionEmail(customer, amount, "Deposit");

        return transaction;
    }

    @Override
    @Transactional
    public Transaction withdraw(Long customerId, BigDecimal amount) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (customer.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        customer.setBalance(customer.getBalance().subtract(amount));
        customer.setLastUpdatedDateTime(LocalDateTime.now());
        customerRepository.save(customer);

        Transaction transaction = new Transaction();
        transaction.setCustomer(customer);
        transaction.setAccount(customer.getAccount());
        transaction.setAmount(amount);
        transaction.setDescription("Withdrawal amount: " + amount);
        transaction.setTransactionDateTime(LocalDateTime.now());
        transaction.setType(TransactionType.WITHDRAW);
        transaction.setStatus(TransactionStatus.COMPLETED);

        transaction = transactionRepository.save(transaction);

        sendTransactionEmail(customer, amount, "Withdrawal");

        return transaction;
    }

    @Override
    @Transactional
    public Transaction transfer(Long fromCustomerId, Long toCustomerId, BigDecimal amount) {
        Transaction withdrawTransaction = withdraw(fromCustomerId, amount);
        Transaction depositTransaction = deposit(toCustomerId, amount);
        return depositTransaction;
    }

    private void sendTransactionEmail(Customer customer, BigDecimal amount, String transactionType) {
        try {
            emailService.sendTransactionEmail(
                    customer,
                    amount,
                    transactionType,
                    customer.getAccount()
            );
            registerMessage(customer, amount, transactionType);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle exception (e.g., log it or notify admin)
        }
    }

    private void registerMessage(Customer customer, BigDecimal amount, String transactionType) {
        String messageContent = String.format(
                "Dear %s, your %s of $%.2f on your account %s has been completed successfully.",
                customer.getFirstname() + " " + customer.getLastname(),
                transactionType.toLowerCase(),
                amount,
                customer.getAccount()
        );

        Message message = new Message();
        message.setCustomer(customer);
        message.setMessage(messageContent);
        message.setDateTime(LocalDateTime.now());

        messageRepository.save(message);
    }
}
