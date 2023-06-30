package com.gznznzjsn.outstagram.service;

import org.neo4j.driver.TransactionContext;

import java.util.UUID;

public interface TaggingService {

    void create(UUID postId, String tagName, TransactionContext tx);

}
