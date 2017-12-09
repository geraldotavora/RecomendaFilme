package br.com.quixada.ufc.recfilme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.quixada.ufc.recfilme.jdbc.ConnectionFactory;
import br.com.quixada.ufc.recfilme.pojo.Ator;
import br.com.quixada.ufc.recfilme.pojo.Usuario;

public class PreferenciaAtorDAO {
	private Connection connection;
	
	public boolean addPrefAtor(Usuario usuario, Ator ator) {
		String sql = "INSERT INTO preferencia_ator (id_usuario, id_ator) VALUES(?, ?)";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, usuario.getCodigo());
			stmt.setInt(2, ator.getId());
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
	
	public boolean removePreferencia(int id) {
		String sql = "DELETE FROM preferencia_ator WHERE id = ?";
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