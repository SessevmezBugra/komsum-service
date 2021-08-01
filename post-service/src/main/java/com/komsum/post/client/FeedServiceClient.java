package com.komsum.post.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.komsum.post.config.ClientConfiguration;
import com.komsum.post.dto.PostDto;

@FeignClient(name = "feed-service", configuration = {ClientConfiguration.class})
public interface FeedServiceClient {
        @RequestMapping(value = "/feed/post", method= RequestMethod.POST)
        public ResponseEntity<Object> createPostFeed(@RequestBody PostDto postDto);

}
