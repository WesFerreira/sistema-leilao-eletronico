package lpII.dto.lance;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LanceNovoDispositivoDTO {

    @Schema(description = "Identificador do dispositivo para o qual o lance está sendo realizado", example = "1")
    private Long dispositivoId;

    @Schema(description = "Identificador do cliente que está realizando o lance", example = "1")
    private Long clienteId;

    @Schema(description = "Data e hora em que o lance foi realizado", example = "2024-12-03T14:30:00")
    private LocalDateTime dataHora;

    @Schema(description = "Valor inicial do lance", example = "1000.00")
    private BigDecimal lanceInicial;

    @Schema(description = "Valor adicional oferecido no lance", example = "150.00")
    private BigDecimal lanceAdicional;

}
