package com.gznznzjsn.outstagram.persistence.repository;

import com.gznznzjsn.outstagram.model.node.Post;
import org.neo4j.driver.TransactionContext;

public interface PostRepository {

    void create(Post post, TransactionContext tx);

}
