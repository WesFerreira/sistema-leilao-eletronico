package lpII.dto.cliente;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class ClienteDTO {

    @Schema(description = "Nome do usuário", example = "João Silva", required = true)
    private String nome;
    @Schema(description = "Documento do usuário", example = "11122233345", required = true)
    private String documento;

}
