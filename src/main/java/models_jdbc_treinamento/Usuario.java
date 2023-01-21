package models_jdbc_treinamento;

import java.util.Objects;

public class Usuario {

	private Long id;
	private String nome;
	private String email;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();

		builder
			.append("Usuario [id]: ").append(id)
			.append(", [nome]: ").append(nome)
			.append(", [email]: ").append(email);

		return builder.toString();
	}
	
	
	
	
}
