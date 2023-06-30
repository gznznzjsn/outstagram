package com.gznznzjsn.outstagram.service;

import org.neo4j.driver.TransactionContext;

import java.util.UUID;

public interface PublicationService {


    void create(UUID accountId, UUID postId, TransactionContext tx);

}
