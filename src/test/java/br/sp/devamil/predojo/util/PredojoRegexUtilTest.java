package br.sp.devamil.predojo.util;

import org.junit.Assert;
import org.junit.Test;

public class PredojoRegexUtilTest {

	@Test
	public void isDataTest() {
		
		String data1 = "23/04/2013";
		String data2 = "01/01/1600";
		String data3 = "28/02/2013";
		String data4 = "30/04/2013";
		
		Assert.assertTrue(PredojoRegexUtil.isData(data1));
		Assert.assertTrue(PredojoRegexUtil.isData(data2));
		Assert.assertTrue(PredojoRegexUtil.isData(data3));
		Assert.assertTrue(PredojoRegexUtil.isData(data4));
	}
	
	@Test
	public void isNotFormatDataTest() {
		
		String data1 = "23/04/13";
		String data2 = "23-04-2013";
		String data3 = "2013/04/23";
		String data4 = "1/1/2003";
		
		Assert.assertFalse(PredojoRegexUtil.isData(data1));
		Assert.assertFalse(PredojoRegexUtil.isData(data2));
		Assert.assertFalse(PredojoRegexUtil.isData(data3));
		Assert.assertFalse(PredojoRegexUtil.isData(data4));
	}
	
	@Test
	public void isNotValidDataTest() {
		
		String data1 = "29/02/2001";
		String data2 = "31-04-2003";
		String data3 = "2013/13/10";
		
		Assert.assertFalse(PredojoRegexUtil.isData(data1));
		Assert.assertFalse(PredojoRegexUtil.isData(data2));
		Assert.assertFalse(PredojoRegexUtil.isData(data3));
	}
	
	@Test
	public void isHoraTest() {
		
		String hora1 = "15:34:22";
		String hora2 = "15:36:04";
		String hora3 = "15:36:33";
		String hora4 = "15:39:22";
		
		Assert.assertTrue(PredojoRegexUtil.isHora(hora1));
		Assert.assertTrue(PredojoRegexUtil.isHora(hora2));
		Assert.assertTrue(PredojoRegexUtil.isHora(hora3));
		Assert.assertTrue(PredojoRegexUtil.isHora(hora4));
	}
	
	@Test
	public void isInicoDoJogoTest() {
		
		String inicioJogo = "New match 11348965 has started";
		Assert.assertTrue(PredojoRegexUtil.isInicioDoJogo(inicioJogo));
	}
	
	@Test
	public void isFimDoJogoTest() {
		
		String fimJogo = "match";
		Assert.assertTrue(PredojoRegexUtil.isFimDoJogo(fimJogo));
	}

}
