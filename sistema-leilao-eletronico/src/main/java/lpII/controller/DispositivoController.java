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
import lpII.exception.ApiException;
import lpII.model.CelularEntity;
import lpII.model.LeilaoEntity;
import lpII.model.MonitorEntity;
import lpII.model.NotebookEntity;
import lpII.service.DispositivoService;
import lpII.service.LeilaoService;

@Path("/dispositivo")
@Blocking
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DispositivoController {

    private final DispositivoService dispositivoService;
    private final LeilaoService leilaoService;

    public DispositivoController(DispositivoService dispositivoService,
                                 LeilaoService leilaoService) {
        this.dispositivoService = dispositivoService;
        this.leilaoService = leilaoService;
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

    private Response atualizarDispositivo(Long id,
                                          DispositivoNovoDTO dispositivoNovoDTO,
                                          Class<? extends PanacheEntityBase> entityClass) {
        DispositivoNovoDTO dispositvoAtualizado = dispositivoService.atualizarDispositivo(id, dispositivoNovoDTO, entityClass);
        if (dispositvoAtualizado != null) {
            return Response.ok(dispositvoAtualizado).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletarDispositivo(@PathParam("id") Long id) {
        dispositivoService.deletarDispositivo(id);
        return Response.noContent().build();
    }

    @POST
    @Path("/cadastrar-monitor")
    public Response cadastrarMonitor(MonitorNovoDTO monitorNovoDTO) {
        LeilaoEntity leilao = leilaoService.buscarLeilaoId(monitorNovoDTO.getIdLeilao());
        if (leilao == null) {
            throw new ApiException("Id do Leilão não encontrado", Response.Status.NOT_FOUND);
        } else {
            return novoDispositivo(monitorNovoDTO, MonitorEntity.class);
        }
    }

    @GET
    @Path("/monitor/{id}")
    public Response findMonitorById(@PathParam("id") Long id) {
        return Response.ok(dispositivoService.findMonitorById(id)).build();
    }

    @PUT
    @Path("/atualizar-monitor/{id}")
    public Response atualizarMonitor(@PathParam("id") Long id, MonitorNovoDTO monitorNovoDTO) {
        return atualizarDispositivo(id, monitorNovoDTO, MonitorEntity.class);
    }

    @POST
    @Path("/cadastrar-notebook")
    public Response cadastrarNotebook(NotebookNovoDTO notebookNovoDTO) {
        LeilaoEntity leilao = leilaoService.buscarLeilaoId(notebookNovoDTO.getIdLeilao());
        if (leilao == null) {
            throw new ApiException("Id do Leilão não encontrado", Response.Status.NOT_FOUND);
        } else {
            return novoDispositivo(notebookNovoDTO, NotebookEntity.class);
        }
    }

    @GET
    @Path("/notebook/{id}")
    public Response findNotebookById(@PathParam("id") Long id) {
        return Response.ok(dispositivoService.findNotebookById(id)).build();
    }

    @PUT
    @Path("/atualizar-notebook/{id}")
    public Response atualizarNotebook(@PathParam("id") Long id, NotebookNovoDTO notebookNovoDTO) {
        return atualizarDispositivo(id, notebookNovoDTO, NotebookEntity.class);
    }

    @POST
    @Path("/cadastrar-celular")
    public Response cadastrarCelular(CelularNovoDTO celularNovoDTO) {
        LeilaoEntity leilao = leilaoService.buscarLeilaoId(celularNovoDTO.getIdLeilao());
        if (leilao == null) {
            throw new ApiException("Id do Leilão não encontrado", Response.Status.NOT_FOUND);
        } else {
            return novoDispositivo(celularNovoDTO, CelularEntity.class);
        }
    }

    @GET
    @Path("/celular/{id}")
    public Response findCelularById(@PathParam("id") Long id) {
        return Response.ok(dispositivoService.findCelularById(id)).build();
    }

    @PUT
    @Path("/atualizar-celular/{id}")
    public Response atualizarCelular(@PathParam("id") Long id, CelularNovoDTO celularNovoDTO) {
        return atualizarDispositivo(id, celularNovoDTO, CelularEntity.class);
    }

}
