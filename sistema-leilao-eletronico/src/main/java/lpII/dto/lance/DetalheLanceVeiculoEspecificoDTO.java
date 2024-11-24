package lpII.dto.lance;

import lombok.Data;
import lpII.model.ClienteEntity;
import lpII.model.LanceEntity;
import lpII.model.VeiculoEntity;

import java.math.BigDecimal;

@Data
public class DetalheLanceVeiculoEspecificoDTO {

    public VeiculoEntity veiculo;
    public ClienteEntity cliente;
    private BigDecimal lanceInicial;

}
