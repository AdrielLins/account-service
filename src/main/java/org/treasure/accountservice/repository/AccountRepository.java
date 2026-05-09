package org.treasure.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.treasure.accountservice.model.AccountModel;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountModel, UUID> {
}
