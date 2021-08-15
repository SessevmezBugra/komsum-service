package com.komsum.file.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.komsum.file.config.ClientConfiguration;
import com.komsum.file.dto.UserDto;

@FeignClient(name = "user-service", configuration = {ClientConfiguration.class})
public interface UserServiceClient {
	@RequestMapping(value = "/user/profile-picture/{pictureId}", method= RequestMethod.PUT)
    public ResponseEntity<UserDto> updateUserProfilePicture(@PathVariable String pictureId);
	
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getUserByUserId(@PathVariable String userId);
}
