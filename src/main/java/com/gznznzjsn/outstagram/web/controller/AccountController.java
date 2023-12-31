package com.gznznzjsn.outstagram.web.controller;

import com.gznznzjsn.outstagram.model.node.Account;
import com.gznznzjsn.outstagram.service.AccountService;
import com.gznznzjsn.outstagram.web.dto.AccountDto;
import com.gznznzjsn.outstagram.web.dto.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;
    private final AccountMapper mapper;

    /**
     * Create new account.
     *
     * @param dto DTO of account to be created
     */
    @MutationMapping(name = "createAccount")
    public void create(final @Arguments AccountDto dto) {
        Account account = mapper.toModel(dto);
        service.create(account);
    }

}
