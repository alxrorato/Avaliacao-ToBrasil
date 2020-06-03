package br.com.alunos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@NotEmpty(message = "O campo nome é obrigatório")
	String nome;
	
	@NotNull(message = "O campo idade é obrigatório")
	Integer idade;
	
	public Aluno() {
	}
	
	public Aluno(Long id, @NotEmpty(message = "O campo nome é obrigatório") String nome,
			@NotNull(message = "O campo idade é obrigatório") Integer idade) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
	}

	public Aluno(@NotEmpty(message = "O campo nome é obrigatório") String nome,
			@NotNull(message = "O campo idade é obrigatório") Integer idade) {
		this.nome = nome;
		this.idade = idade;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getIdade() {
		return idade;
	}
	
	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
