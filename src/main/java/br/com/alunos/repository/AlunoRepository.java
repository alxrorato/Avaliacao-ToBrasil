package br.com.alunos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alunos.model.Aluno;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long>{
	List<Aluno> findByNomeIgnoreCaseContaining(String nome);
}
