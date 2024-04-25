package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
import exception.PessoaException;
import model.entity.PessoaEntity;
import model.entity.VacinaEntity;
import model.entity.VacinacaoEntity;
import model.repository.PessoaRepository;
import model.repository.VacinaRepository;
import model.repository.VacinacaoRepository;

public class VacinacaoService {

	private VacinacaoRepository repository = new VacinacaoRepository();
	private static final int NOTA_MAXIMA = 5;

	public VacinacaoEntity inserir(VacinacaoEntity novaVacinacao) throws PessoaException {
		validarDadosVacinação(novaVacinacao);
		atualizaMediaVacina(novaVacinacao);
		validarPessoaParaReceberVacina(novaVacinacao);
		return repository.inserir(novaVacinacao);
	}

	public boolean excluir(int id) {
		return repository.excluir(id);
	}

	public boolean alterar(VacinacaoEntity alterarVacinacao) throws PessoaException {
		
		validarDadosVacinação(alterarVacinacao);
		atualizaMediaVacina(alterarVacinacao);
		validarPessoaParaReceberVacina(alterarVacinacao);
		
		return repository.alterar(alterarVacinacao);
	}

	public VacinacaoEntity consultarPorId(int id) {
		return repository.consultarPorId(id);
	}

	public ArrayList<VacinacaoEntity> consultarTodos() {
		return repository.consultarTodos();
	}

	public List<VacinacaoEntity> consultarTodasPessoaPorId(int id) {
		return repository.consultarTodasVacinasPorPessoa(id);
	}
	
	
	
	private void validarPessoaParaReceberVacina(VacinacaoEntity vacinacao) throws PessoaException {
		
		VacinaEntity vac = vacinacao.getVacina();
		PessoaEntity paciente = new PessoaRepository().consultarPorId(vacinacao.getIdPessoa());
		
		boolean podeReceberDose = false;
		if (vac.getEstagio() == VacinaEntity.ESTAGIO_INICIAL && paciente.getTipoPessoaCadastrada() == PessoaEntity.PESQUISADOR) {
			podeReceberDose = true;
		}
		
		if (vac.getEstagio() == VacinaEntity.ESTAGIO_TESTE && (paciente.getTipoPessoaCadastrada() == PessoaEntity.PESQUISADOR 
				|| paciente.getTipoPessoaCadastrada() == PessoaEntity.VOLUNTARIO)) {
				podeReceberDose = true;
		}
		
		if (vac.getEstagio() == VacinaEntity.ESTAGIO_APLICACAO_EM_MASSA) {
			podeReceberDose = true;
		}
		
		if (!podeReceberDose) {
			throw new PessoaException("Usuário sem permissão para receber a vacina");
		}
		
	}
	

	private void validarDadosVacinação(VacinacaoEntity novaVacinacao) throws PessoaException {
		if (novaVacinacao.getIdPessoa() == 0 || novaVacinacao.getVacina() == null
				|| novaVacinacao.getVacina().getId() == 0) {
			throw new PessoaException("INFORME O ID DA PESSOA E A VACINA DA APLICAÇÃO");
		}
		novaVacinacao.setDataVacina(LocalDate.now());

		if (novaVacinacao.getAvaliacao() == 0) {
			novaVacinacao.setAvaliacao(NOTA_MAXIMA);
		}
	}

	private void atualizaMediaVacina(VacinacaoEntity novaVacinacao) {
		ArrayList<VacinacaoEntity> dosesAplicada = repository
				.consultarTodasVacinasPorId(novaVacinacao.getVacina().getId());
		
		DecimalFormat formato = new DecimalFormat("#.0");
		
		double novaMediaVacina = 0.0;
		double somatoriaNotasVacina = 0.0;

		for (VacinacaoEntity dose : dosesAplicada) {
			somatoriaNotasVacina += dose.getAvaliacao();
		}
		novaMediaVacina = (somatoriaNotasVacina + novaVacinacao.getAvaliacao()) / (dosesAplicada.size() + 1);
		
		
		VacinaRepository vacinaRepository = new VacinaRepository();
		VacinaEntity vacinaAplicada = vacinaRepository.consultarPorId(novaVacinacao.getVacina().getId());
		vacinaAplicada.setMedia(novaMediaVacina);
		
		vacinaRepository.alterar(vacinaAplicada);
		
		novaVacinacao.setVacina(vacinaAplicada);
		
		
		
	}

}
