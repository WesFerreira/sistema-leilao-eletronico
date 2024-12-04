package lpII.dto.veiculo;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class CarroNovoDTO extends VeiculoNovoDTO {

    @Schema(description = "Tipo do carro (por exemplo, sedan, hatch, SUV)", example = "sedan")
    public String tipo;

    @Schema(description = "Quantidade de portas do carro", example = "4")
    public Integer qtdPorta;

}
