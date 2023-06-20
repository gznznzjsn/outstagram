package com.gznznzjsn.outstagram.persistence.repository;

import com.gznznzjsn.outstagram.model.relationship.Subscription;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper
public interface SubscriptionRepository {

    void create(Subscription subscription);

    void delete(Subscription subscription);

    List<Subscription> readSubscriptions(UUID accountId);

}
