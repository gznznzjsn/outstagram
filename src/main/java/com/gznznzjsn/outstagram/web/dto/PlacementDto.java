package com.gznznzjsn.outstagram.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record PlacementDto(

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        UUID id,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        PostDto source,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        PlaceDto target
) {
}
