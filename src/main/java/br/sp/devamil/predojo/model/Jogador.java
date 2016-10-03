package br.sp.devamil.predojo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author mirla
 *
 */
public class Jogador implements Comparable<Integer>{

	private String nome;

	private List<Jogador> assassinados;

	private double qtdMortes;
	
	private String premio;

	public Jogador(String nome) {
		this.nome = nome;
		this.assassinados = new ArrayList<Jogador>();
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

	public List<Jogador> getAssassinados() {
		return assassinados;
	}

	public void setAssassinados(List<Jogador> assassinados) {
		this.assassinados = assassinados;
	}

	@Override
	public String toString() {
		return " Jogador: " + this.nome + " foi morto: " + qtdMortes
				+ " vezes e " + assassinados.size()
				+ " foi a quantidade de assassinatos. " + ((premio!=null)?" O jogador foi premiado.":"");
	}

	@Override
	public boolean equals(Object object) {

		boolean result = false;
		if (object == null || object.getClass() != getClass()) {
			result = false;
		} else {
			Jogador jogador = (Jogador) object;
			if (this.nome.equals(jogador.getNome())) {
				result = true;
			}
		}
		return result;
	}

	public String getPremio() {
		return premio;
	}

	public void setPremio(String premio) {
		this.premio = premio;
	}

	@Override
	public int compareTo(Integer o) {
		return (this.getQtdAssassinatos() > 0)?1:-1;
	}
}
