package org.treasure.accountservice.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.treasure.accountservice.web.controller.dto.request.AccountRequest;
import org.treasure.accountservice.web.controller.dto.response.AccountResponse;

import java.util.List;
import java.util.UUID;

@Tag(name = "accounts")
public interface AccountsApi {

    @GetMapping
    @Operation(
        summary = "Endpoint de consulta de conta",
        description = "Retorna uma conta do usuário."
    )
    AccountResponse getById(@PathVariable UUID id);

    @GetMapping
    @Operation(
        summary = "Endpoint de consulta de todas as contas",
        description = "Retorna uma lista de todas as contas do usuário."
    )
    List<AccountResponse> getByAll();

    @PostMapping
    @Operation(
        summary = "Endpoint de criação de conta",
        description = "Cria uma nova conta para o usuário."
    )
    AccountResponse create(@RequestBody AccountRequest request);
}
