package br.sp.devamil.predojo.app;

import java.util.List;

import br.sp.devamil.predojo.exception.PredojoException;
import br.sp.devamil.predojo.model.Jogador;
import br.sp.devamil.predojo.repositorio.RepositorioJogador;

public class App {

	public static void main(String[] args) {
		
		System.out.println("App.main()");
		
		RepositorioJogador repositorioJogador = new RepositorioJogador();
		try {
			
			repositorioJogador.inserir("jogo.log");
			List<Jogador> listaJogadores = repositorioJogador.getListaJogadores();
			listaJogadores.forEach(jogador -> System.out.println(jogador));
			
		} catch (PredojoException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
