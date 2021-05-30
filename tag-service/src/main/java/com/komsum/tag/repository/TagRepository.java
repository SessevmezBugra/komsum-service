package com.komsum.tag.repository;


import com.komsum.tag.entity.TagEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends MongoRepository<TagEntity, String> {
}
