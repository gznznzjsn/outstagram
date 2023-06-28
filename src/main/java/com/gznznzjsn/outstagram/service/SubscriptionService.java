package com.gznznzjsn.outstagram.service;

import com.gznznzjsn.outstagram.model.relationship.Subscription;

import java.util.List;
import java.util.UUID;

public interface SubscriptionService {

    /**
     * Subscribe source account to target account.
     *
     * @param sourceId id of source account
     * @param targetId id of target account
     */
    void subscribe(UUID sourceId, UUID targetId);

    /**
     * Unsubscribe source account from target account.
     *
     * @param sourceId id of source account
     * @param targetId id of target account
     */
    void unsubscribe(UUID sourceId, UUID targetId);

    /**
     * Retrieve all subscriptions of account.
     *
     * @param accountId id of account
     * @return all subscriptions of account
     */
    List<Subscription> retrieveSubscriptions(UUID accountId);

    /**
     * Retrieve all subscribers of account.
     *
     * @param accountId id of account
     * @return all subscribers of account
     */
    List<Subscription> retrieveSubscribers(UUID accountId);

}
