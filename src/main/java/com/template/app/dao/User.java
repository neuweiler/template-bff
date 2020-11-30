package com.template.app.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String firstName;

	private String lastName;

	private String userName;

	private String eMail;

	private String phone;

	@ManyToMany
	@JoinTable(name = "user_role")
	private List<Role> roles;

	@JsonIgnore
	private String password;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	Owner owner;
}
