package br.com.alunos.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import br.com.alunos.model.Aluno;

public interface AlunoService {

	Iterable<Aluno> buscarAlunos();

	Aluno incluirAluno(@Valid Aluno aluno);

	Optional<Aluno> buscarAluno(long id);
	
	List<Aluno> buscarAlunoByNome(String nome);
	
	Aluno atualizarAluno(@Valid Aluno aluno);
	
	void excluirAluno(Long id);
	
}
