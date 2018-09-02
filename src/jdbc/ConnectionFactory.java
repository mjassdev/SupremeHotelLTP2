package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
	
	public Connection getConnection() {
		try {
			String nomeUsuario = "ltp2";
			String senhaUsuario = "123";
			String enderecoServidor = "localhost";
			String nomeBanco = "hotelDB";
			
			return DriverManager.getConnection("jdbc:postgresql://"+enderecoServidor+"/"+nomeBanco, nomeUsuario, senhaUsuario);

		}
		catch (SQLException ex){
			System.out.println("Erro");
			
			throw new RuntimeException(ex);
	}

		
	}
	
}
