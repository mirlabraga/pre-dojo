package br.sp.devamil.predojo.repositorio;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import br.sp.devamil.predojo.exception.PredojoException;
import br.sp.devamil.predojo.repositorio.RepositorioJogador;

public class RepositorioJogadorTest {

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
	public void retornarQuantidadeJogadoresTest() throws PredojoException, IOException {
		
		RepositorioJogador repositorioJogador = new RepositorioJogador();
		repositorioJogador.inserir("jogo.log");
		Assert.assertEquals(3, repositorioJogador.getListaJogadores().size());
		
	}
	
	@Test
	public void retornarQuantidadeTotalAssassinatosTest() throws PredojoException, IOException {
		
		RepositorioJogador repositorioJogador = new RepositorioJogador();
		repositorioJogador.inserir("jogo.log");
		Assert.assertEquals(1, repositorioJogador.getQuantidadeTotalAssassinatos());
		
	}
	
	@Test
	public void retornarQuantidadeMortesPorJogadorTest() throws PredojoException, IOException {
		
		RepositorioJogador repositorioJogador = new RepositorioJogador();
		repositorioJogador.inserir("jogo.log");
		
		Double mortesPorJogador_NICK = repositorioJogador.retornarQuantidadeMortesPorJogador("Nick");
		Assert.assertEquals(Double.valueOf(2d), mortesPorJogador_NICK);
		
		Double mortesPorJogador_WORLD = repositorioJogador.retornarQuantidadeMortesPorJogador("<WORLD>");
		Assert.assertEquals(Double.valueOf(0), mortesPorJogador_WORLD);
		
		Double mortesPorJogador_ROMAN = repositorioJogador.retornarQuantidadeMortesPorJogador("Roman");
		Assert.assertEquals(Double.valueOf(0), mortesPorJogador_ROMAN);
		
	}
	
	@Test
	public void retornarQuantidadeAssassinatosPorJogadorTest() throws PredojoException, IOException {
	
		RepositorioJogador repositorioJogador = new RepositorioJogador();
		repositorioJogador.inserir("jogo.log");
		
		Double assassinatosPorJogador_NICK = repositorioJogador.retornarJogador("Nick").getQtdAssassinatos();
		Assert.assertEquals(Double.valueOf(0), assassinatosPorJogador_NICK);
		
		Double assassinatosPorJogador_WORLD = repositorioJogador.retornarJogador("<WORLD>").getQtdAssassinatos();
		Assert.assertEquals(Double.valueOf(0), assassinatosPorJogador_WORLD);
		
		Double assassinatosPorJogador_ROMAN = repositorioJogador.retornarJogador("Roman").getQtdAssassinatos();
		Assert.assertEquals(Double.valueOf(1), assassinatosPorJogador_ROMAN);
	}
	
	//Bônus
	

	@Test
	public void premiarJogadorTest() throws PredojoException, IOException {
		
		RepositorioJogador repositorioJogador = new RepositorioJogador();
		repositorioJogador.inserir("jogo.log");
		
		repositorioJogador.premiarJogador();
		
		String assassinatosPorJogador_NICK = repositorioJogador.retornarJogador("Nick").getPremio();
		Assert.assertNull(assassinatosPorJogador_NICK);
		
		String assassinatosPorJogador_WORLD = repositorioJogador.retornarJogador("<WORLD>").getPremio();
		Assert.assertEquals(String.valueOf("award"), assassinatosPorJogador_WORLD);
		
		String assassinatosPorJogador_ROMAN = repositorioJogador.retornarJogador("Roman").getPremio();
		Assert.assertEquals(String.valueOf("award"), assassinatosPorJogador_ROMAN);
		
	}
}
