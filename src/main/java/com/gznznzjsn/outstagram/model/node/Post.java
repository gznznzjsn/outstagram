package com.gznznzjsn.outstagram.model.node;

import com.gznznzjsn.outstagram.model.relationship.Placement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private UUID id;
    private Placement placement;
    private List<String> photos;
    private String description;

}
