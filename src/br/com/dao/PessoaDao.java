package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ajudantes.MySqlConnectionFactory;
import br.com.model.Pessoa;

public class PessoaDao {

	private Connection connection;
	
	public PessoaDao() {
		
		try {
			this.connection = new MySqlConnectionFactory().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Pessoa> getPessoas(){
		
		List<Pessoa> lstPessoas = new ArrayList<Pessoa>();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("select * from pessoa");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Pessoa pessoa = new Pessoa();
				
				pessoa.setId(resultSet.getLong("id"));
				pessoa.setNome(resultSet.getString("nome"));
				pessoa.setSobrenome(resultSet.getString("sobrenome"));
				pessoa.setTelefone(resultSet.getString("telefone"));
				
				lstPessoas.add(pessoa);
			}
			
			resultSet.close();
			preparedStatement.close();
			
			return lstPessoas;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public void insertPessoa(Pessoa pessoa)  {
			
		String sql = "insert into pessoa (nome, sobrenome, telefone) values (?,?,?)";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, pessoa.getNome());
			preparedStatement.setString(2, pessoa.getSobrenome());
			preparedStatement.setString(3, pessoa.getTelefone());
			
			preparedStatement.execute();
			preparedStatement.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void deletePessoa(Pessoa pessoa) {
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("delete from pessoa where id = ?");
			preparedStatement.setLong(1, pessoa.getId());
			preparedStatement.execute();
			preparedStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void updatePessoa(Pessoa pessoa) {
	
		String sql = "update pessoa set nome = ?, sobrenome = ?, telefone = ? where id = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, pessoa.getNome());
			preparedStatement.setString(2, pessoa.getSobrenome());
			preparedStatement.setString(3, pessoa.getTelefone());
			preparedStatement.setLong(4, pessoa.getId());
			
			preparedStatement.execute();
			preparedStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Pessoa> getPessoa(Long id) {
		List<Pessoa> lstPessoa = new ArrayList<>();
		
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement("select * from pessoa where id = ?");
			
			preparedStatement.setLong(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Pessoa pessoa = new Pessoa();
				
				pessoa.setId(resultSet.getLong("id"));
				pessoa.setNome(resultSet.getString("nome"));
				pessoa.setSobrenome(resultSet.getString("sobrenome"));
				pessoa.setTelefone(resultSet.getString("telefone"));
				
				lstPessoa.add(pessoa);
			}
			resultSet.close();
			preparedStatement.close();
			
			return lstPessoa;	
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
