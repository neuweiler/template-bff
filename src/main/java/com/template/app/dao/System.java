package com.template.app.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class System {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private LocalDate start;

	private LocalDate end;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	Owner owner;

	@ManyToOne
	private SystemType systemType;

//	@JsonManagedReference
//	@OneToMany(mappedBy = "system", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Production> production;
}
