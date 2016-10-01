package br.sp.devamil.predojo.model;

import java.util.Set;

/**
 * 
 * @author mirla
 *
 */
public class Jogador {

	private String nome;
	
	private Set<Jogador> assassinados;
	
	private boolean morto;
	
	private double qtdMortes;
	
	public Jogador(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getQtdAssassinatos() {
		return assassinados.size();
	}

	public double getQtdMortes() {
		return qtdMortes;
	}

	public void setQtdMortes(double qtdMortes) {
		this.qtdMortes = qtdMortes;
	}

	public Set<Jogador> getAssassinados() {
		return assassinados;
	}

	public void setAssassinados(Set<Jogador> assassinados) {
		this.assassinados = assassinados;
	}

	public boolean isMorto() {
		return morto;
	}

	public void setMorto(boolean morto) {
		this.morto = morto;
	}
}
