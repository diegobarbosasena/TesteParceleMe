package br.com.ajudantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionFactory {
	
	String jdbc = "jdbc:mysql://"; 
	String host = "localhost";
	String database = "dbpessoa";
	String user_database = "root";
	String senha_database = "1234";
	

	public Connection getConnection() throws SQLException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(jdbc + host + "/" + database, user_database, senha_database);
	}
}
