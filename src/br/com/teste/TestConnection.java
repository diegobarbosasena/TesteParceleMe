package br.com.teste;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.ajudantes.MySqlConnectionFactory;

public class TestConnection {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = new MySqlConnectionFactory().getConnection();
		System.out.println("Connectou");
		connection.close();
	}
}
