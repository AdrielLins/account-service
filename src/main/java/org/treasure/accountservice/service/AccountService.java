package org.treasure.accountservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.treasure.accountservice.domain.AccountDomain;
import org.treasure.accountservice.exception.NotFoundException;
import org.treasure.accountservice.factory.AccountFactory;
import org.treasure.accountservice.mapper.AccountDomainMapper;
import org.treasure.accountservice.mapper.AccountModelMapper;
import org.treasure.accountservice.repository.AccountRepository;
import org.treasure.accountservice.web.controller.dto.request.AccountRequest;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountFactory factory;
    private final AccountRepository accountRepository;
    private final AccountDomainMapper domainMapper;
    private final AccountModelMapper modelMapper;

    public AccountDomain getAccountById(final UUID accountId) {
        log.info("Getting account with id: {}", accountId);
        return accountRepository.findById(accountId)
            .map(domainMapper::map)
            .orElseThrow(() -> new NotFoundException("Account not found with id: " + accountId));
    }

    public AccountDomain create(final AccountRequest request) {
        log.info("Creating account with name: {}", request.getName());
        var domain = factory.createAccount(domainMapper.map(request));

        var savedEntity = accountRepository.save(modelMapper.map(domain));
        return domainMapper.map(savedEntity);
    }

    public List<AccountDomain> getAll() {
        log.info("Getting all accounts");
        return accountRepository.findAll()
            .stream()
            .map(domainMapper::map)
            .toList();
    }
}
