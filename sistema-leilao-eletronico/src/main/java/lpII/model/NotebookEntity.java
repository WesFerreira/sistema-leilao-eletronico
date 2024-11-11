package lpII.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lpII.dto.dispositivo.DispositivoNovoDTO;
import lpII.dto.dispositivo.NotebookNovoDTO;
import org.modelmapper.ModelMapper;

@Data
@Entity
@DiscriminatorValue("NOTEBOOK")
public class NotebookEntity extends DispositivoEntity {

    private String fabricante;
    private String processador;
    private Double tamanhoTela;
    private String memoria;
    private String memoriaRAM;
    private String peso;

    public NotebookEntity() {

    }

    @Override
    public DispositivoNovoDTO toDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, NotebookNovoDTO.class);
    }
}