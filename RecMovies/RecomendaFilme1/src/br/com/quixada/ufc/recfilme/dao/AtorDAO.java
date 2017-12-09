package br.com.quixada.ufc.recfilme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.quixada.ufc.recfilme.jdbc.ConnectionFactory;
import br.com.quixada.ufc.recfilme.pojo.Ator;

public class AtorDAO {
	private Connection connection;
	
	public boolean addAtor(Ator ator) {
		String sql = "INSERT INTO ator (nome) VALUES(?)";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, ator.getNome());
			int qtdAffect = stmt.executeUpdate();
			stmt.close();
			if(qtdAffect > 0)
				return true;
			else 
				return false;
		}catch(SQLException e) {
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
	
	public boolean deleteAtor(int id) {
		String sql = "DELETE FROM ator where id = ?";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			int qtdAffect = stmt.executeUpdate();
			stmt.close();
			if(qtdAffect > 0)
				return true;
			else
				return false;
		}catch(SQLException e) {
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
	
	public Ator getAtorById(int id) {
		String sql = "SELECT * FROM ator where id = ?";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			Ator ator = new Ator(id, rs.getString("nome"));			
			stmt.close();
			return ator;			
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
	
	public ArrayList<Ator> getListAtor(){
		String sql = "SELECT * FROM ator";
		ArrayList<Ator> listAtor = new ArrayList<Ator>();
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				Ator ator = new Ator(id, nome);
				listAtor.add(ator);
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
		return listAtor;	
	}

}
