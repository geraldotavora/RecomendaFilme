package br.com.quixada.ufc.recfilme.main;

import java.io.Console;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;

import br.com.quixada.ufc.recfilme.dao.*;
import br.com.quixada.ufc.recfilme.pojo.*;

public class Main {

	private static Scanner entrada;
	private static Scanner entrada_int;

	public static void main(String[] args) throws ParseException {
		FilmeDAO filmeDAO = new FilmeDAO();
		UsuarioDAO userDAO = new UsuarioDAO();
		PreferenciaGeneroDAO prefGeneroDAO = new PreferenciaGeneroDAO();
		GeneroDAO generoDAO = new GeneroDAO();
		FilmeAssistidoDAO filmeAssistidoDAO = new FilmeAssistidoDAO();
		AtorDAO atorDAO = new AtorDAO();
		PreferenciaAtorDAO prefAtorDAO = new PreferenciaAtorDAO();
		RecomendaFilmeDAO recomendaDAO = new RecomendaFilmeDAO();
		
		int opcao;
		entrada = new Scanner(System.in);
		entrada_int = new Scanner(System.in);
		boolean fim = false;
		
		while(!fim) {
		//	String app_name = " ############  \n"
			//		        + " ##";
				  
				  
		//	System.out.println(app_name);
			//System.out.println("RecFlix");
			System.out.println(	" oооооооооo                            ооооооoооооооo                       оооо                          \r\n" + 
								" ооооооооооо                          оооооооооооооооo                                                    \r\n" + 
								" оооо   оооо  oоооооооо    оооооооо   оооо  оооо ооооo   ооооооo  оооo  ооо oооо   оооооооо     ооооo     \r\n" + 
								" оооо ооооо  oоооo  оооо  оооо   ооо  оооо  оооо ооооo  оооооооооо oоо  ооo oооо  ооо   оооо   ооооо     \r\n" + 
								" оооо оооо   ооооoooоооо оооо         оооо  оооо ооооo оооо    оооо oооооo  oооо оооо оооооо   оооо       \r\n" + 
								" оооо  оооо   оооооо      оооо   ооо  оооо  оооо ооооo  оооооооооo   oооo   oооо  ооооооo    оооооo       \r\n" + 
								" оооо   оооо   oоооооо      оооооо    оооо  оооо ооооo    оооооо      oo    oооо    оооооо   оооо\r\n" + 
					"");
			System.out.println("	| 1 | Cadastrar					| 0 | Sair");
			System.out.println("	| 2 | Visualizar");
			System.out.println("	| 3 | Excluir");
			System.out.println("	| 4 | Atualizar");
			System.out.println("	| 5 | **RECOMENDAR**");
			
			opcao = entrada.nextInt();
			entrada.nextLine();
			
		switch (opcao) {
		case 1:{
			
			boolean fim_cad = false;
			while(!fim_cad) {
				System.out.println("	| 1 | Cadastrar Filme 				| 6 | Cadastrar Preferencia de Ator");
				System.out.println("	| 2 | Cadastrar Usuario				| 7 | Cadastrar Filme Como Assistido");
				System.out.println("	| 3 | Cadastrar Genero				| 9 | Voltar");
				System.out.println("	| 4 | Cadastrar Ator				| 0 | Sair");
				System.out.println("	| 5 | Cadastrar Preferencia de Genero.");
			
			int opcao_cad = entrada.nextInt();
			entrada.nextLine();
			
			switch(opcao_cad) {
			case 1:{
				String nome, genero, ator_principal, ator_coadjuvante, duracao, nome_diretor, data_lancamento;
				System.out.println("Digite o nome do filme:");
				nome = entrada.nextLine();
				System.out.println("Digite o genero do filme:");
				genero = entrada.nextLine();
				System.out.println("Digite o nome do ator principal:");
				ator_principal = entrada.nextLine();
				System.out.println("Digite o nome do ator coadjuvante:");
				ator_coadjuvante = entrada.nextLine();
				System.out.println("Digite a duração do filme:");
				duracao = entrada.nextLine();
				System.out.println("Digite o nome do diretor do filme:");
				nome_diretor = entrada.nextLine();
				System.out.println("Digite a data(dia/mes/ano) de lançamento do filme:");
				data_lancamento = entrada.nextLine();
				
				DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				Date data = formato.parse(data_lancamento);

				Filme filme = new Filme(nome, genero, ator_principal, ator_coadjuvante, duracao, nome_diretor, data);
				if(filmeDAO.addFilme(filme)) {
					System.out.println("Filme cadastrado com sucesso!");
				}else {
					System.out.println("Error ao cadastrar filme !!");
				}			
				break;
			}case 2:{
				String nome, email;
				
				System.out.println("Digite o nome do usuario:");
				nome = entrada.nextLine();
				System.out.println("Digite o email do usuario:");
				email = entrada.nextLine();
				
				Usuario usuario = new Usuario(nome, email);
				if(userDAO.addUser(usuario)) {
					System.out.println("Usuario cadastrado com sucesso!");
				}else {
					System.out.println("Error ao cadastrar usuario !!");
				}
				break;
			} case 3:{
				String nome;
				System.out.println("Digite o nome do genero:");
				nome = entrada.nextLine();
				Genero genero = new Genero(nome);
				if(generoDAO.addGenero(genero))
					System.out.println("Genero cadastrado com sucesso!");
				else 
					System.out.println("Error ao cadastrar genero !!");
				break;
			} case 4:{
				String nome;
				System.out.println("Digite o nome do ator:");
				nome = entrada.nextLine();
				Ator ator = new Ator(nome);
				if(atorDAO.addAtor(ator))
					System.out.println("Ator cadastrado com sucesso!");
				else 
					System.out.println("Error ao cadastrar ator !!");
				break;
			}case 5:{
				int id_usuario, id_genero;
				System.out.println("Digite o ID do usuario:");
				id_usuario = entrada_int.nextInt();
				System.out.println("Digite o ID do genero:");
				id_genero = entrada_int.nextInt();
				
				Usuario usuario = userDAO.getUserById(id_usuario);
				Genero genero = generoDAO.getGeneroById(id_genero);
				
				if(prefGeneroDAO.addPreferencia(usuario, genero))
					System.out.println("Preferencia cadastrada com sucesso!");
				else
					System.out.println("Error ao cadastrar preferencia !!");
				break;
			} case 6:{
				int id_usuario, id_ator;
				System.out.println("Digite o ID do usuario:");
				id_usuario = entrada_int.nextInt();
				System.out.println("Digite o ID do Ator:");
				id_ator = entrada_int.nextInt();
				Usuario usuario = userDAO.getUserById(id_usuario);
				Ator ator = atorDAO.getAtorById(id_ator);
				if(prefAtorDAO.addPrefAtor(usuario, ator))
					System.out.println("Preferencia cadastrada com sucesso!");
				else
					System.out.println("Error ao cadastrar preferencia !!");				
				break;
			}case 7:{
				int id_usuario, id_filme;
				System.out.println("Digite o ID do usuario que assistiu o filme:");
				id_usuario = entrada_int.nextInt();
				System.out.println("Digite o ID do filme:");
				id_filme = entrada_int.nextInt();
				
				Usuario usuario = userDAO.getUserById(id_usuario);
				Filme filme = filmeDAO.getFilmeById(id_filme);
				
				if(filmeAssistidoDAO.addFilmeAssistido(usuario, filme))
					System.out.println("Filme cadastrado como assistido com sucesso!"); 
				else
					System.out.println("Error ao cadastrar filme !!");
				break;
			}case 9:{
				main(null);
			}case 0:{
				System.exit(0);
				break;
			}default:
				fim_cad = false;
				break;
			}
			}
		}case 2:{
			
			boolean fim_vis = false;
			
			while(!fim_vis) {
			System.out.println("	| 1 | Ver Filmes Assistidos			| 5 | Ver Atores Cadastrados");
			System.out.println("	| 2 | Ver Filmes Cadastrados			| 9 | Voltar");
			System.out.println("	| 3 | Ver Usuarios Cadastrados			| 0 | Sair");
			System.out.println("	| 4 | Ver Generos Cadastrados");
			
			int opcao_vis = entrada.nextInt();
			entrada.nextLine();
			
			switch(opcao_vis) {
			case 1:{
				int id_usuario;
				System.out.println("Digite o ID do usuario:");
				id_usuario = entrada_int.nextInt();
				ArrayList<Filme> lista = filmeAssistidoDAO.getFilmeAssistido(id_usuario);
				for(Filme filme : lista)
					System.out.println(filme.toString());
				break;
			} case 2:{
				ArrayList<Filme> filmes = filmeDAO.getFilmes();
				for(Filme filme : filmes)
					System.out.println(filme.toString());
				break;
			} case 3:{
				ArrayList<Usuario> usuarios = userDAO.getListUser();
				for(Usuario usuario : usuarios)
					System.out.println(usuario.toString());
				break;
			} case 4:{
				ArrayList<Genero> generos = generoDAO.getGenero();
				for(Genero genero : generos)
					System.out.println(genero.toString());
				break;
			} case 5:{
				ArrayList<Ator> atores = atorDAO.getListAtor();
				for(Ator ator : atores)
					System.out.println(ator.toString());
				break;
			} case 9:{
				main(null);
			}case 0:{
				System.exit(0);
				break;
			}default:
				fim_vis = true;
				break;
			}
			}
		}case 3:{
			
			boolean fim_exc = false;
			
			while(!fim_exc) {
			System.out.println("	| 1 | Excluir Filme				| 9 | Voltar");
			System.out.println("	| 2 | Excluir Usuario				| 0 | Sair");
			System.out.println("	| 3 | Excluir Ator");
			
			int opcao_exc = entrada.nextInt();
			entrada.nextLine();
			
			switch(opcao_exc) {
			case 1:{
				int id_filme;
				System.out.println("Digite o ID do filme:");
				id_filme = entrada_int.nextInt();
				if(filmeDAO.deleteFilme(id_filme))
					System.out.println("Filme excluido com sucesso.");
				else
					System.out.println("Error na exclusão do filme !!!");
				break;
			} case 2:{
				int id_usuario;
				System.out.println("Digite o ID do usuario:");
				id_usuario = entrada_int.nextInt();
				if(userDAO.deleteUser(id_usuario))
					System.out.println("Usuario excluido com sucesso.");
				else
					System.out.println("Error na exclusão do usuario !!!");
				break;
			} case 3:{
				int id_ator;
				System.out.println("Digite o ID do autor:");
				id_ator = entrada_int.nextInt();
				if(atorDAO.deleteAtor(id_ator))
					System.out.println("Ator excluido com sucesso.");
				else
					System.out.println("Error na exclusão do ator !!!");
				break;
			}case 9:{
				main(null);
			}case 0:{
				System.exit(0);
				break;
			}default:
				fim_exc = true;
				break;
			}
			}
		}case 4:{
			
			boolean fim_at = false;
			
			while(!fim_at) {
			System.out.println("	| 1 | Atualizar Usuario				| 9 | Voltar");
			System.out.println("	| 2 | Atualizar Filme				| 0 | Sair");
			
			int opcao_atualizar = entrada.nextInt();
			entrada.nextLine();
			
			switch (opcao_atualizar) {
			case 1:{
				int id_usuario_antigo;
				String nome, email;
				System.out.println("Digite o ID do usuario a ser editado:");
				id_usuario_antigo = entrada_int.nextInt();
				System.out.println("Digite o novo nome:");
				nome = entrada.nextLine();
				System.out.println("Digite o novo email:");
				email = entrada.nextLine();
				Usuario usuario = new Usuario(nome, email);
				if(userDAO.updateUsuario(id_usuario_antigo, usuario))
					System.out.println("Usuario editado com sucesso.");
				else
					System.out.println("Error na atualização do usuario !!!");
				break;
			}case 2:{ 
				int id_filme_antigo;
				String nome, genero, ator_p, ator_coad, duracao, diretor, lanc;
				System.out.println("Digite o ID do filme a ser editado:");
				id_filme_antigo = entrada_int.nextInt();
				System.out.println("Digite o novo nome:");
				nome = entrada.nextLine();
				System.out.println("Digite o novo genero:");
				genero = entrada.nextLine();
				System.out.println("Digite o novo ator principal:");
				ator_p = entrada.nextLine();
				System.out.println("Digite o novo ator coadjuvante:");
				ator_coad = entrada.nextLine();
				System.out.println("Digite a nova duraçao:");
				duracao = entrada.nextLine();
				System.out.println("Digite o novo diretor:");
				diretor = entrada.nextLine();
				System.out.println("Digite a nova data de lancamento:");
				lanc = entrada.nextLine();
				
				DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				Date data = formato.parse(lanc);
				
				Filme filme = new Filme(nome, genero, ator_p, ator_coad, duracao, diretor, data);
				if(filmeDAO.updateFilme(id_filme_antigo, filme))
					System.out.println("filme editado com sucesso.");
				else
					System.out.println("Error na atualização do filme !!!");
				break;
			}case 9:{
				main(null);
			}case 0:{
				System.exit(0);
				break;
			}default:
				fim_at = true;
			}
			}
		}case 5:{
			int id_usuario;
			System.out.println("Digite o ID do usuario:");
			id_usuario = entrada_int.nextInt();
			Usuario usuario = userDAO.getUserById(id_usuario);
			ArrayList<Filme> listaFilmes = recomendaDAO.recomendaFilme(usuario);
			for(Filme filme : listaFilmes)
				System.out.println(filme.toString());			
			break;
		}case 0:{
			System.exit(0);
			break;
			
		}default:
			fim = false;
			break;
		}
					
		}
	}
}