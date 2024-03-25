package service;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

import exception.PessoaException;
import model.entity.PessoaEntity;
import model.repository.PessoaRepository;

public class PessoaService  {
	
	private PessoaRepository pessoaRepository = new PessoaRepository();
	
	public PessoaEntity salvar (PessoaEntity entity) throws PessoaException {
		
		
			if (entity.getNome() != null && !entity.getNome().trim().isEmpty() &&
		               entity.getDataNascimento() != null &&
		               entity.getSexo() != null && !entity.getSexo().trim().isEmpty() &&
		               entity.getCpf() != null && !entity.getCpf().trim().isEmpty() || entity.getCpf().length() == 11 &&
		               entity.getTipoPessoaCadastrada() != null && entity.getPaisOrigem().getNomePais() != null) {
				
				if (pessoaRepository.verificarCPF(entity)) {
					throw new PessoaException("CPF JÁ CADASTRO NO BANCO DE DADOS!");
					
				} else {
					entity = pessoaRepository.salvar(entity);
					
				}
				
			} else {
				
				throw new PessoaException("UM DOS CAMPOS NÃO ESTÁ PREENCHIDO");
			}
		
			return entity;
	
		

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
}
