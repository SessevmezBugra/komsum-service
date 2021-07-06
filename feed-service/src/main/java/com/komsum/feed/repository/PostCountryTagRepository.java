package com.komsum.feed.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import com.komsum.feed.entity.PostCountryTagEntity;

@Repository
public interface PostCountryTagRepository extends CassandraRepository<PostCountryTagEntity, String> {

	Slice<PostCountryTagEntity> findByCountryId(Integer countryId, Pageable pageable);

	Slice<PostCountryTagEntity> findByCountryIdAndTagIdIn(Integer countryId, Iterable<Integer> tagIds, Pageable pageable);
}
