package br.com.quixada.ufc.recfilme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.quixada.ufc.recfilme.jdbc.ConnectionFactory;
import br.com.quixada.ufc.recfilme.pojo.*;

public class PreferenciaGeneroDAO {
	private Connection connection;
	
	public PreferenciaGeneroDAO() {}
	
	public boolean addPreferencia(Usuario usuario, Genero genero) {
		String sql = "INSERT INTO preferencia_genero (id_usuario, id_genero) VALUES(?, ?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, usuario.getCodigo());
			stmt.setInt(2, genero.getId());
			int qtdAffect = stmt.executeUpdate();
			stmt.close();
			if(qtdAffect > 0) {
				return true;
			}else {
				return false;
			}			
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
	
	public boolean removePreferencia(int id) {
		String sql = "DELETE FROM preferencia_genero WHERE id = ?";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.close();
			int qtdAffect = stmt.executeUpdate();
			if(qtdAffect > 0)
				return true;
			return false;
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
}
