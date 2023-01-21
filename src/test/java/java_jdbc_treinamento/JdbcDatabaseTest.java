package java_jdbc_treinamento;

import org.junit.Test;

import conexao_jdbc.SingleConnection;

public class JdbcDatabaseTest {

	@Test
	public void initDatabase() {
		SingleConnection.getConnection();
	}
}
