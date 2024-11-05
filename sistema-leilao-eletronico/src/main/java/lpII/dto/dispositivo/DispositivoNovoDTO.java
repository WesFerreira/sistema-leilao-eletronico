package lpII.dto.dispositivo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DispositivoNovoDTO {

    private Long id;
    private String nome;
    private String marca;
    private Integer ano;
    private Integer quantidade;
    public BigDecimal valorInical;

}
