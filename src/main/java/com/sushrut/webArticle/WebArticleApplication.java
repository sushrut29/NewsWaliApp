package com.sushrut.webArticle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sushrut.webArticle.Repositories.RoleRepository;
import com.sushrut.webArticle.configs.AppConstants;
import com.sushrut.webArticle.entity.Role;


@SpringBootApplication
public class WebArticleApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(WebArticleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		try {
			Role r1 = new Role();
			r1.setId(AppConstants.NORMAL_U);
			r1.setName("NORMAL_USER");
			
			Role r2 = new Role();
			r2.setId(AppConstants.ADMIN);
			r2.setName("ADMIN_");
			
			Role r3 = new Role();
			r3.setId(AppConstants.EDITOR);
			r3.setName("EDITOR_");
			
			List<Role> roles = List.of(r1,r2,r3);
			
			roleRepository.saveAll(roles);
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
