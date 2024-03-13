package service;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

import exception.PessoaException;
import model.entity.PessoaEntity;
import model.repository.PessoaRepository;

public class PessoaService  {
	
	private PessoaRepository pessoaRepository = new PessoaRepository();
	
	public PessoaEntity salvar (PessoaEntity entity) throws PessoaException {
		
		
			if (entity.getNome() != null && !entity.getNome().isEmpty() &&
		               entity.getDataNascimento() != null &&
		               entity.getSexo() != null && !entity.getSexo().isEmpty() &&
		               entity.getCpf() != null && !entity.getCpf().isEmpty() &&
		               entity.getTipoPessoaCadastrada() != null && !entity.getTipoPessoaCadastrada().isEmpty() ) {
				
			} 
			
			if (pessoaRepository.verificarCPF(entity)) {
				throw new PessoaException("ERRO CPF JÁ EXISTENTE");
			} else {
				entity = pessoaRepository.salvar(entity);
			}
		
			return entity;
	
		

	}
	
	public boolean excluir (int id) {
		return this.pessoaRepository.excluir(id);
	}
	
	public ArrayList <PessoaEntity> listarTodos(){
		return this.pessoaRepository.consultarTodos();
	}
}
