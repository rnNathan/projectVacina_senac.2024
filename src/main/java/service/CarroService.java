package service;

import java.util.ArrayList;

import exception.PessoaException;
import model.entity.Carro;
import model.repository.CarroRepository;
import model.seletor.CarroSeletor;

public class CarroService {

	private CarroRepository repository = new CarroRepository();

	public ArrayList<Carro> consultarPorFiltro(CarroSeletor seletor) throws PessoaException {
		
		ArrayList<Carro> list = new ArrayList<Carro>();
		
		
		if (seletor.temFiltro()) {
			
		   
		    if (seletor.getAnoInicial() != null && seletor.getAnoInicial() == null) {
		    	throw new PessoaException("Preencher campos em pares corretamente");
			}
		    if (seletor.getAnoInicial() == null && seletor.getAnoInicial() != null) {
		    	throw new PessoaException("Preencher campos em pares corretamente");
			}
		    
		    if (seletor.getValorInicial() != null && seletor.getValorFinal() == null) {
		        throw new PessoaException("Preencher campos em pares corretamente");
		    }
		    
		    if (seletor.getValorInicial() == null && seletor.getValorFinal() != null) {
		        throw new PessoaException("Preencher campos em pares corretamente");
		    }
		    
		    
		    list = repository.consultarPorFiltro(seletor);
		    
		} else {
		    throw new PessoaException("Tem que informar pelo menos um filtro no seletor");
		}
		
		return list;
	}
}
