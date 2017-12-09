package br.com.quixada.ufc.recfilme.pojo;

import java.util.Date;

public class Filme {
	private Integer id_filme;
	private String nomefilme;
	private String genero;
	private String ator_principal;
	private String ator_coadjuvante;
	private String duracao;
	private String nome_diretor;
	private Date lancamento;
	
	public Filme(Integer id_filme, String nomefilme, String genero, String ator_principal, 
			String ator_coadjuvante, String duracao, String nome_diretor, Date lancamento) {
		super();
		this.id_filme = id_filme;
		this.nomefilme = nomefilme;
		this.genero = genero;
		this.ator_principal = ator_principal;
		this.ator_coadjuvante = ator_coadjuvante;
		this.duracao = duracao;
		this.nome_diretor = nome_diretor;
		this.lancamento = lancamento;
	}
	
	public Filme(String nomefilme, String genero, String ator_principal, String ator_coadjuvante,
			String duracao, String nome_diretor, Date lancamento) {
		super();
		this.nomefilme = nomefilme;
		this.genero = genero;
		this.ator_principal = ator_principal;
		this.ator_coadjuvante = ator_coadjuvante;
		this.duracao = duracao;
		this.nome_diretor = nome_diretor;
		this.lancamento = lancamento;
	}
	
	
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getAtor_principal() {
		return ator_principal;
	}

	public void setAtor_principal(String ator_principal) {
		this.ator_principal = ator_principal;
	}

	public String getAtor_coadjuvante() {
		return ator_coadjuvante;
	}

	public void setAtor_coadjuvante(String ator_coadjuvante) {
		this.ator_coadjuvante = ator_coadjuvante;
	}

	public String getNome_diretor() {
		return nome_diretor;
	}

	public void setNome_diretor(String nome_diretor) {
		this.nome_diretor = nome_diretor;
	}

	public Integer getId_filme() {
		return id_filme;
	}
	public void setId_filme(Integer idfilme) {
		this.id_filme = idfilme;
	}
	public String getNomefilme() {
		return nomefilme;
	}
	public void setNomefilme(String nomefilme) {
		this.nomefilme = nomefilme;
	}
	public String getDuracao() {
		return duracao;
	}
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}
	public Date getLancamento() {
		return lancamento;
	}
	public void setLancamento(Date lancamento) {
		this.lancamento = lancamento;
	}
	
	@Override
	public String toString() {
		return "Filme [id=" + id_filme + ", nome=" + nomefilme + ", genero=" + genero + 
				", Ator Principal=" + ator_principal +  ", Ator coadjuvante=" + ator_coadjuvante +
				", Duração=" + duracao + ", Nome Diretor=" + nome_diretor + 
				", Data lançamento=" + lancamento + "]";
	}
}
