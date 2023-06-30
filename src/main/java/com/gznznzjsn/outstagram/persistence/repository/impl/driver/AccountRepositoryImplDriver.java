package com.gznznzjsn.outstagram.persistence.repository.impl.driver;

import com.gznznzjsn.outstagram.model.node.Account;
import com.gznznzjsn.outstagram.persistence.repository.AccountRepository;
import org.neo4j.driver.TransactionContext;
import org.neo4j.driver.Values;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImplDriver implements AccountRepository {

    @Override
    public void create(
            final Account account, final TransactionContext tx
    ) {
        //language=Cypher
        String query = """
                CREATE (
                    a:ACCOUNT {
                                id: $id,
                                name: $name,
                                description: $description,
                                is_private: $isPrivate,
                                created_at: $createdAt
                    }
                )
                """;
        tx.run(
                query,
                Values.parameters(
                        "id", account.getId().toString(),
                        "name", account.getName(),
                        "description", account.getDescription(),
                        "isPrivate", account.getIsPrivate(),
                        "createdAt", account.getCreatedAt()
                )
        );
    }

}


