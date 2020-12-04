package com.template.app.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	private boolean active;

//	@JsonManagedReference
//	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
//	@Column(name = "system")
//	private List<System> systems;

//	@JsonManagedReference
//	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
//	@Column(name = "user")
//	private List<User> users;
}
