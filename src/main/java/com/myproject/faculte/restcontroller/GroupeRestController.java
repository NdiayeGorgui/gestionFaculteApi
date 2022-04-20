package com.myproject.faculte.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.faculte.exception.IdIntrouvableExecption;
import com.myproject.faculte.model.Cour;
import com.myproject.faculte.model.Formation;
import com.myproject.faculte.model.Groupe;

import com.myproject.faculte.service.GroupeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Gestion des Groupes")
@RestController

@CrossOrigin(origins="http://localhost:4200")
public class GroupeRestController {

	@Autowired
	private GroupeService groupeService;

	@ApiOperation(value = "Affiche la liste des groupes")
	@RequestMapping(value = "/Groupes", method = RequestMethod.GET)
	public List<Groupe> getAllGroupes() {
		return groupeService.getAllGroupes();
	}

	@ApiOperation(value = "Récupère un groupe selon son id")
	@RequestMapping(value = "Groupes/{id}", method = RequestMethod.GET)
	public Groupe getGroupe(@PathVariable("id") final Long id) throws IdIntrouvableExecption {
		Optional<Groupe> groupe = groupeService.getGroupe(id);
		if (groupe.isPresent()) {
			return groupe.get();
		} else {
			throw new IdIntrouvableExecption("Le Groupe avec l'id " + id + " n'existe pas");
		}
	}

	@ApiOperation(value = "Ajouter un nouveau groupe")
	@RequestMapping(value = "/Groupes", method = RequestMethod.POST)
	public Groupe createGroupe(@RequestBody Groupe groupe) {
		return groupeService.saveGroupe(groupe);
	}

	@ApiOperation(value = "Modifier un groupe selon son id")
	@RequestMapping(value = "/Groupes/{id}", method = RequestMethod.PUT)
	public Groupe updateGroupe(@PathVariable("id") final Long id, @RequestBody Groupe groupe) {
		Optional<Groupe> g = groupeService.getGroupe(id);
		if (g.isPresent()) {
			Groupe currentGroupe = g.get();

			
			String numeroGroupe=groupe.getNumeroGroupe();
			if(numeroGroupe!=null) {
				currentGroupe.setNumeroGroupe(numeroGroupe);
			}
			Formation formation = groupe.getFormation();
			if (formation != null) {
				currentGroupe.setFormation(formation);
			}

			groupeService.saveGroupe(currentGroupe);
			return currentGroupe;
		} else {
			return null;
		}
	}

	@ApiOperation(value = "Supprimer un groupe selon son id")
	@RequestMapping(value = "Groupes/{id}", method = RequestMethod.DELETE)
	public void deleteGroupe(@PathVariable("id") Long id) {
		groupeService.deleteGroupeById(id);
	}

	@ApiOperation(value = "Affiche la liste des groupes d'une formation sélctionné selon son id")
	@RequestMapping(value = "Groupes/Formations/{id}", method = RequestMethod.GET)
	public List<Groupe> findByFormationId(@PathVariable("id") Long id) {
		return groupeService.findByFormationId(id);
	}

	@ApiOperation(value = "Affiche la liste des groupes d'une formation sélctionné selon son nom")
	@RequestMapping(value = "Groupes/Formations/Nom/{nom}", method = RequestMethod.GET)
	public List<Groupe> findByFormationNomFormation(@PathVariable("nom") String nom) {
		return groupeService.findByFormationNomFormation(nom);
	}

	@ApiOperation(value = "Affiche la liste des groupes pour un enseignant  selon son id")
	@RequestMapping(value = "Groupes/Enseignants/{id}", method = RequestMethod.GET)
	public List<Groupe> findByEnseignantsId(@PathVariable("id") Long id) {
		return groupeService.findByEnseignantsId(id);
	}
	
	@ApiOperation(value = "Affiche la liste des groupes selon le numéro du groupe")
	@RequestMapping(value = "Groupes/Numero/{numero}", method = RequestMethod.GET)
	public List<Groupe> findByNumeroGroupe(@PathVariable("numero") String numero) {
		return groupeService.findByNumeroGroupe(numero);
	}
}
