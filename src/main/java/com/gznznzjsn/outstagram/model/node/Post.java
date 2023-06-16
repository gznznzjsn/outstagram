package com.gznznzjsn.outstagram.model.node;

import com.gznznzjsn.outstagram.model.relationship.Placed;
import com.gznznzjsn.outstagram.model.relationship.Tagged;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private UUID id;
    private Placed place;
    private List<String> photos;
    private String description;
    private Set<Tagged> tags;

}
