package com.komsum.post.client;

import com.komsum.post.dto.PostDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "feed-service")
public interface FeedServiceClient {
        @RequestMapping(value = "/feed/post", method= RequestMethod.POST)
        public ResponseEntity createPostFeed(@RequestBody PostDto postDto);

}
