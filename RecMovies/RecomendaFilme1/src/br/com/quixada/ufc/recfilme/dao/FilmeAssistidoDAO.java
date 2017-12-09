package br.com.quixada.ufc.recfilme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import br.com.quixada.ufc.recfilme.jdbc.ConnectionFactory;
import br.com.quixada.ufc.recfilme.pojo.*;

public class FilmeAssistidoDAO {
	private Connection connection;
	
	public FilmeAssistidoDAO() {}
	
	public boolean addFilmeAssistido(Usuario usuario, Filme filme) {
		String sql = "INSERT INTO filme_assistido (id_usuario, id_filme) VALUES (?, ?)";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, usuario.getCodigo());
			stmt.setInt(2, filme.getId_filme());
			int qtdAffect = stmt.executeUpdate();
			stmt.close();
			if(qtdAffect > 0) return true;
			else return false;
		} catch (SQLException e){
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}		
		return false;
	}
	
	public boolean removeFilmeAssistido(int id) {
		String sql = "DELETE FROM filmes_assistidos WHERE id = ?";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			int qtdAffect = stmt.executeUpdate();
			stmt.close();
			if(qtdAffect > 0) return true;
			else return false;
		}catch (SQLException e){
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}		
		return false;
	}
	
	public ArrayList<Filme> getFilmeAssistido(int id_usuario){
		String sql = "SELECT * FROM filme, filme_assistido where id = id_filme and id_usuario = ?;";
		ArrayList<Filme> listaFilmes = new ArrayList<>();
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id_usuario);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String genero = rs.getString("genero");
				String ator_principal = rs.getString("ator_principal");
				String ator_coadjuvante = rs.getString("ator_coadjuvante");
				String duracao = rs.getString("duracao");
				String nome_diretor = rs.getString("nome_diretor");
				Date data_lancamento = rs.getDate("data_lancamento");
				Filme filme = new Filme(id, nome, genero, ator_principal, ator_coadjuvante, duracao, nome_diretor, data_lancamento);
				listaFilmes.add(filme);
			}
			stmt.close();
		}catch (SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaFilmes;	
	}
}
