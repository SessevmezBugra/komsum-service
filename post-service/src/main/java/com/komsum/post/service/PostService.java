package com.komsum.post.service;

import com.komsum.post.entity.PostEntity;

import java.util.List;

public interface PostService {

    PostEntity create(PostEntity postEntity);

    PostEntity update(PostEntity postEntity);

    void deleteById(String id);

    PostEntity findById(String id);

    List<PostEntity> findPostsByIdIn(List<String> ids);

    List<PostEntity> findByUsername(String username);
}
