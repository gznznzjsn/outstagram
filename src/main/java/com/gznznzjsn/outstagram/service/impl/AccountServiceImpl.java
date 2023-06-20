package com.gznznzjsn.outstagram.service.impl;

import com.gznznzjsn.outstagram.model.node.Account;
import com.gznznzjsn.outstagram.persistence.repository.AccountRepository;
import com.gznznzjsn.outstagram.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Override
    public void create(Account account) {
        account.setId(UUID.randomUUID());
        account.setCreatedAt(LocalDateTime.now());
        repository.create(account);
    }

}
