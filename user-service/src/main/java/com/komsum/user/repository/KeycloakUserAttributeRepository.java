package com.komsum.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.komsum.user.entity.KeycloakUserAttributeEntity;

public interface KeycloakUserAttributeRepository extends JpaRepository<KeycloakUserAttributeEntity, String>{
	
	List<KeycloakUserAttributeEntity> findByNameAndUserIdIn(String name, List<String> ids);
	
}
