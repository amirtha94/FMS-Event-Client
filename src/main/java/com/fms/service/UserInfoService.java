package com.fms.service;

import com.fms.model.User;

import reactor.core.publisher.Mono;

public interface UserInfoService {

	Mono<User> insertUserInfo(User user);
	
	Mono<User> fetchUserData(String username);

	Mono<User> findUserById(String id);

	Mono<User> saveExistingUser(User user);
}
