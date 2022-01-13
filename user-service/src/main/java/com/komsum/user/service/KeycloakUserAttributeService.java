package com.komsum.user.service;

import java.util.List;

import com.komsum.user.entity.KeycloakUserAttributeEntity;

public interface KeycloakUserAttributeService {

	List<KeycloakUserAttributeEntity> findByNameAndUserIdIn(String name, List<String> ids);
}
