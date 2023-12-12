package com.microcommerce.notificationservice.listener;

import com.microcommerce.notificationservice.data.event.AccountCreatedEvent;
import com.microcommerce.notificationservice.service.AccountCreatedEventHandlerService;
import com.microcommerce.notificationservice.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountCreatedEventListener {

    private final AccountCreatedEventHandlerService accountCreatedEventHandler;

    @KafkaListener(topics = "${kafka.topics.account-topic.name}", groupId = "${kafka.topics.account-topic.group-id}", containerFactory = "accountCreatedListenerContainerFactory")
    public void listen(AccountCreatedEvent accountCreatedEvent) {
        accountCreatedEventHandler.sendAccountConfirmationMail(accountCreatedEvent);
    }
}
