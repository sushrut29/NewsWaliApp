package com.sushrut.webArticle.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_details")
public class User {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="id")
	private String id;
	
	@Size(min=2, max=50, message = "First name must be between 2 to 50 characters" )
	@NotBlank
	@Column(name="first_name")
	private String first_name;
	
	@Size(max=50)
	@NotBlank
	@Column(name="last_name")
	private String last_name;
	
	@Email
	@Size(max=50)
	@NotBlank(message="Email Id can't be blank, enter a valid email")
	@Column(name="email")
	private String email;
	
	@Size(min=4, max=10, message = "Length should be between 4 to 10 characters")
	@NotBlank
	private String password;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER , mappedBy = "user")
	private List<News> news = new ArrayList<>(); 
	
	@ManyToMany
	@JoinTable(name = "user_role",
	joinColumns = {@JoinColumn(name="user_id")},
	inverseJoinColumns = {@JoinColumn(name="role_id")})
	private Set<Role> roles = new HashSet<>(); 
	
	
	
}
