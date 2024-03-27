package service;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

import exception.PessoaException;
import model.entity.PessoaEntity;
import model.repository.PessoaRepository;

public class PessoaService  {
	
	private PessoaRepository pessoaRepository = new PessoaRepository();
	
	public PessoaEntity salvar (PessoaEntity entity) throws PessoaException {
		
		validarCamposObrigatorios(entity);
		pessoaRepository.verificarCPF(entity);
		return pessoaRepository.salvar(entity);
		
	}
	
	public Boolean alterar (PessoaEntity alterarPessoa) {
		return pessoaRepository.alterar(alterarPessoa);
	}
	
	public PessoaEntity consultarPorId(int id) {
		return pessoaRepository.consultarPorId(id);
	}
	
	public boolean excluir (int id) {
		return this.pessoaRepository.excluir(id);
	}
	
	public ArrayList <PessoaEntity> listarTodos(){
		return this.pessoaRepository.consultarTodos();
	}
	
	private void validarCamposObrigatorios(PessoaEntity p) throws PessoaException{
		String mensagemValidacao = "";
		if(p.getNome() == null || p.getNome().isEmpty()) {
			mensagemValidacao += " - informe o nome \n";
		}
		if(p.getDataNascimento() == null) {
			mensagemValidacao += " - informe a data de nascimento \n";
		}
		if(p.getCpf() == null || p.getCpf().isEmpty() || p.getCpf().length() != 11) {
			mensagemValidacao += " - informe o CPF";
		}
		if(p.getSexo().equals(null)) {
			mensagemValidacao += " - informe o sexo";
		}
		if(p.getTipoPessoaCadastrada() < 1 || p.getTipoPessoaCadastrada() > 3) {
			mensagemValidacao += " - informe o tipo (entre 1 e 3)";
		}
		if(p.getPaisOrigem() == null) {
			mensagemValidacao += " - informe o país de origem";
		}
		
		if(!mensagemValidacao.isEmpty()) {
			throw new PessoaException("Preencha o(s) seguinte(s) campo(s) \n " + mensagemValidacao);
		}
	}
}
