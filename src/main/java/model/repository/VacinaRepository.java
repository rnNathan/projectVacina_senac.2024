package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.VacinaEntity;

public class VacinaRepository implements BaseRepository<VacinaEntity> {

	@Override
	public VacinaEntity salvar(VacinaEntity salvarVacina) {

		
		String query = "INSERT INTO vacinas (nome, paisOrigem, idPesquisador, estagio, dataIncioPesquisa) values (?, ?, ?, ?, ?)"; 

		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);

		try {

			preencherValoresInsertUpdate(pstmt, salvarVacina);
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			if (resultado.next()) {
				salvarVacina.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {

			System.out.println("ERRO AO SALVAR VACINA!!!");
			System.out.println("ERRO: " + e.getMessage());

		} finally {

			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}

		return salvarVacina;
	}

	public PreparedStatement preencherValoresInsertUpdate(PreparedStatement pstmt, VacinaEntity vacinaEntity)
			throws SQLException {
		pstmt.setString(1, vacinaEntity.getNome());
		pstmt.setString(2, vacinaEntity.getPaisOrigem());
		pstmt.setObject(3, vacinaEntity.getIdPesquisador());
		pstmt.setInt(4, vacinaEntity.getEstagio());
		pstmt.setDate(5, Date.valueOf(vacinaEntity.getDataInicioPesquisa()));
		return pstmt;
	}

	@Override
	public boolean excluir(int id) {
		String query = "delete from vacina where id = " + id;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				retorno = true;

			}

		} catch (SQLException e) {
			System.out.println("ERRO AO EXCLUIR UMA VACINA!");
			System.out.println("ERRO: " + e.getMessage());

		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return retorno;
	}

	@Override
	public boolean alterar(VacinaEntity alterarVacina) {
		boolean retorno = false;
		String query = " UPDATE vacina "
				+ " SET id_pesquisador=?, nome=?, pais_origem=?, estagio_pesquisa=?, data_inicio_pesquisa=? "
				+ " WHERE id=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setInt(1, alterarVacina.getIdPesquisador().getId());
			pstmt.setString(2, alterarVacina.getNome());
			pstmt.setString(3, alterarVacina.getPaisOrigem());
			pstmt.setInt(4, alterarVacina.getEstagio());
			pstmt.setDate(5, Date.valueOf(alterarVacina.getDataInicioPesquisa()));
			pstmt.setInt(6, alterarVacina.getId());
			retorno = pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar vacina");
			System.out.println("Erro: " + e.getMessage());
		} finally {

			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	@Override
	public VacinaEntity consultarPorId(int id) {
		String query = "SELECT * FROM vacinas where id = " + id;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		VacinaEntity vacina = null;
		ResultSet resultado = null;
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				PessoaRepository pessoaRepository = new PessoaRepository();
				vacina = new VacinaEntity();
				vacina.setId(resultado.getInt("id"));
				vacina.setNome(resultado.getString("nome"));
				vacina.setPaisOrigem(resultado.getString("paisOrigem"));
				vacina.setIdPesquisador(pessoaRepository.consultarPorId(resultado.getInt("idPessoa")));
				vacina.setEstagio(resultado.getInt("estagio"));
				vacina.setDataInicioPesquisa(resultado.getDate("dataInicioPesquisa").toLocalDate());

			}

		} catch (SQLException e) {
			System.out.println("ERRO AO CONSULTAR A VACINA POR ID!");
			System.out.println("ERRO: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return vacina;
	}

	@Override
	public ArrayList<VacinaEntity> consultarTodos() {
		ArrayList<VacinaEntity> vacinas = new ArrayList<VacinaEntity>();
		String query = "select * from vacinas";
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				VacinaEntity vacina = new VacinaEntity();
				PessoaRepository repository = new PessoaRepository();
				vacina.setId(resultado.getInt("id"));
				vacina.setNome(resultado.getString("nome"));
				vacina.setPaisOrigem(resultado.getString("paisOrigem"));
				vacina.setIdPesquisador(repository.consultarPorId(resultado.getInt("idPessoa")));
				vacina.setEstagio(resultado.getInt("estagio"));
				vacina.setDataInicioPesquisa(resultado.getDate("dataInicioPesquisa").toLocalDate());
				vacinas.add(vacina);
			}

		} catch (SQLException e) {
			System.out.println("ERRO AO CONSULTAR TODAS AS VACINAS!");
			System.out.println("ERRO: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vacinas;
	}
	

}
