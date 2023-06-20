package com.gznznzjsn.outstagram.web.controller;

import com.gznznzjsn.outstagram.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService service;

    @MutationMapping(name = "subscribeToAccount")
    public void subscribe(
            @Argument UUID sourceId,
            @Argument UUID targetId
    ) {
        service.subscribe(sourceId, targetId);
    }

}
