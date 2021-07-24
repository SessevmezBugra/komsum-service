package com.komsum.feed.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.komsum.feed.entity.PostFileEntity;

@Repository
public interface PostFileRepository extends CassandraRepository<PostFileEntity, String> {

	PostFileEntity findByPostId(String postId);
	
	List<PostFileEntity> findByPostIdIn(Iterable<String> postIds);
}
