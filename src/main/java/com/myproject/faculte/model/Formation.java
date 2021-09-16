package com.myproject.faculte.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "formations")
public class Formation {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name="code_formation")
	 private Long id;
	 private String nomFormation;
	 private double duree;
	 private String annee;
	 
	 
		@OneToMany(mappedBy = "formation")
		@JsonIgnore
		private List<Groupe> groupes;
}
