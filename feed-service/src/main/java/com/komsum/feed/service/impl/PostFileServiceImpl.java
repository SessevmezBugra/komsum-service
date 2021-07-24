package com.komsum.feed.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.komsum.feed.entity.PostFileEntity;
import com.komsum.feed.repository.PostFileRepository;
import com.komsum.feed.service.PostFileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostFileServiceImpl implements PostFileService {
	
	private final PostFileRepository postFileRepository;

	@Override
	public PostFileEntity create(PostFileEntity postFileEntity) {
		return postFileRepository.save(postFileEntity);
	}

	@Override
	public List<PostFileEntity> findByIdIn(List<String> ids) {
		return postFileRepository.findByPostIdIn(ids);
	}

}
