package com.komsum.user.service;

import java.util.List;

import org.keycloak.representations.idm.UserRepresentation;

import com.komsum.user.entity.KeycloakUserEntity;

public interface KeycloakUserService {
	void createUserAttribute(String userId, String key, String value);

	UserRepresentation findByUserId(String userId);
	
	List<KeycloakUserEntity> findByUserIdIn(List<String> userIds);

	List<KeycloakUserEntity> findByUsernameIn(List<String> usernames);

	KeycloakUserEntity findByUsername(String username);
}
