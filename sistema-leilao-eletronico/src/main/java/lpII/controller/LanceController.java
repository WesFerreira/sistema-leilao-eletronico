package lpII.controller;

import io.smallrye.common.annotation.Blocking;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lpII.dto.lance.LanceNovoVeiculoDTO;
import lpII.model.LanceEntity;
import lpII.service.LanceService;

import java.util.List;

@Path("/lance")
@Blocking
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LanceController {

    private final LanceService lanceService;

    public LanceController(LanceService lanceService) {
        this.lanceService = lanceService;
    }

    @POST
    @Path("vincular-veiculo")
    public Response vincularLanceAoVeiculo(LanceNovoVeiculoDTO lanceNovoVeiculoDTO) {
        LanceNovoVeiculoDTO lance = lanceService.vincularLanceAoVeiculo(lanceNovoVeiculoDTO);

        if (lance == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).entity(lance).build();
    }

    @GET
    public List<LanceEntity> listarLances() {
        return lanceService.listarLances();
    }

}
