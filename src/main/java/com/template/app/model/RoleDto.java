package com.template.app.model;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.Data;

@Data
@JGlobalMap
public class RoleDto {
	private String name;
	private String description;
}
