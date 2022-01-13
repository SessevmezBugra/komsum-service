package com.komsum.feed.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.komsum.feed.config.ClientConfiguration;
import com.komsum.feed.dto.KeycloakUserDto;

@FeignClient(name = "user-service", configuration = {ClientConfiguration.class})
public interface UserServiceClient {

	@RequestMapping(value = "/user/username/in", method= RequestMethod.POST)
    public ResponseEntity<List<KeycloakUserDto>> getUsersByUsernameIn(@RequestBody List<String> usernames);
	
}
