package com.komsum.geography.repository;

import com.komsum.geography.entity.NeighborhoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NeighborhoodRepository extends JpaRepository<NeighborhoodEntity, Integer> {
    List<NeighborhoodEntity> findByDistrictId(Integer districtId);
}
