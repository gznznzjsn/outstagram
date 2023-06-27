package com.gznznzjsn.outstagram.service.impl;

import com.gznznzjsn.outstagram.model.exception.IllegalActionException;
import com.gznznzjsn.outstagram.model.node.Account;
import com.gznznzjsn.outstagram.model.relationship.Subscription;
import com.gznznzjsn.outstagram.persistence.repository.SubscriptionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceImplTest {

    @Mock
    private SubscriptionRepository repository;

    @InjectMocks
    private SubscriptionServiceImpl service;

    @Test
    public void subscribeToYourself() {
        UUID sourceId = UUID.fromString(
                "11111111-1111-1111-1111-111111111111"
        );
        UUID targetId = UUID.fromString(
                "11111111-1111-1111-1111-111111111111"
        );
        assertThrows(
                IllegalActionException.class,
                () -> service.subscribe(sourceId, targetId)
        );
        verify(repository, never()).create(any(Subscription.class));
    }

    @Test
    public void subscribeToAnotherAccount() {
        UUID sourceId = UUID.fromString(
                "11111111-1111-1111-1111-111111111111"
        );
        UUID targetId = UUID.fromString(
                "22222222-2222-2222-2222-222222222222"
        );
        service.subscribe(sourceId, targetId);
        verify(repository).create(assertArg(s -> {
            assertNotNull(s.getId());
            assertEquals(sourceId, s.getSource().getId());
            assertEquals(targetId, s.getTarget().getId());
            assertNotNull(s.getCreatedAt());
        }));
    }

    @Test
    public void unsubscribe() {
        UUID sourceId = UUID.fromString(
                "11111111-1111-1111-1111-111111111111"
        );
        UUID targetId = UUID.fromString(
                "22222222-2222-2222-2222-222222222222"
        );
        service.unsubscribe(sourceId, targetId);
        verify(repository).delete(assertArg(s -> {
            assertNotNull(s.getId());
            assertEquals(sourceId, s.getSource().getId());
            assertEquals(targetId, s.getTarget().getId());
            assertNotNull(s.getCreatedAt());
        }));
    }

    @Test
    public void retrieveSubscriptions() {
        UUID accountId = UUID.fromString(
                "11111111-1111-1111-1111-111111111111"
        );
        var sourceAccount = Account.builder()
                .id(accountId)
                .name("SOURCE NAME")
                .description("SOURCE DESCRIPTION")
                .isPrivate(true)
                .createdAt(LocalDateTime.of(1, 1, 1, 1, 1))
                .build();
        var targetAccount1 = Account.builder()
                .id(UUID.fromString("22222222-2222-2222-2222-222222222222"))
                .name("TARGET 1 NAME")
                .description("TARGET 1 DESCRIPTION")
                .isPrivate(true)
                .createdAt(LocalDateTime.of(2, 2, 2, 2, 2))
                .build();
        var targetAccount2 = Account.builder()
                .id(UUID.fromString("33333333-3333-3333-3333-333333333333"))
                .name("TARGET 2 NAME")
                .description("TARGET 2 DESCRIPTION")
                .isPrivate(true)
                .createdAt(LocalDateTime.of(3, 3, 3, 3, 3))
                .build();
        var subscription1 = Subscription.builder()
                .id(UUID.fromString("44444444-4444-4444-4444-444444444444"))
                .source(sourceAccount)
                .target(targetAccount1)
                .createdAt(LocalDateTime.of(4, 4, 4, 4, 4))
                .build();
        var subscription2 = Subscription.builder()
                .id(UUID.fromString("55555555-5555-5555-5555-555555555555"))
                .source(sourceAccount)
                .target(targetAccount2)
                .createdAt(LocalDateTime.of(5, 5, 5, 5, 5))
                .build();
        when(repository.readSubscriptions(any(UUID.class)))
                .thenReturn(List.of(subscription1, subscription2));
        List<Subscription> result = service.retrieveSubscriptions(accountId);
        assertEquals(2, result.size());
        assertEquals(subscription1, result.get(0));
        assertEquals(subscription2, result.get(1));
    }

    @Test
    public void retrieveSubscribers() {
        UUID accountId = UUID.fromString(
                "11111111-1111-1111-1111-111111111111"
        );
        var targetAccount = Account.builder()
                .id(accountId)
                .name("TARGET NAME")
                .description("TARGET DESCRIPTION")
                .isPrivate(true)
                .createdAt(LocalDateTime.of(1, 1, 1, 1, 1))
                .build();
        var sourceAccount1 = Account.builder()
                .id(UUID.fromString("22222222-2222-2222-2222-222222222222"))
                .name("SOURCE 1 NAME")
                .description("SOURCE 1 DESCRIPTION")
                .isPrivate(true)
                .createdAt(LocalDateTime.of(2, 2, 2, 2, 2))
                .build();
        var sourceAccount2 = Account.builder()
                .id(UUID.fromString("33333333-3333-3333-3333-333333333333"))
                .name("SOURCE 2 NAME")
                .description("SOURCE 2 DESCRIPTION")
                .isPrivate(true)
                .createdAt(LocalDateTime.of(3, 3, 3, 3, 3))
                .build();
        var subscription1 = Subscription.builder()
                .id(UUID.fromString("44444444-4444-4444-4444-444444444444"))
                .source(sourceAccount1)
                .target(targetAccount)
                .createdAt(LocalDateTime.of(4, 4, 4, 4, 4))
                .build();
        var subscription2 = Subscription.builder()
                .id(UUID.fromString("55555555-5555-5555-5555-555555555555"))
                .source(sourceAccount2)
                .target(targetAccount)
                .createdAt(LocalDateTime.of(5, 5, 5, 5, 5))
                .build();
        when(repository.readSubscribers(any(UUID.class)))
                .thenReturn(List.of(subscription1, subscription2));
        List<Subscription> result = service.retrieveSubscribers(accountId);
        assertEquals(2, result.size());
        assertEquals(subscription1, result.get(0));
        assertEquals(subscription2, result.get(1));
    }

}
