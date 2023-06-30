package com.gznznzjsn.outstagram.persistence.repository.impl.driver;

import com.gznznzjsn.outstagram.model.exception.InternalLogicException;
import com.gznznzjsn.outstagram.model.relationship.Placement;
import com.gznznzjsn.outstagram.persistence.repository.PlacementRepository;
import org.neo4j.driver.Result;
import org.neo4j.driver.TransactionContext;
import org.neo4j.driver.Values;
import org.springframework.stereotype.Repository;

@Repository
public class PlacementRepositoryImplDriver implements PlacementRepository {

    @Override
    public void create(final Placement placement, final TransactionContext tx) {
        //language=Cypher
        String query = """
                MATCH (a:POST), (b:PLACE)
                WHERE a.id = $source_id AND b.id = $target_id
                CREATE (a)-[:PLACED {id: $id}]->(b);
                """;
        Result result = tx.run(
                query,
                Values.parameters(
                        "source_id", placement.getSource().getId().toString(),
                        "target_id", placement.getTarget().getId().toString(),
                        "id", placement.getId().toString()
                )
        );
        int amount = result.consume().counters().relationshipsCreated();
        if (amount != 1) {
            throw new InternalLogicException(
                    "This method must create 1 placement at once,"
                    + " but %d were provided!"
                            .formatted(amount)
            );
        }
    }

}
