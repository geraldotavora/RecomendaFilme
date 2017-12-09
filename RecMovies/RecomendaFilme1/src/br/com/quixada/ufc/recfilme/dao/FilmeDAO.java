package br.com.quixada.ufc.recfilme.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.quixada.ufc.recfilme.jdbc.ConnectionFactory;
import br.com.quixada.ufc.recfilme.pojo.*;

public class FilmeDAO {
	private Connection connection;
	
	public FilmeDAO() {}
	
	public boolean addFilme(Filme filme) {
		String sql = "INSERT INTO filme (nome, genero, ator_principal, ator_coadjuvante, duracao,"
				+ "nome_diretor, data_lancamento) VALUES(?, ?, ?, ?, ?, ?, ?);";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);	
			java.sql.Date sqlDate = new java.sql.Date(filme.getLancamento().getTime());
			stmt.setString(1, filme.getNomefilme());
			stmt.setString(2, filme.getGenero());
			stmt.setString(3, filme.getAtor_principal());
			stmt.setString(4, filme.getAtor_coadjuvante());
			stmt.setString(5, filme.getDuracao());
			stmt.setString(6, filme.getNome_diretor());
			stmt.setDate(7,  sqlDate);
			int qtdAffected = stmt.executeUpdate();
			stmt.close();
			if(qtdAffected > 0) return true;
		}catch(SQLException e) {
			System.err.println(e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean deleteFilme(int id) {
		String sql = "DELETE FROM filme WHERE id = ?;";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			int qtdAffected = stmt.executeUpdate();
			stmt.close();
			if(qtdAffected > 0) return true;
			else return false;
		}catch(SQLException e) {
			System.err.println(e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return false;
	}
	
	public ArrayList<Filme> getFilmes(){
		String sql = "SELECT *FROM filme;";
		ArrayList<Filme> listaFilme = new ArrayList<>();
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
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
				listaFilme.add(filme);
			}
			stmt.close();			
		}catch(SQLException e) {
			System.err.println(e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return listaFilme;
	}
	
	public boolean updateFilme(int id, Filme filme) {
		String sql = "UPDATE filme SET nome = ?, genero = ?, ator_principal = ?,"
				+ "ator_coadjuvante = ?, duracao = ?, nome_diretor = ?, data_lancamento = ? "
				+ "WHERE id = ?;";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			java.sql.Date sqlDate = new java.sql.Date(filme.getLancamento().getTime());
			stmt.setString(1, filme.getNomefilme());
			stmt.setString(2, filme.getGenero());
			stmt.setString(3, filme.getAtor_principal());
			stmt.setString(4, filme.getAtor_coadjuvante());
			stmt.setString(5, filme.getDuracao());
			stmt.setString(6, filme.getNome_diretor());
			stmt.setDate(7, sqlDate);
			stmt.setInt(8, id);
			
			int qtdAffected = stmt.executeUpdate();
			stmt.close();
			if(qtdAffected > 0) return true;			
		}catch(SQLException e) {
			System.err.println(e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public Filme getFilmeById(int id) {
		String sql = "SELECT * FROM filme where id = ?";
		
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			Filme filme = new Filme(id, rs.getString("nome"), rs.getString("genero"), 
					rs.getString("ator_principal"), rs.getString("ator_coadjuvante"),
					rs.getString("duracao"), rs.getString("nome_diretor"), rs.getDate("data_lancamento"));
			
			stmt.close();
			return filme;
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}	
}
