package com.gznznzjsn.outstagram.web.controller;

import com.gznznzjsn.outstagram.service.SubscriptionService;
import com.gznznzjsn.outstagram.web.dto.SubscriptionDto;
import com.gznznzjsn.outstagram.web.dto.mapper.SubscriptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService service;
    private final SubscriptionMapper mapper;

    /**
     * Subscribe source account to target account.
     *
     * @param sourceId id of source account
     * @param targetId id of target account
     */
    @MutationMapping(name = "subscribeToAccount")
    public void subscribe(
            final @Argument UUID sourceId,
            final @Argument UUID targetId
    ) {
        service.create(sourceId, targetId);
    }

    /**
     * Subscribe source account to target account.
     *
     * @param sourceId id of source account
     * @param targetId id of target account
     */
    @MutationMapping(name = "unsubscribeFromAccount")
    public void unsubscribe(
            final @Argument UUID sourceId,
            final @Argument UUID targetId
    ) {
        service.delete(sourceId, targetId);
    }

    /**
     * Get all subscriptions of account.
     *
     * @param accountId id of account
     * @return DTOs of subscriptions
     */
    @QueryMapping(name = "getSubscriptions")
    public List<SubscriptionDto> getSubscriptions(
            final @Argument UUID accountId
    ) {
        return service.retrieveSubscriptions(accountId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    /**
     * Get all subscribers of account.
     *
     * @param accountId id of account
     * @return DTOs of subscriptions
     */
    @QueryMapping(name = "getSubscribers")
    public List<SubscriptionDto> getSubscribers(
            final @Argument UUID accountId
    ) {
        return service.retrieveSubscribers(accountId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

}
