package com.komsum.user.service;

import java.util.Optional;

import com.komsum.user.entity.UserEntity;

public interface UserService {
	
	UserEntity create(UserEntity userEntity);
	
	UserEntity update(UserEntity userEntity);
	
	Optional<UserEntity> findById(Long id);
	
	UserEntity findByUsername(String username);
	
	UserEntity findByUserId(String userId);

}
