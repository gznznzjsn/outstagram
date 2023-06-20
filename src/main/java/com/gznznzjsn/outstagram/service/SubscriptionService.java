package com.gznznzjsn.outstagram.service;

import java.util.UUID;

public interface SubscriptionService {

    void subscribe(UUID sourceId, UUID targetId);

}
