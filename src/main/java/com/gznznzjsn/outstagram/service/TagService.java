package com.gznznzjsn.outstagram.service;

import com.gznznzjsn.outstagram.model.node.Tag;
import org.neo4j.driver.TransactionContext;

public interface TagService {

    Tag retrieveOrCreateAndRetrieve(String name, TransactionContext tx);

}
