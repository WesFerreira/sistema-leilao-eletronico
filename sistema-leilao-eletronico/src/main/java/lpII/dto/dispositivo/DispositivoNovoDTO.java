package lpII.dto.dispositivo;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@Data
public class DispositivoNovoDTO {

    private Long id;

    @Schema(description = "Nome do dispositivo", example = "MacBook Pro")
    private String nome;

    @Schema(description = "Marca do dispositivo", example = "Apple")
    private String marca;

    @Schema(description = "Ano de fabricação", example = "2021")
    private Integer ano;

    @Schema(description = "Quantidade disponível", example = "10")
    private Integer quantidade;

    @Schema(description = "Identificador do leilão associado", example = "1")
    private Long idLeilao;

    @Schema(description = "Valor inicial do dispositivo no leilão", example = "1500.00")
    public BigDecimal valorInical;

}
