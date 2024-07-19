package com.project.service;


import com.project.dto.UserLoginRequestDto;
import com.project.dto.UserSignUpDto;
import com.project.model.User;


public interface UserService {
	
	void userSignUp(UserSignUpDto userSignUpDto);
	
	User getUserByEmail(String email);
//	
//	UserDto getUserDtoByEmail(String email);
//	
//	List<UserDto> getUsers();
	
	boolean login(UserLoginRequestDto utenteLoginRequestDto);
	
	boolean existsByEmail(String email);
	
}