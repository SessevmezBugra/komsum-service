package com.komsum.geography.service;

import java.util.List;

import com.komsum.geography.entity.NeighborhoodEntity;
import com.komsum.geography.service.common.generic.GenericService;

public interface NeighborhoodService  extends GenericService<NeighborhoodEntity, Integer> {
    List<NeighborhoodEntity> findByDistrictId(Integer districtId);
}
