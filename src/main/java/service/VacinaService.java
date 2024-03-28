package service;

import java.util.ArrayList;

import exception.PessoaException;
import model.entity.VacinaEntity;
import model.entity.VacinacaoEntity;
import model.repository.VacinaRepository;


public class VacinaService {

	VacinaRepository repository = new VacinaRepository();
	
	
	VacinacaoEntity entityVacinacao = new VacinacaoEntity();

	public VacinaEntity salvar(VacinaEntity entity) {
		
		
		return repository.salvar(entity);
	}

	public boolean excluir (int id) throws PessoaException {
		
		if (entityVacinacao.getVacina() == null || entityVacinacao.getVacina().getId().equals(0)) {
			throw new PessoaException("ERRO AO EXCLUIR UMA VACINA");
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

}
