package com.myproject.faculte.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cours")
public class Cour {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name="code_cours")
	 private Long id;
	 private String libelle;
	 private double nbeHeure;
	 
	 @ManyToOne
		private Enseignant enseignant;
	 @ManyToOne
		private TypeCour typecour;

}
