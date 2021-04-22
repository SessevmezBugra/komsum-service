package com.komsum.geography.service;

import com.komsum.geography.entity.CityEntity;
import com.komsum.geography.entity.DistrictEntity;
import com.komsum.geography.service.common.generic.GenericService;

import java.util.List;

public interface DistrictService  extends GenericService<DistrictEntity, Integer> {
    List<DistrictEntity> findByCityId(Integer cityId);
}
