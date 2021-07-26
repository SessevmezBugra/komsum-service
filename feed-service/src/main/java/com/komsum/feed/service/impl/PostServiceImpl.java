package com.komsum.feed.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.komsum.feed.client.GeographyServiceClient;
import com.komsum.feed.client.PostServiceClient;
import com.komsum.feed.client.TagServiceClient;
import com.komsum.feed.dto.PostDto;
import com.komsum.feed.dto.StreetDto;
import com.komsum.feed.dto.TagDto;
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
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final PostServiceClient postServiceClient;
	private final PostFileService postFileService;
	private final TagServiceClient tagServiceClient;
	private final GeographyServiceClient geographyServiceClient;

	@Override
	public SlicedResult<PostDto> findAll(Integer page) {
		Slice<PostEntity> slice = postRepository.findAll(CassandraPageRequest.first(AppConstants.PAGE_SIZE));
		int currpage = 0;
		while (slice.hasNext() && currpage < page) {
			slice = postRepository.findAll(slice.nextPageable());
			currpage++;
		}
		List<PostEntity> desiredPage = slice.getContent();
		List<String> postIds = desiredPage.stream().map(PostEntity::getPostId).distinct()
				.collect(Collectors.toList());
		List<String> foundTagIds = desiredPage.stream().map(PostEntity::getTagId).distinct()
				.collect(Collectors.toList());
		List<Integer> streetIds = desiredPage.stream().map(PostEntity::getStreetId).distinct()
				.collect(Collectors.toList());
		List<PostDto> posts = postServiceClient.getPostsByIdIn(postIds).getBody();
		List<TagDto> tags = tagServiceClient.getTagsByIdIn(foundTagIds).getBody();
		List<StreetDto> streets = geographyServiceClient.getStreetsByIdIn(streetIds).getBody();
		
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
						Optional<TagDto> tagDto = tags.stream().filter(t -> t.getId().equals(s.getTagId())).findFirst();
						tagDto.ifPresent(t -> {
							p.addTagg(t);
						});
						Optional<StreetDto> street = streets.stream().filter(st -> st.getId().equals(s.getStreetId())).findFirst();
						street.ifPresent(sd -> {
							p.setCityId(sd.getCityId());
							p.setCityName(sd.getCityName());
							p.setDistrictId(sd.getDistrictId());
							p.setDistrictName(sd.getDistrictName());
							p.setNeighborhoodName(sd.getNeighborhoodName());
							p.setStreetId(sd.getId());
							p.setStreetName(sd.getName());
						});
					}
				});
				
			});
		}
		return SlicedResult.<PostDto>builder().content(posts).isLast(slice.isLast()).build();
	}

	@Override
	public PostEntity create(PostEntity postEntity) {
		return postRepository.save(postEntity);
	}

}
