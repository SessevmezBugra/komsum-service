package com.komsum.geography.service;

import java.util.List;

import com.komsum.geography.entity.StreetEntity;
import com.komsum.geography.service.common.generic.GenericService;

public interface StreetService  extends GenericService<StreetEntity, Integer> {
    List<StreetEntity> findByNeighborhoodId(Integer neighborhoodId);
    
    List<StreetEntity> findByStreetIdIn(Iterable<Integer> ids);
}
