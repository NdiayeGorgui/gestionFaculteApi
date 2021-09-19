package com.myproject.faculte.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.myproject.faculte.model.Cour;
import com.myproject.faculte.model.Enseignant;
import com.myproject.faculte.repository.EnseignantRepository;

@Service
public class EnseignantServiceImpl implements EnseignantService {
	
	@Autowired
	EnseignantRepository enseignantRepository;

	@Override
	public Enseignant saveEnseignant(Enseignant e) {
		
		return enseignantRepository.save(e);
	}

	@Override
	public Enseignant updateEnseignant(Enseignant e) {
		
		return enseignantRepository.save(e);
	}

	@Override
	public void deleteEnseignant(Enseignant e) {
		enseignantRepository.delete(e);
		
	}

	@Override
	public void deleteEnseignantById(Long id) {
		
		 enseignantRepository.deleteById(id);
	}

	/*
	 * @Override public Enseignant getEnseignant(Long id) { return
	 * enseignantRepository.findById(id).get(); }
	 */
	@Override
	 public Optional<Enseignant> getEnseignant(final Long id) {
	        return enseignantRepository.findById(id);
	    }

	@Override
	public List<Enseignant> getAllEnseignants() {
		
		return enseignantRepository.findAll();
	}

	@Override
	public List<Cour> findByEnseignantCours(Enseignant enseignant) {
		
		return enseignantRepository.findByEnseignantCours(enseignant);
	}

	@Override
	public List<Enseignant> findByEnseignantStatut(String statut) {
		
		return enseignantRepository.findByEnseignantStatut(statut);
	}

	@Override
	public List<Enseignant> findByGroupesId(Long id) {
		
		return enseignantRepository.findByGroupesId(id);
	}

	@Override
	public List<Enseignant> findByFirstNameLastNameStatut(String value) {
		
		return enseignantRepository.findByFirstNameLastNameStatut(value);
	}

	

}
