package br.com.alunos;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.alunos.model.Aluno;
import br.com.alunos.repository.AlunoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AlunoRepositoryTest {

	@Autowired
	private AlunoRepository alunoRepository;
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void insertAluno() {
		System.out.println("Dentro do metodo de teste de insert");
		Aluno aluno = new Aluno("Roberto", 18);
		this.alunoRepository.save(aluno);
		System.out.println("Id do aluno: " + aluno.getId());
		Assertions.assertThat(aluno.getId()).isNotNull();
		Assertions.assertThat(aluno.getNome()).isEqualTo("Roberto");
		Assertions.assertThat(aluno.getIdade()).isEqualTo(18);
	}
	
	@Test
	public void deleteAluno() {
		System.out.println("Dentro do metodo de teste de delete");
		Aluno aluno = new Aluno("Alexandre", 20);
		this.alunoRepository.save(aluno);
		alunoRepository.delete(aluno);
		Assertions.assertThat(alunoRepository.findById(aluno.getId())).isEmpty();
	}	
	
	@Test
	public void updateData() {
		System.out.println("Dentro do metodo de teste de update");
		//Cria o aluno
		Aluno aluno = new Aluno("Sergio", 35);
		this.alunoRepository.save(aluno);
		//Altera o aluno
		aluno.setNome("Sergio da Silva");
		aluno.setIdade(38);
		this.alunoRepository.save(aluno);
		Optional<Aluno> alunoUpd = this.alunoRepository.findById(aluno.getId());
		Assertions.assertThat(alunoUpd.get().getNome()).isEqualTo("Sergio da Silva");
		Assertions.assertThat(alunoUpd.get().getIdade()).isEqualTo(38);
	}	
	
	/*
	 * Testa o no case sensitive para o nome do aluno.
	 * O método findByNomeIgnoreCaseContaining deverá retornar 2 registros na lista. 
	 */
	@Test
	public void findByNomeIgnoreCaseContainingTeste() {
		System.out.println("Dentro do metodo de teste findByNomeIgnoreCaseContainingTeste");
		Aluno aluno = new Aluno("Ricardo Pereira", 16);
		Aluno aluno2 = new Aluno("RICARDO da Silva", 27);
		this.alunoRepository.save(aluno);
		this.alunoRepository.save(aluno2);
		List<Aluno> alunoList = alunoRepository.findByNomeIgnoreCaseContaining("ricardo");
		Assertions.assertThat(alunoList.size()).isEqualTo(2);
	}	
	
	/*
	@Test
	public void insertSeNomeNullGeraException() {
		thrown.expect(ConstraintViolationException.class);
		//Instancia o objeto sem o nome do aluno
		Aluno aluno = new Aluno();
		aluno.setIdade(33);
		this.alunoRepository.save(aluno);
	}
	*/

}
