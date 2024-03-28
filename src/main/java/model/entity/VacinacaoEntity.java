package model.entity;

import java.time.LocalDate;

/**
 * 
 */
public class VacinacaoEntity {
	

	private int idVacinacao;
	private int IdPessoa;
	private VacinaEntity vacina;
	private LocalDate dataVacina;
	private int avaliacao;
	
	public VacinacaoEntity(int id, int idVacinacao, int idPessoa, VacinaEntity vacina, LocalDate dataVacina, int avaliacao) {
		super();
		this.idVacinacao = idVacinacao;
		IdPessoa = idPessoa;
		this.vacina = vacina;
		this.dataVacina = dataVacina;
		this.avaliacao = avaliacao;
		
		
		
	}
	
	public VacinacaoEntity() {	
		super();
	}
	
	



	public int getIdPessoa() {
		return IdPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		IdPessoa = idPessoa;
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

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	
	
	
	
	

}
