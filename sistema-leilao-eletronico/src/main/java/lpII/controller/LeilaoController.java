package lpII.controller;

import io.smallrye.common.annotation.Blocking;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lpII.dto.leilao.LeilaoNovoDTO;
import lpII.service.LeilaoService;

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

}
