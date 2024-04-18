package model.seletor;

public class vacinaSeletor {
	
	private String nomePais;
	private String nomeVacina;
	private String nomePesquisador;
	
	public vacinaSeletor(String nomePais, String nomeVacina, String nomePesquisador) {
		super();
		this.nomePais = nomePais;
		this.nomeVacina = nomeVacina;
		this.nomePesquisador = nomePesquisador;
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
	
	
	

}

