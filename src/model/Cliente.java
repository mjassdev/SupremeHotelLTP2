package model;

import java.util.Date;

public class Cliente {
	private int id;
	private String nome;
	private String cpf;
	private String cidade;
	private String estado;
	private String telefone;
	private String endereco;
	private String email;
	
//	public Cliente(int id, String nome, String cpf, String endereco, String email, String estado, String cidade, String telefone) {
//		this.id = id;
//		this.nome = nome;
//		this.cpf = cpf;
//		this.endereco = endereco;
//		this.email = email;
//		this.estado = estado;
//		this.cidade = cidade;
//		this.telefone = telefone;
//	}
	
	public Cliente(String nome, String cpf, String endereco, String email,  String estado, String cidade, String telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.email = email;
		this.estado = estado;
		this.cidade = cidade;
		this.telefone = telefone;
	}
	
	public Cliente() {
		
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
}
