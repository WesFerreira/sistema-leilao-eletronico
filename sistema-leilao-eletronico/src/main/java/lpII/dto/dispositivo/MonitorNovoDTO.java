package lpII.dto.dispositivo;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class MonitorNovoDTO extends DispositivoNovoDTO {

    @Schema(description = "Tamanho da tela do monitor", example = "27")
    private String tamanhoTela;

}
