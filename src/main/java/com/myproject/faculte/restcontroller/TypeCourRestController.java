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

import com.myproject.faculte.exception.ResourceNotFoundException;
import com.myproject.faculte.model.TypeCour;
import com.myproject.faculte.service.TypeCourService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Gestion des Types de cours")
@RestController

@CrossOrigin(origins="http://localhost:4200")
public class TypeCourRestController {

	@Autowired
	private TypeCourService typeCourService;

	@ApiOperation(value = "Affiche la liste des type de cours")
	@RequestMapping(value = "/TypeCours", method = RequestMethod.GET)
	public List<TypeCour> getAllTypeCours() {
		return typeCourService.getAllTypeCours();
	}

	@ApiOperation(value = "Récupère un type de cours selon son id")
	@RequestMapping(value = "TypeCours/{id}", method = RequestMethod.GET)
	public TypeCour getTypeCours(@PathVariable("id") final Long id) throws ResourceNotFoundException {
		Optional<TypeCour> typeCour = typeCourService.getTypeCour(id);
		if (typeCour.isPresent()) {
			return typeCour.get();
		} else {
			throw new ResourceNotFoundException("Le type de Cours avec l'id " + id + " n'existe pas");
		}
	}

	@ApiOperation(value = "Ajouter un nouveau type de cours")
	@RequestMapping(value = "/TypeCours", method = RequestMethod.POST)
	public TypeCour createTypeCours(@RequestBody TypeCour typeCour) {
		return typeCourService.saveTypeCour(typeCour);
	}

	@ApiOperation(value = "Modifier un type de cours selon son id")
	@RequestMapping(value = "/TypeCours/{id}", method = RequestMethod.PUT)
	public TypeCour updateTypeCours(@PathVariable("id") final Long id, @RequestBody TypeCour typeCour) throws ResourceNotFoundException {
		Optional<TypeCour> tc = typeCourService.getTypeCour(id);
		if (tc.isPresent()) {
			TypeCour currentTypeCour = tc.get();

			double prix = typeCour.getPrix();
			if (prix != 0.0) {
				currentTypeCour.setPrix(prix);
			}
			String type = typeCour.getType();
			if (type != null) {
				currentTypeCour.setType(type);
			}

			typeCourService.saveTypeCour(currentTypeCour);
			return currentTypeCour;
		} else {
			throw new ResourceNotFoundException("Le type de Cours avec l'id " + id + " n'existe pas");
		}
	}

	@ApiOperation(value = "Supprimer un type de cours selon son id")
	@RequestMapping(value = "TypeCours/{id}", method = RequestMethod.DELETE)
	public void deleteTypeCours(@PathVariable("id") Long id)  {
		typeCourService.deleteTypeCourById(id);
	}

}
