package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.VacinaEntity;
import model.seletor.vacinaSeletor;


public class VacinaRepository implements BaseRepository<VacinaEntity> {
	


	@Override
	public VacinaEntity salvar(VacinaEntity novaVacina) {

		String query = "INSERT INTO vacina.vacinas (nome, id, id_pessoa, estagio, dataInicioPesquisa, media) values (?, ?, ?, ?, ?, ?)"; 
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		
		try {
			
			pstmt.setString(1, novaVacina.getNome());
			pstmt.setInt(2, novaVacina.getPaisOrigem().getIdPais());
			pstmt.setInt(3, novaVacina.getPesquisador().getId());
			pstmt.setInt(4, novaVacina.getEstagio());
			pstmt.setDate(5, Date.valueOf(novaVacina.getDataInicioPesquisa()));
			pstmt.setDouble(6, novaVacina.getMedia());
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
		String query = "delete from vacina.vacinas where id_vacina = " + id;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;

		try {
			
			if (stmt.executeUpdate(query) == 1) {
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
		String query = " UPDATE vacina.vacinas "
				+ " SET id_pessoa=?, nome=?, id=?, estagio=?, dataInicioPesquisa=?, media=? "
				+ " WHERE id_vacina=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setInt(1, alterarVacina.getPesquisador().getId());
			pstmt.setString(2, alterarVacina.getNome());
			pstmt.setInt(3, alterarVacina.getPaisOrigem().getIdPais());
			pstmt.setInt(4, alterarVacina.getEstagio());
			pstmt.setDate(5, Date.valueOf(alterarVacina.getDataInicioPesquisa()));
			pstmt.setDouble(6, alterarVacina.getMedia());
			pstmt.setInt(7, alterarVacina.getId());
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
	public VacinaEntity consultarPorId(int idVacina) {
		String query = "SELECT * FROM vacina.vacinas where id_vacina = " + idVacina;
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
				vacina.setPaisOrigem(paisRepository.consultarPorId(resultado.getInt("id")));
				vacina.setPesquisador(pessoaRepository.consultarPorId(resultado.getInt("id_pessoa")));
				vacina.setEstagio(resultado.getInt("estagio"));
				vacina.setDataInicioPesquisa(resultado.getDate("dataInicioPesquisa").toLocalDate());
				vacina.setMedia(resultado.getDouble("media"));
				
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
		String query = "select * from vacina.vacinas";
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
				vacina.setPaisOrigem(paisRepository.consultarPorId(resultado.getInt("id")));
				vacina.setPesquisador(repository.consultarPorId(resultado.getInt("id_pessoa")));
				vacina.setEstagio(resultado.getInt("estagio"));
				vacina.setDataInicioPesquisa(resultado.getDate("dataInicioPesquisa").toLocalDate());
				vacina.setMedia(resultado.getDouble("media"));
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
	
	public ArrayList<VacinaEntity> consultarPorFiltro(vacinaSeletor seletor) {
		
		ArrayList<VacinaEntity> vacinas = new ArrayList<VacinaEntity>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean primeiro = true;
		
		String query = " select v.* from vacina.vacinas v "
				 + " inner join paises p on v.id = p.id "
				 + " inner join pessoa pe on v.id_pessoa = pe.id_pessoa  ";
		
		
		
		if (seletor.getNomeVacina() != null) {
			if (primeiro) {
				query +=  " where ";
			}else {
				query += " and ";
			}
			
			query += " upper(v.nome) like upper  ('%" + seletor.getNomeVacina() + "%')";
			primeiro = false;
			
		}
		
		if (seletor.getNomePais() != null) {
			if (primeiro) {
				query += " where ";
			}else {
				query += " and ";
			}
			
			query += " upper(p.nome_pais) like upper('%" + seletor.getNomePais() + "%')";
			
		}
		
		if(seletor.getNomePesquisador() != null) {
			if (primeiro) {
				query += " where ";
			}else {
				query += " and ";
			}
			
			query += " upper (pe.nome) like upper('%" + seletor.getNomePesquisador() +"%')";
		}
		
		if (seletor.getDataInicioPesquisa() != null && seletor.getDataFinalPesquisa() != null) {
			if (!primeiro) {
				query += " and ";
			}
			
			query += " v.dataInicioPesquisa BETWEEN '" + seletor.getDataInicioPesquisa() + "' and '" + seletor.getDataFinalPesquisa() + "'";
			primeiro = false;
		}

		try {
			
			resultado = stmt.executeQuery(query);
			PessoaRepository repository = new PessoaRepository();
			PaisRepository paisRepository = new PaisRepository();
			while (resultado.next()) {
				VacinaEntity vacina = new VacinaEntity();
				vacina.setId(resultado.getInt("id_vacina"));
				vacina.setNome(resultado.getString("nome"));
				vacina.setPaisOrigem(paisRepository.consultarPorId(resultado.getInt("id")));
				vacina.setPesquisador(repository.consultarPorId(resultado.getInt("id_pessoa")));
				vacina.setEstagio(resultado.getInt("estagio"));
				if(resultado.getDate("dataInicioPesquisa") != null) {
					vacina.setDataInicioPesquisa(resultado.getDate("dataInicioPesquisa").toLocalDate());
				}
				vacina.setMedia(resultado.getDouble("media"));
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
