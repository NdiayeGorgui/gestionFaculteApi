package com.myproject.faculte;


import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;


import com.myproject.faculte.model.Cour;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoursControllerIntegrationTest {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	// @Sql("/test.sql")
	public void getAllCoursTest() {
		List<Cour> cours = testRestTemplate.getForObject("http://localhost:" + port + "/api/Cours", List.class);

		Assertions.assertNotNull(cours);
		Assertions.assertEquals(31, cours.size());

	}

	@Test
	public void getCoursByIdTest() {
		Cour cours = testRestTemplate.getForObject("http://localhost:" + port + "/api/Cours/1", Cour.class);

		Assertions.assertNotNull(cours.getId());
		Assertions.assertEquals(1, cours.getId());
		Assertions.assertEquals("Java", cours.getLibelle());
		Assertions.assertEquals(45, cours.getNbeHeure());

	}

	@Test
	public void saveCoursTest() {
		Cour cours = new Cour();

		cours.setLibelle("english");
		cours.setNbeHeure(77);

		Cour response = testRestTemplate.postForObject("http://localhost:" + port + "/api/Cours/", cours, Cour.class);

		Assertions.assertNotNull(response.getId());
		Assertions.assertEquals("english", response.getLibelle());
		Assertions.assertEquals(77, response.getNbeHeure());

	}

	@Test
	public void updateCoursByIdTest() {
		Cour cours = testRestTemplate.getForObject("http://localhost:" + port + "/api/Cours/58", Cour.class);
		
		cours.setLibelle("Base de données Oracle");
		cours.setNbeHeure(54);

		testRestTemplate.put("http://localhost:" + port + "/api/Cours/58", cours, Cour.class);

		Assertions.assertNotNull(cours.getId());
		Assertions.assertEquals(58, cours.getId());
		Assertions.assertEquals("Base de données Oracle", cours.getLibelle());
		Assertions.assertEquals(54, cours.getNbeHeure());

	}

	@Test
	public void deleteCoursByIdTest() throws Exception {
		Cour cours = testRestTemplate.getForObject("http://localhost:" + port + "/api/Cours/37", Cour.class);

		testRestTemplate.delete("http://localhost:" + port + "/api/Cours/37", cours, Cour.class);

		Assertions.assertEquals(37, cours.getId());

	}
}
