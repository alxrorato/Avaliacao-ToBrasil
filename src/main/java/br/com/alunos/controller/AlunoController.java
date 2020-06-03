package br.com.alunos.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alunos.model.Aluno;
import br.com.alunos.service.AlunoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@GetMapping(path = "alunos")
	@ApiOperation(value = "Retorna a lista de todos os alunos", response = Aluno[].class)
	public ResponseEntity<?> listAll() {
		return new ResponseEntity<>(alunoService.buscarAlunos(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/alunos/{id}")
	@ApiOperation(value = "Retorna o aluno correspondente ao id informado", response = Aluno[].class)
	public ResponseEntity<?> getAlunoById(@PathVariable("id") Long id) {
		Optional<Aluno> aluno = alunoService.buscarAluno(id);
		return new ResponseEntity<>(aluno, HttpStatus.OK);
	}
	
	@GetMapping(path = "/alunos/findByNome/{nome}")
	public ResponseEntity<?> findAlunosByNome(@PathVariable String nome) {
		return new ResponseEntity<>(alunoService.buscarAlunoByNome(nome), HttpStatus.OK);
	}

	@PostMapping(path = "alunos/")
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation(value = "Insere um aluno")
	public ResponseEntity<?> save(@RequestBody @Valid Aluno aluno) {
		return new ResponseEntity<>(alunoService.incluirAluno(aluno), HttpStatus.CREATED);
	}

	@DeleteMapping(path = "alunos/{id}")
	@ApiOperation(value = "Exclui o aluno correspondente ao id informado")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		alunoService.excluirAluno(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(path = "alunos/")
	@ApiOperation(value = "Atualiza o cadastro do aluno")
	public ResponseEntity<?> update(@Valid @RequestBody Aluno aluno) {
		alunoService.atualizarAluno(aluno);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
