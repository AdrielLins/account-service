package org.treasure.accountservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.treasure.accountservice.domain.enums.AccountType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class AccountModel {

    @Id
    private UUID id;
    private String name;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
