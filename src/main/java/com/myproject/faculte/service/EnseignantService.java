package com.myproject.faculte.service;

import java.util.List;
import java.util.Optional;

import com.myproject.faculte.model.Cour;
import com.myproject.faculte.model.Enseignant;
import com.myproject.faculte.model.Groupe;

public interface EnseignantService {
	Enseignant saveEnseignant(Enseignant e);

	Enseignant updateEnseignant(Enseignant e);
	Enseignant findEnseignantByEmail(String mail);

	void deleteEnseignant(Enseignant e);
	void saveEnseignantWihtGroupe(Enseignant enseignant, String numeroGroupe);
	void deleteEnseignantById(Long id);
	void addEnseignantToGroupe(String mail,String numeroGroupe); 
	void deleteEnseignantToGroupe(String mail,String numeroGroupe); 
	Optional<Enseignant> getEnseignant(Long id);

	List<Enseignant> getAllEnseignants();
	
	
	List<Cour> findByEnseignantCours(Enseignant enseignant);
	List<Enseignant> findByEnseignantStatut( String statut);
	List<Enseignant> findByGroupesId(Long id);
	//List<Enseignant> findByGroupesNumeroGroupe(String numeroGroupe);
	List<Enseignant> findByFirstNameLastNameStatut( String value);
	List<Enseignant> findByOrderByLastNameAsc();
}
