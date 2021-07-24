package com.komsum.post.service;

import com.komsum.post.dto.PostDto;
import com.komsum.post.entity.PostEntity;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface PostService {

    PostEntity create(PostDto postDto, MultipartFile file);

    PostEntity update(PostEntity postEntity);

    void deleteById(String id);

    PostEntity findById(String id);

    List<PostEntity> findPostsByIdIn(List<String> ids);

    List<PostEntity> findByUsername(String username);

    List<PostEntity> findAll();
}
