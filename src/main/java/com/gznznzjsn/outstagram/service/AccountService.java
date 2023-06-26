package com.gznznzjsn.outstagram.service;


import com.gznznzjsn.outstagram.model.node.Account;

public interface AccountService {

    /**
     * Create new account.
     *
     * @param account account to be created
     */
    void create(Account account);

}
