package com.komsum.feed.service.impl;

import java.util.List;
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
	private final TagServiceClient tagServiceClient;
	private final GeographyServiceClient geographyServiceClient;

	@Override
	public SlicedResult<PostDto> findByDistrictId(Integer districtId, Integer page) {
		Slice<PostDistrictTagEntity> slice = postDistrictTagRepository.findByDistrictId(districtId, CassandraPageRequest.first(AppConstants.PAGE_SIZE));
		int currpage = 0;
		while (slice.hasNext() && currpage < page) {
			slice = postDistrictTagRepository.findByDistrictId(districtId, slice.nextPageable());
			currpage++;
		}
		List<PostDistrictTagEntity> desiredPage = slice.getContent();
		List<String> postIds = desiredPage.stream().map(PostDistrictTagEntity::getPostId).distinct()
				.collect(Collectors.toList());
		List<String> foundTagIds = desiredPage.stream().map(PostDistrictTagEntity::getTagId).distinct()
				.collect(Collectors.toList());
		List<Integer> streetIds = desiredPage.stream().map(PostDistrictTagEntity::getStreetId).distinct()
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
						p.addTagg(tags.stream().filter(t -> t.getId().equals(s.getTagId())).findFirst().get());
						StreetDto street = streets.stream().filter(st -> st.getId() == s.getStreetId()).findFirst().get();
						p.setCityId(street.getCityId());
						p.setCityName(street.getCityName());
						p.setDistrictId(street.getDistrictId());
						p.setDistrictName(street.getDistrictName());
						p.setNeighborhoodName(street.getNeighborhoodName());
						p.setStreetId(street.getId());
						p.setStreetName(street.getName());
					}
				});
				
			});
		}
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
		List<PostDistrictTagEntity> desiredPage = slice.getContent();
		List<String> postIds = desiredPage.stream().map(PostDistrictTagEntity::getPostId).distinct()
				.collect(Collectors.toList());
		List<String> foundTagIds = desiredPage.stream().map(PostDistrictTagEntity::getTagId).distinct()
				.collect(Collectors.toList());
		List<Integer> streetIds = desiredPage.stream().map(PostDistrictTagEntity::getStreetId).distinct()
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
						p.addTagg(tags.stream().filter(t -> t.getId().equals(s.getTagId())).findFirst().get());
						StreetDto street = streets.stream().filter(st -> st.getId() == s.getStreetId()).findFirst().get();
						p.setCityId(street.getCityId());
						p.setCityName(street.getCityName());
						p.setDistrictId(street.getDistrictId());
						p.setDistrictName(street.getDistrictName());
						p.setNeighborhoodName(street.getNeighborhoodName());
						p.setStreetId(street.getId());
						p.setStreetName(street.getName());
					}
				});
				
			});
		}
		return SlicedResult.<PostDto>builder().content(posts).isLast(slice.isLast()).build();
	}

	@Override
	public PostDistrictTagEntity create(PostDistrictTagEntity postDistrictTagEntity) {
		return postDistrictTagRepository.save(postDistrictTagEntity);
	}

}
