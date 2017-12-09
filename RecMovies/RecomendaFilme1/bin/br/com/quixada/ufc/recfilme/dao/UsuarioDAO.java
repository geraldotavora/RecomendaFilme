package br.com.quixada.ufc.recfilme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.quixada.ufc.recfilme.jdbc.ConnectionFactory;
import br.com.quixada.ufc.recfilme.pojo.Tipo_Filme;
import br.com.quixada.ufc.recfilme.pojo.Usuario;

public class UsuarioDAO {
	private Connection con;

	public UsuarioDAO() {}
	
	public boolean addUser(Usuario user) {
		String sql = "INSERT INTO Usuario (nome, email, endereco, tipo_preferido, ator_preferido) VALUES (?, ?, ?, ?, ?)";
		this.con = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getNome());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getEndereco());
			stmt.setString(4, user.getGenero());
			stmt.setString(5, user.getAtor_pref());
			
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
				this.con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean deleteUser(int id) {
		String sql = "DELETE FROM usuario where idusuario = ?";
		
		this.con = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
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
				this.con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
		
	}
	
	public Usuario getUserById(int id) {
		String sql = "SELECT * FROM usuario where idusuario = ?";
		
		this.con = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			Usuario user = new Usuario(id, rs.getString("nome"), rs.getString("email"), rs.getString("endereco")
					, rs.getString("tipo_preferido"), rs.getString("ator_preferido"));
			
			stmt.close();
			return user;
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Usuario userByName(String name) {
		String sql = "SELECT * FROM Usuario where nome like '?%'";
		this.con = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			Usuario user = new Usuario(rs.getInt("idusuario"), name, rs.getString("email"), rs.getString("endereco"),
					rs.getString("genero"), rs.getString("ator_pref"));
			stmt.close();
			return user;
					
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
		
	}
	
	public ArrayList<Usuario> getListUser(){
		String sql = "SELECT * FROM usuario";
		ArrayList<Usuario> listUser = new ArrayList<Usuario>();
		
		this.con = new ConnectionFactory().getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("idusuario");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String genero = rs.getString("tipo_preferido");
				String ator = rs.getString("ator_preferido");
				String end = rs.getString("endereco");
				
				Usuario user = new Usuario(id, nome, email, end, genero, ator);
				listUser.add(user);
			}
			stmt.close();
		}catch (SQLException e) {
			System.err.println(e.getMessage());
		}finally {
			try {
				this.con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listUser;
		
	}
	
	
}
