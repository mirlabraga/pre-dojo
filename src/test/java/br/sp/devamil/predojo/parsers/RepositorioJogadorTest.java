package br.sp.devamil.predojo.parsers;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.sp.devamil.predojo.exception.PredojoException;
import br.sp.devamil.predojo.model.Jogador;

public class RepositorioJogadorTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testExceptionMessageNoFileFound() throws PredojoException, IOException {
		try {
			RepositorioJogador repositorioJogador = new RepositorioJogador();
			repositorioJogador.inserir("jogo1.log");
		} catch (PredojoException anPredojoException) {
			Assert.assertEquals(
					anPredojoException.getMessage(),
					"[ERRO]: Arquivo com o nome definido não encontrado. Por favor, verifique caminho ou nome do arquivo.");
		}
	}

	@Test
	public void insertTest() throws PredojoException, IOException {
		
		RepositorioJogador readerFile = new RepositorioJogador();
		List<Jogador> jogadores = readerFile.inserir("jogo.log");
		Assert.assertEquals(2, jogadores.size());
		
	}
	
	@Test
	public void retornarQuantidadeAssassinatosTest() throws PredojoException, IOException {
		
		RepositorioJogador repositorioJogador = new RepositorioJogador();
		List<Jogador> jogadores = repositorioJogador.inserir("jogo.log");
		Assert.assertEquals(2, repositorioJogador.getQuantidadeAssassinatos());
		
	}
	
	@Test
	public void retornarQuantidadeMortosTest() throws PredojoException, IOException {
		
		RepositorioJogador repositorioJogador = new RepositorioJogador();
		List<Jogador> jogadores = repositorioJogador.inserir("jogo.log");
		Assert.assertEquals(2, repositorioJogador.getQuantidadeMortos());
		
	}
}
