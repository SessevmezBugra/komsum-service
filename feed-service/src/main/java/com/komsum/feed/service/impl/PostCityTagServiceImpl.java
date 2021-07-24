package com.komsum.feed.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.komsum.feed.client.PostServiceClient;
import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostCityTagEntity;
import com.komsum.feed.entity.PostFileEntity;
import com.komsum.feed.model.SlicedResult;
import com.komsum.feed.repository.PostCityTagRepository;
import com.komsum.feed.service.PostCityTagService;
import com.komsum.feed.service.PostFileService;
import com.komsum.feed.util.constant.AppConstants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostCityTagServiceImpl implements PostCityTagService{
	
	private final PostCityTagRepository postCityTagRepository;
	private final PostServiceClient postServiceClient;
	private final PostFileService postFileService;

	@Override
	public SlicedResult<PostDto> findByCityId(Integer cityId, Integer page) {
		Slice<PostCityTagEntity> slice = postCityTagRepository.findByCityId(cityId, CassandraPageRequest.first(AppConstants.PAGE_SIZE));
		int currpage = 0;
		while (slice.hasNext() && currpage < page) {
			slice = postCityTagRepository.findByCityId(cityId, slice.nextPageable());
			currpage++;
		}
		List<String> postIds = slice.getContent().stream().map(PostCityTagEntity::getPostId).distinct()
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
	public SlicedResult<PostDto> findByCityIdAndTagIdIn(Integer cityId, Iterable<Integer> tagIds, Integer page) {
		Slice<PostCityTagEntity> slice = postCityTagRepository.findByCityIdAndTagIdIn(cityId, tagIds, CassandraPageRequest.first(AppConstants.PAGE_SIZE));
		int currpage = 0;
		while (slice.hasNext() && currpage < page) {
			slice = postCityTagRepository.findByCityIdAndTagIdIn(cityId, tagIds, slice.nextPageable());
			currpage++;
		}
		List<String> postIds = slice.getContent().stream().map(PostCityTagEntity::getPostId).distinct()
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
	public PostCityTagEntity create(PostCityTagEntity postCityTagEntity) {
		return postCityTagRepository.save(postCityTagEntity);
	}

}
