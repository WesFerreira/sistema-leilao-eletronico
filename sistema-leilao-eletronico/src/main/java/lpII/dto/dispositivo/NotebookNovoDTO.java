package lpII.dto.dispositivo;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class NotebookNovoDTO extends DispositivoNovoDTO {

    @Schema(description = "Processador do notebook", example = "Intel Core i7")
    private String processador;

    @Schema(description = "Tamanho da tela do notebook", example = "15.6")
    private String tamanhoTela;

    @Schema(description = "Memória RAM do notebook", example = "16GB")
    private String memoriaRAM;

}
