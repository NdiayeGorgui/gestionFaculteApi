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

	// List<Cour> findByTypeCourType(String type);
	/*
	 * //pour afficher les cours d'un certain type
	 * 
	 * @Query("select c from Cour c where c.libelle like %:libelle and c.typeCour like %:typeCour"
	 * ) List<Cour> findByLibelleId (@Param("libelle") String
	 * libelle,@Param("typeCour") TypeCour typeCour);
	 */

}
