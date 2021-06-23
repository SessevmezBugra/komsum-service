package com.komsum.feed.web;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostFeedEntity;
import com.komsum.feed.model.SlicedResult;
import com.komsum.feed.service.PostFeedService;
import com.komsum.feed.util.constant.ApiPath;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiPath.PostFeedCtrl.CTRL)
@RequiredArgsConstructor
public class PostFeedController {

    private final PostFeedService postFeedService;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<Object> createPost(@RequestBody PostDto postDto){
        postFeedService.create(postDto);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method= RequestMethod.PUT)
    public ResponseEntity<PostFeedEntity> updatePost(@RequestBody PostFeedEntity postFeedEntity){
        return ResponseEntity.ok(postFeedService.update(postFeedEntity));
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<SlicedResult<PostDto>> getPostsByStreetIdAndTagIdIn(@RequestParam(value="streetId", required=true) Integer streetId,
                                                                     @RequestParam(value="tagIds", required=true) List<Integer> tagIds,
                                                                     @RequestParam(value = "pageNumber",required = false) Optional<Integer> pageNumber){
        return ResponseEntity.ok(postFeedService.findByStreetIdAndTagIdInAndPage(streetId, tagIds, pageNumber));
    }
    
    @RequestMapping(value="/all", method=RequestMethod.GET)
    public ResponseEntity<Iterable<PostFeedEntity>> getAllPosts(){
        return ResponseEntity.ok(postFeedService.findAll());
    }

}
