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
import com.myproject.faculte.model.Enseignant;
import com.myproject.faculte.model.TypeCour;
import com.myproject.faculte.service.CourService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Gestion des Cours")
@RestController
@CrossOrigin
public class CourRestController {

	@Autowired
	private CourService courService;

	@ApiOperation(value = "Affiche la liste des cours")
	@RequestMapping(value = "/Cours", method = RequestMethod.GET)
	public List<Cour> getAllCours() {
		return courService.getAllCours();
	}

	@ApiOperation(value = "Récupère un cours selon son id")
	@RequestMapping(value = "Cour/{id}", method = RequestMethod.GET)
	public Cour getCour(@PathVariable("id") final Long id) throws IdIntrouvableExecption {
		Optional<Cour> cour = courService.getCour(id);
		if (cour.isPresent()) {
			return cour.get();
		} else {

			throw new IdIntrouvableExecption("Le Cours avec l'id " + id + " n'existe pas");

		}
	}

	@ApiOperation(value = "Ajouter un nouveau cours")
	@RequestMapping(value = "/Cours", method = RequestMethod.POST)
	public Cour createCour(@RequestBody Cour cour) {
		return courService.saveCour(cour);
	}

	@ApiOperation(value = "Modifier un cours selon son id")
	@RequestMapping(value = "/Cour/{id}", method = RequestMethod.PUT)
	public Cour updateCour(@PathVariable("id") final Long id, @RequestBody Cour cour) {
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
			return null;
		}
	}

	@ApiOperation(value = "Supprimer un cours selon son id")
	@RequestMapping(value = "Cour/{id}", method = RequestMethod.DELETE)
	public void deleteCour(@PathVariable("id") Long id) {
		courService.deleteCourById(id);
	}

	@ApiOperation(value = "Affiche la liste de cours d'un Enseignant sélctionné selon son id")
	@RequestMapping(value = "Cours/Enseignant/{id}", method = RequestMethod.GET)
	public List<Cour> findByEnseignantId(@PathVariable("id") Long id) {
		return courService.findByEnseignantId(id);
	}

	@ApiOperation(value = "Affiche la liste de cours contenant un cercain critére")
	@RequestMapping(value = "Cours/{libelle}", method = RequestMethod.GET)
	public List<Cour> findByLibelleContains(@PathVariable("libelle") String libelle) {
		return courService.findByLibelleContains(libelle);
	}
	/*
	 * @RequestMapping(value = "Cours/TypeCour/{type}",method = RequestMethod.GET)
	 * public List<Cour> findByTypeCourType(@PathVariable("type") String type) {
	 * return courService.findByTypeCourType(type); }
	 */

}
