package com.komsum.feed.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.komsum.feed.entity.PostEntity;

@Repository
public interface PostRepository extends CassandraRepository<PostEntity, String> {


}
