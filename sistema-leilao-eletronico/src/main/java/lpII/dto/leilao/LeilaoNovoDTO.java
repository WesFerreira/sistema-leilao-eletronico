package lpII.dto.leilao;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LeilaoNovoDTO {

    private String endereco;
    private String cidade;
    private String estado;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private LocalDateTime dataVisita;
    private List<Long> instituicaoFinId;

}
