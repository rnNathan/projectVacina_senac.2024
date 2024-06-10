package model.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Carro;
import model.seletor.CarroSeletor;

public class CarroRepository {

	public ArrayList<Carro> consultarPorFiltro(CarroSeletor seletor) {

		ArrayList<Carro> carros = new ArrayList<Carro>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		String query = " select c.* from vacina.carros c "
				+ " inner join montadora m on c.id_montadora = m.id_montadora ";

		query = this.incluirFiltros(seletor, query);

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				MontadoraRepository repository = new MontadoraRepository();
				Carro carro = new Carro();
				carro.setId(resultado.getInt("id_carro"));
				carro.setModelo(resultado.getString("modelo"));
				carro.setAno(resultado.getInt("ano"));
				
				carro.setMontadora(repository.consultarPorId(resultado.getInt("id_montadora")));
				carro.setValor(resultado.getDouble("valor"));
				carros.add(carro);
			}

		} catch (SQLException e) {
			System.out.println("ERRO AO CONSULTAR TODAS AS VACINAS!");
			System.out.println("ERRO: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return carros;
	}

	private String incluirFiltros(CarroSeletor seletor, String query) {
		boolean primeiro = true;

		if (seletor.getNomeMarca() != null) {
			if (primeiro) {
				query += " where ";
			} else {
				query += " and ";
			}

			query += " upper(m.nome) like upper  ('%" + seletor.getNomeMarca() + "%')";
			primeiro = false;

		}

		if (seletor.getModelo() != null) {
			if (primeiro) {
				query += " where ";
			} else {
				query += " and ";
			}

			query += " upper(c.modelo) like upper('%" + seletor.getModelo() + "%')";

		}

		if (seletor.getPaisMarca() != null) {
			if (primeiro) {
				query += " where ";
			} else {
				query += " and ";
			}

			query += " upper(m.pais_fundacao) like upper('%" + seletor.getPaisMarca() + "%')";

		}

		if (seletor.getAnoInicial() != null && seletor.getAnoFinal() != null) {
			if (primeiro) {
				query += " where ";
			} else {
				query += " and ";
			}

			query += " c.ano BETWEEN '" + seletor.getAnoInicial() + "' and '" + seletor.getAnoFinal() + "'";
			primeiro = false;
		} else if (seletor.getAnoInicial() != null) {
			if (primeiro) {
				query += " and ";
			}
			query += "c.ano >= '" + seletor.getAnoInicial() + "'";
			primeiro = false;

		} else if (seletor.getAnoFinal() != null) {
			if (primeiro) {
				query = " and ";
			}
			query += "c.ano <= '" + seletor.getAnoFinal() + "'";
			primeiro = false;
		}
		

		if (seletor.getValorInicial() != null && seletor.getValorFinal() != null) {
			if (primeiro) {
				query += " where ";
			} else {
				query += " and ";
			}

			query += " c.valor BETWEEN '" + seletor.getValorInicial() + "' and '" + seletor.getValorFinal() + "'";
			primeiro = false;
		} else if (seletor.getValorInicial() != null) {
			if (primeiro) {
				query += " and ";
			}
			query += "c.valor >= '" + seletor.getAnoInicial() + "'";
			primeiro = false;

		} else if (seletor.getValorFinal() != null) {
			if (primeiro) {
				query = " and ";
			}
			query += "c.valor <= '" + seletor.getValorFinal() + "'";
			primeiro = false;
		}
	
		return query;
	}

}
