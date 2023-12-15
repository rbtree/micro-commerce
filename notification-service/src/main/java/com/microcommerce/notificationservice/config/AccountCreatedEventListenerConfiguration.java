package com.microcommerce.notificationservice.config;

import com.microcommerce.notificationservice.data.event.AccountCreatedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.UUIDDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class AccountCreatedEventListenerConfiguration {

    @Value("${kafka.broker.host}")
    private String kafkaBrokerHost;

    @Value("${kafka.topics.account-topic.group-id}")
    private String consumerGroupId;

    @Bean
    public ConsumerFactory<UUID, AccountCreatedEvent> accountCreatedEventConsumerFactory() {
        Map<String, Object> properties = new HashMap<>(){{
            put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokerHost);
            put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
            put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, UUIDDeserializer.class);
            put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
            put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, UUIDDeserializer.class);
            put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
            put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
            put(JsonDeserializer.VALUE_DEFAULT_TYPE, AccountCreatedEvent.class.getName());
        }};

        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<UUID, AccountCreatedEvent> accountCreatedListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<UUID, AccountCreatedEvent>();
        factory.setConsumerFactory(accountCreatedEventConsumerFactory());
        return factory;
    }
}
