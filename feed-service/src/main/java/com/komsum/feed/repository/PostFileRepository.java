package com.komsum.feed.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.komsum.feed.entity.PostFileEntity;

@Repository
public interface PostFileRepository extends CassandraRepository<PostFileEntity, String> {

}
