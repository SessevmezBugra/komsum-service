package com.komsum.geography.service;

import java.util.List;

import com.komsum.geography.entity.DistrictEntity;
import com.komsum.geography.service.common.generic.GenericService;

public interface DistrictService  extends GenericService<DistrictEntity, Integer> {
    List<DistrictEntity> findByCityId(Integer cityId);
}
