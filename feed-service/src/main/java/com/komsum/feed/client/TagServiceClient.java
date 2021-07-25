package com.komsum.feed.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.komsum.feed.dto.TagDto;

@FeignClient(name = "tag-service")
public interface TagServiceClient {

	@RequestMapping(value = "/tag/in", method= RequestMethod.POST)
    public ResponseEntity<List<TagDto>> getTagsByIdIn(@RequestBody List<String> ids);
}
