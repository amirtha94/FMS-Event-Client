package com.fms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fms.model.User;
import com.fms.repository.UserInfoRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	PBKDF2Encoder encoder;

	@Autowired
	UserInfoRepository userInfoRepo;

	@Override
	public Mono<User> insertUserInfo(User user) {
		try {
			user.setPassword(encoder.encode(user.getPassword()));
			if (StringUtils.isEmpty(user.getRole())) {
				user.setRole("participant");
			}
			user.setEnabled(true);
			return userInfoRepo.save(user);
		} catch (Exception e) {
			log.error("Exception occured while saving data userinfo {}", e.getMessage());
			return null;
		}
	}

	@Override
	public Mono<User> fetchUserData(String username) {
		return userInfoRepo.findByusername(username);
	}

	@Override
	public Mono<User> findUserById(String id) {
		return userInfoRepo.findById(id);
	}

	@Override
	public Mono<User> saveExistingUser(User user) {
		try {
			return userInfoRepo.save(user);
		} catch (Exception e) {
			log.error("Exception occured while saveExistingUser {}", e.getMessage());
			return null;
		}
	}

}
