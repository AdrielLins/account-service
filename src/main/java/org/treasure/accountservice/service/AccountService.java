package org.treasure.accountservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.treasure.accountservice.domain.AccountDomain;
import org.treasure.accountservice.event.AccountEventProducer;
import org.treasure.accountservice.event.dto.AccountCreatedEvent;
import org.treasure.accountservice.exception.NotFoundException;
import org.treasure.accountservice.factory.AccountFactory;
import org.treasure.accountservice.mapper.AccountDomainMapper;
import org.treasure.accountservice.mapper.AccountModelMapper;
import org.treasure.accountservice.repository.AccountRepository;
import org.treasure.accountservice.web.controller.dto.request.AccountRequest;
import org.treasure.accountservice.web.controller.dto.request.UpdateAccountStatusRequest;

import java.time.Instant;
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
    private final AccountEventProducer accountEventProducer;

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
        var savedDomain = domainMapper.map(savedEntity);

        accountEventProducer.publishAccountCreated(
            new AccountCreatedEvent(
                UUID.randomUUID(),
                savedDomain.getId(),
                savedDomain.getStatus().name(),
                Instant.now(),
                1
            )
        );

        return savedDomain;
    }

    public List<AccountDomain> getAll() {
        log.info("Getting all accounts");
        return accountRepository.findAll()
            .stream()
            .map(domainMapper::map)
            .toList();
    }

    public AccountDomain update(final UUID accountId,
                                final AccountRequest request) {
        log.info("Updating account with id: {}", accountId);
        var account = getAccountById(accountId);
        account.setName(request.getName());
        account.setType(request.getType());

        var updatedEntity = accountRepository.save(modelMapper.map(account));
        return domainMapper.map(updatedEntity);
    }

    public void updateStatus(final UUID id,
                             final UpdateAccountStatusRequest request) {
        log.info("Updating account with id: {} to status: {}", id, request.getStatus());
        var account = getAccountById(id);
        account.setStatus(request.getStatus());

        accountRepository.save(modelMapper.map(account));
    }
}
