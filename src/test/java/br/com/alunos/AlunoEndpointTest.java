package br.com.alunos;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.alunos.model.Aluno;
import br.com.alunos.repository.AlunoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AlunoEndpointTest {

	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;
	
	@MockBean
	private AlunoRepository alunoRepository;
	
	/*
	 * Setup p/ que essas linhas sejam sempre executadas antes de cada método de teste
	 */
	@Before
	public void Setup() {
		Optional<Aluno> alunoOpt = Optional.of(new Aluno(1L, "Pedro", 26));
		BDDMockito.when(alunoRepository.findById(alunoOpt.get().getId())).thenReturn(alunoOpt);
	}	
	
	@Test
	public void getAlunosByIdWhenExisteReturn200() {
		ResponseEntity<Aluno> response = 
				restTemplate.getForEntity("/alunos/{id}", Aluno.class, 1L);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void getAlunosByIdWhenNaoExisteReturn404() {
		ResponseEntity<Aluno> response = restTemplate.getForEntity("/alunos/{id}", Aluno.class, 99L);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void deleteAlunoExistenteReturn200() {
		BDDMockito.doNothing().when(alunoRepository).deleteById(1L);
		ResponseEntity<String> exchange = restTemplate.exchange("/alunos/{id}", HttpMethod.DELETE, null, String.class, 1L);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void deleteWhenAlunoNaoExisteReturn404() {
		BDDMockito.doNothing().when(alunoRepository).deleteById(1L);
		ResponseEntity<String> exchange = restTemplate.exchange("/alunos/{id}", HttpMethod.DELETE, null, String.class, -1L);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(404);
	}
	
	@Test
	public void createTestePersistenciaReturn201() throws Exception {
		Aluno aluno = new Aluno(3L, "Vitoria", 29);
		BDDMockito.when(alunoRepository.save(aluno)).thenReturn(aluno);
		ResponseEntity<Aluno> response = restTemplate.postForEntity("/alunos/", aluno, Aluno.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
		Assertions.assertThat(response.getBody().getId()).isNotNull();
	}
	
}
