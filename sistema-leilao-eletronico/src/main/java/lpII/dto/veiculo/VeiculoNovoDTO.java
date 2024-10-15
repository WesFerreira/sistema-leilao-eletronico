package lpII.dto.veiculo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VeiculoNovoDTO {

    private Long id;
    private String modelo;
    private String marca;
    private Integer ano;
    private String renavam;
    private String chassi;
    private Long idLeilao;
    private BigDecimal valorInicial;

}
