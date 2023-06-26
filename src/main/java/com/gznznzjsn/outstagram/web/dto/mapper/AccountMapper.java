package com.gznznzjsn.outstagram.web.dto.mapper;

import com.gznznzjsn.outstagram.model.node.Account;
import com.gznznzjsn.outstagram.web.dto.AccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    /**
     * Map {@link AccountDto} to {@link Account}.
     *
     * @param dto dto of account
     * @return model of account
     */
    Account toModel(AccountDto dto);

    /**
     * Map {@link Account} to {@link AccountDto}.
     *
     * @param model model of account
     * @return dto of account
     */
    AccountDto toDto(Account model);

}
