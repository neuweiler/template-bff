package com.template.app.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class SystemType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String code;

	private String name;
}
