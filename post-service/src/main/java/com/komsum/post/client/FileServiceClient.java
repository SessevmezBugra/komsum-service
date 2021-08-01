package com.komsum.post.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.komsum.post.config.ClientConfiguration;
import com.komsum.post.config.FeignSupportConfig;

@FeignClient(name = "file-service", configuration = {FeignSupportConfig.class, ClientConfiguration.class})
public interface FileServiceClient {

	@RequestMapping(value = "/file", method= RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createFile(@RequestPart("file") MultipartFile file);
}
