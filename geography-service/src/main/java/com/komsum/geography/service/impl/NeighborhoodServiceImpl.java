package com.komsum.geography.service.impl;

import com.komsum.geography.entity.NeighborhoodEntity;
import com.komsum.geography.repository.NeighborhoodRepository;
import com.komsum.geography.service.NeighborhoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NeighborhoodServiceImpl implements NeighborhoodService {

    private final NeighborhoodRepository neighborhoodRepository;

    @Override
    public NeighborhoodEntity create(NeighborhoodEntity entity) {
        return neighborhoodRepository.save(entity);
    }

    @Override
    public NeighborhoodEntity update(NeighborhoodEntity entity) {
        return neighborhoodRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        neighborhoodRepository.deleteById(id);
    }

    @Override
    public NeighborhoodEntity findById(Integer id) {
        return neighborhoodRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Neighborhood not found for this ID " + id));
    }

    @Override
    public List<NeighborhoodEntity> findByDistrictId(Integer districtId) {
        return neighborhoodRepository.findByDistrictId(districtId);
    }
}
