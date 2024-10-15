package lpII.controller;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.annotation.Blocking;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lpII.dto.veiculo.CarroNovoDTO;
import lpII.dto.veiculo.VeiculoNovoDTO;
import lpII.model.CarroEntity;
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

    @GET
    public Response todosVeiculos(@QueryParam("page") @DefaultValue("0") Integer page,
                                  @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        var veiculos = veiculoService.listarTodosVeiculos(page, pageSize);
        return Response.ok(veiculos).build();
    }

    @POST
    @Path("/cadastrar-carro")
    public Response cadastrarCarro(CarroNovoDTO carroNovoDTO) {
        return novoVeiculo(carroNovoDTO, CarroEntity.class);
    }

    @GET
    @Path("/carro/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(veiculoService.findCarroById(id)).build();
    }

    private Response novoVeiculo(VeiculoNovoDTO veiculoDTO, Class<? extends PanacheEntityBase> entityClass) {
        VeiculoNovoDTO veiculoNovoDTO = veiculoService.cadastrarVeiculo(veiculoDTO, entityClass);
        if (veiculoNovoDTO != null) {
            return Response.status(Response.Status.CREATED).entity(veiculoNovoDTO.getId()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
