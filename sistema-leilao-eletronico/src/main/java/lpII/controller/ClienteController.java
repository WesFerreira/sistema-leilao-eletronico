package lpII.controller;

import io.smallrye.common.annotation.Blocking;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lpII.dto.cliente.ClienteDTO;
import lpII.model.ClienteEntity;
import lpII.service.ClienteService;

import java.util.List;

@Path("/cliente")
@Blocking
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @POST
    public Response cadastrarCliente(ClienteDTO clienteDTO) {
        clienteService.cadastrarCliente(clienteDTO);
        return Response.status(Response.Status.CREATED).build();
    }


    @GET
    public List<ClienteEntity> listarTodosClientes(){
        return clienteService.listarTodosClientes();
    }

    @GET
    @Path("{id}")
    public Response buscarClienteId(@PathParam("id") Long id) {
        ClienteEntity cliente = clienteService.buscarClienteId(id);
        if (cliente != null) {
            return Response.ok(cliente).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response atualizarCliente(@PathParam("id") Long id, ClienteDTO clienteDTO) {
        ClienteDTO cliente = clienteService.atualizarCliente(id,clienteDTO);
        if (cliente != null) {
            return Response.ok(cliente).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletarCliente(@PathParam("id") Long id) {
        ClienteEntity cliente = clienteService.buscarClienteId(id);
        if (cliente != null) {
            clienteService.deletarCliente(id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
