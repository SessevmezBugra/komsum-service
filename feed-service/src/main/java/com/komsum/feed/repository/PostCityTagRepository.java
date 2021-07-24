package com.komsum.feed.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import com.komsum.feed.entity.PostCityTagEntity;

@Repository
public interface PostCityTagRepository  extends CassandraRepository<PostCityTagEntity, String> {
	
	Slice<PostCityTagEntity> findByCityId(Integer cityId, Pageable pageable);
	
	Slice<PostCityTagEntity> findByCityIdAndTagIdIn(Integer cityId, Iterable<String> tagIds, Pageable pageable);
}
