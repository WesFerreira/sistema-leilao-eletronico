package lpII.dto.dispositivo;

import lombok.Data;

@Data
public class MonitorNovoDTO extends DispositivoNovoDTO {

    private String fabricante;
    private String tamanhoTela;

}
