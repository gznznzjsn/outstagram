package com.gznznzjsn.outstagram.service.impl;

import com.gznznzjsn.outstagram.model.exception.IllegalActionException;
import com.gznznzjsn.outstagram.model.node.Account;
import com.gznznzjsn.outstagram.model.relationship.Subscription;
import com.gznznzjsn.outstagram.persistence.repository.Neo4jCustomDriver;
import com.gznznzjsn.outstagram.persistence.repository.SubscriptionRepository;
import com.gznznzjsn.outstagram.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final Neo4jCustomDriver driver;
    private final SubscriptionRepository repository;

    @Override
    public void create(final UUID sourceId, final UUID targetId) {
        try (Session session = driver.getSession()) {
            session.executeWriteWithoutResult(tx -> {
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
                        repository.create(subscription, tx);
                    }
            );
        }
    }

    @Override
    public void delete(final UUID sourceId, final UUID targetId) {
        try (Session session = driver.getSession()) {
            session.executeWriteWithoutResult(tx -> {
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
                        repository.delete(subscription, tx);
                    }
            );
        }
    }

    @Override
    public List<Subscription> retrieveSubscriptions(final UUID accountId) {
        try (Session session = driver.getSession()) {
            return session.executeRead(
                    tx -> repository.readSubscriptions(accountId, tx)
            );
        }
    }

    @Override
    public List<Subscription> retrieveSubscribers(final UUID accountId) {
        try (Session session = driver.getSession()) {
            return session.executeRead(
                    tx -> repository.readSubscribers(accountId, tx)
            );
        }
    }

}
