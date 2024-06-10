package controller;

import exception.PessoaException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Montadora;
import service.MontadoraService;


@Path("/montadora")
public class MontadoraController {

	private MontadoraService service = new MontadoraService();
	
	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Montadora inserir (Montadora novaMontadora) throws PessoaException {
		return service.inserir(novaMontadora);
		
		}
	
	
}
