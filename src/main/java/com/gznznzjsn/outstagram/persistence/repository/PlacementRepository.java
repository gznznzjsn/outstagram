package com.gznznzjsn.outstagram.persistence.repository;

import com.gznznzjsn.outstagram.model.relationship.Placement;
import org.neo4j.driver.TransactionContext;

public interface PlacementRepository {

    void create(Placement placement, TransactionContext tx);

}
