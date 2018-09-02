package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.ConnectionFactory;
import model.Cliente;

public class ClienteDao {

	
	private Connection con;
	
	public ClienteDao(){
		this.con = new ConnectionFactory().getConnection();
	}

	public boolean add(Cliente c) {
		String sql = "INSERT INTO cliente(nome,cpf, endereco, email, cidade, estado, telefone) VALUES (?,?,?,?,?,?,?);";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getCpf());
			stmt.setString(3, c.getEndereco());
			stmt.setString(4, c.getEmail());
			stmt.setString(5, c.getCidade());
			stmt.setString(6, c.getEstado());
			stmt.setString(7, c.getTelefone());
			stmt.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
