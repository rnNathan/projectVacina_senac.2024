package model.seletor;

import java.time.LocalDate;

public class vacinaSeletor {
	
	private String nomePais;
	private String nomeVacina;
	private String nomePesquisador;
	private LocalDate dataInicioPesquisa;
	private LocalDate dataFinalPesquisa; 

	
	public vacinaSeletor(String nomePais, String nomeVacina, String nomePesquisador, LocalDate dataInicioPesquisa, LocalDate dataFinalPesquisa) {
		super();
		this.nomePais = nomePais;
		this.nomeVacina = nomeVacina;
		this.nomePesquisador = nomePesquisador;
		this.dataInicioPesquisa = dataInicioPesquisa;
		this.dataFinalPesquisa = dataFinalPesquisa;
	}

	public vacinaSeletor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNomePais() {
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}

	public String getNomeVacina() {
		return nomeVacina;
	}

	public void setNomeVacina(String nomeVacina) {
		this.nomeVacina = nomeVacina;
	}

	public String getNomePesquisador() {
		return nomePesquisador;
	}

	public void setNomePesquisador(String nomePesquisador) {
		this.nomePesquisador = nomePesquisador;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

	public LocalDate getDataFinalPesquisa() {
		return dataFinalPesquisa;
	}

	public void setDataFinalPesquisa(LocalDate dataFinalPesquisa) {
		this.dataFinalPesquisa = dataFinalPesquisa;
	}


	
	

}

