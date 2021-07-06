package com.komsum.feed.service;

import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostNeighborhoodTagEntity;
import com.komsum.feed.model.SlicedResult;

public interface PostNeighborhoodTagService {
	
	PostNeighborhoodTagEntity create(PostNeighborhoodTagEntity postNeighborhoodTagEntity);

	SlicedResult<PostDto> findByNeighborhoodId(Integer neighborhoodId, Integer page);

	SlicedResult<PostDto> findByNeighborhoodIdAndTagIdIn(Integer neighborhoodId, Iterable<Integer> tagIds, Integer page);
}
