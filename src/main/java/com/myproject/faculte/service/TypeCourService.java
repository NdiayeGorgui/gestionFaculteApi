package com.myproject.faculte.service;

import java.util.List;
import java.util.Optional;

import com.myproject.faculte.model.TypeCour;

public interface TypeCourService {
	
	TypeCour saveTypeCour(TypeCour tc);

	TypeCour updateTypeCour(TypeCour tc);

	void deleteTypeCour(TypeCour tc);

	void deleteTypeCourById(Long id);

	Optional<TypeCour>  getTypeCour(Long id);

	List<TypeCour> getAllTypeCours();

}
