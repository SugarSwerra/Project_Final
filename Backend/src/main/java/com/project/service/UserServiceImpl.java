package com.project.service;


import java.util.Optional;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.dao.UserDao;
import com.project.dto.UserLoginRequestDto;
import com.project.dto.UserSignUpDto;
import com.project.model.Role;
import com.project.model.User;




@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	
	
	@Override
	public void userSignUp(UserSignUpDto userSignUpDto) {
		String sha256hex = DigestUtils.sha256Hex(userSignUpDto.getPassword());
		User user = new User();
		Role role = new Role();
		role.setIdRole(3);
		
		user.setName(userSignUpDto.getName());
		user.setLastname(userSignUpDto.getLastname());
		user.setEmail(userSignUpDto.getEmail());
		user.setCity(userSignUpDto.getCity());
		user.setPassword(sha256hex);
		user.setIdRole_fk(role);
		
		userDao.save(user);
		
	}
	
	@Override
	public User getUserByEmail(String email) {
		Optional<User> utenteOptional = userDao.findByEmail(email);
		
		if(utenteOptional.isEmpty()) {
			return new User();
		}
		
		return utenteOptional.get();
	}

	
	
	@Override
	public boolean login(UserLoginRequestDto userLoginRequestDto) {
		String email = userLoginRequestDto.getEmail();
		String password = userLoginRequestDto.getPassword();
		
		User user = getUserByEmail(email);
		
		if (user == null) {
			return false;
		}
		
		String passwordCrpitata = DigestUtils.sha256Hex(password);
		boolean passwordMatch = passwordCrpitata.equals(user.getPassword());
		
		return passwordMatch;
	}
	
	@Override
	public boolean existsByEmail(String email) {
		
		return userDao.existsByEmail(email);
	}

	

}
