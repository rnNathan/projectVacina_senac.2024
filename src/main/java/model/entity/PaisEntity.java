package model.entity;

public class PaisEntity {
	
	private int idPais;
	private String nomePais;
	private String sigla;
	public PaisEntity() {
		
		super();
	
	}
	public PaisEntity(int idPais, String nomePais, String sigla) {
		super();
		this.idPais = idPais;
		this.nomePais = nomePais;
		this.sigla = sigla;
	}
	public int getIdPais() {
		return idPais;
	}
	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}
	public String getNomePais() {
		return nomePais;
	}
	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	

}
