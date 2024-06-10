package model.entity;

import java.time.LocalDate;

public class Montadora {
	
	private Integer id;
	private String nome;
	private String paisFundacao;
	private String nomeFundador;
	private LocalDate dataFundacao;
	
	public Montadora() {
		
	}
	
	 
	
	public Montadora(Integer id, String nome, String paisFundacao, String nomeFundador, LocalDate dataFundacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.paisFundacao = paisFundacao;
		this.nomeFundador = nomeFundador;
		this.dataFundacao = dataFundacao;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPaisFundacao() {
		return paisFundacao;
	}
	public void setPaisFundacao(String paisFundacao) {
		this.paisFundacao = paisFundacao;
	}



	public String getNomeFundador() {
		return nomeFundador;
	}



	public void setNomeFundador(String nomeFundador) {
		this.nomeFundador = nomeFundador;
	}



	public LocalDate getDataFundacao() {
		return dataFundacao;
	}



	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
	

}
