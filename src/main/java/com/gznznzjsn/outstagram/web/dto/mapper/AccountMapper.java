package com.gznznzjsn.outstagram.web.dto.mapper;

import com.gznznzjsn.outstagram.model.node.Account;
import com.gznznzjsn.outstagram.web.dto.AccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toModel(AccountDto dto);

    AccountDto toDto(Account entity);

}
