package model.entity;

import java.time.LocalDate;

public class VacinaEntity {
	
	private int id;
	private String nome;
	private PaisEntity paisOrigem;
	private PessoaEntity pesquisador;
	private Integer estagio;
	private LocalDate dataInicioPesquisa;
	private double mediaVacina;
	
	
	
	public VacinaEntity() {
		super();
	}
	
	public VacinaEntity(int id, String nome, PaisEntity paisOrigem, PessoaEntity idPesquisador, Integer estagio, LocalDate dataInicioPesquisa,  double mediaVacina) {
		super();
		this.id = id;
		this.nome = nome;
		this.paisOrigem = paisOrigem;
		this.pesquisador = idPesquisador;
		this.estagio = estagio;
		this.dataInicioPesquisa = dataInicioPesquisa;
		this.mediaVacina = mediaVacina;
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
	public PaisEntity getPaisOrigem() {
		return paisOrigem;
	}
	public void setPaisOrigem(PaisEntity paisOrigem) {
		this.paisOrigem = paisOrigem;
	}
	public PessoaEntity getPesquisador() {
		return pesquisador;
	}
	public void setPesquisador(PessoaEntity pesquisador) {
		this.pesquisador = pesquisador;
	}
	public int getEstagio() {
		return estagio;
	}
	public void setEstagio(int estagio) {
		this.estagio = estagio;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

	public double getMediaVacina() {
		return mediaVacina;
	}

	public void setMediaVacina(double mediaVacina) {
		this.mediaVacina = mediaVacina;
	}
	
	
	
	
	
	
	
	
	

}
