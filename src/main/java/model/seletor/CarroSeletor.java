package model.seletor;

public class CarroSeletor extends BaseSeletor{
	
	private String nomeMarca;
	private String modelo;
	private Integer anoInicial;
	private Integer anoFinal;
	private String paisMarca;
	private Double valorInicial;
	private Double valorFinal;
	
	public boolean temFiltro() {
		return  (this.nomeMarca != null && this.nomeMarca.trim().length() > 0) 
			 || (this.modelo != null && this.modelo.trim().length() > 0) 
			 || (this.anoInicial != null)
		   	 || (this.anoFinal != null)
		   	 || (this.paisMarca != null)
		   	 || (this.anoInicial != null)
		   	 || (this.anoFinal != null);
	}
	
	public CarroSeletor() {
		
	}

	public String getNomeMarca() {
		return nomeMarca;
	}

	public void setNomeMarca(String nomeMarca) {
		this.nomeMarca = nomeMarca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAnoInicial() {
		return anoInicial;
	}

	public void setAnoInicial(Integer anoInicial) {
		this.anoInicial = anoInicial;
	}

	public Integer getAnoFinal() {
		return anoFinal;
	}

	public void setAnoFinal(Integer anoFinal) {
		this.anoFinal = anoFinal;
	}

	public String getPaisMarca() {
		return paisMarca;
	}

	public void setPaisMarca(String paisMarca) {
		this.paisMarca = paisMarca;
	}

	public Double getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(Double valorInicial) {
		this.valorInicial = valorInicial;
	}

	public Double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
	}
	
	
	
	

}
