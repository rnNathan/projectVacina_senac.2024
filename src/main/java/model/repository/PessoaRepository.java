package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.entity.PessoaEntity;

public class PessoaRepository implements BaseRepository<PessoaEntity> {

	
	public boolean verificarCPF (PessoaEntity pessoaEntity ) {
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
			System.out.println("Erro VERIFICAR CADASTRO");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}
	
	
	@Override
	public PessoaEntity salvar(PessoaEntity novaEntidade) {
		String query = "INSERT INTO pessoa (nome, dataNascimento, sexo, cpf, tipoPessoaCadastrada)"
				+ " VALUES (?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		
		try {
			//Estou pegando um metodo setString para obter o tipo que irei cadastrar
			//depois mostro a posição da coluna e insero o valor que será armazenado no repositorio.
			preencherValoresUpdateEInsert(pstmt, novaEntidade);
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
		
			if (resultado.next()) {
				novaEntidade.setId(resultado.getInt(1));
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao salvar pessoa");
			System.out.println("Erro: " + e.getMessage());
		}finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
			
		}
			
		return novaEntidade;
	}
	
	
	private PreparedStatement preencherValoresUpdateEInsert(PreparedStatement pstmt, PessoaEntity novaEntidade) throws SQLException {
		pstmt.setString(1, novaEntidade.getNome());
		pstmt.setDate(2, Date.valueOf(novaEntidade.getDataNascimento()));
		pstmt.setString(3, novaEntidade.getSexo());
		pstmt.setString(4, novaEntidade.getCpf());
		pstmt.setString(5, novaEntidade.getTipoPessoaCadastrada());	
		return pstmt;
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		String query = "DELETE FROM pessoa WHERE id = " + id;
		boolean retorno = false;
		
		try {			
			if (stmt.executeUpdate(query) == 1) {
				retorno = true;		
			}
							
		} catch (SQLException e) {
			System.out.println("ERRO AO EXCLUIR UMA ENTIDADE.");
			System.out.println("ERRO: " + e.getMessage());
		}finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
			
		}
			
		return retorno;
	}

	@Override
	public boolean alterar(PessoaEntity objeto) {
		
		return false;
	}

	@Override
	public PessoaEntity consultarPorId(int id) {
		
		return null;
	}

	@Override
	public ArrayList<PessoaEntity> consultarTodos() {
		
			ArrayList  <PessoaEntity> listaPessoa = new ArrayList<PessoaEntity>();
			Connection conn = Banco.getConnection();
			Statement stmt = Banco.getStatement(conn);
			ResultSet resultado = null;
			String query = "SELECT * FROM pessoa";
			try {
				resultado = stmt.executeQuery(query);
				while (resultado.next()) {
					PessoaEntity pessoaEntity = new PessoaEntity();
					pessoaEntity.setId(Integer.parseInt(resultado.getString("id")));
					pessoaEntity.setNome(resultado.getString("nome"));
					pessoaEntity.setDataNascimento(resultado.getDate("dataNascimento").toLocalDate());
					pessoaEntity.setSexo(resultado.getString("sexo"));
					pessoaEntity.setTipoPessoaCadastrada(resultado.getString("tipoPessoaCadastrada"));
					listaPessoa.add(pessoaEntity);
				}
				
				
			} catch (SQLException e) {
				
				System.out.println("Erro ao consultar todas entidade no banco.");
				System.out.println("ERRO: " + e.getMessage());
			}finally {
				
				Banco.closeResultSet(resultado);
				Banco.closeStatement(stmt);
				Banco.closeConnection(conn);
			}
			
		
		return listaPessoa;
	}
	
}
