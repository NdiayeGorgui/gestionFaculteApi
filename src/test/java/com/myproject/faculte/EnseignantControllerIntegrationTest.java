package com.myproject.faculte;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import com.myproject.faculte.model.Enseignant;
import com.myproject.faculte.repository.EnseignantRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnseignantControllerIntegrationTest {
	
	@LocalServerPort
	private int port;
	private String baseURL="http://localhost";
	@Autowired
	private EnseignantRepository enseignantRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@BeforeAll
	public void init() {
		restTemplate=new RestTemplate();
	}
	@BeforeEach
	public void setUp() {
		baseURL=baseURL.concat(":").concat(port+"").concat("/api/Enseignants");
	}
	
	@Test
	public void addEnseignantTest() {
		
		Enseignant enseignant=new Enseignant();
		enseignant.setFirstName("fifi");
		enseignant.setLastName("mimi");
		enseignant.setAdress("rue tillemont");
		enseignant.setMail("mimi@test.com");
		enseignant.setTelephone("514-897-8578");
		enseignant.setStatut("Permanent");
		
		Enseignant response= restTemplate.postForObject(baseURL, enseignant, Enseignant.class);
		Assertions.assertNotNull(response.getId());
		Assertions.assertEquals("fifi", response.getFirstName());
		
		
	}
	@Test
	public void getAllEnseignantsTest() {
		List<Enseignant> enseignants=restTemplate.getForObject(baseURL, List.class);
		Assertions.assertEquals(12, enseignants.size());
		Assertions.assertEquals(13, enseignantRepository.findAll().size());
	}
	@Test
	public void findEnseignantByIdTest() {
		Enseignant enseignant=restTemplate.getForObject(baseURL+"/{id}", Enseignant.class,13);
		
		Assertions.assertAll(
				()->Assertions.assertNotNull(enseignant),
				()->Assertions.assertEquals(13,enseignant.getId()),
				()->Assertions.assertEquals("fifi",enseignant.getFirstName())
				);
		
	}
	@Test
	public void updateEnseignantByIdTest() {
		Enseignant enseignant=new Enseignant();
		enseignant.setFirstName("lili");
		enseignant.setLastName("didi");
		
		 restTemplate.put(baseURL+"/{id}", enseignant,13);
		 Enseignant enseignantFromDB=enseignantRepository.findById(13L).get();
		 Assertions.assertAll(
					()->Assertions.assertNotNull(enseignantFromDB),
					()->Assertions.assertEquals("lili",enseignantFromDB.getFirstName()),
					()->Assertions.assertEquals("didi",enseignantFromDB.getLastName())
					);

	}
	@Test
	public void deleteEnseignantByIdTest() {
		int recordCount=enseignantRepository.findAll().size();
		Assertions.assertEquals(13,recordCount);
		restTemplate.delete(baseURL+"/{id}",13);
		Assertions.assertEquals(12,enseignantRepository.findAll().size());
	 }
	
	

}
