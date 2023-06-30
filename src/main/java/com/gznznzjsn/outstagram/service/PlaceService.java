package com.gznznzjsn.outstagram.service;

import com.gznznzjsn.outstagram.model.node.Place;
import org.neo4j.driver.TransactionContext;

public interface PlaceService {

    Place retrieveOrCreateAndRetrieve(String name, TransactionContext tx);

}
