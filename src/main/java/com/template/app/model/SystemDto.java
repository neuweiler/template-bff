package com.template.app.model;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.Data;

import java.time.LocalDate;

@Data
@JGlobalMap
public class SystemDto {
	private int id;
	private String name;
	private LocalDate start;
	private LocalDate end;
	private OwnerDto owner;
	private SystemTypeDto systemType;
//	@JMap("production")
//	private List<ProductionDto> productions;
}
