package com.komsum.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.komsum.user.entity.KeycloakUserAttributeEntity;
import com.komsum.user.repository.KeycloakUserAttributeRepository;
import com.komsum.user.service.KeycloakUserAttributeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KeycloakUserAttributeServiceImpl implements KeycloakUserAttributeService {

	private final KeycloakUserAttributeRepository userAttributeRepository;
	
	@Override
	public List<KeycloakUserAttributeEntity> findByNameAndUserIdIn(String name, List<String> ids) {
		return userAttributeRepository.findByNameAndUserIdIn(name, ids);
	}

}
