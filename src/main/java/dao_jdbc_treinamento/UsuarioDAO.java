package dao_jdbc_treinamento;

import java.sql.Connection;
import java.sql.PreparedStatement;

import conexao_jdbc.SingleConnection;
import models_jdbc_treinamento.Usuario;

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Usuario usuario) {
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO public.usuario(nome, email) ").append("VALUES (?, ?);");

		try {

			PreparedStatement statement = connection.prepareStatement(sql.toString());

			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getEmail());

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
