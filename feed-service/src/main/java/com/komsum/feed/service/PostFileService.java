package com.komsum.feed.service;

import java.util.List;

import com.komsum.feed.entity.PostFileEntity;

public interface PostFileService {
	
	PostFileEntity create(PostFileEntity postFileEntity);
	
	List<PostFileEntity> findByIdIn(List<String> ids);

}
