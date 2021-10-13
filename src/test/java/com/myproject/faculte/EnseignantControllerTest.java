package com.myproject.faculte;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class EnseignantControllerTest {
	
	 @Autowired
	    private MockMvc mockMvc;
	 
	 @Test
	    public void testGetEnseignants() throws Exception {
	        mockMvc.perform(get("/Enseignants"))
	            .andExpect(status().isOk())   //on attend une reponse 200 ok
	            .andExpect(jsonPath("$[0].firstName", is("Gogo")));
	    }
	 
	

}
