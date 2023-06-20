package com.gznznzjsn.outstagram.model.relationship;

import com.gznznzjsn.outstagram.model.node.Place;
import com.gznznzjsn.outstagram.model.node.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Placement {

    private UUID id;
    private Post source;
    private Place target;

}
