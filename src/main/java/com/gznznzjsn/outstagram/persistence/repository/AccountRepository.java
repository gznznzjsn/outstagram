package com.gznznzjsn.outstagram.persistence.repository;

import com.gznznzjsn.outstagram.model.node.Account;
import org.neo4j.driver.TransactionContext;

public interface AccountRepository {

    /**
     * Create new account.
     *
     * @param account account to be created
     */
    void create(Account account, TransactionContext tx);

}
