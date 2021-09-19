package com.myproject.faculte.service;

import java.util.List;
import java.util.Optional;

import com.myproject.faculte.model.Cour;
import com.myproject.faculte.model.Enseignant;

public interface EnseignantService {
	Enseignant saveEnseignant(Enseignant e);

	Enseignant updateEnseignant(Enseignant e);

	void deleteEnseignant(Enseignant e);

	void deleteEnseignantById(Long id);

	Optional<Enseignant> getEnseignant(Long id);

	List<Enseignant> getAllEnseignants();
	
	
	List<Cour> findByEnseignantCours(Enseignant enseignant);
	List<Enseignant> findByEnseignantStatut( String statut);
	List<Enseignant> findByGroupesId(Long id);
	List<Enseignant> findByFirstNameLastNameStatut( String value);
}
