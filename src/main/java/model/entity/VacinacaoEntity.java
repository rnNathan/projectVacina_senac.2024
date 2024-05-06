package model.entity;

import java.time.LocalDate;

/**
 * 
 */
public class VacinacaoEntity {
	
	private int idVacinacao;
	private int idPessoa;
	private VacinaEntity vacina;
	private LocalDate dataVacina;
	private double avaliacao;
	
	public VacinacaoEntity(int id, int idVacinacao, int idPessoa, VacinaEntity vacina, LocalDate dataVacina, double avaliacao) {
		super();
		this.idVacinacao = idVacinacao;
		this.idPessoa = idPessoa;
		this.vacina = vacina;
		this.dataVacina = dataVacina;
		this.avaliacao = avaliacao;
	}
	
	public VacinacaoEntity() {	
		super();
	}
	
	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public int getIdVacinacao() {
		return idVacinacao;
	}

	public void setIdVacinacao(int idVacinacao) {
		this.idVacinacao = idVacinacao;
	}

	public VacinaEntity getVacina() {
		return vacina;
	}

	public void setVacina(VacinaEntity vacina) {
		this.vacina = vacina;
	}

	public LocalDate getDataVacina() {
		return dataVacina;
	}

	public void setDataVacina(LocalDate dataVacina) {
		this.dataVacina = dataVacina;
	}

	public double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(double avaliacao) {
		this.avaliacao = avaliacao;
	}

}
