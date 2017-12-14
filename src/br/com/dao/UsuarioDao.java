package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.ajudantes.MySqlConnectionFactory;
import br.com.model.Usuario;

public class UsuarioDao {

	private Connection connection;
	
	
	public UsuarioDao() {
		
		try {
			this.connection = new MySqlConnectionFactory().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertUsuario(Usuario usuario) {
		
		String sql = "insert into usuario (usuario, senha) values (?,?)";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, usuario.getUsuario());
			preparedStatement.setString(2, usuario.getSenha());
			
			preparedStatement.execute();
			preparedStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean checkLogin(String usuario, String senha) {
		
		boolean check = false;
		
		String sql = "select * from usuario where usuario = ? and senha = ?";
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
			
			preparedStatement.setString(1, usuario);
			preparedStatement.setString(2, senha);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
				check = true;
			}
			resultSet.close();
			preparedStatement.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
}

