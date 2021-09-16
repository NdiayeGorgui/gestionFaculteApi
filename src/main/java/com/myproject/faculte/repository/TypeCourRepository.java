package com.myproject.faculte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.faculte.model.TypeCour;


@Repository
public interface TypeCourRepository extends JpaRepository<TypeCour, Long> {

}
