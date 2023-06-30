package com.gznznzjsn.outstagram.persistence.repository;

import com.gznznzjsn.outstagram.model.relationship.Tagging;
import org.neo4j.driver.TransactionContext;

public interface TaggingRepository {

    void create(Tagging tagging, TransactionContext tx);

}
