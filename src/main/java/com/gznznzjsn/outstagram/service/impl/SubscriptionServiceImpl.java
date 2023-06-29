package com.gznznzjsn.outstagram.service.impl;

import com.gznznzjsn.outstagram.model.exception.IllegalActionException;
import com.gznznzjsn.outstagram.model.exception.InternalLogicException;
import com.gznznzjsn.outstagram.model.node.Account;
import com.gznznzjsn.outstagram.model.relationship.Subscription;
import com.gznznzjsn.outstagram.persistence.repository.SubscriptionRepository;
import com.gznznzjsn.outstagram.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repository;

    @Override
    @Transactional
    public void create(final UUID sourceId, final UUID targetId) {
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
        Long amount = repository.create(subscription);
        if (amount != 1) {
            throw new InternalLogicException(
                    "This method must create 1 subscription at once,"
                    + " but %d were provided!"
                            .formatted(amount)
            );
        }
    }

    @Override
    @Transactional
    public void delete(final UUID sourceId, final UUID targetId) {
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
        Long amount = repository.delete(subscription);
        if (amount != 1) {
            throw new InternalLogicException(
                    "This method must delete 1 subscription at once,"
                    + " but %d were provided!"
                            .formatted(amount)
            );
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subscription> retrieveSubscriptions(final UUID accountId) {
        return repository.readSubscriptions(accountId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subscription> retrieveSubscribers(final UUID accountId) {
        return repository.readSubscribers(accountId);
    }

}
