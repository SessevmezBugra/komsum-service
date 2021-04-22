package com.komsum.geography.service.impl;

import com.komsum.geography.entity.DistrictEntity;
import com.komsum.geography.repository.DistrictRepository;
import com.komsum.geography.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    @Override
    public DistrictEntity create(DistrictEntity entity) {
        return districtRepository.save(entity);
    }

    @Override
    public DistrictEntity update(DistrictEntity entity) {
        return districtRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        districtRepository.deleteById(id);
    }

    @Override
    public DistrictEntity findById(Integer id) {
        return districtRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("District not found for this ID " + id));
    }

    @Override
    public List<DistrictEntity> findByCityId(Integer cityId) {
        return districtRepository.findByCityId(cityId);
    }
}
