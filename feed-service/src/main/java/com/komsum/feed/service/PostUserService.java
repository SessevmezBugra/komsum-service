package com.komsum.feed.service;

import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostUserEntity;
import com.komsum.feed.model.SlicedResult;

public interface PostUserService {
	
	PostUserEntity create(PostUserEntity postUserEntity);
	
	SlicedResult<PostDto> findByUsername(String username, Integer page);

}