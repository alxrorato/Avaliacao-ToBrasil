package br.com.alunos.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alunos.error.ResourceNotFoundException;
import br.com.alunos.model.Aluno;
import br.com.alunos.repository.AlunoRepository;

@Service
public class AlunoServiceOperacoes implements AlunoService {

	@Autowired
	private AlunoRepository alunoDAO;
	
	@Override
	public Iterable<Aluno> buscarAlunos() {
		return alunoDAO.findAll();
	}

	@Override
	public Aluno incluirAluno(@Valid Aluno aluno) {
		return alunoDAO.save(aluno);
	}

	@Override
	public Optional<Aluno> buscarAluno(long id) {
		verificaSeAlunoExiste(id);
		return alunoDAO.findById(id);
	}

	@Override
	public List<Aluno> buscarAlunoByNome(String nome) {
		return alunoDAO.findByNomeIgnoreCaseContaining(nome);
	}

	@Override
	public Aluno atualizarAluno(Aluno aluno) {
		verificaSeAlunoExiste(aluno.getId());
		return alunoDAO.save(aluno);
	}

	@Override
	public void excluirAluno(Long id) {
		verificaSeAlunoExiste(id);
		alunoDAO.deleteById(id);
	}

	private boolean alunoExiste(Long id) {
		return(alunoDAO.findById(id).isPresent());
	}

	private void verificaSeAlunoExiste(Long id) {
		if (!alunoExiste(id))
			throw new ResourceNotFoundException("Aluno não encontrado para o ID: " + id);
	}
	
}
