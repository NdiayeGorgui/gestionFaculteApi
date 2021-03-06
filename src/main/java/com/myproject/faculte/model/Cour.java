package com.myproject.faculte.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "cours")

public class Cour {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name="code_cours")
	 private Long id;
	 @NotBlank
	 @Column(unique = true)
	 private String libelle;
	 private double nbeHeure;
	 
	 @ManyToOne
		private Enseignant enseignant;
	
	 @ManyToOne
		private TypeCour typecour;
	
	 
	 @JsonIgnore
	 @ManyToMany(mappedBy = "cours", fetch = FetchType.LAZY)
	 private List<Formation> formations= new ArrayList<>();
}
