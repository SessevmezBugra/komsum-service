package com.komsum.feed.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.komsum.feed.client.PostServiceClient;
import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostEntity;
import com.komsum.feed.entity.PostFileEntity;
import com.komsum.feed.model.SlicedResult;
import com.komsum.feed.repository.PostRepository;
import com.komsum.feed.service.PostFileService;
import com.komsum.feed.service.PostService;
import com.komsum.feed.util.constant.AppConstants;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
	
	private final PostRepository postRepository;
	private final PostServiceClient postServiceClient;
	private final PostFileService postFileService;

	@Override
	public SlicedResult<PostDto> findAll(Integer page) {
		Slice<PostEntity> slice = postRepository.findAll(CassandraPageRequest.first(AppConstants.PAGE_SIZE));
		int currpage = 0;
		while (slice.hasNext() && currpage < page) {
			slice = postRepository.findAll(slice.nextPageable());
			currpage++;
		}
		List<String> postIds = slice.getContent().stream().map(PostEntity::getPostId).distinct()
				.collect(Collectors.toList());
		List<PostDto> posts = postServiceClient.getPostsByIdIn(postIds).getBody();
		List<PostFileEntity> files = postFileService.findByIdIn(postIds);
		files.stream().forEach(f -> {
			posts.stream().forEach(p -> {
				if(p.getId().equals(f.getPostId())) {
					p.setFileId(f.getFileId());
				}
			});
		});
		return SlicedResult.<PostDto>builder().content(posts).isLast(slice.isLast()).build();
	}

	@Override
	public PostEntity create(PostEntity postEntity) {
		return postRepository.save(postEntity);
	}

}
