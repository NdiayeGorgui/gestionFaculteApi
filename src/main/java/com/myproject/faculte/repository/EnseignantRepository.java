package com.myproject.faculte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myproject.faculte.model.Cour;
import com.myproject.faculte.model.Enseignant;


@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {

	// List<Enseignant> findByEnseignantLastName(String name);
	// List<Enseignant> findByEnseignantLasttLike(String name);

	// pour afficher la liste des enseignants( le nom ,prenom et statut de l"ens)
	@Query("select e from Enseignant e where e.firstName like %:prenom and e.lastName like %:nom and e.statut like %:statut")
	List<Enseignant> findByFirstNameLastNameStatut(@Param("prenom") String prenom, @Param("nom") String nom,
			@Param("statut") String statut);

	// pour afficher la liste des enseignants(suivant le statut de l"ens)
	@Query("select e from Enseignant e where  e.statut like %:statut")
	List<Enseignant> findByEnseignantStatut(@Param("statut") String statut);

	// pour avoir la liste des cours effectuée par un enseignant 
	@Query("select c from Cour c where c.enseignant = ?1")
	List<Cour> findByEnseignantCours(Enseignant enseignant);
	
	
	
	
}
