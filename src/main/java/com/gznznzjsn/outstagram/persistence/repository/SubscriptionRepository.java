package com.gznznzjsn.outstagram.persistence.repository;

import com.gznznzjsn.outstagram.model.relationship.Subscription;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubscriptionRepository {

    void create(Subscription subscription);

    void delete(Subscription subscription);

}
