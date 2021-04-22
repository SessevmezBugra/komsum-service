package com.komsum.geography.service;

import com.komsum.geography.entity.CityEntity;
import com.komsum.geography.entity.NeighborhoodEntity;
import com.komsum.geography.service.common.generic.GenericService;

import java.util.List;

public interface NeighborhoodService  extends GenericService<NeighborhoodEntity, Integer> {
    List<NeighborhoodEntity> findByDistrictId(Integer districtId);
}
