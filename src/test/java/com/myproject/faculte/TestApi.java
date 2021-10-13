package com.myproject.faculte;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.myproject.faculte.restcontroller.EnseignantRestController;
import com.myproject.faculte.service.EnseignantService;

@WebMvcTest(controllers = EnseignantRestController.class)
public class TestApi {
	 @Autowired
	    private MockMvc mockMvc;
	 @MockBean
	 private EnseignantService enseignantService;
	 
	 @Test
	    public void testGetEnseignants() throws Exception {
	        mockMvc.perform(get("/Enseignants"))
	            .andExpect(status().isOk()); //on attend une reponse 200 ok
	    }

}
