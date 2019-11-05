package com.example.dao;

import java.util.ArrayList;

//import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.entity.User;

public class DT_user {
	
	private static RestTemplate restTemplate = new RestTemplate();
	private String ENDPOINT_URL = "http://localhost:3000/usuarios";
	
	public User[] getUsers() {
		
		HttpHeaders headers = new HttpHeaders();
		ArrayList<User> lisU = new ArrayList<User>();
		HttpEntity<ArrayList <User>> users = new HttpEntity <ArrayList <User>>(headers);
		
		
		ResponseEntity<User[]> result = restTemplate.getForEntity(ENDPOINT_URL, User[].class);
		User[] userList = result.getBody();
		
		
		
		return userList;
	}
}
