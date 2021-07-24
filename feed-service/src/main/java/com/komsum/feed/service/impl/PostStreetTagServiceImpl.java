package com.komsum.feed.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.komsum.feed.client.PostServiceClient;
import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostFileEntity;
import com.komsum.feed.entity.PostStreetTagEntity;
import com.komsum.feed.model.SlicedResult;
import com.komsum.feed.repository.PostStreetTagRepository;
import com.komsum.feed.service.PostFileService;
import com.komsum.feed.service.PostStreetTagService;
import com.komsum.feed.util.constant.AppConstants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostStreetTagServiceImpl implements PostStreetTagService{
	
	private final PostStreetTagRepository postStreetTagRepository;
	private final PostServiceClient postServiceClient;
	private final PostFileService postFileService;

	@Override
	public SlicedResult<PostDto> findByStreetId(Integer streetId, Integer page) {
		Slice<PostStreetTagEntity> slice = postStreetTagRepository.findByStreetId(streetId, CassandraPageRequest.first(AppConstants.PAGE_SIZE));
		int currpage = 0;
		while (slice.hasNext() && currpage < page) {
			slice = postStreetTagRepository.findByStreetId(streetId, slice.nextPageable());
			currpage++;
		}
		List<String> postIds = slice.getContent().stream().map(PostStreetTagEntity::getPostId).distinct()
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
	public SlicedResult<PostDto> findByStreetIdAndTagIdIn(Integer streetId, Iterable<Integer> tagIds, Integer page) {
		Slice<PostStreetTagEntity> slice = postStreetTagRepository.findByStreetIdAndTagIdIn(streetId, tagIds, CassandraPageRequest.first(AppConstants.PAGE_SIZE));
		int currpage = 0;
		while (slice.hasNext() && currpage < page) {
			slice = postStreetTagRepository.findByStreetIdAndTagIdIn(streetId, tagIds, slice.nextPageable());
			currpage++;
		}
		List<String> postIds = slice.getContent().stream().map(PostStreetTagEntity::getPostId).distinct()
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
	public PostStreetTagEntity create(PostStreetTagEntity postStreetTagEntity) {
		return postStreetTagRepository.save(postStreetTagEntity);
	}

}
