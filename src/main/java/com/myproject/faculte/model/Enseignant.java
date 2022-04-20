package com.myproject.faculte.model;


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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enseignants")
public class Enseignant  {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name="code_enseignant")
	 private Long id;
	 
	 @Length(min = 3, max = 20, message = "Nombre de caracteres entre 3 et 20")
	 @Column(name="first_name")
	 private String firstName;
	 @Length(min = 3, max = 20, message = "Nombre de caracteres entre 3 et 20")
	 @Column(name="last_name")
	 private String lastName;
	 private String adress;
	 
	@Email(message = "L'adresse Email saisie est invalide")
	@Column(unique = true)
	 private String mail;
	 @Length(min = 10, max = 25, message = "Nombre de caracteres entre 10 et 25")
	 private String telephone;
	 private String statut;
	
	 
	 @JsonIgnore
		@OneToMany(mappedBy = "enseignant")
		private List<Cour> cours;
	 
	 @JsonIgnore
	 @ManyToMany(mappedBy = "enseignants", fetch = FetchType.EAGER)
	 private List<Groupe> groupes = new ArrayList<>();


		


	
	 
	 
	 
}
