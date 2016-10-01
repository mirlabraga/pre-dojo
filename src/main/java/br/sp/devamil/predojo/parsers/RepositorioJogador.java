package br.sp.devamil.predojo.parsers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import br.sp.devamil.predojo.exception.PredojoException;
import br.sp.devamil.predojo.model.Jogador;
import br.sp.devamil.predojo.util.PredojoRegexUtil;

/**
 * 
 * @author mirla
 *
 */

public class RepositorioJogador {

	private List<Jogador> listaJogadores;

	public RepositorioJogador() {
		this.listaJogadores = new ArrayList<Jogador>();
	}

	public List<Jogador> inserir(String pathDados) throws PredojoException {

		System.out.println("ReaderFile.readFirstLineFromFile()");
		validar(pathDados);

		try (Stream<String> stream = Files.lines(Paths.get(pathDados))) {

			stream.forEach(line -> {
				if (!PredojoRegexUtil.isInicioDoJogo(line)
						&& !PredojoRegexUtil.isFimDoJogo(line)) {

					String[] acao = line.split("-");
					String[] evento = acao[1].split("killed");

					String nomeSujeito = evento[0];
					String nomePredicato = evento[1].split("using|by")[0];

					Jogador jogadorSujeito = new Jogador(nomeSujeito.trim());
					jogadorSujeito.setAssassinados(new HashSet<Jogador>());
					Jogador jogadorPredicato = new Jogador(nomePredicato);
					jogadorPredicato.setAssassinados(new HashSet<Jogador>());

					if (!"<WORLD>".equalsIgnoreCase(jogadorSujeito.getNome())) {
						jogadorSujeito.getAssassinados().add(jogadorPredicato);
					}

					jogadorPredicato.setMorto(true);
					listaJogadores.add(jogadorSujeito);
					listaJogadores.add(jogadorPredicato);
				}
			});

		} catch (IOException e) {
			throw new PredojoException(
					"[ERRO]: Um erro ocorreu quando estava sendo capturado as informações do arquivo do jogo");
		}

		return listaJogadores;
	}

	public int getQuantidadeAssassinatos() {
		
		System.out.println("RepositorioJogador.getQuantidadeAssassinatos()");

		int qtdAssasinatos = 0;
		for (Jogador jogador : listaJogadores) {
			qtdAssasinatos = qtdAssasinatos + jogador.getAssassinados().size();
		}

		return qtdAssasinatos;
	}

	public int getQuantidadeMortos() {

		System.out.println("RepositorioJogador.getQuantidadeMortos()");
		
		int qtdMortos = 0;
		for (Jogador jogador : listaJogadores) {
			if (jogador.isMorto()) {
				qtdMortos = qtdMortos + 1;
			}
		}

		return qtdMortos;
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

		if (PredojoRegexUtil.isInicioDoJogo(readAllLines.get(0))) {

			throw new PredojoException(
					"[ERRO]: Um erro ocorreu quando estava sendo capturado as informações do arquivo do jogo. "
							+ "O arquivo não contém a linha que determina início do jogo.");

		} else if (PredojoRegexUtil.isFimDoJogo(readAllLines.get(readAllLines
				.size() - 1))) {

			throw new PredojoException(
					"[ERRO]: Um erro ocorreu quando estava sendo capturado as informações do arquivo do jogo. "
							+ "O arquivo não contém a linha que determina fim do jogo.");

		}
	}
}
