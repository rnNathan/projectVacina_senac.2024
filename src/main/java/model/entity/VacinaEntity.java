package model.entity;

import java.time.LocalDate;

public class VacinaEntity {
	
	public static final int ESTAGIO_INICIAL= 1;
	public static final int ESTAGIO_TESTE= 1;
	public static final int ESTAGIO_APLICACAO_EM_MASSA = 1;
	
	private int id;
	private String nome;
	private PaisEntity paisOrigem;
	private PessoaEntity pesquisador;
	private Integer estagio;
	private LocalDate dataInicioPesquisa;
	private double media;
	
	
	
	public VacinaEntity() {
		super();
	}
	
	public VacinaEntity(int id, String nome, PaisEntity paisOrigem, PessoaEntity idPesquisador, int estagio, LocalDate dataInicioPesquisa, double media) {
		super();
		this.id = id;
		this.nome = nome;
		this.paisOrigem = paisOrigem;
		this.pesquisador = idPesquisador;
		this.estagio = estagio;
		this.dataInicioPesquisa = dataInicioPesquisa;
		this.media = media;
		
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

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public void setId(int id) {
		this.id = id;
	}

                             
	
	
	
	
	
	
	
	
	
	

}
