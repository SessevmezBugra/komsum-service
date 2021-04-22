package com.komsum.geography.repository;

import com.komsum.geography.entity.StreetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreetRepository extends JpaRepository<StreetEntity, Integer> {
    List<StreetEntity> findByNeighborhoodId(Integer neighborhoodId);
}
