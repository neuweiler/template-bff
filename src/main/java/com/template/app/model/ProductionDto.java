package com.template.app.model;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@JGlobalMap
public class ProductionDto {
	private BigDecimal value;
	private int year;
	private int month;
	private SystemDto system;
	private Instant createdAt;
	private String createdBy;
}
