package com.project.controller;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.dto.UserLoginRequestDto;
import com.project.dto.UserLoginResponseDto;
import com.project.dto.UserSignUpDto;
import com.project.model.Role;
import com.project.model.User;
import com.project.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public UserLoginResponseDto issueToken(String email) {
		byte[] secret = "f8932bg09bf302eb0eb08b2f0820h2n0f9h92e0".getBytes();
		Key key = Keys.hmacShaKeyFor(secret);
		
		User userInfo = userService.getUserByEmail(email);
		Map<String, Object> map = new HashMap<>();
		map.put("nome", userInfo.getName());
		map.put("cognome", userInfo.getLastname());
		map.put("email", email);
		
		
		
		map.put("ruolo", 3);
		
		Date creation = new Date();
		Date end = java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(15L));
		String tokenJwts = Jwts.builder()
				.setClaims(map)
				.setIssuer("http://localhost:8080")
				.setIssuedAt(creation)
				.setExpiration(end)
				.signWith(key)
				.compact();
		
		UserLoginResponseDto token = new UserLoginResponseDto();
		
		token.setToken(tokenJwts);
		token.setTokenCreationTime(creation);
		token.setTtl(end);
		
		return token;
	}
	
	@POST
	@Path("/reg")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response userSignUp(@Valid @RequestBody UserSignUpDto userSignupDto) {
		if(!Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}", userSignupDto.getPassword())) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		try {
			if(userService.existsByEmail(userSignupDto.getEmail())) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			
			userService.userSignUp(userSignupDto);
			
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	
			
		}
	


	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
		try {
			if (userService.login(userLoginRequestDto)) {
				return Response.ok(issueToken(userLoginRequestDto.getEmail())).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
}
