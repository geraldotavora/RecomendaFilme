package br.com.quixada.ufc.recfilme.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import br.com.quixada.ufc.recfilme.jdbc.ConnectionFactory;
import br.com.quixada.ufc.recfilme.pojo.Filme;
import br.com.quixada.ufc.recfilme.pojo.Usuario;

public class RecomendaFilmeDAO {
	private Connection connection;
	
	public ArrayList<Filme> recomendaFilme(Usuario usuario) {
		String sql = "select distinct(id), nome, genero, ator_principal, ator_coadjuvante, duracao, " + 
				"nome_diretor, data_lancamento from filme, filme_assistido where id in " + 
				"(select id from filme except(select id_filme from filme_assistido where id_usuario = ?)) and " + 
				"((genero in (select nome from genero, preferencia_genero where id_usuario = ? and " + 
				"id = id_genero)) or (ator_principal in (select nome from ator, preferencia_ator " + 
				"where id_usuario = ? and id = id_ator)) or (ator_coadjuvante in " + 
				"(select nome from ator, preferencia_ator where id_usuario = ? and id = id_ator)));";
		ArrayList<Filme> listaFilmes = new ArrayList<>();
		this.connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, usuario.getCodigo());
			stmt.setInt(2, usuario.getCodigo());
			stmt.setInt(3, usuario.getCodigo());
			stmt.setInt(4, usuario.getCodigo());
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
				Filme filme = new Filme(id, nome, genero, ator_principal, 
										ator_coadjuvante, duracao, nome_diretor, data_lancamento);
				listaFilmes.add(filme);
			}
			
		}catch(SQLException e) {
			System.err.println(e);
		} finally {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return listaFilmes;
	}
}
