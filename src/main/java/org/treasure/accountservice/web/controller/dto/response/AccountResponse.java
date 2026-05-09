package org.treasure.accountservice.web.controller.dto.response;

import lombok.Data;
import org.treasure.accountservice.domain.enums.AccountType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AccountResponse {
    private UUID id;
    private String name;
    private AccountType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
