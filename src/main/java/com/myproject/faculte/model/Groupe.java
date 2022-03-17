package com.myproject.faculte.model;

import java.io.Serializable;
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
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groupes")
public class Groupe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name="id_groupe")
	private Long id;
	private String numeroGroupe;
	@ManyToOne
	private Formation formation;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "enseignants_groupes", joinColumns = {
			@JoinColumn(name = "groupe_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "enseignant_id", referencedColumnName = "id", nullable = false, updatable = false) })
	@JsonIgnore
	private Set<Enseignant> enseignants = new HashSet<>();
}
