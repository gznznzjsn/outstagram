package com.gznznzjsn.outstagram.web.dto.mapper;

import com.gznznzjsn.outstagram.model.relationship.Subscription;
import com.gznznzjsn.outstagram.web.dto.SubscriptionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AccountMapper.class})
public interface SubscriptionMapper {

    /**
     * Map {@link SubscriptionDto} to {@link Subscription}.
     *
     * @param dto dto of subscription
     * @return model of subscription
     */
    Subscription toModel(SubscriptionDto dto);

    /**
     * Map {@link Subscription} to {@link SubscriptionDto}.
     *
     * @param model model of subscription
     * @return dto of subscription
     */
    SubscriptionDto toDto(Subscription model);

}
