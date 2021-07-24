package com.komsum.feed.web;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostCityTagEntity;
import com.komsum.feed.entity.PostCountryTagEntity;
import com.komsum.feed.entity.PostDistrictTagEntity;
import com.komsum.feed.entity.PostEntity;
import com.komsum.feed.entity.PostFileEntity;
import com.komsum.feed.entity.PostNeighborhoodTagEntity;
import com.komsum.feed.entity.PostStreetTagEntity;
import com.komsum.feed.entity.PostTagEntity;
import com.komsum.feed.model.SlicedResult;
import com.komsum.feed.service.PostCityTagService;
import com.komsum.feed.service.PostCountryTagService;
import com.komsum.feed.service.PostDistrictTagService;
import com.komsum.feed.service.PostFileService;
import com.komsum.feed.service.PostNeighborhoodTagService;
import com.komsum.feed.service.PostService;
import com.komsum.feed.service.PostStreetTagService;
import com.komsum.feed.service.PostTagService;
import com.komsum.feed.util.constant.ApiPath;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiPath.PostFeedCtrl.CTRL)
@RequiredArgsConstructor
public class PostFeedController {

	private final PostService postService;
	private final PostTagService postTagService;
	private final PostCountryTagService postCountryTagService;
	private final PostCityTagService postCityTagService;
	private final PostDistrictTagService postDistrictTagService;
	private final PostNeighborhoodTagService postNeighborhoodTagService;
	private final PostStreetTagService postStreetTagService;
	private final ModelMapper modelMapper;
	private final PostFileService postFileService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> createPost(@RequestBody PostDto postDto) {
		PostCountryTagEntity postCountryTagEntity = modelMapper.map(postDto, PostCountryTagEntity.class);
		PostCityTagEntity postCityTagEntity = modelMapper.map(postDto, PostCityTagEntity.class);
		PostDistrictTagEntity postDistrictTagEntity = modelMapper.map(postDto, PostDistrictTagEntity.class);
		PostNeighborhoodTagEntity postNeighborhoodTagEntity = modelMapper.map(postDto, PostNeighborhoodTagEntity.class);
		PostStreetTagEntity postStreetTagEntity = modelMapper.map(postDto, PostStreetTagEntity.class);
		PostTagEntity postTagEntity = modelMapper.map(postDto, PostTagEntity.class);
		PostEntity postEntity = modelMapper.map(postDto, PostEntity.class);
		PostFileEntity postFileEntity = modelMapper.map(postDto, PostFileEntity.class);
		
		postCountryTagEntity.setPostId(postDto.getId());
		postCityTagEntity.setPostId(postDto.getId());
		postDistrictTagEntity.setPostId(postDto.getId());
		postNeighborhoodTagEntity.setPostId(postDto.getId());
		postStreetTagEntity.setPostId(postDto.getId());
		postTagEntity.setPostId(postDto.getId());
		postEntity.setPostId(postDto.getId());
		postFileEntity.setPostId(postDto.getId());
		
		if(!ObjectUtils.isEmpty(postFileEntity.getFileId())) {
			postFileService.create(postFileEntity);
		}
		postService.create(postEntity);
		postDto.getTagIds().forEach(tagId -> {
			postCountryTagEntity.setTagId(tagId);
			postCityTagEntity.setTagId(tagId);
			postDistrictTagEntity.setTagId(tagId);
			postNeighborhoodTagEntity.setTagId(tagId);
			postStreetTagEntity.setTagId(tagId);
			postTagEntity.setTagId(tagId);

			postCountryTagService.create(postCountryTagEntity);
			postCityTagService.create(postCityTagEntity);
			postDistrictTagService.create(postDistrictTagEntity);
			postNeighborhoodTagService.create(postNeighborhoodTagEntity);
			postStreetTagService.create(postStreetTagEntity);
			postTagService.create(postTagEntity);
		});
		return ResponseEntity.ok().build();
	}

//	@RequestMapping(method = RequestMethod.PUT)
//	public ResponseEntity<PostEntity> updatePost(@RequestBody PostEntity postFeedEntity) {
//		return ResponseEntity.ok(postFeedService.update(postFeedEntity));
//	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<SlicedResult<PostDto>> getPostsByCountryIdAndCityIdAndDistrictIdAndNeighborhoodIdAndStreetIdAndTagIdIn(
			@RequestParam(value = "countryId") Optional<Integer> countryId,
			@RequestParam(value = "cityId") Optional<Integer> cityId,
			@RequestParam(value = "districtId") Optional<Integer> districtId,
			@RequestParam(value = "neighborhoodId") Optional<Integer> neighborhoodId,
			@RequestParam(value = "streetId") Optional<Integer> streetId,
			@RequestParam(value = "tagIds", required = false) List<Integer> tagIds,
			@RequestParam(value = "pageNumber") Optional<Integer> pageNumber) {
		Integer desiredPage = pageNumber.map(dp -> dp).orElse(0);
		if (countryId.isPresent()) {
			if (cityId.isPresent()) {
				if (districtId.isPresent()) {
					if (neighborhoodId.isPresent()) {
						if (streetId.isPresent()) {
							if (ObjectUtils.isEmpty(tagIds)) {
								return ResponseEntity
										.ok(postStreetTagService.findByStreetId(streetId.get(), desiredPage));
							} else {
								return ResponseEntity.ok(postStreetTagService.findByStreetIdAndTagIdIn(streetId.get(),
										tagIds, desiredPage));
							}
						} else {
							if (ObjectUtils.isEmpty(tagIds)) {
								return ResponseEntity.ok(postNeighborhoodTagService
										.findByNeighborhoodId(neighborhoodId.get(), desiredPage));
							} else {
								return ResponseEntity.ok(postNeighborhoodTagService
										.findByNeighborhoodIdAndTagIdIn(neighborhoodId.get(), tagIds, desiredPage));
							}
						}
					} else {
						if (ObjectUtils.isEmpty(tagIds)) {
							return ResponseEntity
									.ok(postDistrictTagService.findByDistrictId(districtId.get(), desiredPage));
						} else {
							return ResponseEntity.ok(postDistrictTagService.findByDistrictIdAndTagIdIn(districtId.get(),
									tagIds, desiredPage));
						}
					}
				} else {
					if (ObjectUtils.isEmpty(tagIds)) {
						return ResponseEntity.ok(postCityTagService.findByCityId(cityId.get(), desiredPage));
					} else {
						return ResponseEntity
								.ok(postCityTagService.findByCityIdAndTagIdIn(cityId.get(), tagIds, desiredPage));
					}
				}
			} else {
				if (ObjectUtils.isEmpty(tagIds)) {
					return ResponseEntity.ok(postCountryTagService.findByCountryId(countryId.get(), desiredPage));
				} else {
					return ResponseEntity
							.ok(postCountryTagService.findByCountryIdAndTagIdIn(countryId.get(), tagIds, desiredPage));
				}
			}
		} else {
			return ResponseEntity.ok(postService.findAll(desiredPage));
		}

	}

//	@RequestMapping(value = "/all", method = RequestMethod.GET)
//	public ResponseEntity<Iterable<PostEntity>> getAllPosts() {
//		return ResponseEntity.ok(postFeedService.findAll());
//	}

}
