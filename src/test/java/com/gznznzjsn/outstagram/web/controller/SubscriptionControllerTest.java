package com.gznznzjsn.outstagram.web.controller;

import com.gznznzjsn.outstagram.model.node.Account;
import com.gznznzjsn.outstagram.model.relationship.Subscription;
import com.gznznzjsn.outstagram.service.SubscriptionService;
import com.gznznzjsn.outstagram.web.dto.mapper.MapperConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@GraphQlTest(SubscriptionController.class)
@Import(MapperConfig.class)
class SubscriptionControllerTest {

    @Autowired
    private GraphQlTester tester;

    @MockBean
    private SubscriptionService service;

    @Test
    void subscribe() {
        UUID sourceId = UUID.fromString(
                "11111111-1111-1111-1111-111111111111"
        );
        UUID targetId = UUID.fromString(
                "22222222-2222-2222-2222-222222222222"
        );
        tester.documentName("request/subscribe-to-account-request")
                .variable("sourceId", sourceId)
                .variable("targetId", targetId)
                .execute();
        verify(service).create(sourceId, targetId);
    }

    @Test
    void unsubscribe() {
        UUID sourceId = UUID.fromString(
                "11111111-1111-1111-1111-111111111111"
        );
        UUID targetId = UUID.fromString(
                "22222222-2222-2222-2222-222222222222"
        );
        tester.documentName("request/unsubscribe-from-account-request")
                .variable("sourceId", sourceId)
                .variable("targetId", targetId)
                .execute();
        verify(service).delete(sourceId, targetId);
    }

    @Test
    void getSubscribers() throws IOException, URISyntaxException {
        String method = "get-subscribers";
        String requestFilename = "request/" + method + "-request";
        String responseFilepath = "classpath:graphql-test/response/"
                                  + method + "-response.json";
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
        when(service.retrieveSubscribers(accountId))
                .thenReturn(List.of(subscription1, subscription2));
        tester.documentName(requestFilename)
                .variable("accountId", accountId)
                .execute()
                .path("$")
                .matchesJson(Files.readString(Path.of(
                        ResourceUtils.getURL(responseFilepath).toURI()
                )));
    }

    @Test
    void getSubscriptions() throws IOException, URISyntaxException {
        String method = "get-subscriptions";
        String requestFilename = "request/" + method + "-request";
        String responseFilepath = "classpath:graphql-test/response/"
                                  + method + "-response.json";
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
        when(service.retrieveSubscriptions(accountId))
                .thenReturn(List.of(subscription1, subscription2));
        tester.documentName(requestFilename)
                .variable("accountId", accountId)
                .execute()
                .path("$")
                .matchesJson(Files.readString(Path.of(
                        ResourceUtils.getURL(responseFilepath).toURI()
                )));
    }

}
