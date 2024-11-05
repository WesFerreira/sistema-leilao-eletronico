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
import lpII.model.veiculo.CaminhaoEntity;
import lpII.model.veiculo.CarroEntity;
import lpII.model.veiculo.MotoEntity;
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

}
