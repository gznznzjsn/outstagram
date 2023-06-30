package com.gznznzjsn.outstagram.persistence.repository.impl.driver;

import com.gznznzjsn.outstagram.model.exception.InternalLogicException;
import com.gznznzjsn.outstagram.model.relationship.Tagging;
import com.gznznzjsn.outstagram.persistence.repository.TaggingRepository;
import org.neo4j.driver.Result;
import org.neo4j.driver.TransactionContext;
import org.neo4j.driver.Values;
import org.springframework.stereotype.Repository;

@Repository
public class TaggingRepositoryImplDriver implements TaggingRepository {

    @Override
    public void create(
            final Tagging tagging, final TransactionContext tx
    ) {
        //language=Cypher
        String query = """
                MATCH (a:POST), (b:TAG)
                WHERE a.id = $source_id AND b.id = $target_id
                CREATE (a)-[:TAGGED_WITH {id: $id}]->(b);
                """;
        Result result = tx.run(
                query,
                Values.parameters(
                        "source_id", tagging.getSource().getId().toString(),
                        "target_id", tagging.getTarget().getId().toString(),
                        "id", tagging.getId().toString()
                )
        );
        int amount = result.consume().counters().relationshipsCreated();
        if (amount != 1) {
            throw new InternalLogicException(
                    "This method must create 1 tagging at once,"
                    + " but %d were provided!"
                            .formatted(amount)
            );
        }
    }

}
