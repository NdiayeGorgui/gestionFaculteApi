package com.myproject.faculte.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.faculte.model.Formation;
import com.myproject.faculte.repository.FormationRepository;


@Service
public class FormationServiceImpl implements FormationService {

	
	@Autowired
	FormationRepository formationRepository;
	@Override
	public Formation saveFormation(Formation f) {
		
		return formationRepository.save(f);
	}

	@Override
	public Formation updateFormation(Formation f) {
		
		return formationRepository.save(f);
	}

	@Override
	public void deleteFormation(Formation f) {
		formationRepository.delete(f);
		
	}

	@Override
	public void deleteFormationById(Long id) {
		formationRepository.deleteById(id);
		
	}

	@Override
	public Optional<Formation> getFormation(Long id) {
		
		return formationRepository.findById(id);
	}

	@Override
	public List<Formation> getAllFormations() {
		
		return formationRepository.findAll();
	}

	@Override
	public List<Formation> findByCoursId(Long id) {
		
		return formationRepository.findByCoursId(id);
	}

	@Override
	public List<Formation> findByCoursLibelle(String libelle) {
		
		return formationRepository.findByCoursLibelle(libelle);
	}

	@Override
	public List<Formation> findByNomFormationContains(String nom) {
		
		return formationRepository.findByNomFormationContains(nom);
	}

}
