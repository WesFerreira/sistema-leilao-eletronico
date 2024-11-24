package lpII.dto.lance;

import lombok.Data;
import lpII.model.ClienteEntity;
import lpII.model.DispositivoEntity;

import java.math.BigDecimal;

@Data
public class DetalheLanceDispositivoEspecificoDTO {

    public DispositivoEntity dispositivo;
    public ClienteEntity cliente;
    private BigDecimal lanceInicial;

}
