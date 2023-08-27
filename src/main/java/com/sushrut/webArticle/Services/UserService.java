package com.sushrut.webArticle.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushrut.webArticle.Repositories.UserRepository;
import com.sushrut.webArticle.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User u) {
		return userRepository.save(u);
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}

}
