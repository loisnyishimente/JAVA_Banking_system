package rw.ac.rca.spring_boot_template.services.serviceImpl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import rw.ac.rca.spring_boot_template.models.Customer;

import java.math.BigDecimal;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendTransactionEmail(Customer customer, BigDecimal amount, String transactionType, String account) throws MessagingException {
        String subject = "Transaction Notification";
        String messageContent = String.format(
                "Dear %s, your %s of $%.2f on your account %s has been completed successfully.",
                customer.getFirstname() + " " + customer.getLastname(),
                transactionType.toLowerCase(),
                amount,
                account
        );

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(customer.getEmail());
        helper.setSubject(subject);
        helper.setText(messageContent, true);

        javaMailSender.send(mimeMessage);
    }
}
