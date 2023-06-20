package com.gznznzjsn.outstagram.service.impl;

import com.gznznzjsn.outstagram.model.exception.IllegalActionException;
import com.gznznzjsn.outstagram.model.node.Account;
import com.gznznzjsn.outstagram.model.relationship.Subscription;
import com.gznznzjsn.outstagram.repository.SubscriptionRepository;
import com.gznznzjsn.outstagram.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repository;

    @Override
    public void subscribe(UUID sourceId, UUID targetId) {
        if (sourceId.equals(targetId)) {
            throw new IllegalActionException("You cannot subscribe to yourself");
        }
        var subscription = Subscription.builder()
                .id(UUID.randomUUID())
                .source(
                        Account.builder()
                                .id(sourceId)
                                .build()
                )
                .target(
                        Account.builder()
                                .id(targetId)
                                .build()
                )
                .createdAt(LocalDateTime.now())
                .build();
        repository.create(subscription);
    }

}
