package lpII.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class VeiculoNotFoundExceptionMapper implements ExceptionMapper<VeiculoNotFoundException> {

    @Override
    public Response toResponse(VeiculoNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(),
                "Id do Veiculo não encontrado").build();
    }
}
