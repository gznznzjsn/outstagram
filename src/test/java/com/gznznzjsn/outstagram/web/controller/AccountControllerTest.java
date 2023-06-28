package com.gznznzjsn.outstagram.web.controller;

import com.gznznzjsn.outstagram.service.AccountService;
import com.gznznzjsn.outstagram.web.dto.mapper.MapperConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.verify;

@GraphQlTest(AccountController.class)
@Import(MapperConfig.class)
class AccountControllerTest {

    @Autowired
    private GraphQlTester tester;

    @MockBean
    private AccountService service;

    @Test
    void create() {
        String name = "NAME";
        String description = "DESCRIPTION";
        boolean isPrivate = true;
        tester.documentName("request/create-account-request")
                .variable("name", name)
                .variable("description", description)
                .variable("isPrivate", isPrivate)
                .execute();
        verify(service).create(assertArg(a -> {
            assertNull(a.getId());
            assertNull(a.getCreatedAt());
            assertEquals(name, a.getName());
            assertEquals(description, a.getDescription());
            assertEquals(isPrivate, a.getIsPrivate());
        }));
    }

}
