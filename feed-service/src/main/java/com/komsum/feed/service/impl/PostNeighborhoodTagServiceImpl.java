package com.komsum.feed.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.komsum.feed.client.PostServiceClient;
import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostNeighborhoodTagEntity;
import com.komsum.feed.model.SlicedResult;
import com.komsum.feed.repository.PostNeighborhoodTagRepository;
import com.komsum.feed.service.PostNeighborhoodTagService;
import com.komsum.feed.util.constant.AppConstants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostNeighborhoodTagServiceImpl implements PostNeighborhoodTagService{

	private final PostNeighborhoodTagRepository postNeighborhoodTagRepository;
	private final PostServiceClient postServiceClient;
	
	@Override
	public SlicedResult<PostDto> findByNeighborhoodId(Integer neighborhoodId, Integer page) {
		Slice<PostNeighborhoodTagEntity> slice = postNeighborhoodTagRepository.findByNeighborhoodId(neighborhoodId, CassandraPageRequest.first(AppConstants.PAGE_SIZE));
		int currpage = 0;
		while (slice.hasNext() && currpage < page) {
			slice = postNeighborhoodTagRepository.findByNeighborhoodId(neighborhoodId, slice.nextPageable());
			currpage++;
		}
		List<String> postIds = slice.getContent().stream().map(PostNeighborhoodTagEntity::getPostId).distinct()
				.collect(Collectors.toList());
		List<PostDto> posts = postServiceClient.getPostsByIdIn(postIds).getBody();
		return SlicedResult.<PostDto>builder().content(posts).isLast(slice.isLast()).build();
	}

	@Override
	public SlicedResult<PostDto> findByNeighborhoodIdAndTagIdIn(Integer neighborhoodId, Iterable<Integer> tagIds,
			Integer page) {
		Slice<PostNeighborhoodTagEntity> slice = postNeighborhoodTagRepository.findByNeighborhoodIdAndTagIdIn(neighborhoodId, tagIds, CassandraPageRequest.first(AppConstants.PAGE_SIZE));
		int currpage = 0;
		while (slice.hasNext() && currpage < page) {
			slice = postNeighborhoodTagRepository.findByNeighborhoodIdAndTagIdIn(neighborhoodId, tagIds, slice.nextPageable());
			currpage++;
		}
		List<String> postIds = slice.getContent().stream().map(PostNeighborhoodTagEntity::getPostId).distinct()
				.collect(Collectors.toList());
		List<PostDto> posts = postServiceClient.getPostsByIdIn(postIds).getBody();
		return SlicedResult.<PostDto>builder().content(posts).isLast(slice.isLast()).build();
	}

	@Override
	public PostNeighborhoodTagEntity create(PostNeighborhoodTagEntity postNeighborhoodTagEntity) {
		return postNeighborhoodTagRepository.save(postNeighborhoodTagEntity);
	}

}