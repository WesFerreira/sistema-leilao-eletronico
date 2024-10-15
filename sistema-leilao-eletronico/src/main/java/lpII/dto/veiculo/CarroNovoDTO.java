package lpII.dto.veiculo;

import lombok.Data;

@Data
public class CarroNovoDTO extends VeiculoNovoDTO {

    public String tipo;
    public Integer qtdPorta;

}
