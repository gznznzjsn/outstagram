package com.gznznzjsn.outstagram.service.impl;

import com.gznznzjsn.outstagram.model.exception.IllegalActionException;
import com.gznznzjsn.outstagram.model.node.Account;
import com.gznznzjsn.outstagram.model.relationship.Subscription;
import com.gznznzjsn.outstagram.persistence.repository.SubscriptionRepository;
import com.gznznzjsn.outstagram.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repository;

    @Override
    public void subscribe(final UUID sourceId, final UUID targetId) {
        if (sourceId.equals(targetId)) {
            throw new IllegalActionException("You can't subscribe to yourself");
        }
        var source = Account.builder()
                .id(sourceId)
                .build();
        var target = Account.builder()
                .id(targetId)
                .build();
        var subscription = Subscription.builder()
                .id(UUID.randomUUID())
                .source(source)
                .target(target)
                .createdAt(LocalDateTime.now())
                .build();
        repository.create(subscription);
    }

    @Override
    public void unsubscribe(final UUID sourceId, final UUID targetId) {
        var source = Account.builder()
                .id(sourceId)
                .build();
        var target = Account.builder()
                .id(targetId)
                .build();
        var subscription = Subscription.builder()
                .id(UUID.randomUUID())
                .source(source)
                .target(target)
                .createdAt(LocalDateTime.now())
                .build();
        repository.delete(subscription);
    }

    @Override
    public List<Subscription> retrieveSubscriptions(final UUID accountId) {
        return repository.readSubscriptions(accountId);
    }

    @Override
    public List<Subscription> retrieveSubscribers(final UUID accountId) {
        return repository.readSubscribers(accountId);
    }

}
