package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Montadora;

public class MontadoraRepository  implements BaseRepository<Montadora> {

	@Override
	public Montadora inserir(Montadora novaMontadora) {
		String query = "INSERT INTO vacina.montadora (nome, pais_fundacao, nome_fundador, data_fundacao) values (?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		
		
		try {
			pstmt.setString(1, novaMontadora.getNome());
			pstmt.setString(2, novaMontadora.getPaisFundacao());
			pstmt.setString(3, novaMontadora.getNomeFundador());
			pstmt.setDate(4, Date.valueOf(novaMontadora.getDataFundacao()));
			pstmt.execute();
			
			
			ResultSet resultado = pstmt.getGeneratedKeys();
			if (resultado.next()) {
				novaMontadora.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("ERRO AO CADASTRAR UMA MONTADORA!");
			System.out.println("ERRO: " + e.getMessage());

		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return novaMontadora;

	}

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Montadora objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Montadora consultarPorId(int id) {
		String query = "SELECT * FROM vacina.montadora where id_montadora = " + id;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		Montadora montadora = null;
		ResultSet resultado = null;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				montadora = new Montadora();
				montadora.setId(resultado.getInt("id_montadora"));
				montadora.setNome(resultado.getString("nome"));
				montadora.setPaisFundacao(resultado.getString("pais_fundacao"));
				montadora.setNomeFundador(resultado.getString("nome_fundador"));
				montadora.setDataFundacao(resultado.getDate("data_fundacao").toLocalDate());
	
				// VacinacaoRepository repository = new VacinacaoRepository();
				// pessoa.setTodasVacinas(repository.consultarTodasVacinasPorPessoa(pessoa.getId()));

			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar montadora por ID.");
			System.out.println("ERRO: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return montadora;
		
	}

	@Override
	public ArrayList<Montadora> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}



	
	

}
