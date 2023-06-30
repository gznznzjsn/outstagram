package com.gznznzjsn.outstagram.persistence.repository;

import com.gznznzjsn.outstagram.model.relationship.Publication;
import org.neo4j.driver.TransactionContext;

public interface PublicationRepository {

    void create(Publication publication, TransactionContext tx);

}
