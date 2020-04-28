package com.fms.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.fms.model.User;

import reactor.core.publisher.Mono;

public interface UserInfoRepository extends ReactiveMongoRepository<User, String>{

	Mono<User> findByusername(String username);
	
	@Query("update User  set role =?1 where employeeId=?2")
	Mono<Integer> updateUserRole(String role,String employeeId);
}
