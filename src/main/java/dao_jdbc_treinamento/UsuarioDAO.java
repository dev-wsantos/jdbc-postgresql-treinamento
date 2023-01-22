package dao_jdbc_treinamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao_jdbc.SingleConnection;
import models_jdbc_treinamento.Usuario;

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Usuario usuario) {
		StringBuilder sql = new StringBuilder();

		sql.append("insert into public.usuario(nome, email) ")
		   .append("values (?, ?);");

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
		
		while(rs.next()) {
			
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
}
