package com.template.app.model;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.Data;

@Data
@JGlobalMap
public class SystemTypeDto {
	private String code;
	private String name;
}
