package com.komsum.feed.service;

import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostTagEntity;
import com.komsum.feed.model.SlicedResult;

public interface PostTagService {
	
	PostTagEntity create(PostTagEntity postTagEntity);

	SlicedResult<PostDto> findByTagIdIn(Iterable<Integer> tagIds, Integer page);
}
