package lpII.dto.dispositivo;

import lombok.Data;

@Data
public class CelularNovoDTO extends DispositivoNovoDTO{

    private String fabricante;
    private String sistemaOperacional;
    private String memoria;

}
