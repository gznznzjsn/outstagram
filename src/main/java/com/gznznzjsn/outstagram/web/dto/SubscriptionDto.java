package com.gznznzjsn.outstagram.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public record SubscriptionDto(

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        UUID id,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        AccountDto source,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        AccountDto target,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        LocalDateTime createdAt

) {
}
