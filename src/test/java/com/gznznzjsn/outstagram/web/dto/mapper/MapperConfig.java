package com.gznznzjsn.outstagram.web.dto.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    /**
     * Account mapper for tests.
     *
     * @return created mapper
     */
    @Bean
    public AccountMapper testMapper() {
        return Mappers.getMapper(AccountMapper.class);
    }

}
