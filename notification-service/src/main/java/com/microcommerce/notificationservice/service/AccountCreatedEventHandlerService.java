package com.microcommerce.notificationservice.service;

import com.microcommerce.notificationservice.data.event.AccountCreatedEvent;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountCreatedEventHandlerService {
    private static final String ACCOUNT_CONFIRMATION_EMAIL_TEMPLATE = "account_confirmation_template";
    private static final String UTF_8_ENCODING = "UTF-8";
    private static final String ACCOUNT_CONFIRMATION_URL = "http://localhost:8080/auth/confirm?token=";

    private final EmailSenderService emailSenderService;
    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendAccountConfirmationMail(AccountCreatedEvent accountCreatedEvent) {
        var accountConfirmationMessage = emailSender.createMimeMessage();

        Context context = new Context();
        context.setVariable("firstName", accountCreatedEvent.getFirstName());
        context.setVariable("confirmationLink", ACCOUNT_CONFIRMATION_URL + accountCreatedEvent.getActivationToken());
        String content = templateEngine.process(ACCOUNT_CONFIRMATION_EMAIL_TEMPLATE, context);

        try {
            var helper = new MimeMessageHelper(accountConfirmationMessage, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setFrom(sender);
            helper.setTo(accountCreatedEvent.getEmail());
            helper.setSubject("New MicroCommerce Account");
            helper.setText(content, true);

            emailSenderService.sendHtmlMailMessage(accountConfirmationMessage, context);
        } catch (MessagingException ex) {
            log.error(ex.getMessage());
        }
    }
}
