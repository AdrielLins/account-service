package org.treasure.accountservice.domain;

import lombok.Builder;
import lombok.Data;
import org.treasure.accountservice.domain.enums.AccountStatus;
import org.treasure.accountservice.domain.enums.AccountType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class AccountDomain {
    private UUID id;
    private String name;
    private AccountType type;
    private AccountStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
