package lpII.controller;

import io.smallrye.common.annotation.Blocking;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lpII.dto.instituicaoFinanceira.InstituicaoFinanceiraDTO;
import lpII.model.InstituicaoFinanceiraEntity;
import lpII.service.InstituicaoFinanceiraService;

import java.util.List;

@Path("/instituicao-financeira")
@Blocking
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InstituicaoFinanceiraController {

    private final InstituicaoFinanceiraService instituicaoFinanceiraService;

    public InstituicaoFinanceiraController(InstituicaoFinanceiraService instituicaoFinanceiraService) {
        this.instituicaoFinanceiraService = instituicaoFinanceiraService;
    }

    @POST
    public Response cadastrarInstituicaoFinanceira(InstituicaoFinanceiraDTO instituicaoFinanceiraDTO) {
        instituicaoFinanceiraService.cadastrarInstituicaoFinanceira(instituicaoFinanceiraDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public List<InstituicaoFinanceiraEntity> listarTodasInstituicoesFin(){
        return instituicaoFinanceiraService.listarTodasInstituicoesFin();
    }

    @GET
    @Path("{id}")
    public Response buscarInstituicaoId(@PathParam("id") Long id) {
        InstituicaoFinanceiraEntity instituicao = instituicaoFinanceiraService.buscarInstituicaoId(id);
        if (instituicao != null) {
            return Response.ok(instituicao).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    public Response atualizarInstituicao(@PathParam("id") Long id, InstituicaoFinanceiraDTO instituicaoFinanceiraDTO) {
        InstituicaoFinanceiraDTO instituicao = instituicaoFinanceiraService.atualizarInstituicao(id, instituicaoFinanceiraDTO);
        if (instituicao != null) {
            return Response.ok(instituicao).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletarInstituicao(@PathParam("id") Long id) {
        InstituicaoFinanceiraEntity instituicao = instituicaoFinanceiraService.buscarInstituicaoId(id);
        if (instituicao != null) {
            instituicaoFinanceiraService.deletarInstituicao(id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
