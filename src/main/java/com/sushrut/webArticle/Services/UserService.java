package com.sushrut.webArticle.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sushrut.webArticle.Exception.ResourceNotFoundException;
import com.sushrut.webArticle.Repositories.UserRepository;
import com.sushrut.webArticle.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	public User createUser(User u) {
	//	u.setPassword(passwordEncoder.encode(u.getPassword()));
		return userRepository.save(u);
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User getUserById(String id) {
		User u = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
		return u;
	}
	
	public User updateUser(User user, String id) 
	{
		User u = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
		u.setFirst_name(user.getFirst_name());
		u.setLast_name(u.getLast_name());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		
		User updatedUser = userRepository.save(u);
		return updatedUser;
	}
	
	public void deleteUser(String id) {
		User u = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
		userRepository.delete(u);
	}

	public Optional<User> getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.existsByEmail(email);
	}

}
