package com.sushrut.webArticle.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.filter.OncePerRequestFilter;

import com.sushrut.webArticle.Repositories.UserRepository;
import com.sushrut.webArticle.Services.RoleService;
import com.sushrut.webArticle.Services.UserService;
import com.sushrut.webArticle.entity.JwtLoginRequest;
import com.sushrut.webArticle.entity.JwtRequest;
import com.sushrut.webArticle.entity.JwtResponse;
import com.sushrut.webArticle.entity.MessageResponce;
import com.sushrut.webArticle.entity.Role;
import com.sushrut.webArticle.entity.User;
import com.sushrut.webArticle.security.JwtHelper;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtHelper jwtHelper;
	
	private Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtLoginRequest request) {

		logger.info("request details " + request.getEmail() + " " + request.getPassword());
        this.doAuthenticate(request.getEmail(), request.getPassword());

        logger.info("authenticated");

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        logger.info("user details " + userDetails.getUsername() + " " + userDetails.getPassword());
        String token = this.jwtHelper.generateToken(userDetails);
        logger.info("token details " + token);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        logger.info("response details " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@PostMapping("/signUp")
	public ResponseEntity<MessageResponce> signUp(@RequestBody JwtRequest request){
		
		logger.info("request details " + request.getEmail() + " " + request.getPassword());
		if(userService.existsByEmail(request.getEmail())) {
			return new ResponseEntity<>(new MessageResponce("Email already exist"), HttpStatus.BAD_REQUEST);
		}
		
		logger.info("request details " + request.getEmail() + " " + request.getPassword());
		User user = new User();
		user.setEmail(request.getEmail());
		user.setFirst_name(request.getFirstName());
		user.setLast_name(request.getLastName());
		user.setPassword(encoder.encode(request.getPassword()));
		
		Role role = roleService.findById(AppConstants.NORMAL_U).get();
		user.getRoles().add(role);
		
		userService.createUser(user);
		
	//	logger.info("request details " + request.getEmail() + " " + request.getPassword());
	//	userRepo.save(user);
		logger.info("request details " + request.getEmail() + " " + request.getPassword());
		return ResponseEntity.ok(new MessageResponce("User registered successfully!"));
		
	}
	
    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
