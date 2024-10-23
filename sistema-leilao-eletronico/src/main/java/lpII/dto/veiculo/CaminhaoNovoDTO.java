package lpII.dto.veiculo;

import lombok.Data;

@Data
public class CaminhaoNovoDTO extends VeiculoNovoDTO {

    public Integer capacidadeCarga;
    public Integer numeroEixos;

}
