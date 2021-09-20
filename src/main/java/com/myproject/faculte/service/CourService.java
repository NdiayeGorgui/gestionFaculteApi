package com.myproject.faculte.service;

import java.util.List;
import java.util.Optional;

import com.myproject.faculte.model.Cour;

public interface CourService {

	Cour saveCour(Cour c);

	Cour updateCour(Cour c);

	void deleteCour(Cour c);

	void deleteCourById(Long id);

	Optional<Cour> getCour(Long id);

	List<Cour> getAllCours();

	
	List<Cour> findByLibelleContains(String libelle);

	List<Cour> findByEnseignantId(Long id);

	List<Cour> findByFormationsId(Long id);

	List<Cour> findByFormationsNomFormation(String nom);
}
