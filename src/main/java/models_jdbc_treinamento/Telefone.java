package models_jdbc_treinamento;

import java.util.Objects;

public class Telefone {

	private Long id;
	private String ddd;
	private String numero;
	private String tipo;

	private Long usuarioId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getUsuario() {
		return usuarioId;
	}

	public void setUsuario(Long usuario) {
		this.usuarioId = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, usuarioId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		return Objects.equals(id, other.id) && Objects.equals(usuarioId, other.usuarioId);
	}

	@Override
	public String toString() {
		return String.format("Telefone [id=%s, ddd=%s, numero=%s, usuarioId=%s]", id, ddd, numero, usuarioId);
	}

}
