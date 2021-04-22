package com.komsum.geography.service.impl;

import com.komsum.geography.entity.StreetEntity;
import com.komsum.geography.repository.StreetRepository;
import com.komsum.geography.service.StreetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StreetServiceImpl implements StreetService {

    private final StreetRepository streetRepository;

    @Override
    public StreetEntity create(StreetEntity entity) {
        return streetRepository.save(entity);
    }

    @Override
    public StreetEntity update(StreetEntity entity) {
        return streetRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        streetRepository.deleteById(id);
    }

    @Override
    public StreetEntity findById(Integer id) {
        return streetRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Street not found for this ID " + id));
    }

    @Override
    public List<StreetEntity> findByNeighborhoodId(Integer neighborhoodId) {
        return streetRepository.findByNeighborhoodId(neighborhoodId);
    }
}
