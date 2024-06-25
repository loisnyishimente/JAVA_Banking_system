package rw.ac.rca.spring_boot_template.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import rw.ac.rca.spring_boot_template.exceptions.BadRequestAlertException;
import rw.ac.rca.spring_boot_template.mailHandler.Mail;
import rw.ac.rca.spring_boot_template.models.User;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

@Service
public class MailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String appEmail;

    @Autowired
    public MailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendTransactionEmail(User user, BigDecimal amount, String transactionType, String accountNumber) {
        String subject = "Transaction Notification";
        String message = String.format(
                "Dear %s,\n\nYour %s of $%s on your account %s has been completed successfully.\n\nBest Regards,\n%s",
                user.getUsername(), transactionType, amount, accountNumber, "Your Bank Name"
        );

        sendEmail(user.getEmail(), subject, message, "transaction-notification");
    }

    private void sendEmail(String to, String subject, String content, String templateName) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariable("message", content);
            String html = templateEngine.process(templateName, context);

            helper.setTo(to);
            helper.setText(html, true);
            helper.setSubject(subject);
            helper.setFrom(appEmail);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new BadRequestAlertException("Failed to send email: " + e.getMessage());
        }
    }

    // Existing methods ...
}
