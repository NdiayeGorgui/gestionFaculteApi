package com.myproject.faculte.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.faculte.exception.IdIntrouvableExecption;
import com.myproject.faculte.model.Enseignant;
import com.myproject.faculte.service.EnseignantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Gestion des Enseignants")
@RestController
//@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:3000")

public class EnseignantRestController {

	@Autowired
	private EnseignantService enseignantService;

	@ApiOperation(value = "Affiche la liste des Enseignants")
	@RequestMapping(value = "/Enseignants", method = RequestMethod.GET)
	public List<Enseignant> getAllEnseignants() {
		return enseignantService.getAllEnseignants();
	}

	/*
	 * @RequestMapping(value = "Enseignant/{id}", method = RequestMethod.GET) public
	 * Enseignant getEnseignantById(@PathVariable("id") Long id) { return
	 * enseignantService.getEnseignant(id); }
	 */
	@ApiOperation(value = "Récupère un Enseignant selon son id")
	@RequestMapping(value = "Enseignants/{id}", method = RequestMethod.GET)
	public Enseignant getEnseignant(@PathVariable("id") final Long id) throws IdIntrouvableExecption {
		Optional<Enseignant> enseignant = enseignantService.getEnseignant(id);
		if (enseignant.isPresent()) {
			return enseignant.get();
		} else {
			throw new IdIntrouvableExecption("L'enseignant avec l'id " + id + " n'existe pas");
		}
	}

	@ApiOperation(value = "Ajouter un nouveau Enseignant")
	@RequestMapping(value = "/Enseignants", method = RequestMethod.POST)
	public Enseignant createEnseignant(@Valid @RequestBody Enseignant enseignant) {
		return enseignantService.saveEnseignant(enseignant);
	}

	/*
	 * @RequestMapping(value = "/Enseignants",method = RequestMethod.PUT) public
	 * Enseignant updateEnseignant(@RequestBody Enseignant enseignant) { return
	 * enseignantService.updateEnseignant(enseignant); }
	 */
	@ApiOperation(value = "Modifier un Enseignant selon son id")
	@RequestMapping(value = "/Enseignants/{id}", method = RequestMethod.PUT)
	public Enseignant updateEnseignant(@PathVariable("id") final Long id, @RequestBody Enseignant enseignant) {
		Optional<Enseignant> e = enseignantService.getEnseignant(id);
		if (e.isPresent()) {
			Enseignant currentEnseignant = e.get();

			String firstName = enseignant.getFirstName();
			if (firstName != null) {
				currentEnseignant.setFirstName(firstName);
			}
			String lastName = enseignant.getLastName();
			if (lastName != null) {
				currentEnseignant.setLastName(lastName);
				
			}
			String mail = enseignant.getMail();
			if (mail != null) {
				currentEnseignant.setMail(mail);
			}
			String adress = enseignant.getAdress();
			if (adress != null) {
				currentEnseignant.setAdress(adress);
				
			}
			String telephone = enseignant.getTelephone();
			if (telephone != null) {
				currentEnseignant.setTelephone(telephone);
			}
			String statut = enseignant.getStatut();
			if (statut != null) {
				currentEnseignant.setStatut(statut);
				
			}
			enseignantService.saveEnseignant(currentEnseignant);
			return currentEnseignant;
		} else {
			return null;
		}
	}

	@ApiOperation(value = "Supprimer un Enseignant selon son id")
	@RequestMapping(value = "Enseignants/{id}", method = RequestMethod.DELETE)
	public void deleteEnseignant(@PathVariable("id") Long id) {
		enseignantService.deleteEnseignantById(id);
	}

	@ApiOperation(value = "Affiche la liste des  Enseignants  selon le statut sélctionné")
	@RequestMapping(value = "/Enseignants/Statut/{statut}", method = RequestMethod.GET)
	public List<Enseignant> findByEnseignantStatut(@PathVariable("statut") String statut) {
		return enseignantService.findByEnseignantStatut(statut);
	}

	@ApiOperation(value = "Affiche la liste des  Enseignants  selon  le nom, le prénom ou le statut")
	@RequestMapping(value = "/Enseignants/Cherche/{value}", method = RequestMethod.GET)
	public List<Enseignant> findByFirstNameLastNameStatut(@PathVariable("value") String value) {
		return enseignantService.findByFirstNameLastNameStatut(value);
	}

	@ApiOperation(value = "Affiche la liste des  Enseignants  pour un groupe selon son id")
	@RequestMapping(value = "/Enseignants/Groupe/{id}", method = RequestMethod.GET)
	public List<Enseignant> findByGroupesId(@PathVariable("id") Long id) {
		return enseignantService.findByGroupesId(id);
	}
	
	

	@ApiOperation(value = "Affiche la liste des Enseignants par ordre alphabétique suivant les noms")
	@RequestMapping(value = "Trier/Enseignants", method = RequestMethod.GET)
	public List<Enseignant> findByOrderByLastNameAsc() {
		return enseignantService.findByOrderByLastNameAsc();
	}

}
