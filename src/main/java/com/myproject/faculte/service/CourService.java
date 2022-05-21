package com.myproject.faculte.service;

import java.util.List;
import java.util.Optional;

import com.myproject.faculte.model.Cour;

public interface CourService {

	Cour saveCour(Cour c);

	Cour updateCour(Cour c);

	void deleteCour(Cour c);

	void deleteCourById(Long id);
	Cour findCourByLibelle(String libelle);
	void addCoursToFormation(String libelle,String nomFormation); 
	void saveCoursWithFormation(Cour cours,String nomFormation); 
	void deleteCoursToFormation(String libelle, String nomFormation);
	Optional<Cour> getCour(Long id);
	double getSumNbeHeure();
	double getSumNbeHeureByEnseignant(Long id);
	double getSumNbeHeureSupByEnseignant(Long id);
	double getSumNbeHeureSousServiceByEnseignant(Long id);
	double getSumNbeHeureByStatut(String statut);
	double getSumNbeHeureByTypecoursId(Long id);
	

	List<Cour> getAllCours();

	
	List<Cour> findByLibelleContains(String libelle);

	List<Cour> findByEnseignantId(Long id);

	List<Cour> findByFormationsId(Long id);

	List<Cour> findByFormationsNomFormation(String nom);
}
