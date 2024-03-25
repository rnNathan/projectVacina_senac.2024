package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.PessoaEntity;
import model.entity.VacinaEntity;

public class VacinaRepository implements BaseRepository<VacinaEntity> {

	@Override
	public VacinaEntity salvar(VacinaEntity novaVacina) {

		String query = "INSERT INTO vacina.vacinas (nome, id_pais, id_pessoa, estagio, dataInicioPesquisa) values (?, ?, ?, ?, ?)"; 
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		
		try {
			
			pstmt.setString(1, novaVacina.getNome());
			pstmt.setInt(2, novaVacina.getPaisOrigem().getIdPais());
			pstmt.setInt(3, novaVacina.getPesquisador().getId());
			pstmt.setInt(4, novaVacina.getEstagio());
			pstmt.setDate(5, Date.valueOf(novaVacina.getDataInicioPesquisa()));
			pstmt.execute();
			
			ResultSet resultado = pstmt.getGeneratedKeys();
			if (resultado.next()) {
				
				novaVacina.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {

			System.out.println("ERRO AO SALVAR VACINA!!!");
			System.out.println("ERRO: " + e.getMessage());

		} finally {

			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}

		return novaVacina;
	}

	@Override
	public boolean excluir(int id) {
		String query = "delete from vacinas where id = " + id;
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
		String query = " UPDATE vacinas "
				+ " SET id_pessoa=?, nome=?, id_pais=?, estagio=?, dataInicioPesquisa=? "
				+ " WHERE id=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setInt(1, alterarVacina.getPesquisador().getId());
			pstmt.setString(2, alterarVacina.getNome());
			pstmt.setInt(3, alterarVacina.getPaisOrigem().getIdPais());
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
		String query = "SELECT * FROM vacinas where id_vacina = " + id;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		VacinaEntity vacina = null;
		ResultSet resultado = null;
		try {
			resultado = stmt.executeQuery(query);
			PessoaRepository pessoaRepository = new PessoaRepository();
			PaisRepository paisRepository = new PaisRepository();
			if (resultado.next()) {
				vacina = new VacinaEntity();
				vacina.setId(resultado.getInt("id_vacina"));
				vacina.setNome(resultado.getString("nome"));
				vacina.setPaisOrigem(paisRepository.consultarPorId(resultado.getInt("id_pais")));
				PessoaEntity pesquisador = pessoaRepository.consultarPorId(resultado.getInt("id_pessoa"));
				vacina.setPesquisador(pesquisador);
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
			PessoaRepository repository = new PessoaRepository();
			PaisRepository paisRepository = new PaisRepository();
			while (resultado.next()) {
				VacinaEntity vacina = new VacinaEntity();
				vacina.setId(resultado.getInt("id_vacina"));
				vacina.setNome(resultado.getString("nome"));
				vacina.setPaisOrigem(paisRepository.consultarPorId(resultado.getInt("id_pais")));
				vacina.setPesquisador(repository.consultarPorId(resultado.getInt("id_pessoa")));
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
