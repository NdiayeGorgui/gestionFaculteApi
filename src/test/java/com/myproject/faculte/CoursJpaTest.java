package com.myproject.faculte;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.myproject.faculte.model.Cour;
import com.myproject.faculte.model.Enseignant;
import com.myproject.faculte.model.TypeCour;
import com.myproject.faculte.repository.CourRepository;
import com.myproject.faculte.service.CourService;


@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class CoursJpaTest {

	@Autowired
	private CourService courService;

	@MockBean
	private CourRepository courRepository;

	@Test
	@Order(1)
	@Rollback(value = false)
	public void saveCoursTest() {

		Cour cours1 = new Cour();
		// cours1.setId(1L);
		cours1.setLibelle("maths");
		cours1.setNbeHeure(25);

		when(courRepository.save(cours1)).thenReturn(cours1);
		// Assertions.assertThat(cours1.getId()).isGreaterThan(0);
		assertEquals(cours1, courService.saveCour(cours1));

	}

	@Test
	@Order(2)
	public void getCoursByIdTest() {

		Cour cours1 = new Cour();
		cours1.setId(1L);
		cours1.setLibelle("maths");
		cours1.setNbeHeure(25);

		when(courService.getCour(1L)).thenReturn(Optional.of(cours1));
		Assertions.assertThat(cours1.getId()).isEqualTo(1L);

	}

	@Test
	@Order(3)
	public void getListOfCoursesTest() {
		Cour cours1 = new Cour();
		cours1.setId(1L);
		cours1.setLibelle("maths");
		cours1.setNbeHeure(25);
		
		Cour cours2 = new Cour();
		cours2.setId(2L);
		cours2.setLibelle("comptabilité");
		cours2.setNbeHeure(25);
		
		when(courService.getAllCours()).thenReturn(Stream.of(cours1,cours2).collect(Collectors.toList()));
		assertEquals(2, courService.getAllCours().size());
		// oubien
		//List<Cour> cours = courService.getAllCours();
		//cours.add(cours1);
		//cours.add(cours2);
		//Assertions.assertThat(cours.size()).isEqualTo(2);

	}

	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateCoursByIdTest() {

		Cour cours1 = new Cour();
		cours1.setId(1L);
		cours1.setLibelle("maths");
		cours1.setNbeHeure(25);

		when(courRepository.save(cours1)).thenReturn(cours1);
		cours1.setLibelle("anglais");
		Cour coursUdated = courService.saveCour(cours1);

		Assertions.assertThat(coursUdated.getLibelle()).isEqualTo("anglais");

	}

	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteEmployeeByIdTest() {

		Cour cours1 = new Cour();
		cours1.setId(1L);
		cours1.setLibelle("maths");
		cours1.setNbeHeure(25);

		when(courRepository.save(cours1)).thenReturn(cours1);
		courService.deleteCourById(1L);

		Cour cours2 = null;
		Optional<Cour> optionalCours = courService.getCour(1L);
		if (optionalCours.isPresent()) {
			cours2 = optionalCours.get();
		}

		Assertions.assertThat(cours2).isNull();

	}

}
