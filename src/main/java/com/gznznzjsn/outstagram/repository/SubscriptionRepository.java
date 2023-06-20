package com.gznznzjsn.outstagram.repository;

import com.gznznzjsn.outstagram.model.relationship.Subscription;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubscriptionRepository {

    void create(Subscription subscription);

}
