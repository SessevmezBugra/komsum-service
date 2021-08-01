package com.komsum.feed.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.komsum.feed.config.ClientConfiguration;
import com.komsum.feed.dto.StreetDto;

@FeignClient(name = "geography-service", configuration = {ClientConfiguration.class})
public interface GeographyServiceClient {
	
	@RequestMapping(value = "/geography/street/in", method= RequestMethod.POST)
    public ResponseEntity<List<StreetDto>> getStreetsByIdIn(@RequestBody List<Integer> ids);

}
