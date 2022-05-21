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

import com.myproject.faculte.exception.ResourceNotFoundException;
import com.myproject.faculte.model.Cour;
import com.myproject.faculte.model.CoursFormation;
import com.myproject.faculte.model.Enseignant;
import com.myproject.faculte.model.TypeCour;
import com.myproject.faculte.service.CourService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Gestion des Cours")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CourRestController {

	@Autowired
	private CourService courService;

	@ApiOperation(value = "Affiche la liste des cours")
	@RequestMapping(value = "/Cours", method = RequestMethod.GET)
	public List<Cour> getAllCours() {
		return courService.getAllCours();
	}

	@ApiOperation(value = "Récupère un cours selon son id")
	@RequestMapping(value = "Cours/{id}", method = RequestMethod.GET)
	public Cour getCour(@PathVariable("id") final Long id) throws ResourceNotFoundException {
		Optional<Cour> cour = courService.getCour(id);
		if (cour.isPresent()) {
			return cour.get();
		} else {

			throw new ResourceNotFoundException("Le Cours avec l'id " + id + " n'existe pas");

		}
	}

	@ApiOperation(value = "Ajouter un nouveau cours")
	@RequestMapping(value = "/Cours", method = RequestMethod.POST)
	public Cour createCour(@RequestBody Cour cour) {
		return courService.saveCour(cour);
	}
	
	 @ApiOperation(value = "Affecter un cours à une formation")
	  
	  @RequestMapping(value = "/Cours/addCoursToFormation/{libelle}/{formation}", method = RequestMethod.POST)
	  public void addCoursToFormation(@PathVariable("libelle") String libelle,@PathVariable("formation") String formation) {
		 courService.addCoursToFormation(libelle,formation);
	  }
	 
		//autre maniéere de faire
	 @ApiOperation(value = "Affecter un cours à une formation")
	  
	  @RequestMapping(value = "/Cours/addCoursToFormation", method = RequestMethod.POST)
	  public void addCoursToFormations(@RequestBody CoursFormation coursFormation) {
		 courService.addCoursToFormation(coursFormation.getLibelle(),coursFormation.getNomFormation());
	  }
	 
	 
	 @ApiOperation(value = "Enregistrer un nouveau cours et l'affecter automatiquement à une formation")
	  
	  @RequestMapping(value = "/Cours/saveCoursWithFormation/{formation}", method = RequestMethod.POST)
	  public void saveCoursWithFormation(@Valid @RequestBody Cour cours,@PathVariable("formation") String formation) {
		 courService.saveCoursWithFormation(cours,formation);
	  }
	 
	 @ApiOperation(value = "Supprimer un cours d'une formation")
	  
	  @RequestMapping(value = "/Cours/deleteCoursToFormation/{libelle}/{formation}", method = RequestMethod.DELETE)
	  public void deleteCoursToFormation(@PathVariable("libelle") String libelle,@PathVariable("formation") String formation) {
		 courService.deleteCoursToFormation(libelle,formation);
	  }

	@ApiOperation(value = "Modifier un cours selon son id")
	@RequestMapping(value = "/Cours/{id}", method = RequestMethod.PUT)
	public Cour updateCour(@PathVariable("id") final Long id, @RequestBody Cour cour) throws ResourceNotFoundException {
		Optional<Cour> c = courService.getCour(id);
		if (c.isPresent()) {
			Cour currentCour = c.get();

			String libelle = cour.getLibelle();
			if (libelle != null) {
				currentCour.setLibelle(libelle);
			}
			double nbreHeure = cour.getNbeHeure();
			if (nbreHeure != 0.0) {
				currentCour.setNbeHeure(nbreHeure);
				;
			}
			Enseignant ens = cour.getEnseignant();
			if (ens != null) {
				currentCour.setEnseignant(ens);
			}
			TypeCour typeCour = cour.getTypecour();
			if (typeCour != null) {
				currentCour.setTypecour(typeCour);
			}

			courService.saveCour(currentCour);
			return currentCour;
		} else {
			throw new ResourceNotFoundException("Le Cours avec l'id " + id + " n'existe pas");
		}
	}

	@ApiOperation(value = "Supprimer un cours selon son id")
	@RequestMapping(value = "Cours/{id}", method = RequestMethod.DELETE)
	public void deleteCour(@PathVariable("id") Long id) {
		
		courService.deleteCourById(id);
	}

	@ApiOperation(value = "Affiche la liste de cours d'un Enseignant sélctionné selon son id")
	@RequestMapping(value = "Cours/Enseignants/{id}", method = RequestMethod.GET)
	public List<Cour> findByEnseignantId(@PathVariable("id") Long id) {
		return courService.findByEnseignantId(id);
	}

	@ApiOperation(value = "Affiche la liste de cours contenant un cercain critére")
	@RequestMapping(value = "Cours/Libelle/{libelle}", method = RequestMethod.GET)
	public List<Cour> findByLibelleContains(@PathVariable("libelle") String libelle) {
		return courService.findByLibelleContains(libelle);
	}

	@ApiOperation(value = "Affiche la liste de cours selon l'id de la formation selectionnée")
	@RequestMapping(value = "Cours/Formations/{id}", method = RequestMethod.GET)
	public List<Cour> findByFormationsId(@PathVariable("id") Long id) {
		return courService.findByFormationsId(id);
	}

	@ApiOperation(value = "Affiche la liste de cours selon le nom de la formation")
	@RequestMapping(value = "Cours/FormationName/{nom}", method = RequestMethod.GET)
	public List<Cour> findByFormationsNomFormation(@PathVariable("nom") String nom) {
		return courService.findByFormationsNomFormation(nom);
	}
	
	@ApiOperation(value = "Affiche le nombre total d'heures pour un type de cours")
	@RequestMapping(value = "Cours/TypeCours/SumHours/{id}", method = RequestMethod.GET)
	public double getSumNbeHeureByTypecoursId(@PathVariable("id") Long id) {
		return courService.getSumNbeHeureByTypecoursId(id);
	}
	
	@ApiOperation(value = "Affiche le nombre total d'heures pour un enseignant")
	@RequestMapping(value = "Cours/Enseignants/SumHours/{id}", method = RequestMethod.GET)
	public double getSumNbeHeureByEnseignant(@PathVariable("id") Long id) {
		return courService.getSumNbeHeureByEnseignant(id);
	}
	
	@ApiOperation(value = "Affiche le nombre total d'heures par statut")
	@RequestMapping(value = "Cours/Enseignants/Statut/SumHours/{statut}", method = RequestMethod.GET)
	public double getSumNbeHeureByStatut(@PathVariable("statut") String statut) {
		return courService.getSumNbeHeureByStatut(statut);
	}
	
	@ApiOperation(value = "Affiche les heures sup pour un enseignant")
	@RequestMapping(value = "Cours/Enseignants/HeureSup/SumHours/{id}", method = RequestMethod.GET)
	public double getSumNbeHeureSupByStatut(@PathVariable("id") Long id) {
		return courService.getSumNbeHeureSupByEnseignant(id);
	}
	
	@ApiOperation(value = "Affiche les heures sous service pour un enseignant")
	@RequestMapping(value = "Cours/Enseignants/HeureSousService/SumHours/{id}", method = RequestMethod.GET)
	public double getSumNbeHeureSousServiceByEnseignant(@PathVariable("id") Long id) {
		return courService.getSumNbeHeureSousServiceByEnseignant(id);
	}
	
	@ApiOperation(value = "Affiche le nombre total d'heures ")
	@RequestMapping(value = "Cours/Enseignants/Global/SumHours", method = RequestMethod.GET)
	public double getSumNbeHeureSupByStatut() {
		return courService.getSumNbeHeure();
	}
	
	

}
