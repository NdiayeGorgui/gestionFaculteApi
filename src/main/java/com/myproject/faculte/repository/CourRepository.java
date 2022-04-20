package com.myproject.faculte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myproject.faculte.model.Cour;
import com.myproject.faculte.model.TypeCour;

@Repository
public interface CourRepository extends JpaRepository<Cour, Long> {

	// pour chercher les cours qui ont le nom donné en parametre
	List<Cour> findByLibelle(String libelle);

	// pour chercher les cours dont le nom contenant le nom donné en parametre
	List<Cour> findByLibelleContains(String libelle);

	List<Cour> findByEnseignantId(Long id);

	// liste des cours pour une formation selon l'id de la formation
	List<Cour> findByFormationsId(Long id);

	// liste des cours pour une formation selon le nom de la formation
	List<Cour> findByFormationsNomFormation(String nom);
	//chercher un cours suivant son libelle
	Cour findBylibelle(String libelle);

}
