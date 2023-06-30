package com.gznznzjsn.outstagram.persistence.repository;

import com.gznznzjsn.outstagram.model.node.Tag;
import org.neo4j.driver.TransactionContext;

import java.util.Optional;

public interface TagRepository {

    Optional<Tag> findByName(String name, TransactionContext tx);

    void create(Tag tag, TransactionContext tx);

}
