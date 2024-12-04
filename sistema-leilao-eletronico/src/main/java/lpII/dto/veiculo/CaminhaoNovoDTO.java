package lpII.dto.veiculo;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class CaminhaoNovoDTO extends VeiculoNovoDTO {

    @Schema(description = "Capacidade de carga do caminhão (em toneladas)", example = "20")
    public Integer capacidadeCarga;

    @Schema(description = "Número de eixos do caminhão", example = "6")
    public Integer numeroEixos;

}
