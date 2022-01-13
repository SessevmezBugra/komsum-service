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
import com.komsum.feed.client.UserServiceClient;
import com.komsum.feed.dto.KeycloakUserAttributeDto;
import com.komsum.feed.dto.KeycloakUserDto;
import com.komsum.feed.dto.PostDto;
import com.komsum.feed.dto.StreetDto;
import com.komsum.feed.dto.TagDto;
import com.komsum.feed.entity.PostFileEntity;
import com.komsum.feed.entity.PostStreetTagEntity;
import com.komsum.feed.entity.PostTagEntity;
import com.komsum.feed.model.SlicedResult;
import com.komsum.feed.repository.PostTagRepository;
import com.komsum.feed.service.PostFileService;
import com.komsum.feed.service.PostTagService;
import com.komsum.feed.util.constant.AppConstants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostTagServiceImpl implements PostTagService{
	
	private final PostTagRepository postTagRepository;
	private final PostServiceClient postServiceClient;
	private final PostFileService postFileService;
	private final TagServiceClient tagServiceClient;
	private final GeographyServiceClient geographyServiceClient;
	private final UserServiceClient userServiceClient;

	@Override//test
	public SlicedResult<PostDto> findByTagIdIn(Iterable<String> tagIds, Integer page) {
		Slice<PostStreetTagEntity> slice = postTagRepository.findByTagIdIn(tagIds, CassandraPageRequest.first(AppConstants.PAGE_SIZE));
		int currpage = 0;
		while (slice.hasNext() && currpage < page) {
			slice = postTagRepository.findByTagIdIn(tagIds, slice.nextPageable());
			currpage++;
		}
		List<PostStreetTagEntity> desiredPage = slice.getContent();
		List<String> postIds = desiredPage.stream().map(PostStreetTagEntity::getPostId).distinct()
				.collect(Collectors.toList());
		List<String> foundTagIds = desiredPage.stream().map(PostStreetTagEntity::getTagId).distinct()
				.collect(Collectors.toList());
		List<Integer> streetIds = desiredPage.stream().map(PostStreetTagEntity::getStreetId).distinct()
				.collect(Collectors.toList());
		List<String> usernames = desiredPage.stream().map(PostStreetTagEntity::getUsername).distinct()
				.collect(Collectors.toList());
		
		List<PostDto> posts = postServiceClient.getPostsByIdIn(postIds).getBody();
		List<TagDto> tags = tagServiceClient.getTagsByIdIn(foundTagIds).getBody();
		List<StreetDto> streets = geographyServiceClient.getStreetsByIdIn(streetIds).getBody();
		List<KeycloakUserDto> users = userServiceClient.getUsersByUsernameIn(usernames).getBody();
		
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
						Optional<KeycloakUserDto> user = users.stream().filter(u -> u.getUsername().equals(s.getUsername())).findFirst();
						user.ifPresent(u -> {
							p.setFirstName(u.getFirstName());
							p.setLastName(u.getLastName());
							Optional<KeycloakUserAttributeDto> attribute = u.getAttributes().stream().filter(pic -> pic.getName().equals("pictureId")).findFirst();
							attribute.ifPresent(att -> {
								p.setProfilePictureId(att.getValue());
							});
						});
					}
				});
				
			});
		}
		return SlicedResult.<PostDto>builder().content(posts).isLast(slice.isLast()).build();
	}

	@Override
	public PostTagEntity create(PostTagEntity postTagEntity) {
		return postTagRepository.save(postTagEntity);
	}

}
