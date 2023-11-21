package com.somativa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.somativa.entitie.Curso;
import com.somativa.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/curso")
@Tag(name = "Curso", description="API REST DE GERENCIAMENTO DE CURSOS")
public class CursoController {
	
	private final CursoService cursoService; 

	@Autowired 
	public CursoController(CursoService cursoService) { 
	this.cursoService = cursoService; 
	} 

	@PostMapping("/") 
	@Operation(summary = "Cadastra um curso") 
	public ResponseEntity<Curso> createCurso(@RequestBody @Valid Curso curso) { 
		Curso salvaCurso = cursoService.salvaCurso(curso); 
	return ResponseEntity.status(HttpStatus.CREATED).body(salvaCurso); 
	} 

	@PutMapping("/{id}") 
	@Operation(summary = "Alterar um curso") 
	public ResponseEntity<Curso> alteraCursoControl(@PathVariable Long id, @RequestBody @Valid Curso curso){ 
		Curso alteraCurso = cursoService.alteraCurso(id, curso); 
		if (alteraCurso != null) { 
			return ResponseEntity.ok(curso); 
		} 
		else { 
			return ResponseEntity.notFound().build(); 
		} 
	} 

	@GetMapping("/{id}") 
	@Operation(summary = "Localiza curso por ID") 
		public ResponseEntity<Curso> buscaCursoControlId (@PathVariable Long id){ 
			Curso curso = cursoService.buscaCursoById(id); 
			if(curso != null) { 
				return ResponseEntity.ok(curso); 
			} 
			else { 
				return ResponseEntity.notFound().build(); 
			} 
		} 

		@GetMapping("/") 
		@Operation(summary = "Apresenta todos os cursos") 
		public ResponseEntity<List<Curso>> buscaTodosCursosControl(){ 
			List<Curso> curso = cursoService.buscaTodosCursos(); 
			return ResponseEntity.ok(curso); 
		} 

	@DeleteMapping("/{id}") 
	@Operation(summary = "Apagar um curso") 
	public ResponseEntity<Curso> apagarCursoControl(@PathVariable Long id){ 
		boolean apagar = cursoService.apagarCurso(id); 
		if (apagar) { 
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
		} 
		else { 
			return ResponseEntity.notFound().build(); 	} 

	} 

}
