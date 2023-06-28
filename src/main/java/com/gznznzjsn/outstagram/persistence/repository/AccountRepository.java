package com.gznznzjsn.outstagram.persistence.repository;

import com.gznznzjsn.outstagram.model.node.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountRepository {

    /**
     * Create new account.
     *
     * @param account account to be created
     */
    void create(Account account);

}
