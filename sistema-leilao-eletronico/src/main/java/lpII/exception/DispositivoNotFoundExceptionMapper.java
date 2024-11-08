package lpII.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DispositivoNotFoundExceptionMapper implements ExceptionMapper<DispositivoNotFoundExceptio> {

    @Override
    public Response toResponse(DispositivoNotFoundExceptio exception) {
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(),
                "Id do Dispositivo Eletrônico não encontrado").build();
    }
}
