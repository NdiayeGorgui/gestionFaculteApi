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
import com.myproject.faculte.service.FormationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Gestion des Formations")
@RestController
//@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200")
public class FormationRestController {

	@Autowired
	private FormationService formationService;

	@ApiOperation(value = "Affiche la liste des formations")
	@RequestMapping(value = "/Formations", method = RequestMethod.GET)
	public List<Formation> getAllCours() {
		return formationService.getAllFormations();
	}

	@ApiOperation(value = "Récupère une formation selon son id")
	@RequestMapping(value = "Formations/{id}", method = RequestMethod.GET)
	public Formation getFormation(@PathVariable("id") final Long id) throws IdIntrouvableExecption {
		Optional<Formation> formation = formationService.getFormation(id);
		if (formation.isPresent()) {
			return formation.get();
		} else {
			throw new IdIntrouvableExecption("La formation avec l'id " + id + " n'existe pas");
		}
	}

	@ApiOperation(value = "Ajouter une nouvelle formation")
	@RequestMapping(value = "/Formations", method = RequestMethod.POST)
	public Formation createFormation(@RequestBody Formation formation) {
		return formationService.saveFormation(formation);
	}

	@ApiOperation(value = "Modifier une formation selon son id")
	@RequestMapping(value = "/Formations/{id}", method = RequestMethod.PUT)
	public Formation updateFormation(@PathVariable("id") final Long id, @RequestBody Formation formation) {
		Optional<Formation> f = formationService.getFormation(id);
		if (f.isPresent()) {
			Formation currentFormation = f.get();

			String nomFormation = formation.getNomFormation();
			if (nomFormation != null) {
				currentFormation.setNomFormation(nomFormation);
			}
			double duree = formation.getDuree();
			if (duree != 0.0) {
				currentFormation.setDuree(duree);
				;
			}
			String anne = formation.getAnnee();
			if (anne != null) {
				currentFormation.setAnnee(anne);
			}

			formationService.saveFormation(currentFormation);
			return currentFormation;
		} else {
			return null;
		}
	}

	@ApiOperation(value = "Supprimer une formation selon son id")
	@RequestMapping(value = "Formations/{id}", method = RequestMethod.DELETE)
	public void deleteFormation(@PathVariable("id") Long id) {
		formationService.deleteFormationById(id);
	}
	
	@ApiOperation(value = "Affiche la liste des formations où un un cours existe selon son id")
	@RequestMapping(value = "Formations/Cours/{id}",  method = RequestMethod.GET)
	public List<Formation> findByCoursId(@PathVariable("id") Long id) {
		return formationService.findByCoursId(id);
	}
	
	@ApiOperation(value = "Affiche la liste des formations où un un cours existe selon son libellé")
	@RequestMapping(value = "Formations/Cours/Libelle/{libelle}",  method = RequestMethod.GET)
	public List<Formation> findByCoursLibelle(@PathVariable("libelle") String libelle) {
		return formationService.findByCoursLibelle(libelle);
	}
	
	@ApiOperation(value = "Affiche la liste de formations contenant un cercain critére")
	@RequestMapping(value = "Formations/Cherche/{nom}", method = RequestMethod.GET)
	public List<Formation> findByNomFormationContains(@PathVariable("nom") String nom) {
		return formationService.findByNomFormationContains(nom);
	}

}
