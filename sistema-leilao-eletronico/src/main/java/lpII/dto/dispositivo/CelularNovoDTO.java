package lpII.dto.dispositivo;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class CelularNovoDTO extends DispositivoNovoDTO{

    @Schema(description = "Sistema operacional do celular", example = "IOS")
    private String sistemaOperacional;

    @Schema(description = "Memória interna do celular", example = "128GB")
    private String memoria;

}
