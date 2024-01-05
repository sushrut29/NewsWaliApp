package com.sushrut.webArticle.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User u = userService.createUser(user);
		return new ResponseEntity<>(u, HttpStatus.CREATED);
	}
	
	// get all users
	
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers()) ;
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable String userId) {
		return ResponseEntity.ok(userService.getUserById(userId)) ;
	}
	
	@GetMapping("/user/{email}")
	public ResponseEntity<Optional<User>> getUserByEmail(@PathVariable String email) {
		return ResponseEntity.ok(userService.getUserByEmail(email)) ;
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User u , @PathVariable String userId) {
		User updatedUser= userService.updateUser(u, userId);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity deleteUser(@PathVariable String userId) {
	    try {
		userService.deleteUser(userId);
	    }
	    catch(Exception e){
	      e.printStackTrace();
	    }
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}
