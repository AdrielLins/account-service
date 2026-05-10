package org.treasure.accountservice.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.treasure.accountservice.event.dto.AccountCreatedEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountEventProducer {

    private final KafkaTemplate<String, AccountCreatedEvent> kafkaTemplate;

    @Value("${app.kafka.topic.account-created}")
    private String accountCreatedTopic;

    public void publishAccountCreated(final AccountCreatedEvent event) {

        kafkaTemplate.send(
            accountCreatedTopic,
            event.accountId().toString(),
            event
        );

        log.info(
            "Published AccountCreatedEvent for accountId: {}",
            event.accountId()
        );
    }
}
