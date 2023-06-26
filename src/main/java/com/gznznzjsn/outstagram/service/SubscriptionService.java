package com.gznznzjsn.outstagram.service;

import com.gznznzjsn.outstagram.model.relationship.Subscription;

import java.util.List;
import java.util.UUID;

public interface SubscriptionService {

    void subscribe(UUID sourceId, UUID targetId);

    void unsubscribe(UUID sourceId, UUID targetId);

    List<Subscription> retrieveSubscriptions(UUID accountId);

    List<Subscription> retrieveSubscribers(UUID accountId);

}
