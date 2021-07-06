package com.komsum.feed.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.komsum.feed.client.PostServiceClient;
import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostStreetTagEntity;
import com.komsum.feed.entity.PostTagEntity;
import com.komsum.feed.model.SlicedResult;
import com.komsum.feed.repository.PostTagRepository;
import com.komsum.feed.service.PostTagService;
import com.komsum.feed.util.constant.AppConstants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostTagServiceImpl implements PostTagService{
	
	private final PostTagRepository postTagRepository;
	private final PostServiceClient postServiceClient;

	@Override
	public SlicedResult<PostDto> findByTagIdIn(Iterable<Integer> tagIds, Integer page) {
		Slice<PostStreetTagEntity> slice = postTagRepository.findByTagIdIn(tagIds, CassandraPageRequest.first(AppConstants.PAGE_SIZE));
		int currpage = 0;
		while (slice.hasNext() && currpage < page) {
			slice = postTagRepository.findByTagIdIn(tagIds, slice.nextPageable());
			currpage++;
		}
		List<String> postIds = slice.getContent().stream().map(PostStreetTagEntity::getPostId).distinct()
				.collect(Collectors.toList());
		List<PostDto> posts = postServiceClient.getPostsByIdIn(postIds).getBody();
		return SlicedResult.<PostDto>builder().content(posts).isLast(slice.isLast()).build();
	}

	@Override
	public PostTagEntity create(PostTagEntity postTagEntity) {
		return postTagRepository.save(postTagEntity);
	}

}