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
	public VacinaEntity salvar(VacinaEntity salvarVacina) {

		String query = "INSERT INTO vacinas (nome, paisOrigem, id_pessoa, estagio, dataInicioPesquisa) values (?, ?, ?, ?, ?)"; 
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		
		

		try {
			
			PessoaRepository pessoaRepository = new PessoaRepository();
			pstmt.setString(1, salvarVacina.getNome());
			pstmt.setString(2, salvarVacina.getPaisOrigem());
			pstmt.setObject(3, pessoaRepository.consultarPorId(salvarVacina.getId()));
			pstmt.setInt(4, salvarVacina.getEstagio());
			pstmt.setDate(5, Date.valueOf(salvarVacina.getDataInicioPesquisa()));
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
				+ " SET id_pessoa=?, nome=?, paisOrigem=?, estagio=?, dataInicioPesquisa=? "
				+ " WHERE id=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setInt(1, alterarVacina.getPesquisador().getId());
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
		String query = "SELECT * FROM vacinas where id_vacina = " + id;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		VacinaEntity vacina = null;
		ResultSet resultado = null;
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				PessoaRepository pessoaRepository = new PessoaRepository();
				vacina = new VacinaEntity();
				vacina.setId(resultado.getInt("id_vacina"));
				vacina.setNome(resultado.getString("nome"));
				vacina.setPaisOrigem(resultado.getString("paisOrigem"));
				vacina.setPesquisador(pessoaRepository.consultarPorId(resultado.getInt("id_pessoa")));
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
				
				vacina.setId(resultado.getInt("id_vacina"));
				vacina.setNome(resultado.getString("nome"));
				vacina.setPaisOrigem(resultado.getString("paisOrigem"));
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
