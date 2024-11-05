package lpII.controller;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.annotation.Blocking;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lpII.dto.dispositivo.DispositivoNovoDTO;
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

}
