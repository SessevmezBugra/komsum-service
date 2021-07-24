package com.komsum.feed.service;

import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostCountryTagEntity;
import com.komsum.feed.model.SlicedResult;

public interface PostCountryTagService {
	
	PostCountryTagEntity create(PostCountryTagEntity postCountryTagEntity);
	
	SlicedResult<PostDto> findByCountryId(Integer countryId, Integer page);

	SlicedResult<PostDto> findByCountryIdAndTagIdIn(Integer countryId, Iterable<String> tagIds, Integer page);

}
