package com.myproject.faculte.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.faculte.model.TypeCour;
import com.myproject.faculte.repository.TypeCourRepository;


@Service
public class TypeCourServiceImpl implements TypeCourService {
	
	@Autowired
	TypeCourRepository typeCourRepository;

	@Override
	public TypeCour saveTypeCour(TypeCour tc) {
		
		return typeCourRepository.save(tc);
	}

	@Override
	public TypeCour updateTypeCour(TypeCour tc) {
		
		return typeCourRepository.save(tc);
	}

	@Override
	public void deleteTypeCour(TypeCour tc) {
		typeCourRepository.delete(tc);
		
	}

	@Override
	public void deleteTypeCourById(Long id) {
		typeCourRepository.deleteById(id);
		
	}

	@Override
	public Optional<TypeCour> getTypeCour(Long id) {
		
		return typeCourRepository.findById(id);
	}

	@Override
	public List<TypeCour> getAllTypeCours() {
		
		return typeCourRepository.findAll();
	}

}
