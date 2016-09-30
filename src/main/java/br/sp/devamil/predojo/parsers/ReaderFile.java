package br.sp.devamil.predojo.parsers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import br.sp.devamil.predojo.exception.PredojoException;
import br.sp.devamil.predojo.util.PredojoRegexUtil;

public class ReaderFile {

	public static void readFirstLineFromFile(String path)
			throws PredojoException, IOException {

		System.out.println("ReaderFile.readFirstLineFromFile()");
		verificarArquivoJogo(path);

		try (Stream<String> stream = Files.lines(Paths.get(path))) {
			stream.forEach(s -> System.out.println(s));
		} catch (NoSuchFileException e) {
			new PredojoException(
					"[ERRO]: Arquivo com o nome definido não encontrado. Por favor, verifique caminho ou nome do arquivo.");
		} catch (IOException e) {
			new PredojoException(
					"[ERRO]: Um erro ocorreu quando estava sendo capturado as informações do arquivo do jogo");
		}
	}

	private static void verificarArquivoJogo(String path) throws IOException {
		
		System.out.println("ReaderFile.verificarArquivoJogo()");
		
		List<String> readAllLines = Files.readAllLines(Paths.get(path));
		if (PredojoRegexUtil.isInicioDoJogo(readAllLines.get(0))) {
			
		}
		System.out.println(readAllLines.get(readAllLines.size() - 1));
	}
}
