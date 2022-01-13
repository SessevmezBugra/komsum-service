package com.komsum.file.client;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.komsum.file.config.ClientConfiguration;

@FeignClient(name = "user-service", configuration = {ClientConfiguration.class})
public interface UserServiceClient {
	@RequestMapping(value = "/user/profile-picture/{pictureId}", method= RequestMethod.PUT)
    public ResponseEntity<Object> updateUserProfilePicture(@PathVariable String pictureId);
	
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserRepresentation> getUserByUserId(@PathVariable String userId);
}
