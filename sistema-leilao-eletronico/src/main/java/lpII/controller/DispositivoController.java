package lpII.controller;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.annotation.Blocking;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lpII.dto.dispositivo.CelularNovoDTO;
import lpII.dto.dispositivo.DispositivoNovoDTO;
import lpII.dto.dispositivo.MonitorNovoDTO;
import lpII.dto.dispositivo.NotebookNovoDTO;
import lpII.model.CelularEntity;
import lpII.model.MonitorEntity;
import lpII.model.NotebookEntity;
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

    @POST
    @Path("/cadastrar-notebook")
    public Response cadastrarNotebook(NotebookNovoDTO notebookNovoDTO) {
        return novoDispositivo(notebookNovoDTO, NotebookEntity.class);
    }
    @GET
    @Path("/notebook/{id}")
    public Response findNotebookById(@PathParam("id") Long id) {
        return Response.ok(dispositivoService.findNotebookById(id)).build();
    }

    @POST
    @Path("/cadastrar-celular")
    public Response cadastrarCelular(CelularNovoDTO celularNovoDTO) {
        return novoDispositivo(celularNovoDTO, CelularEntity.class);
    }

    @GET
    @Path("/celular/{id}")
    public Response findCelularById(@PathParam("id") Long id) {
        return Response.ok(dispositivoService.findCelularById(id)).build();
    }

}
