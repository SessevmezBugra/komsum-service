package com.komsum.geography.service;

import com.komsum.geography.entity.CityEntity;
import com.komsum.geography.entity.StreetEntity;
import com.komsum.geography.service.common.generic.GenericService;

import java.util.List;

public interface StreetService  extends GenericService<StreetEntity, Integer> {
    List<StreetEntity> findByNeighborhoodId(Integer neighborhoodId);
}
