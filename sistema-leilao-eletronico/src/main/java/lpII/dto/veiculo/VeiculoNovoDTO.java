package lpII.dto.veiculo;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@Data
public class VeiculoNovoDTO {

    private Long id;

    @Schema(description = "Modelo do veículo", example = "Civic")
    private String modelo;

    @Schema(description = "Marca do veículo", example = "Honda")
    private String marca;

    @Schema(description = "Ano de fabricação do veículo", example = "2020")
    private Integer ano;

    @Schema(description = "Número do RENAVAM do veículo", example = "123456789")
    private String renavam;

    @Schema(description = "Número do chassi do veículo", example = "9BWZZZ377VT004251")
    private String chassi;

    @Schema(description = "Identificador do leilão associado ao veículo", example = "1")
    private Long idLeilao;

    @Schema(description = "Valor inicial do veículo no leilão", example = "50000.00")
    private BigDecimal valorInicial;

}
