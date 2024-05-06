package model.seletor;

import java.time.LocalDate;

public class PessoaSeletor extends BaseSeletor {

	private String nomePessoa;
	private String cpf;
	private String nomePais;
	private LocalDate dataNascimento;

	public PessoaSeletor() {
		super();

	}

	public PessoaSeletor(String nomePessoa, String cpf, String nomePais, LocalDate dataNascimento) {
		super();
		this.nomePessoa = nomePessoa;
		this.cpf = cpf;
		this.nomePais = nomePais;
		this.dataNascimento = dataNascimento;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomePais() {
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
