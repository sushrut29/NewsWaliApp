package com.sushrut.webArticle.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.sushrut.webArticle.security.CustomUserDetailService;

import org.springframework.security.core.userdetails.User;

@Configuration
public class AppConfig {
	
	
	@Autowired
    private CustomUserDetailService customUserDetailService;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());

	}
/*	@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder().
                username("sush")
                .password(passwordEncoder().encode("root")).roles("ADMIN").
                build();
        
        UserDetails user2 = User.builder().
                username("rishi")
                .password(passwordEncoder().encode("root")).roles("ADMIN").
                build();
        
        return new InMemoryUserDetailsManager(user1,user2);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
