package com.komsum.post.service.impl;

import java.time.Instant;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.komsum.post.client.FeedServiceClient;
import com.komsum.post.client.FileServiceClient;
import com.komsum.post.dto.PostDto;
import com.komsum.post.entity.PostEntity;
import com.komsum.post.error.EntityNotFoundException;
import com.komsum.post.repository.PostRepository;
import com.komsum.post.service.PostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final FeedServiceClient feedServiceClient;
    private final ModelMapper modelMapper;
    private final FileServiceClient fileServiceClient;

    @Override
    public PostEntity create(PostDto postDto, MultipartFile file) {
        PostEntity postEntity = modelMapper.map(postDto, PostEntity.class);
        postEntity.setCreatedAt(Instant.now());
        postRepository.save(postEntity);
        postDto.setId(postEntity.getId());
        postDto.setCreatedAt(postEntity.getCreatedAt());
        if(!ObjectUtils.isEmpty(file)) {
        	String fileId = fileServiceClient.createFile(file).getBody();
        	postDto.setFileId(fileId);
        }
        feedServiceClient.createPostFeed(postDto);
        return postEntity;
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

    @Override
    public List<PostEntity> findAll() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }
}
