package com.microcommerce.notificationservice.service;

import com.microcommerce.notificationservice.data.event.OrderCreatedEvent;
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
@RequiredArgsConstructor
@Slf4j
public class OrderCreatedEventHandlerService {
    private static final String ORDER_CONFIRMED_EMAIL_TEMPLATE = "order_confirmation_template";
    private static final String UTF_8_ENCODING = "UTF-8";

    private final EmailSenderService emailSenderService;
    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendOrderConfirmedMail(OrderCreatedEvent orderCreatedEvent) {
        var orderConfirmationMessage = emailSender.createMimeMessage();

        Context context = new Context();
        context.setVariable("orderedProducts", orderCreatedEvent.getOrderItems());
        String content = templateEngine.process(ORDER_CONFIRMED_EMAIL_TEMPLATE, context);

        try {
            var helper = new MimeMessageHelper(orderConfirmationMessage, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setFrom(sender);
            helper.setTo(orderCreatedEvent.getUser().getEmail());
            helper.setSubject("MicroCommerce Order Confirmed");
            helper.setText(content, true);

            emailSenderService.sendHtmlMailMessage(orderConfirmationMessage, context);
        } catch (MessagingException exception) {
            log.info(exception.getMessage());
        }
    }
}
