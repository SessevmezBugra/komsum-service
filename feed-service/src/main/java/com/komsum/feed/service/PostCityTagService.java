package com.komsum.feed.service;

import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostCityTagEntity;
import com.komsum.feed.model.SlicedResult;

public interface PostCityTagService {
	
	PostCityTagEntity create(PostCityTagEntity postCityTagEntity);
	
	SlicedResult<PostDto> findByCityId(Integer cityId, Integer page);
	
	SlicedResult<PostDto> findByCityIdAndTagIdIn(Integer cityId, Iterable<Integer> tagIds, Integer page);

}
