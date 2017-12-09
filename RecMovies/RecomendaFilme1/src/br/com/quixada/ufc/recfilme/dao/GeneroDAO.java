package br.com.quixada.ufc.recfilme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.quixada.ufc.recfilme.jdbc.ConnectionFactory;
import br.com.quixada.ufc.recfilme.pojo.Genero;

public class GeneroDAO {
	private Connection connection;
	
	public GeneroDAO() {}
	
	public boolean addGenero(Genero genero) {
		String sql = "INSERT INTO genero (nome) VALUES(?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, genero.getNome());
			int qtdAffect = stmt.executeUpdate();
			stmt.close();
			if(qtdAffect > 0)
				return true;
			return false;
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
	
	public Genero getGeneroById(int id) {
		String sql = "SELECT * FROM genero where id = ?";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			Genero genero = new Genero(id, rs.getString("nome"));			
			stmt.close();
			return genero;
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
	
	public ArrayList<Genero> getGenero(){
		String sql = "SELECT *FROM genero;";
		ArrayList<Genero> listaGeneros = new ArrayList<>();
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				Genero genero = new Genero(id, nome);
				listaGeneros.add(genero);
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
		return listaGeneros;
	}
}
