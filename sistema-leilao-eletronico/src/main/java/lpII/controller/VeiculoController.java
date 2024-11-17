package lpII.controller;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.annotation.Blocking;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lpII.dto.veiculo.CaminhaoNovoDTO;
import lpII.dto.veiculo.MotoNovaDTO;
import lpII.dto.veiculo.CarroNovoDTO;
import lpII.dto.veiculo.VeiculoNovoDTO;
import lpII.model.CaminhaoEntity;
import lpII.model.CarroEntity;
import lpII.model.MotoEntity;
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
            return Response.status(Response.Status.CREATED).entity(veiculoNovoDTO.getId()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    public Response todosVeiculos(@QueryParam("page") @DefaultValue("0") Integer page,
                                  @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        var veiculos = veiculoService.listarTodosVeiculos(page, pageSize);
        return Response.ok(veiculos).build();
    }

    private Response atualizarVeiculo(Long id,
                                      VeiculoNovoDTO veiculoNovoDTO,
                                      Class<? extends PanacheEntityBase> entityClass) {
        VeiculoNovoDTO veiculoAtualizado = veiculoService.atualizarVeiculo(id, veiculoNovoDTO, entityClass);
        if (veiculoAtualizado != null) {
            return Response.ok(veiculoAtualizado).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/cadastrar-carro")
    public Response cadastrarCarro(CarroNovoDTO carroNovoDTO) {
        return novoVeiculo(carroNovoDTO, CarroEntity.class);
    }

    @GET
    @Path("/carro/{id}")
    public Response findCarroById(@PathParam("id") Long id) {
        return Response.ok(veiculoService.findCarroById(id)).build();
    }

    @PUT
    @Path("/atualizar-carro/{id}")
    public Response atualizarCarro(@PathParam("id") Long id, CarroNovoDTO carroNovoDTO) {
        return atualizarVeiculo(id, carroNovoDTO, CarroEntity.class);
    }

    @POST
    @Path("/cadastrar-moto")
    public Response cadastrarMoto(MotoNovaDTO motoNovaDTO) {
        return novoVeiculo(motoNovaDTO, MotoEntity.class);
    }

    @GET
    @Path("/moto/{id}")
    public Response findMotoById(@PathParam("id") Long id) {
        return Response.ok(veiculoService.findMotoById(id)).build();
    }

    @PUT
    @Path("/atualizar-moto/{id}")
    public Response atualizarMotocicleta(@PathParam("id") Long id, MotoNovaDTO motoNovaDTO) {
        return atualizarVeiculo(id, motoNovaDTO, MotoEntity.class);
    }

    @POST
    @Path("/cadastrar-caminhao")
    public Response cadastrarCaminhao(CaminhaoNovoDTO caminhaoNovoDTO) {
        return novoVeiculo(caminhaoNovoDTO, CaminhaoEntity.class);
    }

    @GET
    @Path("/caminhao/{id}")
    public Response findCaminhao(@PathParam("id") Long id) {
        return Response.ok(veiculoService.findCaminhaoById(id)).build();
    }

    @PUT
    @Path("/atualizar-caminhao/{id}")
    public Response atualizarCaminhao(@PathParam("id") Long id, CaminhaoNovoDTO caminhaoNovoDTO) {
        return atualizarVeiculo(id, caminhaoNovoDTO, CaminhaoEntity.class);
    }

}
