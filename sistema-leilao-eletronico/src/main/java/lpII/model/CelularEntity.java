package lpII.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lpII.dto.dispositivo.CelularNovoDTO;
import lpII.dto.dispositivo.DispositivoNovoDTO;
import org.modelmapper.ModelMapper;

@Data
@Entity
@DiscriminatorValue("CELULAR")
public class CelularEntity extends DispositivoEntity {

    private String fabricante;
    private String sistemaOperacional;
    private String memoria;

    @Override
    public DispositivoNovoDTO toDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, CelularNovoDTO.class);
    }
}
