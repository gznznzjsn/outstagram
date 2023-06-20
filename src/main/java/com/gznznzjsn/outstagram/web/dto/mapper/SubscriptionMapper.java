package com.gznznzjsn.outstagram.web.dto.mapper;

import com.gznznzjsn.outstagram.model.relationship.Subscription;
import com.gznznzjsn.outstagram.web.dto.SubscriptionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AccountMapper.class})
public interface SubscriptionMapper {

    Subscription toModel(SubscriptionDto dto);

    SubscriptionDto toDto(Subscription entity);

}
