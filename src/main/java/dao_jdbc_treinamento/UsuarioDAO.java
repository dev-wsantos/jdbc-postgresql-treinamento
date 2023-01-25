package dao_jdbc_treinamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao_jdbc.SingleConnection;
import models_jdbc_treinamento.Telefone;
import models_jdbc_treinamento.TelefoneUsuario;
import models_jdbc_treinamento.Usuario;

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Usuario usuario) {
		StringBuilder sql = new StringBuilder();

		sql.append("insert into public.usuario(nome, email) ").append("values (?, ?);");

		try {

			PreparedStatement statement = connection.prepareStatement(sql.toString());

			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getEmail());

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public List<Usuario> listar() throws SQLException {

		List<Usuario> lista = new ArrayList<Usuario>();

		StringBuilder sql = new StringBuilder();
		sql.append("select id, nome, email from public.usuario;");

		PreparedStatement statement = connection.prepareStatement(sql.toString());
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {

			Usuario usuario = new Usuario();

			usuario.setId(rs.getLong("id"));
			usuario.setNome(rs.getString("nome"));
			usuario.setEmail(rs.getString("email"));

			lista.add(usuario);
		}

		return lista;
	}

	public Usuario buscar(Long id) throws SQLException {

		Usuario usuario = new Usuario();

		StringBuilder sql = new StringBuilder();
		sql.append("select id, nome, email FROM public.usuario WHERE id = " + id);

		PreparedStatement statement = connection.prepareStatement(sql.toString());
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {

			usuario.setId(rs.getLong("id"));
			usuario.setNome(rs.getString("nome"));
			usuario.setEmail(rs.getString("email"));
		}

		return usuario;
	}

	public void atualizar(Usuario usuario) {
		StringBuilder sql = new StringBuilder();

		sql.append("update public.usuario set nome = ?, email = ? where id = " + usuario.getId());

		try {

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getEmail());

			statement.execute();
			connection.commit();

		} catch (SQLException e) {

			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deletar(Long id) {
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("delete from public.usuario where id = " + id);

			PreparedStatement statement = connection.prepareStatement(sql.toString());

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void salvarTelefone(Telefone telefone) {

		try {
			StringBuilder sql = new StringBuilder();

			sql.append("insert into public.telefone (ddd, numero, tipo, usuario_id)").append(" values(?, ?, ?, ?);");

			PreparedStatement statement = connection.prepareStatement(sql.toString());
			statement.setString(1, telefone.getDdd());
			statement.setString(2, telefone.getNumero());
			statement.setString(3, telefone.getTipo());
			statement.setLong(4, telefone.getUsuario());

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
				e.printStackTrace();
			} catch (Exception e2) {
				e.printStackTrace();
			}
		}
	}

	public List<TelefoneUsuario> listaUsuariosTelefone() throws SQLException {

		List<TelefoneUsuario> lista = new ArrayList<TelefoneUsuario>();

		StringBuilder sql = new StringBuilder();

		sql.append("select us.nome, ").append("us.email, ").append("tl.ddd, ").append("tl.numero, ").append("tl.tipo, ")
				.append("us.id ").append("FROM telefone tl ").append("INNER JOIN usuario us ")
				.append("ON tl.usuario_id = us.id");

		PreparedStatement statement = connection.prepareStatement(sql.toString());
		ResultSet rs = statement.executeQuery();

		List<Usuario> usuarios = listar();

		while (rs.next()) {

			TelefoneUsuario telefoneUsuario = new TelefoneUsuario();

			telefoneUsuario.setDdd(rs.getString("ddd"));
			telefoneUsuario.setNumero(rs.getString("numero"));
			telefoneUsuario.setTipo(rs.getString("tipo"));

			for (Usuario u : usuarios) {

				telefoneUsuario.usuario = u;

				telefoneUsuario.usuario.setId(rs.getLong("id"));
				telefoneUsuario.usuario.setNome(rs.getString("nome"));
				telefoneUsuario.usuario.setEmail(rs.getString("email"));

			}

			lista.add(telefoneUsuario);
		}

		return lista;
	}

	public TelefoneUsuario buscarTelefone(Long id) throws SQLException {

		TelefoneUsuario telefone = new TelefoneUsuario();

		StringBuilder sql = new StringBuilder();
		sql.append("select id, ddd, numero from public.telefone where id = " + id);

		PreparedStatement statement = connection.prepareStatement(sql.toString());
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			telefone.setId(rs.getLong("id"));
			telefone.setDdd(rs.getString("ddd"));
			telefone.setNumero(rs.getString("numero"));
		}

		return telefone;

	}

	public void atualizarTelefone(TelefoneUsuario telefone) {

		try {

			StringBuilder sql = new StringBuilder();

			sql.append("update public.telefone ").append("set ddd = ?, numero = ?, tipo = ?, usuario_id = ? ")
					.append("where id = " + telefone.getId());

			PreparedStatement statement = connection.prepareStatement(sql.toString());

			statement.setString(1, telefone.getDdd());
			statement.setString(2, telefone.getNumero());
			statement.setString(3, telefone.getTipo());
			statement.setLong(4, telefone.getUsuario().getId());

			statement.execute();
			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();
				e.printStackTrace();

			} catch (Exception e2) {
				// TODO: handle exception
			}
			e.printStackTrace();
		}
	}

	public void deletarTelefone(Long idUsuario) {
		try {

			StringBuilder sqlFone = new StringBuilder();
			sqlFone.append("delete from telefone where usuario_id = " + idUsuario);

			StringBuilder sqlUsuario = new StringBuilder();
			sqlUsuario.append("delete from usuario where id = " + idUsuario);

			PreparedStatement statementFone = connection.prepareStatement(sqlFone.toString());
			statementFone.executeUpdate();
			connection.commit();

			PreparedStatement statementUsuario = connection.prepareStatement(sqlUsuario.toString());
			statementUsuario.executeUpdate();
			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();

			} catch (Exception ex) {
				ex.printStackTrace();

			}

			e.printStackTrace();
		}

	}
}
