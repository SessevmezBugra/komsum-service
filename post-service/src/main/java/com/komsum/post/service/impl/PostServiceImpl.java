package com.komsum.post.service.impl;

import com.komsum.post.entity.PostEntity;
import com.komsum.post.error.EntityNotFoundException;
import com.komsum.post.repository.PostRepository;
import com.komsum.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public PostEntity create(PostEntity postEntity) {
        return postRepository.save(postEntity);
    }

    @Override
    public PostEntity update(PostEntity postEntity) {
        return postRepository.save(postEntity);
    }

    @Override
    public void deleteById(String id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostEntity findById(String id) {
        return postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Post not found."));
    }

    @Override
    public List<PostEntity> findPostsByIdIn(List<String> ids) {
        return postRepository.findByIdInOrderByCreatedAtDesc(ids);
    }

    @Override
    public List<PostEntity> findByUsername(String username) {
        return postRepository.findByUsernameOrderByCreatedAtDesc(username);
    }
}
