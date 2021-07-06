package com.komsum.feed.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import com.komsum.feed.entity.PostStreetTagEntity;

@Repository
public interface PostStreetTagRepository extends CassandraRepository<PostStreetTagEntity, String> {

	Slice<PostStreetTagEntity> findByStreetId(Integer streetId, Pageable pageable);

	Slice<PostStreetTagEntity> findByStreetIdAndTagIdIn(Integer streetId, Iterable<Integer> tagIds, Pageable pageable);

}
