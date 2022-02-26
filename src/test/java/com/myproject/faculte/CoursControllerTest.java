package com.myproject.faculte;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.faculte.model.Cour;
import com.myproject.faculte.restcontroller.CourRestController;
import com.myproject.faculte.service.CourService;

@WebMvcTest(CourRestController.class)

public class CoursControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CourService courService;

	@Test
	public void getCoursByIdTest() throws Exception {

		Cour cours = new Cour();
		cours.setId(1L);
		cours.setLibelle("maths");
		cours.setNbeHeure(25);

		when(courService.getCour(1L)).thenReturn(Optional.of(cours));
		// mockMvc.perform(MockMvcRequestBuilders.get("/Cours/1"))
		mockMvc.perform(MockMvcRequestBuilders.get("/Cours/{id}", 1)).andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.libelle").value("maths"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.nbeHeure").value(25)).andExpect(status().isOk());

	}

	@Test
	public void getCoursByBadIdTest() throws Exception {

		// mock the data return by the cours service class
		Cour cours = new Cour();
		cours.setId(1L);
		cours.setLibelle("maths");
		cours.setNbeHeure(25);

		when(courService.getCour(1L)).thenReturn(Optional.of(cours));

		// create a mock http request to verify the expected result

		// mockMvc.perform(MockMvcRequestBuilders.get("/Cour/1"))
		mockMvc.perform(MockMvcRequestBuilders.get("/Cours/{id}", 11)).andDo(print())
				.andExpect(status().is4xxClientError());

	}

	@Test
	public void getAllCoursTest() throws Exception {

		// mock the data return by the cours service class
		Cour cours1 = new Cour();
		cours1.setId(1L);
		cours1.setLibelle("maths");
		cours1.setNbeHeure(25);

		Cour cours2 = new Cour();
		cours2.setId(2L);
		cours2.setLibelle("informatique");
		cours2.setNbeHeure(40);

		when(courService.getAllCours()).thenReturn(Arrays.asList(cours1, cours2));

		// create a mock http request to verify the expected result

		mockMvc.perform(MockMvcRequestBuilders.get("/Cours").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				// .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].libelle", is("maths")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].libelle", is("informatique")))
				.andExpect(status().isOk());

	}

	@Test
	public void saveCoursTest() throws Exception {

		// mock the cour data that we have to save

		Cour cours = new Cour();
		cours.setId(1L);
		cours.setLibelle("maths");
		cours.setNbeHeure(25);

		when(courService.saveCour(any(Cour.class))).thenReturn(cours);

		mockMvc.perform(MockMvcRequestBuilders.post("/Cours").content(new ObjectMapper().writeValueAsString(cours))
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.libelle").value("maths"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.nbeHeure").value(25));

	}

	@Test
	public void updateCoursTest() throws Exception {

		// mock the cour data that we have to save

		Cour cours = new Cour();
		cours.setId(1L);
		cours.setLibelle("maths");
		cours.setNbeHeure(25);

		when(courService.getCour(1L)).thenReturn(Optional.of(cours));

		cours.setLibelle("anglais");
		// cours.setNbeHeure(35);
		when(courService.saveCour(any(Cour.class))).thenReturn(cours);
		Cour coursUdated = courService.saveCour(cours);

		mockMvc.perform(MockMvcRequestBuilders.put("/Cours/{id}", 1)
				.content(new ObjectMapper().writeValueAsString(coursUdated)).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.libelle").value("anglais"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.nbeHeure").value(25));

	}

	@Test
	public void deleteCoursTest() throws Exception {

		// mock the cour data that we have to save

		Cour cours = new Cour();
		cours.setId(1L);
		cours.setLibelle("maths");
		cours.setNbeHeure(25);

		when(courService.getCour(1L)).thenReturn(Optional.of(cours));

		mockMvc.perform(MockMvcRequestBuilders.delete("/Cours/{id}", 1).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").doesNotExist());

	}

}
