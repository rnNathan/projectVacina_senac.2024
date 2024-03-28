package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exception.PessoaException;
import model.entity.PessoaEntity;
import model.entity.VacinaEntity;
import model.entity.VacinacaoEntity;
import model.repository.VacinacaoRepository;



public class VacinacaoService {
	
	private VacinacaoRepository repository = new VacinacaoRepository();
	private static final int NOTA_MAXIMA = 5;
	private VacinacaoEntity vacinacao = new VacinacaoEntity();
	
	public VacinacaoEntity salvar (VacinacaoEntity novaVacinacao) throws PessoaException{
		
		if (novaVacinacao.getIdPessoa() == 0 || novaVacinacao.getVacina() == null || novaVacinacao.getVacina().getId() == 0) {
			throw new PessoaException("INFORME O ID DA PESSOA E A VACINA DA APLICAÇÃO");
			
		}
		novaVacinacao.setDataVacina(LocalDate.now());
		
		if (novaVacinacao.getAvaliacao() == 0) {
			novaVacinacao.setAvaliacao(NOTA_MAXIMA);
		}
		
			if (novaVacinacao.getVacina().getPesquisador().getTipoPessoaCadastrada()  == 2 && 
					novaVacinacao.getVacina().getEstagio() == 1) {
				throw new PessoaException("PESSOA NÃO É COMPATIVEL");
				
			}
			
			if (novaVacinacao.getVacina().getPesquisador().getTipoPessoaCadastrada() == 3 && novaVacinacao.getVacina().getEstagio() == 1 ||  novaVacinacao.getVacina().getEstagio() == 2 ) {
				throw new PessoaException("tipo de pessoa do pesquisador não é compativel com estagio da vacina");
			}
		
		
		return repository.salvar(novaVacinacao);
	}
	
	
	public boolean excluir (int id) {
		return repository.excluir(id);
	}
	
	public boolean alterar(VacinacaoEntity vacinacao) {
		return repository.alterar(vacinacao);
	}
	
	public VacinacaoEntity consultarPorId(int id) {
		return repository.consultarPorId(id);
	}
	
	public ArrayList<VacinacaoEntity> consultarTodos (){
		return repository.consultarTodos();
	}
	
	public List<VacinacaoEntity> consultarTodasPessoaPorId(int id) {
		return repository.consultarTodasVacinasPorPessoa(id);
	}
	
	

}
