package com.myproject.faculte;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myproject.faculte.model.Cour;
import com.myproject.faculte.model.Enseignant;
import com.myproject.faculte.model.Groupe;
import com.myproject.faculte.repository.CourRepository;
import com.myproject.faculte.repository.EnseignantRepository;
import com.myproject.faculte.repository.GroupeRepository;

@SpringBootTest
class FaculteApplicationTests {

	@Autowired
	private EnseignantRepository enseignantRepository;

	@Autowired
	private CourRepository courRepository;
	
	@Autowired
	private GroupeRepository groupeRepository;

	// methode de test pour enregistrer un enseignant
	@Test
	public void testCreateEnseignant() {
		Enseignant ens = new Enseignant("bouba", "diop", "2255, rue tillemont", "diop@hotmail.fr", "4355423654",
				"contactuel");
		enseignantRepository.save(ens);
	}

	// methode de test pour chercher un enseignant
	@Test
	public void testFindEnseignant() {
		Enseignant e = enseignantRepository.findById(2L).get();
		System.out.println(e.getFirstName()+" "+e.getLastName());
	}

	// methode de test pour modifier un enseignant
	@Test
	public void testUpdateEnseignant() {
		Enseignant e = enseignantRepository.findById(2L).get();
		e.setStatut("vacataire");
		enseignantRepository.save(e);
	}

	// methode de test pour supprimer un enseignant
	@Test
	public void testDeleteEnseignant() {
		enseignantRepository.deleteById(6L);
		;
	}

	// methode de test pour lister les enseignants
	@Test
	public void testListerTousEnseignants() {
		List<Enseignant> ens = enseignantRepository.findAll();
		for (Enseignant e : ens) {
			System.out.println(e.getFirstName()+" "+e.getLastName());
		}
	}
	// methode de test pour lister les cours dont le nom est donné en parametre
	@Test
	public void testFindByLibelle() {
		List<Cour> cours = courRepository.findByLibelle("Base de données");
		for (Cour c : cours) {
			System.out.println(c.getLibelle());
		}
	}
	
	// methode de test pour lister les cours dont le nom contient le mot qui est donné en parametre
	@Test
	public void testFindByLibelleContains() {
		List<Cour> cours = courRepository.findByLibelleContains("app");
		for (Cour c : cours) {
			System.out.print(c.getLibelle());
		}
	}
	
	@Test public void testfindByFirstNameLastNameStatut()
	{
	List<Enseignant> ens = enseignantRepository.findByFirstNameLastNameStatut("moussa", "fall","vacataire");
	for (Enseignant e : ens)
	{
		System.out.println(e.getFirstName()+" "+e.getLastName()+" "+e.getStatut());
	}
	}
	// test pour trouver la liste des enseignants selon le statut
	@Test public void testfindByEnseignantStatut()
	{
	List<Enseignant> ens = enseignantRepository.findByEnseignantStatut("vacataire");
	for (Enseignant e : ens)
	{
		System.out.println(e.getFirstName()+" "+e.getLastName()+" "+e.getStatut());
	}
	}
	
	// test pour trouver la liste des cours d'un enseignant donné
	@Test
	public void testfindByEnseignantCours()
	{
		Enseignant ens = new Enseignant();
	    ens.setId(1L);
	List<Cour> cours = enseignantRepository.findByEnseignantCours(ens);
	for (Cour c : cours)
	{
	System.out.println(c.getLibelle());
	}
	
	
}
	//trouver la liste des cours d'un enseignant selon l'id donné
	@Test
	public void testfindByEnseignantId()
	{
		
	List<Cour> cours = courRepository.findByEnseignantId(2L);
		for (Cour c : cours){
		System.out.println(c.getLibelle());
		}
	}
	
	//test pour trouver la liste des groupes d'une formation selon l'id de la formation donné
		@Test
		public void testfindByFormationId()
		{
			
		List<Groupe> groupes = groupeRepository.findByFormationId(2L);
		for (Groupe g : groupes)
		{
		System.out.println(g.getId());
		}
		}
		
		//test pour  trouver la liste des Enseignants pour un groupe
		@Test public void testfindByGroupesId()
		{
		List<Enseignant> ens = enseignantRepository.findByGroupesId(2L);
		for (Enseignant e : ens)
		{
			System.out.println(e.getFirstName());
		}
		}
		//test pour trouver la liste des groupes pour un enseignant
		@Test public void testfindByEnseignantsId()
		{
		List<Groupe> grps = groupeRepository.findByEnseignantsId(2L);
		for (Groupe g : grps)
		{
			System.out.println(g.getId());
		}
		}
}