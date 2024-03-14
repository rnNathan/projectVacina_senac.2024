package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.VacinaEntity;

public class VacinaRepository implements BaseRepository<VacinaEntity> {

	
	
	@Override
	public VacinaEntity salvar(VacinaEntity salvarVacina) {
		
		String query = "INSERT INTO vacinas (nome, paisOrigem, idPesquisador, estagio, dataIncioPesquisa) values (?, ?, ?, ?)"; 
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
			
		}finally {
			
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		
		return salvarVacina;
	}
	
	
	public PreparedStatement preencherValoresInsertUpdate(PreparedStatement pstmt, VacinaEntity vacinaEntity) throws SQLException {
		pstmt.setString(1, vacinaEntity.getNome());
		pstmt.setString(2, vacinaEntity.getPaisOrigem());
		pstmt.setObject(3, vacinaEntity.getIdPesquisador());
		pstmt.setInt(4, vacinaEntity.getEstagio());
		pstmt.setDate(5, Date.valueOf(vacinaEntity.getDataInicioPesquisa()));
		return pstmt;
	}

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(VacinaEntity objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VacinaEntity consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<VacinaEntity> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
