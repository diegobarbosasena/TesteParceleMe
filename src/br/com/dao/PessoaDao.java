package br.com.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ajudantes.MySqlConnectionFactory;
import br.com.model.Pessoa;
import javafx.scene.image.Image;

public class PessoaDao {

	private Connection connection;
	public Image image;
	
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
	
	public void insertPessoa(Pessoa pessoa, InputStream foto )  {
			
		String sql = "insert into pessoa (nome, sobrenome, telefone, foto) values (?,?,?,?)";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, pessoa.getNome());
			preparedStatement.setString(2, pessoa.getSobrenome());
			preparedStatement.setString(3, pessoa.getTelefone());
			
			preparedStatement.setBinaryStream(4, foto);
			
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
	
	public void updatePessoa(Pessoa pessoa, InputStream foto) {
	
		String sql = "update pessoa set nome = ?, sobrenome = ?, telefone = ?, foto = ? where id = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, pessoa.getNome());
			preparedStatement.setString(2, pessoa.getSobrenome());
			preparedStatement.setString(3, pessoa.getTelefone());
			
			preparedStatement.setBinaryStream(4, foto);
			
			preparedStatement.setLong(5, pessoa.getId());
			
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
	
	public Image getFoto(Long id) {
		
		String sql = "select * from pessoa where id = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				InputStream is = resultSet.getBinaryStream("foto");
				OutputStream os = new FileOutputStream(new File("photo.jpg"));
				
				byte[] content = new byte[1024];
				int size = 0;
				
				while ((size = is.read(content)) != -1) {
					os.write(content, 0, size);
				}
				os.close();
				is.close();
				
				image = new Image("file:photo.jpg", true);
			}
			
			preparedStatement.close();
			resultSet.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
}
