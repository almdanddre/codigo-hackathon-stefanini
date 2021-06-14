package com.stefanine.hackathon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stefanine.hackathon.model.Aluno;
import com.stefanine.hackathon.repository.AlunoRepository;

@CrossOrigin
@Controller
@RequestMapping(value = "/alunos", consumes = "application/x-www-form-urlencoded" )
public class alunoController {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping
	public ResponseEntity<?> listarTodos(){
		List<Aluno> alunos = alunoRepository.findAll();
		if(alunos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(alunos);
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrarAluno(@RequestBody Aluno aluno){
		System.out.println("entrei no método");
		Aluno alunoSalvo = alunoRepository.save(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
	}
	
	@DeleteMapping("/{matricula}")
	public ResponseEntity<?> removerAluno(@PathVariable Integer matricula){
		Optional<Aluno> aluno = alunoRepository.findById(matricula);
		if(aluno.isPresent()) {
			alunoRepository.deleteById(matricula);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O aluno com a matricula "+matricula+" não foi encontrado ou não existe" );		
	}
	
//	@PutMapping("/{matricula}")
//	public ResponseEntity<?> atualizarAluno(@PathVariable Integer matricula, @RequestBody Aluno alunoRecebido){
//		Optional<Aluno> aluno = alunoRepository.findById(matricula);
//		if(aluno.isPresent()) {
//			BeanUtils.copyProperties(alunoRecebido, aluno.get(), "id");
//			alunoRepository.save(aluno.get());
//			return ResponseEntity.ok(aluno.get());
//		}else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O aluno com a matricula "+matricula+" não existe ou não foi encontrado");
//		}
//	}
	
	
}
