package com.komsum.feed.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.komsum.feed.dto.PostDto;

@FeignClient(name = "post-service")
public interface PostServiceClient {

    @RequestMapping(value = "/post/in", method= RequestMethod.POST)
    public ResponseEntity<List<PostDto>> getPostsByIdIn(@RequestBody List<String> ids);

}
