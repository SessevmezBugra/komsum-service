package com.komsum.geography.service.impl;

import com.komsum.geography.entity.CityEntity;
import com.komsum.geography.repository.CityRepository;
import com.komsum.geography.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public CityEntity create(CityEntity entity) {
        return cityRepository.save(entity);
    }

    @Override
    public CityEntity update(CityEntity entity) {
        return cityRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        cityRepository.deleteById(id);
    }

    @Override
    public CityEntity findById(Integer id) {
        return cityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("City not found for this ID. " + id));
    }

    @Override
    public List<CityEntity> findAll() {
        return cityRepository.findAll();
    }
}
