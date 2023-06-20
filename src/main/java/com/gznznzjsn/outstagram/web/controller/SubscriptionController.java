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

    @MutationMapping(name = "subscribeToAccount")
    public void subscribe(
            @Argument UUID sourceId,
            @Argument UUID targetId
    ) {
        service.subscribe(sourceId, targetId);
    }

    @MutationMapping(name = "unsubscribeFromAccount")
    public void unsubscribe(
            @Argument UUID sourceId,
            @Argument UUID targetId
    ) {
        service.unsubscribe(sourceId, targetId);
    }

    @QueryMapping(name = "getSubscriptions")
    public List<SubscriptionDto> getSubscriptions(@Argument UUID accountId) {
        return service.retrieveSubscriptions(accountId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

}
