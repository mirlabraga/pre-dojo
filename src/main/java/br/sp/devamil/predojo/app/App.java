package br.sp.devamil.predojo.app;

import java.util.List;

import br.sp.devamil.predojo.exception.PredojoException;
import br.sp.devamil.predojo.model.Jogador;
import br.sp.devamil.predojo.repositorio.RepositorioJogador;

public class App {
	
	private static int posicao = 1;

	public static void main(String[] args) {
		
		System.out.println("App.main()");
		RepositorioJogador repositorioJogador = new RepositorioJogador();
		
		try {
			
			repositorioJogador.inserir("jogo.log");
			repositorioJogador.premiarJogador();
			
			List<Jogador> listaJogadores = repositorioJogador.getListaJogadores();
			
			System.out.println(" ********************* ");
			System.out.println(" Ranking ");
			System.out.println(" ********************* ");
			
			listaJogadores.forEach(jogador -> {
				
				System.out.print(posicao+"o lugar ");
				System.out.println(jogador);
				App.posicao = App.posicao+1;
			});
			
			
		} catch (PredojoException e) {
			System.out.println(e.getMessage());
		}
	}
}
