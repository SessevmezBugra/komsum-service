package com.komsum.feed.service;

import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostStreetTagEntity;
import com.komsum.feed.model.SlicedResult;

public interface PostStreetTagService {
	
	PostStreetTagEntity create(PostStreetTagEntity postStreetTagEntity);

	SlicedResult<PostDto> findByStreetId(Integer streetId, Integer page);

	SlicedResult<PostDto> findByStreetIdAndTagIdIn(Integer streetId, Iterable<String> tagIds, Integer page);
}
