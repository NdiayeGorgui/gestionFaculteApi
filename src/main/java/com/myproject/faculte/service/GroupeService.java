package com.myproject.faculte.service;

import java.util.List;
import java.util.Optional;

import com.myproject.faculte.model.Groupe;



public interface GroupeService {
	
	Groupe saveGroupe(Groupe g);

	Groupe updateGroupe(Groupe g);

	void deleteGroupe(Groupe g);

	void deleteGroupeById(Long id);

	Optional<Groupe>  getGroupe(Long id);

	List<Groupe> getAllGroupes();
	
	
	List<Groupe> findByFormationId(Long id);
	List<Groupe> findByFormationNomFormation(String nom);
	List<Groupe> findByEnseignantsId(Long id);
}
