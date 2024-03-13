package controller;



import java.util.ArrayList;

import exception.PessoaException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.PessoaEntity;
import model.repository.PessoaRepository;
import service.PessoaService;

@Path("/pessoa")
public class PessoaController {
	
	private PessoaService pessoaService = new PessoaService();
	
	@POST
	@Path("/salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PessoaEntity salvar(PessoaEntity novaPessoa) throws PessoaException {
		return this.pessoaService.salvar(novaPessoa);
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean excluir (@PathParam("id")int id) {
		return this.pessoaService.excluir(id);
	}
	
	@GET 
	@Path("listar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<PessoaEntity> listarTodos(){
		return this.pessoaService.listarTodos();
		
	}

}
