package com.komsum.feed.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.komsum.feed.client.PostServiceClient;
import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostCountryTagEntity;
import com.komsum.feed.entity.PostFileEntity;
import com.komsum.feed.model.SlicedResult;
import com.komsum.feed.repository.PostCountryTagRepository;
import com.komsum.feed.service.PostCountryTagService;
import com.komsum.feed.service.PostFileService;
import com.komsum.feed.util.constant.AppConstants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostCountryTagServiceImpl implements PostCountryTagService {

	private final PostCountryTagRepository postCountryTagRepository;
	private final PostServiceClient postServiceClient;
	private final PostFileService postFileService;
	
	@Override
	public SlicedResult<PostDto> findByCountryId(Integer countryId, Integer page) {
		Slice<PostCountryTagEntity> slice = postCountryTagRepository.findByCountryId(countryId, CassandraPageRequest.first(AppConstants.PAGE_SIZE));
		int currpage = 0;
		while (slice.hasNext() && currpage < page) {
			slice = postCountryTagRepository.findByCountryId(countryId, slice.nextPageable());
			currpage++;
		}
		List<String> postIds = slice.getContent().stream().map(PostCountryTagEntity::getPostId).distinct()
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
	public SlicedResult<PostDto> findByCountryIdAndTagIdIn(Integer countryId, Iterable<Integer> tagIds, Integer page) {
		Slice<PostCountryTagEntity> slice = postCountryTagRepository.findByCountryIdAndTagIdIn(countryId, tagIds, CassandraPageRequest.first(AppConstants.PAGE_SIZE));
		int currpage = 0;
		while (slice.hasNext() && currpage < page) {
			slice = postCountryTagRepository.findByCountryIdAndTagIdIn(countryId, tagIds, slice.nextPageable());
			currpage++;
		}
		List<String> postIds = slice.getContent().stream().map(PostCountryTagEntity::getPostId).distinct()
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
	public PostCountryTagEntity create(PostCountryTagEntity postCountryTagEntity) {
		return postCountryTagRepository.save(postCountryTagEntity);
	}

}
