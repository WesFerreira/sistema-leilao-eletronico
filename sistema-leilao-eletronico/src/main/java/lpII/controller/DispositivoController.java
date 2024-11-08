package lpII.controller;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.annotation.Blocking;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lpII.dto.dispositivo.DispositivoNovoDTO;
import lpII.dto.dispositivo.MonitorNovoDTO;
import lpII.model.MonitorEntity;
import lpII.service.DispositivoService;

@Path("/dispositivo")
@Blocking
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DispositivoController {

    private final DispositivoService dispositivoService;

    public DispositivoController(DispositivoService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }

    private Response novoDispositivo(DispositivoNovoDTO dispositivoDTO, Class<? extends PanacheEntityBase> entityClass) {
        DispositivoNovoDTO dispositivoNovoDTO = dispositivoService.cadastrarDispositivo(dispositivoDTO, entityClass);
        if (dispositivoNovoDTO != null) {
            return Response.status(Response.Status.CREATED).entity(dispositivoNovoDTO.getId()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    public Response todosDispositivos(@QueryParam("page") @DefaultValue("0") Integer page,
                                  @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        var dispositivos = dispositivoService.listarTodosDispositivos(page, pageSize);
        return Response.ok(dispositivos).build();
    }

    @POST
    @Path("/cadastrar-monitor")
    public Response cadastrarMonitor(MonitorNovoDTO monitorNovoDTO) {
        return novoDispositivo(monitorNovoDTO, MonitorEntity.class);
    }

    @GET
    @Path("/monitor/{id}")
    public Response findMonitorById(@PathParam("id") Long id) {
        return Response.ok(dispositivoService.findMonitorById(id)).build();
    }

}
