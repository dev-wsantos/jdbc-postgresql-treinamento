package java_jdbc_treinamento;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import dao_jdbc_treinamento.UsuarioDAO;
import models_jdbc_treinamento.Usuario;

public class JdbcDatabaseTest {

	@Test
	public void initDatabase() {

		UsuarioDAO userdao = new UsuarioDAO();
		Usuario usuario = new Usuario();

		usuario.setNome("Wellington");
		usuario.setEmail("teste@gmail.com");

		userdao.salvar(usuario);
	}

	@Test
	public void initListar() {
		UsuarioDAO dao = new UsuarioDAO();
		try {
			List<Usuario> lista = dao.listar();

			for (Usuario u : lista) {
				System.out.println(u);
				System.out.println("-------------------");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Test
	public void initBuscar() {
		UsuarioDAO dao = new UsuarioDAO();
		try {
			Usuario usuario = dao.buscar(2L);
			System.out.println(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void initAtualizar() {

		try {

			UsuarioDAO dao = new UsuarioDAO();
			Usuario usuario = dao.buscar(4L);

			usuario.setNome("Paulo Júnior");
			usuario.setEmail("paulo.junior@yahoo.com");

			dao.atualizar(usuario);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void initDeletar() {

		try {
			UsuarioDAO dao = new UsuarioDAO();
			dao.deletar(2L);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
