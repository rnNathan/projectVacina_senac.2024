package service;

import exception.PessoaException;
import model.entity.Montadora;
import model.repository.MontadoraRepository;

public class MontadoraService {
	
	private MontadoraRepository repository = new MontadoraRepository();
	
	public Montadora inserir (Montadora novaMontadora) throws PessoaException {
		this.validarCamposObrigatorios(novaMontadora);
		return repository.inserir(novaMontadora);
	}
	
	private void validarCamposObrigatorios(Montadora m) throws PessoaException {
		
		String mensagemValidacao = "";
		if (m.getNome() == null || m.getNome().isEmpty()) {
			mensagemValidacao += " - informe o nome \n";
		}
		if (m.getDataFundacao() == null) {
			mensagemValidacao += " - informe a data de fundação \n";
		}
		if (m.getNomeFundador() == null || m.getNomeFundador().isEmpty()) {
			mensagemValidacao += " - informe o nome do fundador";
		}
		if (m.getPaisFundacao() == null || m.getPaisFundacao().isEmpty()) {
			mensagemValidacao += " - informe o pais de fundação";
		}
	
		if (!mensagemValidacao.isEmpty()) {
			throw new PessoaException("Preencha o(s) seguinte(s) campo(s) \n " + mensagemValidacao);
		}
	}

}
