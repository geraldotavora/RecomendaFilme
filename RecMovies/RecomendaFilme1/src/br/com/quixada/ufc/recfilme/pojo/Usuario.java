package br.com.quixada.ufc.recfilme.pojo;

public class Usuario {
	private int codigo;
	private String nome;
	private String email;
	
	
	public Usuario(Integer codigo, String nome, String email) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
	}
	
	public Usuario(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Usuario [Id=" + codigo + ", Nome=" + nome + ", Email=" + email + "]";
	}
}
