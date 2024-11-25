package lpII.controller;

import io.smallrye.common.annotation.Blocking;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lpII.service.ExportService;

import java.io.IOException;

@Path("/export")
@Blocking
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExportController {

    private final ExportService exportService;

    public ExportController(ExportService exportService) {
        this.exportService = exportService;
    }

    @GET
    @Path("/export")
    public String exportLeilao(@QueryParam("id") Long leilaoId) {
        try {
            String tempDir = System.getProperty("java.io.tmpdir");
            String filePath = tempDir + "leilao_" + leilaoId + ".det";
            exportService.exportarLeilao(leilaoId, filePath);
            return "Arquivo exportado com sucesso: " + filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao exportar o leilão: " + e.getMessage();
        }
    }

}
