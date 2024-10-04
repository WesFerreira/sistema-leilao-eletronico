package lpII.controller;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.annotation.Blocking;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lpII.dto.veiculo.VeiculoNovoDTO;
import lpII.service.VeiculoService;

@Path("/veiculo")
@Blocking
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    private Response novoVeiculo(VeiculoNovoDTO veiculoDTO, Class<? extends PanacheEntityBase> entityClass) {
        VeiculoNovoDTO veiculoNovoDTO = veiculoService.cadastrarVeiculo(veiculoDTO, entityClass);
        if (veiculoNovoDTO != null) {
            return Response.status(Response.Status.CREATED).entity(veiculoNovoDTO).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
