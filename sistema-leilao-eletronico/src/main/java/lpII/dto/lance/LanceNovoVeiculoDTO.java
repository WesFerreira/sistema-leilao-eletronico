package lpII.dto.lance;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LanceNovoVeiculoDTO {

    private Long veiculoId;
    private Long clienteId;
    private LocalDateTime dataHora;
    private BigDecimal lanceInicial;
    private BigDecimal lanceAdicional;

}
