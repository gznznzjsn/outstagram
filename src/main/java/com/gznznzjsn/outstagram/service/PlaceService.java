package com.gznznzjsn.outstagram.service;

import com.gznznzjsn.outstagram.model.node.Place;

public interface PlaceService {

    Place retrieveOrCreateAndRetrieve(String name);

}
