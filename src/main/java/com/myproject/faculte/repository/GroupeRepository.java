package com.myproject.faculte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.faculte.model.Enseignant;
import com.myproject.faculte.model.Groupe;

@Repository
public interface GroupeRepository extends JpaRepository<Groupe, Long> {
	
	// liste des groupes selon le numero du groupe
	List<Groupe>findByNumeroGroupe(String numero);

	// liste des groupes selon l'id de la formation
	List<Groupe> findByFormationId(Long id);

	// liste des groupes selon le nom de la formation
	List<Groupe> findByFormationNomFormation(String nom);

	// la liste des groupes selon l'id d'un enseignant
	List<Groupe> findByEnseignantsId(Long id);
	
	Groupe findBynumeroGroupe(String numeroGroupe);
}
