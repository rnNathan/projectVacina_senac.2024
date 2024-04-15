
package model.entity;

import java.time.LocalDate;
import java.util.List;

public class PessoaEntity  {
	
	public static final int PESQUISADOR= 1;
	public static final int VOLUNTARIO= 2;
	public static final int PUBLICO_GERAL = 3;
	
	
	private int id;
	private String nome;
	private LocalDate dataNascimento;
	private String  sexo;
	private String cpf;
	private Integer tipoPessoaCadastrada;
	private List<VacinacaoEntity> todasVacinas;
	private PaisEntity paisOrigem;
	
	
	
	public PessoaEntity(int id, String nome, LocalDate dataNascimento, String sexo, String cpf,
			Integer tipoPessoaCadastrada, List<VacinacaoEntity> todasVacinas, PaisEntity paisOrigem) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.tipoPessoaCadastrada = tipoPessoaCadastrada;
		this.todasVacinas = todasVacinas;
		this.paisOrigem = paisOrigem;

	}
	public PessoaEntity() {
		super();
	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Integer getTipoPessoaCadastrada() {
		return tipoPessoaCadastrada;
	}
	public void setTipoPessoaCadastrada(Integer tipoPessoaCadastrada) {
		this.tipoPessoaCadastrada = tipoPessoaCadastrada;
	}
	public List<VacinacaoEntity> getTodasVacinas() {
		return todasVacinas;
	}
	public void setTodasVacinas(List<VacinacaoEntity> todasVacinas) {
		this.todasVacinas = todasVacinas;
	}
	public PaisEntity getPaisOrigem() {
		return paisOrigem;
	}
	public void setPaisOrigem(PaisEntity paisOrigem) {
		this.paisOrigem = paisOrigem;
	}
	
	
	
	
	
	

}
