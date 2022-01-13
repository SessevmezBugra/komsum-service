package com.komsum.feed.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import com.komsum.feed.entity.PostUserEntity;

@Repository
public interface PostUserRepository extends CassandraRepository<PostUserEntity, String> {

	Slice<PostUserEntity> findByUsername(String username, Pageable pageable);

}
