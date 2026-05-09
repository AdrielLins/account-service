package org.treasure.accountservice.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.treasure.accountservice.mapper.AccountResponseMapper;
import org.treasure.accountservice.service.AccountService;
import org.treasure.accountservice.web.controller.dto.request.AccountRequest;
import org.treasure.accountservice.web.controller.dto.response.AccountResponse;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/accounts")
public class AccountsController implements AccountsApi {

    private final AccountService service;
    private final AccountResponseMapper responseMapper;

    @Override
    @GetMapping("{id}")
    public AccountResponse getById(@PathVariable final UUID id) {
        return responseMapper.map(
            service.getAccountById(id)
        );
    }

    @GetMapping
    @Override
    public List<AccountResponse> getByAll() {
        return responseMapper.mapAll(
            service.getAll()
        );
    }

    @Override
    @PostMapping
    public AccountResponse create(@Valid @RequestBody final AccountRequest request) {
        return responseMapper.map(
            service.create(request)
        );
    }
}
