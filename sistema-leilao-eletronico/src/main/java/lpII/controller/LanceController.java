package lpII.controller;

import io.smallrye.common.annotation.Blocking;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lpII.dto.lance.LanceNovoDispositivoDTO;
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
    @Path("lance-veiculo")
    public Response lanceNovoVeiculo(LanceNovoVeiculoDTO lanceNovoVeiculoDTO) {
        LanceNovoVeiculoDTO lance = lanceService.lanceNovoVeiculo(lanceNovoVeiculoDTO);

        return Response.status(Response.Status.OK).entity(lance).build();
    }

    @POST
    @Path("lance-dispositivo")
    public Response lanceNovoDispositivo(LanceNovoDispositivoDTO lanceNovoDispositivoDTO) {
        LanceNovoDispositivoDTO lance = lanceService.lanceNovoDispositivo(lanceNovoDispositivoDTO);

        return Response.status(Response.Status.OK).entity(lance).build();
    }

    @GET
    public List<LanceEntity> listarLances() {
        return lanceService.listarLances();
    }

}
