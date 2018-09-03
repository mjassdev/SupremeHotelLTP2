package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			stmt.close();
			con.close();
			return true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(Cliente c) {
		String sql = "UPDATE cliente SET nome = ?, cpf = ?, endereco = ?, email = ?, cidade = ?, estado = ?, telefone = ? WHERE id = ?;";
		
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
			stmt.close();
			con.close();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(Cliente c) {
		String sql = "DELETE FROM cliente WHERE id = ?;";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, c.getId());

			stmt.execute();
			stmt.close();
			con.close();
			return true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Cliente> getList(){
		
		List<Cliente> clientes = new ArrayList<>();
		String sql = "SELECT * FROM cliente";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getLong("id"));
				c.setNome(rs.getString("nome"));
				c.setCpf(rs.getString("cpf"));
				c.setEndereco(rs.getString("endereco"));
				c.setEmail(rs.getString("email"));
				c.setCidade(rs.getString("cidade"));
				c.setEstado(rs.getString("estado"));
				c.setTelefone(rs.getString("telefone"));
				clientes.add(c);
			}
			stmt.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro na lista");
			return null;
		}
		return clientes;
	}
	
}
