package com.gznznzjsn.outstagram.service.impl;

import com.gznznzjsn.outstagram.model.node.Account;
import com.gznznzjsn.outstagram.repository.AccountRepository;
import com.gznznzjsn.outstagram.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Override
    public void create(Account account) {
        account.setCreatedAt(LocalDateTime.now());
        repository.create(account);
    }

}