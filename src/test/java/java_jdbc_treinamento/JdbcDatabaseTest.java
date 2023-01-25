package java_jdbc_treinamento;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import dao_jdbc_treinamento.UsuarioDAO;
import models_jdbc_treinamento.Telefone;
import models_jdbc_treinamento.TelefoneUsuario;
import models_jdbc_treinamento.Usuario;

public class JdbcDatabaseTest {

	@Test
	public void initSalvar() {

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

	@Test
	public void initSalvarTelefone() {

		try {

			UsuarioDAO dao = new UsuarioDAO();

			Telefone telefone = new Telefone();
			telefone.setDdd("83");
			telefone.setNumero("111111111");
			telefone.setTipo("Redidencial");
			Usuario usuario = dao.buscar(6L);
			telefone.setUsuario(usuario.getId());

			dao.salvarTelefone(telefone);
			System.out.println("Telefone salvo");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Test
	public void listarTelefones() throws SQLException {

		UsuarioDAO dao = new UsuarioDAO();

		try {
			List<TelefoneUsuario> lista = dao.listaUsuariosTelefone();

			for (TelefoneUsuario telefone : lista) {
				System.out.println(telefone);
				System.out.println("---------------------------------");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Test
	public void initBuscarTelefone() {
		try {

			UsuarioDAO dao = new UsuarioDAO();

			TelefoneUsuario telefone = dao.buscarTelefone(3L);
			System.out.println(telefone);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void atualizarTelefone() {

		UsuarioDAO dao = new UsuarioDAO();

		try {

			TelefoneUsuario telefone = dao.buscarTelefone(10L);

			telefone.setDdd("81");
			telefone.setNumero("222222222");
			telefone.setTipo("Residencial");
			telefone.setUsuario(dao.buscar(6L));

			dao.atualizarTelefone(telefone);

			System.out.println("Telefone atualizado.");
			System.out.println(telefone);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// delete on cascade
	@Test
	public void deletarTelefone() {

		UsuarioDAO dao = new UsuarioDAO();
		dao.deletarTelefone(6L);
		System.out.println("Telefone(s) deletado(s).");

	}

}
