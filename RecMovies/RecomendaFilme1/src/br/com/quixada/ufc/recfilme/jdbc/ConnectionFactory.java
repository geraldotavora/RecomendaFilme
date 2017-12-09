package br.com.quixada.ufc.recfilme.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private final String ip = "localhost";
	private final Integer port = 5432;
	private final String user = "postgres";
	private final String password = "cassiano07";
	private final String database = "RecomendaFilme";
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://"+ip+":"+port+"/"+database, user, password);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
