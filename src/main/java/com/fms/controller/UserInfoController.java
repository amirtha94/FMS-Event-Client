package com.fms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fms.model.User;
import com.fms.service.UserInfoService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserInfoController {

	@Autowired
	UserInfoService userInfoService;

	@PostMapping("/userInfo")
	public Mono<ResponseEntity<String>> saveUserDetails(@RequestBody User user) {
		Mono<User> response = userInfoService.insertUserInfo(user);
		Mono<ResponseEntity<String>> message = response.map(i -> {
			String res = "Failed";
			if (i != null) {
				res = "Data Saved Successfully!";
			}
			return new ResponseEntity<>(res, HttpStatus.OK);
		}).defaultIfEmpty(new ResponseEntity<>("Failed to save", HttpStatus.INTERNAL_SERVER_ERROR));

		return message;
	}

	@GetMapping("/userInfo/{username}")
	public Mono<ResponseEntity<User>> fetchUserInfo(@PathVariable String username) {
		Mono<User> response = userInfoService.fetchUserData(username);

		Mono<ResponseEntity<User>> message = response.map(i -> {
			return new ResponseEntity<>(i, HttpStatus.OK);
		}).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		return message;
	}

	@PutMapping("/userInfo/changerole/{employeeId}/{role}")
	public Mono<ResponseEntity<String>> addUserRole(@PathVariable String employeeId, @PathVariable String role) {
		return userInfoService.findUserById(employeeId).flatMap(user -> {
			user.setRole(role);
			return userInfoService.saveExistingUser(user);
		}).map(i -> new ResponseEntity<>("Data Updated Successfully!", HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>("Updation Failed", HttpStatus.INTERNAL_SERVER_ERROR));
	}

}
