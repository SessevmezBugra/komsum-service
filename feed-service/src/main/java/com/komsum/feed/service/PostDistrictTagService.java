package com.komsum.feed.service;

import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostDistrictTagEntity;
import com.komsum.feed.model.SlicedResult;

public interface PostDistrictTagService {
	
	PostDistrictTagEntity create(PostDistrictTagEntity postDistrictTagEntity);

	SlicedResult<PostDto> findByDistrictId(Integer districtId, Integer page);

	SlicedResult<PostDto> findByDistrictIdAndTagIdIn(Integer districtId, Iterable<Integer> tagIds, Integer page);
	
}
