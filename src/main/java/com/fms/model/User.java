package com.fms.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User  {

	@Id
	private String employeeId;
	
	private String email;
	
	private String mobileNo;
	
	private String empName;
	
	private String username;
	
	private String password;

	private boolean enabled ;
	
	private String role;
	
	@JsonIgnore
	@JsonInclude(Include.NON_EMPTY)
	private List<Role> roles;

	
}
