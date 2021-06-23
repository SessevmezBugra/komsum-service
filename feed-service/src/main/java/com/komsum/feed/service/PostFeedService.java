package com.komsum.feed.service;

import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostFeedEntity;
import com.komsum.feed.model.SlicedResult;
import org.springframework.data.domain.Pageable;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;

public interface PostFeedService {

    void create(PostDto postDto);

    PostFeedEntity update(PostFeedEntity postFeedEntity);

    Iterable<PostFeedEntity> findAll();

    SlicedResult<PostDto> findByStreetIdAndTagIdInAndPage(Integer streetId, Iterable<Integer> tagIds, Optional<Integer> pageNumber);
}
