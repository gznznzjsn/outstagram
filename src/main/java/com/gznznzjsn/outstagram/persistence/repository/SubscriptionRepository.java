package com.gznznzjsn.outstagram.persistence.repository;

import com.gznznzjsn.outstagram.model.relationship.Subscription;
import org.neo4j.driver.TransactionContext;

import java.util.List;
import java.util.UUID;

public interface SubscriptionRepository {

    /**
     * Create subscription relationship.
     *
     * @param subscription relationship to be created
     */
    void create(Subscription subscription, TransactionContext tx);

    /**
     * Delete subscription relationship.
     *
     * @param subscription relationship to be deleted
     */
    void delete(Subscription subscription, TransactionContext tx);

    /**
     * Read all subscriptions of account.
     *
     * @param accountId id of account
     * @return all subscriptions of account
     */
    List<Subscription> readSubscriptions(UUID accountId, TransactionContext tx);

    /**
     * Read all subscribers of account.
     *
     * @param accountId id of account
     * @return all subscribers of account
     */
    List<Subscription> readSubscribers(UUID accountId, TransactionContext tx);

}
