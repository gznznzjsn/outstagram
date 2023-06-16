package com.gznznzjsn.outstagram.web.controller;

import com.gznznzjsn.outstagram.model.node.Account;
import com.gznznzjsn.outstagram.service.AccountService;
import com.gznznzjsn.outstagram.web.dto.AccountDto;
import com.gznznzjsn.outstagram.web.dto.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/outstagram-api/v1/accounts")
public class AccountController {

    private final AccountService service;
    private final AccountMapper mapper;

    @PostMapping
    public void create(@RequestBody AccountDto dto) {
        Account account = mapper.toModel(dto);
        System.out.println(account);
        service.create(account);
    }

}
