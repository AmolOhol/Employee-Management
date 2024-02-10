package com.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.User;
import com.employee.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public String getUserRoleByUsername(String username) {
		
		User user = userRepo.findByUsername(username);
		
		String role = null;
		
		if(user != null)
		{
			role=user.getRole();
		}
		
		return role;
	}
	
}
