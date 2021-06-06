package com.komsum.feed.web;

import com.komsum.feed.dto.PostDto;
import com.komsum.feed.entity.PostFeedEntity;
import com.komsum.feed.service.PostFeedService;
import com.komsum.feed.util.constant.ApiPath;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPath.PostFeedCtrl.CTRL)
@RequiredArgsConstructor
public class PostFeedController {

    private final PostFeedService postFeedService;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity createPost(@RequestBody PostDto postDto){
        postFeedService.create(postDto);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method= RequestMethod.PUT)
    public ResponseEntity<PostFeedEntity> updatePost(@RequestBody PostFeedEntity postFeedEntity){
        return ResponseEntity.ok(postFeedService.update(postFeedEntity));
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity getPostsByStreetIdAndTagIdIn(@RequestParam(value="streetId", required=true) Integer streetId,
                                                      @RequestParam(value="tagIds", required=true) List<Integer> tagIds){
        return ResponseEntity.ok(postFeedService.findByStreetIdAndTagIdIn(streetId, tagIds));
    }

}
