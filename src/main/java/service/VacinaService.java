package service;

import java.util.ArrayList;

import exception.PessoaException;
import model.entity.VacinaEntity;
import model.entity.VacinacaoEntity;
import model.repository.VacinaRepository;
import model.repository.VacinacaoRepository;
import model.seletor.vacinaSeletor;


public class VacinaService {

	VacinaRepository repository = new VacinaRepository();
	
	
	VacinacaoEntity entityVacinacao = new VacinacaoEntity();

	public VacinaEntity inserir(VacinaEntity entity) {
		return repository.inserir(entity);
	}

	public boolean excluir (int id) throws PessoaException {
		
		VacinacaoRepository vacinacao = new VacinacaoRepository();
		ArrayList<VacinacaoEntity> listVacina = vacinacao.consultarTodasVacinasPorId(id);
		
		if (listVacina.size() > 0) {
			throw new PessoaException("Não pode excluir uma vacina, pois ela tem uma vacina cadastrada");
		}
		
		
		return repository.excluir(id);
	}

	public boolean alterar(VacinaEntity entity) {
		return repository.alterar(entity);
	}

	public VacinaEntity consultarPorId(int id) {
		return repository.consultarPorId(id);
	}

	public ArrayList<VacinaEntity> listarTodas() {
		return repository.consultarTodos();
	}
	
	public ArrayList<VacinaEntity> consultarPorFiltro(vacinaSeletor seletor) {
		return repository.consultarPorFiltro(seletor);
	}

}
