package com.sushrut.webArticle.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushrut.webArticle.Repositories.RoleRepository;
import com.sushrut.webArticle.entity.Role;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepo;
	
	public Optional<Role> findById(String normalU) {
		// TODO Auto-generated method stub
		return roleRepo.findById(normalU);
	}

}
