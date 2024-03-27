package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.PaisEntity;

public class PaisRepository implements BaseRepository<PaisEntity> {

	@Override
	public PaisEntity salvar(PaisEntity novoPais) {
		String query = "INSERT INTO vacina.paises "
				+ "(nome_pais, sigla) "
				+ "VALUES(?, ?);";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setString(1, novoPais.getNomePais());
			pstmt.setString(2, novoPais.getSigla());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			if (resultado.next()) {
				novoPais.setIdPais(resultado.getInt(1));
			}
			
		} catch (SQLException e) {
			System.out.println("ERRO EM SALVAR PAIS!");
			System.out.println("ERRO:" + e.getMessage());
		}finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
				
		return novoPais;
	}

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(PaisEntity alterarPais) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PaisEntity consultarPorId(int id) {
		String query = "SELECT * FROM vacina.paises where id = " + id;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		PaisEntity pais = null;
		ResultSet resultado = null;
		
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next())
			{
				pais = new PaisEntity();
				pais.setIdPais(resultado.getInt("id"));
				pais.setNomePais(resultado.getString("nome_pais"));
				pais.setSigla(resultado.getString("sigla"));
			}
			
		} catch (SQLException e) 
		{
			System.out.println("ERRO EM CONSULTAR POR ID O PAÍS!");
			System.out.println("ERRO:" + e.getMessage());
		} finally 
		{
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return pais;
	}

	@Override
	public ArrayList<PaisEntity> consultarTodos() {
		ArrayList<PaisEntity> listaPais = new ArrayList<PaisEntity>();
		String query = "SELECT * FROM vacina.paises";
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		PaisEntity pais = null;
		ResultSet resultado = null;
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next())
			{
				pais = new PaisEntity();
				pais.setIdPais(resultado.getInt("id"));
				pais.setNomePais(resultado.getString("nome_pais"));
				pais.setSigla(resultado.getString("sigla"));
			}
			
		} catch (SQLException e) 
		{
			System.out.println("ERRO EM CONSULTAR TODOS OS PAISES!");
			System.out.println("ERRO:" + e.getMessage());
		} finally 
		{
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		
		
		return listaPais;
	}

}
