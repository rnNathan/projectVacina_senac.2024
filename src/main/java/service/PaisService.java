package service;

import java.util.ArrayList;

import model.entity.PaisEntity;
import model.repository.PaisRepository;

public class PaisService {
	
	PaisRepository repository = new PaisRepository();
	
	public PaisEntity inserir (PaisEntity novoPais) {
		return repository.inserir(novoPais);
	}
	
	public PaisEntity consultarPorId(int id) {
		return repository.consultarPorId(id);
	}
	
	public ArrayList<PaisEntity> consultarTodos (){
		return repository.consultarTodos();
	}
	
	

}
