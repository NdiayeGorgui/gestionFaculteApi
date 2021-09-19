package com.myproject.faculte.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.faculte.model.Groupe;
import com.myproject.faculte.repository.GroupeRepository;


@Service
public class GroupeServiceImpl implements GroupeService {
	
	@Autowired
	GroupeRepository groupeRepository;

	@Override
	public Groupe saveGroupe(Groupe g) {
		
		return groupeRepository.save(g);
	}

	@Override
	public Groupe updateGroupe(Groupe g) {
		
		return groupeRepository.save(g);
	}

	@Override
	public void deleteGroupe(Groupe g) {
		groupeRepository.delete(g);
		
	}

	@Override
	public void deleteGroupeById(Long id) {
		groupeRepository.deleteById(id);
		
	}

	@Override
	public Optional<Groupe> getGroupe(Long id) {
		
		return groupeRepository.findById(id);
	}

	@Override
	public List<Groupe> getAllGroupes() {
		
		return groupeRepository.findAll();
	}

	

	@Override
	public List<Groupe> findByEnseignantsId(Long id) {
		
		return groupeRepository.findByEnseignantsId(id);
	}

	@Override
	public List<Groupe> findByFormationNomFormation(String nom) {
		
		return groupeRepository.findByFormationNomFormation(nom);
	}

	@Override
	public List<Groupe> findByFormationId(Long id) {
		
		return groupeRepository.findByFormationId(id);
	}

}
