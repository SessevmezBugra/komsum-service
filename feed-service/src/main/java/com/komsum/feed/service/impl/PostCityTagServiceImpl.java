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
public class PostCityTagServiceImpl implements PostCityTagService {

	private final PostCityTagRepository postCityTagRepository;
	private final PostServiceClient postServiceClient;
	private final PostFileService postFileService;
	private final TagServiceClient tagServiceClient;
	private final GeographyServiceClient geographyServiceClient;

	@Override
	public SlicedResult<PostDto> findByCityId(Integer cityId, Integer page) {
		Slice<PostCityTagEntity> slice = postCityTagRepository.findByCityId(cityId,
				CassandraPageRequest.first(AppConstants.PAGE_SIZE));
		int currpage = 0;
		while (slice.hasNext() && currpage < page) {
			slice = postCityTagRepository.findByCityId(cityId, slice.nextPageable());
			currpage++;
		}
		List<PostCityTagEntity> desiredPage = slice.getContent();
		List<String> postIds = desiredPage.stream().map(PostCityTagEntity::getPostId).distinct()
				.collect(Collectors.toList());
		List<String> foundTagIds = desiredPage.stream().map(PostCityTagEntity::getTagId).distinct()
				.collect(Collectors.toList());
		List<Integer> streetIds = desiredPage.stream().map(PostCityTagEntity::getStreetId).distinct()
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
	public SlicedResult<PostDto> findByCityIdAndTagIdIn(Integer cityId, Iterable<String> tagIds, Integer page) {
		Slice<PostCityTagEntity> slice = postCityTagRepository.findByCityIdAndTagIdIn(cityId, tagIds,
				CassandraPageRequest.first(AppConstants.PAGE_SIZE));
		int currpage = 0;
		while (slice.hasNext() && currpage < page) {
			slice = postCityTagRepository.findByCityIdAndTagIdIn(cityId, tagIds, slice.nextPageable());
			currpage++;
		}
		List<PostCityTagEntity> desiredPage = slice.getContent();
		List<String> postIds = desiredPage.stream().map(PostCityTagEntity::getPostId).distinct()
				.collect(Collectors.toList());
		List<String> foundTagIds = desiredPage.stream().map(PostCityTagEntity::getTagId).distinct()
				.collect(Collectors.toList());
		List<Integer> streetIds = desiredPage.stream().map(PostCityTagEntity::getStreetId).distinct()
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
						Optional<StreetDto> street = streets.stream().filter(st -> st.getId() == s.getStreetId()).findFirst();
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
	public PostCityTagEntity create(PostCityTagEntity postCityTagEntity) {
		return postCityTagRepository.save(postCityTagEntity);
	}

}
