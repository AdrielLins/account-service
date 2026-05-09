package org.treasure.accountservice.web.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.treasure.accountservice.domain.enums.AccountType;

@Data
public class AccountRequest {

    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    private AccountType type;
}
