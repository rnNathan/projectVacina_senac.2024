package model.entity;

import java.time.LocalDate;
import java.util.List;

public class PessoaEntity  {
	
	//Classe onde vai ser espeficado os valores do meu objeto.
	
	private int id;
	private String nome;
	private LocalDate dataNascimento;
	private String  sexo;
	private String cpf;
	private String tipoPessoaCadastrada;
	private List<VacinacaoEntity> todasVacinas;
	
	
	
	public PessoaEntity(int id, String nome, LocalDate dataNascimento, String sexo, String cpf,
			String tipoPessoaCadastrada, List<VacinacaoEntity> todasVacinas) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.tipoPessoaCadastrada = tipoPessoaCadastrada;
		this.todasVacinas = todasVacinas;

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
	public String getTipoPessoaCadastrada() {
		return tipoPessoaCadastrada;
	}
	public void setTipoPessoaCadastrada(String tipoPessoaCadastrada) {
		this.tipoPessoaCadastrada = tipoPessoaCadastrada;
	}
	public List<VacinacaoEntity> getTodasVacinas() {
		return todasVacinas;
	}
	public void setTodasVacinas(List<VacinacaoEntity> todasVacinas) {
		this.todasVacinas = todasVacinas;
	}
	
	
	
	
	
	

}
