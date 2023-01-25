package models_jdbc_treinamento;

import java.util.Objects;

public class TelefoneUsuario {

	private Long id;
	private String ddd;
	private String numero;
	private String tipo;

	public Usuario usuario;

	public TelefoneUsuario() {
		usuario = new Usuario();
	}

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TelefoneUsuario other = (TelefoneUsuario) obj;
		return Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return String.format("TelefoneUsuario [ddd=%s, numero=%s]", ddd, numero);
	}

}
