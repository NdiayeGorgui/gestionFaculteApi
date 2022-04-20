package com.myproject.faculte.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.faculte.model.Cour;
import com.myproject.faculte.model.Formation;
import com.myproject.faculte.repository.CourRepository;
import com.myproject.faculte.repository.FormationRepository;

@Service
public class CourServiceImpl implements CourService{
	
	@Autowired
	CourRepository courRepository;
	@Autowired
	FormationRepository formationRepository;

	
	@Override
	public Cour saveCour(Cour c) {
		
		return courRepository.save(c);
	}

	@Override
	public Cour updateCour(Cour c) {
		
		return courRepository.save(c);
	}

	@Override
	public void deleteCour(Cour c) {
		courRepository.delete(c);
		
	}

	@Override
	public void deleteCourById(Long id) {
		courRepository.deleteById(id);
		
	}

	@Override
	public Optional<Cour> getCour(Long id) {
		
		return courRepository.findById(id);
	}

	@Override
	public List<Cour> getAllCours() {
		
		return courRepository.findAll();
	}

	@Override
	public List<Cour> findByEnseignantId(Long id) {
		
		return courRepository.findByEnseignantId(id);
	}

	@Override
	public List<Cour> findByLibelleContains(String libelle) {
		
		return courRepository.findByLibelleContains(libelle);
	}

	@Override
	public List<Cour> findByFormationsId(Long id) {
		
		return courRepository.findByFormationsId(id);
	}

	@Override
	public List<Cour> findByFormationsNomFormation(String nom) {
		
		return courRepository.findByFormationsNomFormation(nom);
	}

	@Override
	public Cour findCourByLibelle(String libelle) {
		return courRepository.findBylibelle(libelle);
		
	}

	@Override
	public void addCoursToFormation(String libelle, String nomFormation) {
		Cour cours=findCourByLibelle(libelle);
		Formation formation=formationRepository.findByNomFormation(nomFormation);
		
		if (cours.getFormations() != null) {
			cours.getFormations().add(formation);
			courRepository.save(cours);
		}
		if (formation.getGroupes() != null) {
			formation.getCours().add(cours);
			formationRepository.save(formation);
		}
		
	}
	
	@Override
	public void deleteCoursToFormation(String libelle, String nomFormation) {
		Cour cours=findCourByLibelle(libelle);
		Formation formation=formationRepository.findByNomFormation(nomFormation);
		
		if (cours.getFormations() != null) {
			cours.getFormations().remove(formation);
			courRepository.save(cours);
		}
		if (formation.getGroupes() != null) {
			formation.getCours().remove(cours);
			formationRepository.save(formation);
		}
		
	}
	//enregistrer un nouveau cours et l'affecter automatiquement à une formation
	@Override
	public void saveCoursWithFormation(Cour cours, String nomFormation) {
		Cour crs=saveCour( cours);
		Formation formation=formationRepository.findByNomFormation(nomFormation);
		if (crs.getFormations() != null) {
			crs.getFormations().add(formation);
			courRepository.save(crs);
		}
		if (formation.getGroupes() != null) {
			formation.getCours().add(crs);
			formationRepository.save(formation);
		}
	}

	
	/*
	 * @Override public List<Cour> findByTypeCourType(String type) {
	 * 
	 * return courRepository.findByTypeCourType(type); }
	 */
	 

}
