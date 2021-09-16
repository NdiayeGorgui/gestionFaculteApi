package com.myproject.faculte.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.faculte.model.Cour;
import com.myproject.faculte.repository.CourRepository;

@Service
public class CourServiceImpl implements CourService{
	
	@Autowired
	CourRepository courRepository;

	
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
		// TODO Auto-generated method stub
		return courRepository.findByLibelleContains(libelle);
	}

	
	/*
	 * @Override public List<Cour> findByTypeCourType(String type) {
	 * 
	 * return courRepository.findByTypeCourType(type); }
	 */
	 

}
