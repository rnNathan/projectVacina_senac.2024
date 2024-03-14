package model.entity;

import java.time.LocalDate;

public class VacinaEntity {
	
	private Integer id;
	private String nome;
	private String paisOrigem;
	private PessoaEntity idPesquisador;
	private Integer estagio;
	private LocalDate dataInicioPesquisa;
	
	
	public VacinaEntity() {
		super();
	}
	
	public VacinaEntity(Integer id, String nome, String paisOrigem, PessoaEntity idPesquisador, Integer estagio, LocalDate dataInicioPesquisa) {
		super();
		this.id = id;
		this.nome = nome;
		this.paisOrigem = paisOrigem;
		this.idPesquisador = idPesquisador;
		this.estagio = estagio;
		this.dataInicioPesquisa = dataInicioPesquisa;
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
	public String getPaisOrigem() {
		return paisOrigem;
	}
	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}
	public PessoaEntity getIdPesquisador() {
		return idPesquisador;
	}
	public void setIdPesquisador(PessoaEntity idPesquisador) {
		this.idPesquisador = idPesquisador;
	}
	public Integer getEstagio() {
		return estagio;
	}
	public void setEstagio(Integer estagio) {
		this.estagio = estagio;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}
	
	
	
	
	
	
	

}
