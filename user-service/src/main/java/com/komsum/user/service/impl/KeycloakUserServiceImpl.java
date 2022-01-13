package com.komsum.user.service.impl;

import java.util.List;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.komsum.user.dao.KeycloakUserDao;
import com.komsum.user.entity.KeycloakUserEntity;
import com.komsum.user.repository.KeycloakUserRepository;
import com.komsum.user.service.KeycloakUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KeycloakUserServiceImpl implements KeycloakUserService {
	
	private final KeycloakUserDao keycloakUserDao;
	private final KeycloakUserRepository keycloakUserRepository;
	private final Environment environment;

	@Override
	public void createUserAttribute(String userId, String key, String value) {
		keycloakUserDao.createAttribute(userId, key, value);
	}

	@Override
	public UserRepresentation findByUserId(String userId) {
		return keycloakUserDao.findByUserId(userId);
	}

	@Override
	public List<KeycloakUserEntity> findByUserIdIn(List<String> ids) {
		return keycloakUserRepository.findByIdInAndRealmId(ids, environment.getProperty("keycloak.realm"));
	}

	@Override
	public List<KeycloakUserEntity> findByUsernameIn(List<String> usernames) {
		return keycloakUserRepository.findByUsernameInAndRealmId(usernames, environment.getProperty("keycloak.realm"));
	}

	@Override
	public KeycloakUserEntity findByUsername(String username) {
		return keycloakUserRepository.findByUsernameAndRealmId(username, environment.getProperty("keycloak.realm"));
	}

}
