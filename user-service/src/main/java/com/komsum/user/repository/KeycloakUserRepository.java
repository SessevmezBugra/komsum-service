package com.komsum.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.komsum.user.entity.KeycloakUserEntity;

public interface KeycloakUserRepository extends JpaRepository<KeycloakUserEntity, String>{
	List<KeycloakUserEntity> findByIdInAndRealmId(List<String> ids, String realmId);

	List<KeycloakUserEntity> findByUsernameInAndRealmId(List<String> usernames, String realmId);

	KeycloakUserEntity findByUsernameAndRealmId(String username, String realmId);

}
