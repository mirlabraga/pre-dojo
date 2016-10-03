package br.sp.devamil.predojo.repositorio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import br.sp.devamil.predojo.exception.PredojoException;
import br.sp.devamil.predojo.model.Jogador;
import br.sp.devamil.predojo.util.PredojoUtil;

/**
 * 
 * @author mirla
 *
 */

public class RepositorioJogador {

	private List<Jogador> listaJogadores;
	
	private final String PREMIO_AWARD = "award";

	public RepositorioJogador() {
		this.listaJogadores = new ArrayList<Jogador>();
	}

	public void inserir(String pathDados) throws PredojoException {

		System.out.println("ReaderFile.readFirstLineFromFile()");
		validar(pathDados);

		try (Stream<String> stream = Files.lines(Paths.get(pathDados))) {

			stream.forEach(line -> {
				
				if (!PredojoUtil.isInicioDoJogo(line)
						&& !PredojoUtil.isFimDoJogo(line)) {

					String[] acao = line.split("-");
					String[] evento = acao[1].split("killed");

					String nomeSujeito = evento[0];
					String nomePredicato = evento[1].split("using|by")[0];
					
					//String arma = evento[1].split("using|by")[1];

					Jogador jogadorSujeito = inserirJogador(nomeSujeito.trim());
					Jogador jogadorPredicato = inserirJogador(nomePredicato.trim());
					

					if (!"<WORLD>".equalsIgnoreCase(jogadorSujeito.getNome())) {
						jogadorSujeito.getAssassinados().add(jogadorPredicato);
					}

					jogadorPredicato.setQtdMortes(jogadorPredicato.getQtdMortes() + 1d);
				}
			});

		} catch (IOException e) {
			throw new PredojoException(
					"[ERRO]: Um erro ocorreu quando estava sendo capturado as informações do arquivo do jogo");
		}
	}

	private Jogador inserirJogador(String nomeJogador) {
		
		System.out.println("RepositorioJogador.inserirJogador("+nomeJogador+")");
		
		Jogador jogador = new Jogador(nomeJogador);
		
		if(!listaJogadores.contains(jogador)) {
			listaJogadores.add(jogador);	
		} else {
			return retornarJogador(nomeJogador);
		}
		
		return jogador;
	}
	
	public Jogador retornarJogador(String nomeJogador) {
		
		System.out.println("RepositorioJogador.retornarJogador("+nomeJogador+")");
		
		for (Jogador jogadorList : listaJogadores) {
			if(jogadorList.getNome().equals(nomeJogador)) {
				return jogadorList;
			}
		}
		
		return null;
	}

	public int getQuantidadeTotalAssassinatos() {
		
		System.out.println("RepositorioJogador.getQuantidadeAssassinatos()");

		int qtdAssasinatos = 0;
		for (Jogador jogador : listaJogadores) {
			qtdAssasinatos = qtdAssasinatos + jogador.getAssassinados().size();
		}

		return qtdAssasinatos;
	}

	public double retornarQuantidadeMortesPorJogador(String nomeJogador) {
		
		System.out
				.println("RepositorioJogador.retornarQuantidadeMortesPorJogador("+nomeJogador+")");
		
		for (Jogador jogador : listaJogadores) {
			if(jogador.getNome().equals(nomeJogador)) {
				return jogador.getQtdMortes();
			}
		}
		return 0;
	}
	
	

	private static void validar(String path) throws PredojoException {

		System.out.println("ReaderFile.validarArquivoJogo()");

		List<String> readAllLines = null;
		try {
			readAllLines = Files.readAllLines(Paths.get(path));
		} catch (NoSuchFileException e) {
			
			throw new PredojoException(
					"[ERRO]: Arquivo com o nome definido não encontrado. Por favor, verifique caminho ou nome do arquivo.");
		} catch (IOException e) {
			throw new PredojoException(
					"[ERRO]: Um erro ocorreu quando estava sendo capturado as informações do arquivo do jogo.");
		}

		if (!PredojoUtil.isInicioDoJogo(readAllLines.get(0))) {

			throw new PredojoException(
					"[ERRO]: Um erro ocorreu quando estava sendo capturado as informações do arquivo do jogo. "
							+ "O arquivo não contém a linha que determina início do jogo.");

		} else if (!PredojoUtil.isFimDoJogo(readAllLines.get(readAllLines
				.size() - 1))) {

			throw new PredojoException(
					"[ERRO]: Um erro ocorreu quando estava sendo capturado as informações do arquivo do jogo. "
							+ "O arquivo não contém a linha que determina fim do jogo.");

		}
	}

	public List<Jogador> getListaJogadores() {
		return listaJogadores;
	}
	
	//Bônus
	
	public void premiarJogador() {
		
		listaJogadores.forEach(jogador -> {
			if(jogador.getQtdMortes() == 0 ) {
				jogador.setPremio(PREMIO_AWARD);
			}
		});
	}
}
