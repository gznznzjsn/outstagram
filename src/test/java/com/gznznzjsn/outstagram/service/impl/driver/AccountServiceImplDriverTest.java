package com.gznznzjsn.outstagram.service.impl.driver;

import com.gznznzjsn.outstagram.model.node.Account;
import com.gznznzjsn.outstagram.persistence.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplDriverTest {

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private AccountServiceImplDriver service;

    @Test
    void createAccountWithUnsetIdAndCreatedAt() {
        var account = Account.builder()
                .name("NAME")
                .description("DESCRIPTION")
                .isPrivate(true)
                .build();
        service.create(account);
        verify(repository).create(assertArg(a -> {
            assertNotNull(a.getId());
            assertNotNull(a.getCreatedAt());
            assertEquals(account.getName(), a.getName());
            assertEquals(account.getDescription(), a.getDescription());
            assertEquals(account.getIsPrivate(), a.getIsPrivate());
        }));
    }

    @Test
    void createAccountWithSetIdAndCreatedAt() {
        UUID providedId = UUID.fromString(
                "11111111-1111-1111-1111-111111111111"
        );
        var providedCreatedAt = LocalDateTime.of(1, 1, 1, 1, 1);
        var account = Account.builder()
                .id(providedId)
                .createdAt(providedCreatedAt)
                .name("NAME")
                .description("DESCRIPTION")
                .isPrivate(true)
                .build();
        service.create(account);
        verify(repository).create(assertArg(a -> {
            assertNotEquals(providedId, a.getId());
            assertNotEquals(providedCreatedAt, a.getCreatedAt());
            assertEquals(account.getName(), a.getName());
            assertEquals(account.getDescription(), a.getDescription());
            assertEquals(account.getIsPrivate(), a.getIsPrivate());
        }));
    }

}
