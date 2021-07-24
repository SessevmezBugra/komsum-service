package com.komsum.post.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "file-service")
public interface FileServiceClient {

	@RequestMapping(value = "/file", method= RequestMethod.POST)
    public ResponseEntity<String> createFile(@RequestParam("file") MultipartFile file);
}
