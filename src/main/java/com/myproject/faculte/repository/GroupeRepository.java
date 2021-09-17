package com.myproject.faculte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.faculte.model.Enseignant;
import com.myproject.faculte.model.Groupe;



@Repository
public interface GroupeRepository extends JpaRepository<Groupe, Long>{

	List<Groupe> findByFormationId(Long id);
	//la liste des groupes pour un enseignant
	List<Groupe> findByEnseignantsId(Long id);
}
