package lpII.dto.dispositivo;

import lombok.Data;

@Data
public class NotebookNovoDTO extends DispositivoNovoDTO {

    private String fabricante;
    private String processador;
    private String tamanhoTela;
    private String memoriaRAM;

}
