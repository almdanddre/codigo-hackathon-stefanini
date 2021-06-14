package com.stefanine.hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stefanine.hackathon.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
	
}
