package com.template.app.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
public class Production {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private BigDecimal value;

	private int year;

	private int month;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	private System system;

	private Instant createdAt;

	private String createdBy;
}
