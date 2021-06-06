package com.komsum.feed.service;

import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostFeedEntity;

import java.util.List;

public interface PostFeedService {

    void create(PostDto postDto);

    PostFeedEntity update(PostFeedEntity postFeedEntity);

    List<PostFeedEntity> findAll();

    List<PostFeedEntity> findByStreetIdAndTagIdIn(Integer streetId, Iterable<Integer> tagIds);
}
