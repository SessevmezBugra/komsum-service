package com.komsum.feed.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.komsum.feed.client.PostServiceClient;
import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostDistrictTagEntity;
import com.komsum.feed.entity.PostFileEntity;
import com.komsum.feed.model.SlicedResult;
import com.komsum.feed.repository.PostDistrictTagRepository;
import com.komsum.feed.service.PostDistrictTagService;
import com.komsum.feed.service.PostFileService;
import com.komsum.feed.util.constant.AppConstants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostDistrictTagServiceImpl implements PostDistrictTagService {
	
	private final PostDistrictTagRepository postDistrictTagRepository;
	private final PostServiceClient postServiceClient;
	private final PostFileService postFileService;

	@Override
	public SlicedResult<PostDto> findByDistrictId(Integer districtId, Integer page) {
		Slice<PostDistrictTagEntity> slice = postDistrictTagRepository.findByDistrictId(districtId, CassandraPageRequest.first(AppConstants.PAGE_SIZE));
		int currpage = 0;
		while (slice.hasNext() && currpage < page) {
			slice = postDistrictTagRepository.findByDistrictId(districtId, slice.nextPageable());
			currpage++;
		}
		List<String> postIds = slice.getContent().stream().map(PostDistrictTagEntity::getPostId).distinct()
				.collect(Collectors.toList());
		List<PostDto> posts = postServiceClient.getPostsByIdIn(postIds).getBody();
		if(!ObjectUtils.isEmpty(postIds)) {
			List<PostFileEntity> files = postFileService.findByIdIn(postIds);
			files.stream().forEach(f -> {
				posts.stream().forEach(p -> {
					if(p.getId().equals(f.getPostId())) {
						p.setFileId(f.getFileId());
					}
				});
			});
		}
		slice.getContent().stream().forEach(s -> {
			posts.forEach(p -> {
				if(s.getPostId().equals(p.getId())) {
					p.getTagIds().add(s.getTagId());
				}
			});
		});
		return SlicedResult.<PostDto>builder().content(posts).isLast(slice.isLast()).build();
	}

	@Override
	public SlicedResult<PostDto> findByDistrictIdAndTagIdIn(Integer districtId, Iterable<String> tagIds, Integer page) {
		Slice<PostDistrictTagEntity> slice = postDistrictTagRepository.findByDistrictIdAndTagIdIn(districtId, tagIds, CassandraPageRequest.first(AppConstants.PAGE_SIZE));
		int currpage = 0;
		while (slice.hasNext() && currpage < page) {
			slice = postDistrictTagRepository.findByDistrictIdAndTagIdIn(districtId, tagIds, slice.nextPageable());
			currpage++;
		}
		List<String> postIds = slice.getContent().stream().map(PostDistrictTagEntity::getPostId).distinct()
				.collect(Collectors.toList());
		List<PostDto> posts = postServiceClient.getPostsByIdIn(postIds).getBody();
		if(!ObjectUtils.isEmpty(postIds)) {
			List<PostFileEntity> files = postFileService.findByIdIn(postIds);
			files.stream().forEach(f -> {
				posts.stream().forEach(p -> {
					if(p.getId().equals(f.getPostId())) {
						p.setFileId(f.getFileId());
					}
				});
			});
		}
		slice.getContent().stream().forEach(s -> {
			posts.forEach(p -> {
				if(s.getPostId().equals(p.getId())) {
					p.getTagIds().add(s.getTagId());
				}
			});
		});
		return SlicedResult.<PostDto>builder().content(posts).isLast(slice.isLast()).build();
	}

	@Override
	public PostDistrictTagEntity create(PostDistrictTagEntity postDistrictTagEntity) {
		return postDistrictTagRepository.save(postDistrictTagEntity);
	}

}
