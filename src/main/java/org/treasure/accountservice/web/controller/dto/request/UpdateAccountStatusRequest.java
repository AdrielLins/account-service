package org.treasure.accountservice.web.controller.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.treasure.accountservice.domain.enums.AccountStatus;

@Data
public class UpdateAccountStatusRequest {

    @NotNull
    private AccountStatus status;
}
