package com.komsum.feed.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.komsum.feed.client.PostServiceClient;
import com.komsum.feed.client.TagServiceClient;
import com.komsum.feed.dto.PostDto;
import com.komsum.feed.dto.TagDto;
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
	private final TagServiceClient tagServiceClient;
	
	@Override
	public SlicedResult<PostDto> findByCountryId(Integer countryId, Integer page) {
		Slice<PostCountryTagEntity> slice = postCountryTagRepository.findByCountryId(countryId, CassandraPageRequest.first(AppConstants.PAGE_SIZE));
		int currpage = 0;
		while (slice.hasNext() && currpage < page) {
			slice = postCountryTagRepository.findByCountryId(countryId, slice.nextPageable());
			currpage++;
		}
		List<PostCountryTagEntity> desiredPage = slice.getContent();
		List<String> postIds = desiredPage.stream().map(PostCountryTagEntity::getPostId).distinct()
				.collect(Collectors.toList());
		List<String> foundTagIds = desiredPage.stream().map(PostCountryTagEntity::getTagId).distinct()
				.collect(Collectors.toList());
		List<PostDto> posts = postServiceClient.getPostsByIdIn(postIds).getBody();
		List<TagDto> tags = tagServiceClient.getTagsByIdIn(foundTagIds).getBody();
		if (!ObjectUtils.isEmpty(postIds)) {
			List<PostFileEntity> files = postFileService.findByIdIn(postIds);
			posts.stream().forEach(p -> {
				files.stream().forEach(f -> {
					if (p.getId().equals(f.getPostId())) {
						p.setFileId(f.getFileId());
					}
				});
				desiredPage.stream().forEach(s -> {
					if (s.getPostId().equals(p.getId())) {
						p.addTagg(tags.stream().filter(t -> t.getId().equals(s.getTagId())).findFirst().get());
					}
				});
			});
		}
		return SlicedResult.<PostDto>builder().content(posts).isLast(slice.isLast()).build();
	}

	@Override
	public SlicedResult<PostDto> findByCountryIdAndTagIdIn(Integer countryId, Iterable<String> tagIds, Integer page) {
		Slice<PostCountryTagEntity> slice = postCountryTagRepository.findByCountryIdAndTagIdIn(countryId, tagIds, CassandraPageRequest.first(AppConstants.PAGE_SIZE));
		int currpage = 0;
		while (slice.hasNext() && currpage < page) {
			slice = postCountryTagRepository.findByCountryIdAndTagIdIn(countryId, tagIds, slice.nextPageable());
			currpage++;
		}
		List<PostCountryTagEntity> desiredPage = slice.getContent();
		List<String> postIds = desiredPage.stream().map(PostCountryTagEntity::getPostId).distinct()
				.collect(Collectors.toList());
		List<String> foundTagIds = desiredPage.stream().map(PostCountryTagEntity::getTagId).distinct()
				.collect(Collectors.toList());
		List<PostDto> posts = postServiceClient.getPostsByIdIn(postIds).getBody();
		List<TagDto> tags = tagServiceClient.getTagsByIdIn(foundTagIds).getBody();
		if (!ObjectUtils.isEmpty(postIds)) {
			List<PostFileEntity> files = postFileService.findByIdIn(postIds);
			posts.stream().forEach(p -> {
				files.stream().forEach(f -> {
					if (p.getId().equals(f.getPostId())) {
						p.setFileId(f.getFileId());
					}
				});
				desiredPage.stream().forEach(s -> {
					if (s.getPostId().equals(p.getId())) {
						p.addTagg(tags.stream().filter(t -> t.getId().equals(s.getTagId())).findFirst().get());
					}
				});
			});
		}
		return SlicedResult.<PostDto>builder().content(posts).isLast(slice.isLast()).build();
	}

	@Override
	public PostCountryTagEntity create(PostCountryTagEntity postCountryTagEntity) {
		return postCountryTagRepository.save(postCountryTagEntity);
	}

}
