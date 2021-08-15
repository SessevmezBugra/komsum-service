package com.komsum.user.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.komsum.user.entity.UserEntity;
import com.komsum.user.repository.UserRepository;
import com.komsum.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;

	@Override
	public UserEntity create(UserEntity userEntity) {
		return userRepository.save(userEntity);
	}

	@Override
	public UserEntity update(UserEntity userEntity) {
		return userRepository.save(userEntity);
	}

	@Override
	public Optional<UserEntity> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public UserEntity findByUserId(String userId) {
		return userRepository.findByUserId(userId);
	}

}
