package lpII.controller;

import io.smallrye.common.annotation.Blocking;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lpII.dto.leilao.LeilaoNovoDTO;
import lpII.model.LeilaoEntity;
import lpII.service.LeilaoService;

import java.util.List;

@Path("/leilao")
@Blocking
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LeilaoController {

    private final LeilaoService leilaoService;

    public LeilaoController(LeilaoService leilaoService) {
        this.leilaoService = leilaoService;
    }

    @POST
    public Response cadastrarLeilao(LeilaoNovoDTO leilaoDTO) {
        LeilaoNovoDTO leilao = leilaoService.cadastrarLeilao(leilaoDTO);
        return Response.status(Response.Status.CREATED).entity(leilao).build();
    }

    @GET
    public List<LeilaoEntity> listarTodosLeiloes() {
        return leilaoService.listarTodosLeiloes();
    }

    @GET
    @Path("{id}")
    public LeilaoEntity buscarLeilaoId(@PathParam("id") Long id) {
        return leilaoService.buscarLeilaoId(id);
    }

}
