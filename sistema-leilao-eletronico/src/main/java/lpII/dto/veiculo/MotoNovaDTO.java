package lpII.dto.veiculo;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class MotoNovaDTO extends VeiculoNovoDTO {

    @Schema(description = "Número de cilindradas da moto", example = "1250")
    public Integer cilindradas;

}
