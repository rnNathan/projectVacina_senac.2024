package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import exception.PessoaException;
import model.entity.VacinacaoEntity;
import model.repository.VacinacaoRepository;

public class VacinacaoService {
	
	VacinacaoRepository repository = new VacinacaoRepository();
	private static final int NOTA_MAXIMA = 5;
	
	public VacinacaoEntity salvar (VacinacaoEntity novaVacinacao) throws PessoaException{
		
		if (novaVacinacao.getIdpessoa() == 0 || novaVacinacao.getVacina() == null || novaVacinacao.getVacina().getId() == 0) {
			throw new PessoaException("INFORME O ID DA PESSOA E A VACINA DA APLICAÇÃO");
			
		}
		novaVacinacao.setDataVacina(LocalDate.now());
		
		if (novaVacinacao.getAvaliacao() == 0) {
			novaVacinacao.setAvaliacao(NOTA_MAXIMA);
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
