package com.myproject.faculte.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
		
		 @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
		    @JoinTable(name = "cours_formations",
		            joinColumns = {
		                    @JoinColumn(name = "formation_id", referencedColumnName = "id",
		                            nullable = false, updatable = false)},
		            inverseJoinColumns = {
		                    @JoinColumn(name = "cours_id", referencedColumnName = "id",
		                            nullable = false, updatable = false)})
		 @JsonIgnore
		 private Set<Cour> cours = new HashSet<>();
}
