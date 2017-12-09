package br.com.quixada.ufc.recfilme.pojo;

public class Ator {
	private int id;
	private String nome;
	
	public Ator(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Ator(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Genero [Id=" + id + ", Nome=" + nome + "]";
	}
}
