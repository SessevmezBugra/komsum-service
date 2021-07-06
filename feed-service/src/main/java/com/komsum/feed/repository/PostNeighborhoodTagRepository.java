package com.komsum.feed.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import com.komsum.feed.entity.PostNeighborhoodTagEntity;

@Repository
public interface PostNeighborhoodTagRepository extends CassandraRepository<PostNeighborhoodTagEntity, String> {

	Slice<PostNeighborhoodTagEntity> findByNeighborhoodId(Integer neighborhoodId, Pageable pageable);

	Slice<PostNeighborhoodTagEntity> findByNeighborhoodIdAndTagIdIn(Integer neighborhoodId, Iterable<Integer> tagIds, Pageable pageable);
}
