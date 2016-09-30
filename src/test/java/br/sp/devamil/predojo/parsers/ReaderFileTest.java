package br.sp.devamil.predojo.parsers;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.sp.devamil.predojo.exception.PredojoException;

public class ReaderFileTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@SuppressWarnings("static-access")
	@Test
	public void testExceptionMessageNoFileFound() throws PredojoException, IOException {
		try {
			ReaderFile readerFile = new ReaderFile();
			readerFile.readFirstLineFromFile("jogo1.log");
		} catch (PredojoException anPredojoException) {
			Assert.assertEquals(
					anPredojoException.getMessage(),
					"[ERRO]: Arquivo com o nome definido não encontrado. Por favor, verifique caminho ou nome do arquivo.");
		}
	}

	@Test
	public void testFormateLine() throws PredojoException, IOException {
		
		ReaderFile readerFile = new ReaderFile();
		readerFile.readFirstLineFromFile("jogo.log");
	}

}
