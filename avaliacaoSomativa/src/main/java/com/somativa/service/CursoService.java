package com.somativa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somativa.entitie.Curso;
import com.somativa.repository.CursoRepository;

@Service
public class CursoService {
	
private final CursoRepository cursoRepository;
	
	@Autowired
	public CursoService (CursoRepository cursoRepository) {
		this.cursoRepository = cursoRepository;
	}
	
	public List<Curso> buscaTodosCursos(){
		return cursoRepository.findAll();
	}
	
	public Curso buscaCursoById(Long id) {
		Optional <Curso> curso = cursoRepository.findById(id);
		return curso.orElse(null);
	}
	
	public Curso salvaCurso (Curso curso) {
		return cursoRepository.save(curso);
	}
	
	public Curso alteraCurso(long id, Curso alteraCurso) {
		Optional <Curso> existeCurso = cursoRepository.findById(id);
		if (existeCurso.isPresent()) {
			alteraCurso.setId(id);
			return cursoRepository.save(alteraCurso);
		}
		return null;
	}
	public boolean apagarCurso(Long id) {
		Optional <Curso> existeCurso = cursoRepository.findById(id);
		if (existeCurso.isPresent()) {
			cursoRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
