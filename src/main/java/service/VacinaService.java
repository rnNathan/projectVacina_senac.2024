package service;

import java.sql.SQLException;
import java.util.ArrayList;

import exception.PessoaException;
import model.entity.VacinaEntity;
import model.repository.VacinaRepository;

public class VacinaService {
	
	VacinaRepository repository = new VacinaRepository();
	
	
	
	public VacinaEntity salvar (VacinaEntity entity){
		
		return repository.salvar(entity);
	}
	
	public boolean excluir (int id) {
		return repository.excluir(id);
	}
	
	public boolean alterar (VacinaEntity entity)  {
		return repository.alterar(entity);
	}
	
	public VacinaEntity consultarPorId(int id)  {
		return repository.consultarPorId(id);
	}
	
	public ArrayList<VacinaEntity> listarTodas() {
		return repository.consultarTodos();
	}
	
	

}
