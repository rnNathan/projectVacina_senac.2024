package service;

import java.util.ArrayList;

import model.entity.VacinacaoEntity;
import model.repository.VacinacaoRepository;

public class VacinacaoService {
	
	VacinacaoRepository repository = new VacinacaoRepository();
	
	public VacinacaoEntity salvar (VacinacaoEntity vacinacao) {
		this.campoObrigatorio(vacinacao);
		return repository.salvar(vacinacao);
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
	
	
	private boolean campoObrigatorio (VacinacaoEntity entity) {
		boolean retorno = false;
		if (entity.getDataVacina() == null) {
			System.out.println("PRECISA SER PREENCHIDO A DATA!");
		} else {
			retorno = true;
		}
		
		
		
		return retorno;
	}

}
