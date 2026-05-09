package org.treasure.accountservice.factory;

import com.github.f4b6a3.uuid.UuidCreator;
import org.springframework.stereotype.Component;
import org.treasure.accountservice.domain.AccountDomain;

import java.time.LocalDateTime;

@Component
public class AccountFactory {

    public AccountDomain createAccount(final AccountDomain domain) {
        return AccountDomain.builder()
            .id(UuidCreator.getTimeOrderedEpoch())
            .name(domain.getName())
            .type(domain.getType())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }
}
