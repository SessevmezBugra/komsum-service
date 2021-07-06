package com.komsum.feed.service;

import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostEntity;
import com.komsum.feed.model.SlicedResult;

public interface PostService {
	
	PostEntity create(PostEntity postEntity);
	
	SlicedResult<PostDto> findAll(Integer page);

}
