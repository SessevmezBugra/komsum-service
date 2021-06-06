package com.komsum.feed.service.impl;

import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostFeedEntity;
import com.komsum.feed.repository.PostFeedRepository;
import com.komsum.feed.service.PostFeedService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostFeedServiceImpl implements PostFeedService {

    private final PostFeedRepository postFeedRepository;
    private final ModelMapper modelMapper;

    @Override
    public void create(PostDto postDto) {
        postDto.getTagIds().forEach( tagId -> {
            PostFeedEntity postFeedEntity = modelMapper.map(postDto, PostFeedEntity.class);
            postFeedEntity.setTagId(tagId);
            postFeedRepository.save(postFeedEntity);
        });
    }

    @Override
    public PostFeedEntity update(PostFeedEntity postFeedEntity) {
        return postFeedRepository.save(postFeedEntity);
    }

    @Override
    public List<PostFeedEntity> findAll() {
        return postFeedRepository.findAll();
    }

    @Override
    public List<PostFeedEntity> findByStreetIdAndTagIdIn(Integer streetId, Iterable<Integer> tagIds) {
        return postFeedRepository.findByStreetIdAndTagIdIn(streetId, tagIds);
    }

//    public PostFeedEntity convertTo(PostDto postDto)
}
