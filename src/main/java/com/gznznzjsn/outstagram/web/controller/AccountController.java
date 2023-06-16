package com.gznznzjsn.outstagram.web.controller;

import com.gznznzjsn.outstagram.model.node.Account;
import com.gznznzjsn.outstagram.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository repository;

    @PostMapping
    public void create(@RequestBody Account account) {
        repository.create(account);
    }

}
