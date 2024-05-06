package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.PessoaException;

import model.entity.PessoaEntity;
import model.entity.VacinaEntity;
import model.seletor.PessoaSeletor;
import model.seletor.VacinaSeletor;

public class PessoaRepository implements BaseRepository<PessoaEntity> {

	public boolean verificarCPF(PessoaEntity pessoaEntity) throws PessoaException {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;
		String query = "SELECT cpf FROM pessoa WHERE cpf = '" + pessoaEntity.getCpf() + "'";

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				retorno = true;
			}

		} catch (SQLException e) {
			throw new PessoaException("CPF JÁ CADASTRADO NO BANCO!");
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	@Override
	public PessoaEntity inserir(PessoaEntity novaEntidade) {
		String query = "INSERT INTO vacina.pessoa (nome, dataNascimento, sexo, cpf, tipoPessoaCadastrada, id)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);

		try {
			// Estou pegando um metodo setString para obter o tipo que irei cadastrar
			// depois mostro a posição da coluna e insero o valor que será armazenado no
			// repositorio.
			preencherValoresUpdateEInsert(pstmt, novaEntidade);
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();

			if (resultado.next()) {
				novaEntidade.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao salvar pessoa");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);

		}

		return novaEntidade;
	}

	private PreparedStatement preencherValoresUpdateEInsert(PreparedStatement pstmt, PessoaEntity novaEntidade)
			throws SQLException {
		pstmt.setString(1, novaEntidade.getNome());
		pstmt.setDate(2, Date.valueOf(novaEntidade.getDataNascimento()));
		pstmt.setString(3, novaEntidade.getSexo());
		pstmt.setString(4, novaEntidade.getCpf());
		pstmt.setInt(5, novaEntidade.getTipoPessoaCadastrada());
		pstmt.setInt(6, novaEntidade.getPaisOrigem().getIdPais());
		return pstmt;
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		String query = "DELETE FROM vacina.pessoa WHERE id_pessoa = " + id;
		boolean retorno = false;

		try {
			if (stmt.executeUpdate(query) == 1) {
				retorno = true;
			}

		} catch (SQLException e) {
			System.out.println("ERRO AO EXCLUIR UMA ENTIDADE.");
			System.out.println("ERRO: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);

		}

		return retorno;
	}

	@Override
	public boolean alterar(PessoaEntity pessoaEditada) {

		boolean alterou = false;
		String query = " UPDATE vacina.pessoa "
				+ " SET nome=?, cpf=?, sexo=?, dataNascimento=?, tipoPessoaCadastrada=?, id=? " + " WHERE id_pessoa=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			this.preencherValoresUpdateEInsert(stmt, pessoaEditada);
			stmt.setInt(7, pessoaEditada.getId());
			alterou = stmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return alterou;

	}

	@Override
	public PessoaEntity consultarPorId(int id) {
		String query = "SELECT * FROM vacina.pessoa where id_pessoa = " + id;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		PessoaEntity pessoa = null;
		ResultSet resultado = null;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				pessoa = new PessoaEntity();
				pessoa.setId(resultado.getInt("id_pessoa"));
				pessoa.setNome(resultado.getString("nome"));
				pessoa.setDataNascimento(resultado.getDate("dataNascimento").toLocalDate());
				pessoa.setSexo(resultado.getString("sexo"));
				pessoa.setCpf(resultado.getString("cpf"));
				pessoa.setTipoPessoaCadastrada(resultado.getInt("tipoPessoaCadastrada"));
				PaisRepository paisRepository = new PaisRepository();
				pessoa.setPaisOrigem(paisRepository.consultarPorId(resultado.getInt("id")));
				// VacinacaoRepository repository = new VacinacaoRepository();
				// pessoa.setTodasVacinas(repository.consultarTodasVacinasPorPessoa(pessoa.getId()));

			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar pessoa por ID.");
			System.out.println("ERRO: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return pessoa;
	}

	@Override
	public ArrayList<PessoaEntity> consultarTodos() {

		ArrayList<PessoaEntity> listaPessoa = new ArrayList<PessoaEntity>();

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT * FROM vacina.pessoa";
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				PessoaEntity pessoaEntity = new PessoaEntity();
				pessoaEntity.setId(resultado.getInt("id_pessoa"));
				pessoaEntity.setNome(resultado.getString("nome"));
				pessoaEntity.setDataNascimento(resultado.getDate("dataNascimento").toLocalDate());
				pessoaEntity.setSexo(resultado.getString("sexo"));
				pessoaEntity.setCpf(resultado.getString("cpf"));
				pessoaEntity.setTipoPessoaCadastrada(resultado.getInt("tipoPessoaCadastrada"));
				PaisRepository paisRepository = new PaisRepository();
				pessoaEntity.setPaisOrigem(paisRepository.consultarPorId(resultado.getInt("id")));
				// VacinacaoRepository repository = new VacinacaoRepository();
				// pessoaEntity.setTodasVacinas(repository.consultarTodasVacinasPorPessoa(pessoaEntity.getId()));
				listaPessoa.add(pessoaEntity);
			}

		} catch (SQLException e) {

			System.out.println("Erro ao consultar todas entidade no banco.");
			System.out.println("ERRO: " + e.getMessage());
		} finally {

			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return listaPessoa;
	}

	public ArrayList<PessoaEntity> listarPorPerquisador() {
		ArrayList<PessoaEntity> listaPessoa = new ArrayList<PessoaEntity>();

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT * FROM vacina.pessoa WHERE tipoPessoaCadastrada = '" + 1 + "'";
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				PessoaEntity pessoaEntity = new PessoaEntity();
				pessoaEntity.setId(resultado.getInt("id_pessoa"));
				pessoaEntity.setNome(resultado.getString("nome"));
				pessoaEntity.setDataNascimento(resultado.getDate("dataNascimento").toLocalDate());
				pessoaEntity.setSexo(resultado.getString("sexo"));
				pessoaEntity.setCpf(resultado.getString("cpf"));
				pessoaEntity.setTipoPessoaCadastrada(resultado.getInt("tipoPessoaCadastrada"));
				PaisRepository paisRepository = new PaisRepository();
				pessoaEntity.setPaisOrigem(paisRepository.consultarPorId(resultado.getInt("id")));
				// VacinacaoRepository repository = new VacinacaoRepository();
				// pessoaEntity.setTodasVacinas(repository.consultarTodasVacinasPorPessoa(pessoaEntity.getId()));
				listaPessoa.add(pessoaEntity);
			}

		} catch (SQLException e) {

			System.out.println("Erro ao consultar todas os pesquisadores.");
			System.out.println("ERRO: " + e.getMessage());
		} finally {

			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return listaPessoa;
	}

	public ArrayList<PessoaEntity> consultarPorFiltro(PessoaSeletor seletor) {

		ArrayList<PessoaEntity> pessoas = new ArrayList<PessoaEntity>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean primeiro = true;

		String query = " select pe.* from vacina.pessoa pe " + " inner join paises p on pe.id = p.id ";

		if (seletor.getNomePessoa() != null) {
			if (primeiro) {
				query += " where ";
			} else {
				query += " and ";
			}

			query += " upper(pe.nome) like upper  ('%" + seletor.getNomePessoa() + "%')";
			primeiro = false;

		}

		if (seletor.getNomePais() != null) {
			if (primeiro) {
				query += " where ";
			} else {
				query += " and ";
			}

			query += " upper(p.nome_pais) like upper('%" + seletor.getNomePais() + "%')";

		}

		if (seletor.getDataNascimento() != null) {
			if (primeiro) {
				query += " and ";
			}
			query += "pe.dataNascimento = " + seletor.getDataNascimento();
			primeiro = false;
		}
			try {
				resultado = stmt.executeQuery(query);
				while (resultado.next()) {
					PessoaEntity pessoaEntity = new PessoaEntity();
					pessoaEntity.setId(resultado.getInt("id_pessoa"));
					pessoaEntity.setNome(resultado.getString("nome"));
					pessoaEntity.setDataNascimento(resultado.getDate("dataNascimento").toLocalDate());
					pessoaEntity.setSexo(resultado.getString("sexo"));
					pessoaEntity.setCpf(resultado.getString("cpf"));
					pessoaEntity.setTipoPessoaCadastrada(resultado.getInt("tipoPessoaCadastrada"));
					PaisRepository paisRepository = new PaisRepository();
					pessoaEntity.setPaisOrigem(paisRepository.consultarPorId(resultado.getInt("id")));
					// VacinacaoRepository repository = new VacinacaoRepository();
					// pessoaEntity.setTodasVacinas(repository.consultarTodasVacinasPorPessoa(pessoaEntity.getId()));
					pessoas.add(pessoaEntity);
				}

			} catch (SQLException e) {
				System.out.println("ERRO AO CONSULTAR TODAS AS PESSOAS SELETO!");
				System.out.println("ERRO: " + e.getMessage());
			} finally {
				Banco.closeResultSet(resultado);
				Banco.closeStatement(stmt);
				Banco.closeConnection(conn);
			}

			return pessoas;

		

	}

}
