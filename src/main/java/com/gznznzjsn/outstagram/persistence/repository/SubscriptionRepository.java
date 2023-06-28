package com.gznznzjsn.outstagram.persistence.repository;

import com.gznznzjsn.outstagram.model.relationship.Subscription;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper
public interface SubscriptionRepository {

    /**
     * Create subscription relationship.
     *
     * @param subscription relationship to be created
     * @return number of created subscriptions
     */
    Long create(Subscription subscription);

    /**
     * Delete subscription relationship.
     *
     * @param subscription relationship to be deleted
     * @return number of deleted subscriptions
     */
    Long delete(Subscription subscription);

    /**
     * Read all subscriptions of account.
     *
     * @param accountId id of account
     * @return all subscriptions of account
     */
    List<Subscription> readSubscriptions(UUID accountId);

    /**
     * Read all subscribers of account.
     *
     * @param accountId id of account
     * @return all subscribers of account
     */
    List<Subscription> readSubscribers(UUID accountId);

}
