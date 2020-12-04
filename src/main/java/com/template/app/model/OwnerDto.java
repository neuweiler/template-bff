package com.template.app.model;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.Data;

@Data
@JGlobalMap
public class OwnerDto {
	private int id;
	private String name;
	private boolean active;
//	private List<SystemDto> systems;
//	private List<UserDto> users;
}
