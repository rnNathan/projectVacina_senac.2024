package controller;

import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.VacinacaoEntity;
import service.VacinacaoService;

@Path("/vacinacao")
public class VacinacaoController {
	
	private VacinacaoService service = new VacinacaoService();
	
	@POST
	@Path("/salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public VacinacaoEntity salvar (VacinacaoEntity repository) {
		return service.salvar(repository);
	}
	
	
	@DELETE
	@Path("/excluir")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean excluir (int id) {
		return service.excluir(id);
	}
	
	@PUT
	@Path("/alterar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean alterar (VacinacaoEntity entity) {
		return service.alterar(entity);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public VacinacaoEntity consultarPorId(@PathParam("id") int id) {
		return service.consultarPorId(id);
	}
	
	public ArrayList<VacinacaoEntity> consultarTodos(){
		return service.consultarTodos();
	}
	
	

}
