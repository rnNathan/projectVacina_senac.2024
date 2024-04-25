package controller;

import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.PaisEntity;
import service.PaisService;

@Path("/pais")
public class PaisController {
	
	private PaisService paisService = new PaisService();
	
	
	@POST
	@Path("/salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PaisEntity inserir (PaisEntity novoPais) {
		return paisService.inserir(novoPais);
		}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PaisEntity consultarPorId(@PathParam("id")int id) {
		return paisService.consultarPorId(id);
	}
	
	
	
	@GET
	@Path("/todos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<PaisEntity> listarTodos() {
		return paisService.consultarTodos();
	}
	
}
