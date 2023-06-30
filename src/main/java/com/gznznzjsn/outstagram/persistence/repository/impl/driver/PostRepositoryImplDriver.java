package com.gznznzjsn.outstagram.persistence.repository.impl.driver;

import com.gznznzjsn.outstagram.model.node.Post;
import com.gznznzjsn.outstagram.persistence.repository.PostRepository;
import org.neo4j.driver.TransactionContext;
import org.neo4j.driver.Values;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepositoryImplDriver implements PostRepository {

    @Override
    public void create(Post post, TransactionContext tx) {
        //language=Cypher
        String query = """
                CREATE (:POST {
                    id: $id,
                    photos: $photos,
                    description: $description
                });
                """;
        tx.run(
                query,
                Values.parameters(
                        "id", post.getId().toString(),
                        "photos", post.getPhotos(),
                        "description", post.getDescription()
                )
        );
    }

}
