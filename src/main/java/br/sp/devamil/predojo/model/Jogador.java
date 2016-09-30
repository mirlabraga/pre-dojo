package br.sp.devamil.predojo.model;

/**
 * 
 * @author mirla
 *
 */
public class Jogador {

	private String nome;
	
	private double qtdAssassinatos;
	
	private double qtdMortes;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getQtdAssassinatos() {
		return qtdAssassinatos;
	}

	public void setQtdAssassinatos(double qtdAssassinatos) {
		this.qtdAssassinatos = qtdAssassinatos;
	}

	public double getQtdMortes() {
		return qtdMortes;
	}

	public void setQtdMortes(double qtdMortes) {
		this.qtdMortes = qtdMortes;
	}
}
