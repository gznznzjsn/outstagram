package com.gznznzjsn.outstagram.repository;

import com.gznznzjsn.outstagram.model.node.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountRepository {

    void create(Account account);

}
