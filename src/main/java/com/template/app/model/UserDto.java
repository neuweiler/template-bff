package com.template.app.model;

import com.template.app.dao.Owner;
import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.Data;

import java.util.List;

@Data
@JGlobalMap
public class UserDto {
	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private String eMail;
	private String phone;
	private List<RoleDto> roles;
	private Owner owner;
}
