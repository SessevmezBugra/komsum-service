package com.komsum.user.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.komsum.user.config.CurrentUserProvider;
import com.komsum.user.entity.KeycloakUserEntity;
import com.komsum.user.service.KeycloakUserService;
import com.komsum.user.util.constant.ApiPaths;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@RequiredArgsConstructor
public class KeycloakUserController implements SecuredRestController {
	
	private final CurrentUserProvider currentUserProvider;
	private final KeycloakUserService keycloakUserService;
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<KeycloakUserEntity> getUserByUserId(@PathVariable String username){
        return ResponseEntity.ok(keycloakUserService.findByUsername(username));
    }
	
	@RequestMapping(value = "/user-id/in", method = RequestMethod.POST)
    public ResponseEntity<List<KeycloakUserEntity>> getUsersByUserIdIn(@RequestBody List<String> userIds){
        return ResponseEntity.ok(keycloakUserService.findByUserIdIn(userIds));
    }
	
	@RequestMapping(value = "/username/in", method = RequestMethod.POST)
    public ResponseEntity<List<KeycloakUserEntity>> getUsersByUsernameIn(@RequestBody List<String> usernames){
        return ResponseEntity.ok(keycloakUserService.findByUsernameIn(usernames));
    }
	
	@RequestMapping(value = "/profile-picture/{pictureId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUserProfilePictureByUserId(@PathVariable String pictureId){
		keycloakUserService.createUserAttribute(currentUserProvider.getCurrentUser().getUserId(), "pictureId", pictureId);
        return ResponseEntity.ok().build();
    }
	

}
