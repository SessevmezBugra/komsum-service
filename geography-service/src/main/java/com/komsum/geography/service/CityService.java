package com.komsum.geography.service;

import com.komsum.geography.entity.CityEntity;
import com.komsum.geography.service.common.generic.GenericService;

import java.util.List;

public interface CityService extends GenericService<CityEntity, Integer> {

    List<CityEntity> findAll();
}
