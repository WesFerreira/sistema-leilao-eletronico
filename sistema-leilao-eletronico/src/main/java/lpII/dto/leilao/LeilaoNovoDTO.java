package lpII.dto.leilao;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LeilaoNovoDTO {

    @Schema(description = "Endereço onde o leilão será realizado", example = "Rua das Flores")
    private String endereco;

    @Schema(description = "Cidade onde o leilão será realizado", example = "São Paulo")
    private String cidade;

    @Schema(description = "Estado onde o leilão será realizado", example = "SP")
    private String estado;

    @Schema(description = "Data e hora de início do leilão", example = "2024-12-10T09:00:00")
    private LocalDateTime dataInicio;

    @Schema(description = "Data e hora de término do leilão", example = "2025-12-10T18:00:00")
    private LocalDateTime dataFim;

    @Schema(description = "Data e hora da visita prévia ao local do leilão", example = "2025-02-08T10:00:00")
    private LocalDateTime dataVisita;

    @Schema(description = "Lista de identificadores das instituições financeiras participantes", example = "[1, 2]")
    private List<Long> instituicaoFinId;

}
