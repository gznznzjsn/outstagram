package com.gznznzjsn.outstagram.service;

import org.neo4j.driver.TransactionContext;

import java.util.UUID;

public interface PlacementService {

    void create(UUID postId, String placeName, TransactionContext tx);

}
