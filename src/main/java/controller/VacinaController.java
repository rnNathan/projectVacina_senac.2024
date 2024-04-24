package controller;

import java.util.ArrayList;
import exception.PessoaException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.VacinaEntity;
import model.seletor.vacinaSeletor;
import service.VacinaService;

@Path("/vacina")
public class VacinaController {
	
	VacinaService service = new VacinaService();
	
	
	@Path("/salvar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public VacinaEntity salvar(VacinaEntity vacina) throws PessoaException {
		return service.salvar(vacina);
	}
	
	@Path("/excluir/{id}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean excluir(@PathParam("id") int id) throws PessoaException {
		return service.excluir(id);
	}
	
	@Path("/altera")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar (VacinaEntity altera) {
		return service.alterar(altera);
	}
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public VacinaEntity consultarPorId(@PathParam("id") int id) {
		return service.consultarPorId(id);
	}
	
	@Path("/listarTodos")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<VacinaEntity> listaTodas(){
		return service.listarTodas();
	}
	
	@Path("/filtro")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<VacinaEntity> consultarPorFiltro(vacinaSeletor seletor){
		return service.consultarPorFiltro(seletor);
	}

}
