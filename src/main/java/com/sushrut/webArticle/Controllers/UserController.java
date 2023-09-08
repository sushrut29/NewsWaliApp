package com.sushrut.webArticle.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sushrut.webArticle.Services.UserService;
import com.sushrut.webArticle.entity.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//create user
	
	@PostMapping("/")
	public User createUser(@Valid @RequestBody User user) {
		User u = userService.createUser(user);
		return u;
	}
	
	// get all users
	
	@GetMapping("/")
	public List<User> getAllUsers(){
		List<User> users = userService.getAllUsers();
		return users;
	}
	

}
