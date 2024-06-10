package controller;

import java.util.ArrayList;

import exception.PessoaException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Carro;
import model.seletor.CarroSeletor;
import service.CarroService;

@Path("/carro")
public class CarroController {

	private CarroService service = new CarroService();

	@Path("/filtro")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Carro> consultarPorFiltro(CarroSeletor seletor) throws PessoaException {
		return service.consultarPorFiltro(seletor);
	}

}
