package com.myproject.faculte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.faculte.model.Formation;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {

	// la liste des formations où un un cours existe selon son id
	List<Formation> findByCoursId(Long id);

	// la liste des formations où un un cours existe selon son nom
	List<Formation> findByCoursLibelle(String libelle);
	
	// pour chercher les formations dont le nom contenant le nom donné en parametre
	List<Formation >findByNomFormationContains(String nom);
	Formation findByNomFormation(String nomFormation);

}
