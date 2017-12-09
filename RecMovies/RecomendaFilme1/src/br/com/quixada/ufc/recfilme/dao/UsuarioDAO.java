package br.com.quixada.ufc.recfilme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.quixada.ufc.recfilme.jdbc.ConnectionFactory;
import br.com.quixada.ufc.recfilme.pojo.Usuario;

public class UsuarioDAO {
	private Connection connection;

	public UsuarioDAO() {}
	
	public boolean addUser(Usuario user) {
		String sql = "INSERT INTO Usuario (nome, email) VALUES (?, ?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getNome());
			stmt.setString(2, user.getEmail());
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
	
	public boolean deleteUser(int id) {
		String sql = "DELETE FROM usuario where id = ?";
		
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			int qtdAffect = stmt.executeUpdate();
			stmt.close();
			if(qtdAffect > 0)
				return true;
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
	
	public Usuario getUserById(int id) {
		String sql = "SELECT * FROM usuario where id = ?";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			Usuario user = new Usuario(id, rs.getString("nome"), rs.getString("email"));
			stmt.close();
			return user;
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
	
	public Usuario userByName(String name) {
		String sql = "SELECT * FROM Usuario where nome like '?%'";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			Usuario user = new Usuario(rs.getInt("id"), name, rs.getString("email"));
			stmt.close();
			return user;			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;	
	}
	
	public ArrayList<Usuario> getListUser(){
		String sql = "SELECT * FROM usuario";
		ArrayList<Usuario> listUser = new ArrayList<Usuario>();
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String email = rs.getString("email");	
				Usuario user = new Usuario(id, nome, email);
				listUser.add(user);
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
		return listUser;
	}
	
	public boolean updateUsuario(int id, Usuario usuario) {
		String sql = "UPDATE usuario SET nome = ?, email = ? WHERE id = ?;";
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setInt(3, id);
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
}
