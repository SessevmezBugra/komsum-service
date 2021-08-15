package com.komsum.user.web;

import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.komsum.user.config.CurrentUserProvider;
import com.komsum.user.entity.UserEntity;
import com.komsum.user.service.UserService;
import com.komsum.user.util.constant.ApiPaths;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@RequiredArgsConstructor
public class UserController implements SecuredRestController {
	
	private final UserService userService;
	private final CurrentUserProvider currentUserProvider;
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserEntity> getUserByUserId(@PathVariable String userId){
        return ResponseEntity.ok(userService.findByUserId(userId));
    }
	
	@RequestMapping(value = "/profile-picture/{pictureId}", method = RequestMethod.PUT)
    public ResponseEntity<UserEntity> updateUserProfilePictureByUserId(@PathVariable String pictureId){
		UserEntity userEntity = userService.findByUserId(currentUserProvider.getCurrentUser().getUserId());
		if(ObjectUtils.isEmpty(userEntity)) {
			userEntity = new UserEntity();
			userEntity.setUserId(currentUserProvider.getCurrentUser().getUserId());
			userEntity.setUsername(currentUserProvider.getCurrentUser().getUsername());
		}
		userEntity.setProfilePictureId(pictureId);
		userService.update(userEntity);
        return ResponseEntity.ok(userEntity);
    }

}
