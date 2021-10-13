package com.myproject.faculte.service;

import java.util.List;
import java.util.Optional;

import com.myproject.faculte.model.Formation;




public interface FormationService {
	
	Formation saveFormation(Formation f);

	Formation updateFormation(Formation f);

	void deleteFormation(Formation f);

	void deleteFormationById(Long id);

	Optional<Formation>  getFormation(Long id);

	List<Formation> getAllFormations();
	
	
	List<Formation> findByCoursId(Long id);
	
	List<Formation> findByCoursLibelle(String libelle);
	List<Formation >findByNomFormationContains(String nom);
	

}
