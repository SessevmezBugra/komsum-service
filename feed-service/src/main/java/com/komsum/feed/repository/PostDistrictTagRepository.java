package com.komsum.feed.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import com.komsum.feed.entity.PostDistrictTagEntity;

@Repository
public interface PostDistrictTagRepository extends CassandraRepository<PostDistrictTagEntity, String> {

	Slice<PostDistrictTagEntity> findByDistrictId(Integer countryId, Pageable pageable);

	Slice<PostDistrictTagEntity> findByDistrictIdAndTagIdIn(Integer countryId, Iterable<Integer> tagIds, Pageable pageable);
}
