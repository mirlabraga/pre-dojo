package br.sp.devamil.predojo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author mirla
 *
 */
public class PredojoRegexUtil {

	private final static String REGEX_DATE          =  "^(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((1[6-9]|[2-9]\\d)"
			+ "\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((1[6-9]|[2-9]\\d)\\d{2}))|"
			+ "((0[1-9]|1\\d|2[0-8])\\/02\\/((1[6-9]|[2-9]\\d)\\d{2}))|"
			+ "(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$";

	private final static String REGEX_TIME          =  "^(?:(?:([01]?\\d|2[0-3]):)?([0-5]?\\d):)?([0-5]?\\d)$";
	private final static String REGEX_JOGO          =  "(^[Mm]atch)";


	public static boolean isData(String strData) {
		Pattern pattern = Pattern.compile(REGEX_DATE);
		Matcher matcher = pattern.matcher(strData);
		return matcher.find();
	}

	public static boolean isHora(String strData) {
		Pattern pattern = Pattern.compile(REGEX_TIME);
		Matcher matcher = pattern.matcher(strData);
		return matcher.find();
	}
	
	public static boolean isInicioDoJogo(String strInicioJogo) {
		Pattern pattern = Pattern.compile("^(New)\\s");
		Matcher matcher = pattern.matcher(strInicioJogo);
		return matcher.find();
	}
	
	public static boolean isFimDoJogo(String strFimJogo) {
		Pattern pattern = Pattern.compile(REGEX_JOGO);
		Matcher matcher = pattern.matcher(strFimJogo);
		return matcher.find();
	}
}