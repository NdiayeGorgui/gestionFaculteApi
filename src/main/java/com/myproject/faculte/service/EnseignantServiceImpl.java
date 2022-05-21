package com.myproject.faculte.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.faculte.service.GroupeService;
import com.myproject.faculte.model.Cour;
import com.myproject.faculte.model.Enseignant;
import com.myproject.faculte.model.Groupe;
import com.myproject.faculte.repository.EnseignantRepository;
import com.myproject.faculte.repository.GroupeRepository;

@Service
public class EnseignantServiceImpl implements EnseignantService {

	@Autowired
	EnseignantRepository enseignantRepository;
	@Autowired
	GroupeRepository groupeRepository;

	@Override
	public Enseignant saveEnseignant(Enseignant e) {
		// e.getGroupes().add(null);

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

	@Override
	public List<Enseignant> findByOrderByLastNameAsc() {

		return enseignantRepository.findByOrderByLastNameAsc();
	}

	@Override
	public void addEnseignantToGroupe(String mail, String numeroGroupe) {
		Enseignant enseignant = findEnseignantByEmail(mail);
		Groupe groupe = groupeRepository.findBynumeroGroupe(numeroGroupe);
		if (enseignant.getGroupes() != null) {
			enseignant.getGroupes().add(groupe);
			enseignantRepository.save(enseignant);
		}
		if (groupe.getEnseignants() != null) {
			groupe.getEnseignants().add(enseignant);
			groupeRepository.save(groupe);
		}

	}

	@Override
	public Enseignant findEnseignantByEmail(String mail) {

		return enseignantRepository.findByMail(mail);
	}

	@Override
	public void deleteEnseignantToGroupe(String mail, String numeroGroupe) {
		Enseignant enseignant = findEnseignantByEmail(mail);
		Groupe groupe = groupeRepository.findBynumeroGroupe(numeroGroupe);
		if (enseignant.getGroupes() != null) {
			enseignant.getGroupes().remove(groupe);
			enseignantRepository.save(enseignant);
		}
		if (groupe.getEnseignants() != null) {
			groupe.getEnseignants().remove(enseignant);
			groupeRepository.save(groupe);
		}
		
	}
//enregistrer un nouveau enseignant et l'affecter automatiquement à un groupe
	@Override
	public void saveEnseignantWihtGroupe(Enseignant enseignant, String numeroGroupe) {
		Enseignant ens=saveEnseignant(enseignant);
		Groupe groupe = groupeRepository.findBynumeroGroupe(numeroGroupe);
		if (ens.getGroupes() != null) {
			ens.getGroupes().add(groupe);
			enseignantRepository.save(ens);
		}
		if (groupe.getEnseignants() != null) {
			groupe.getEnseignants().add(ens);
			groupeRepository.save(groupe);
		}
		
	}

	

}
