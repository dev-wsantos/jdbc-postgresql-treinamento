package java_jdbc_treinamento;

import org.junit.Test;

import conexao_jdbc.SingleConnection;
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
}
