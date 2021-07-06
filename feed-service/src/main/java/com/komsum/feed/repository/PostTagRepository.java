package com.komsum.feed.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import com.komsum.feed.entity.PostStreetTagEntity;
import com.komsum.feed.entity.PostTagEntity;

@Repository
public interface PostTagRepository extends CassandraRepository<PostTagEntity, String> {

	Slice<PostStreetTagEntity> findByTagIdIn(Iterable<Integer> tagIds, Pageable pageable);

}
