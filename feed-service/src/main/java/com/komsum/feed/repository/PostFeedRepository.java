package com.komsum.feed.repository;

import com.komsum.feed.entity.PostFeedEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostFeedRepository extends CassandraRepository<PostFeedEntity, String> {

    Slice<PostFeedEntity> findByStreetIdAndTagIdIn(Integer streetId, Iterable<Integer> tagIds, Pageable pageable);
}
