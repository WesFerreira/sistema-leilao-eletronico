package lpII.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lpII.dto.dispositivo.DispositivoNovoDTO;
import lpII.dto.dispositivo.MonitorNovoDTO;
import org.modelmapper.ModelMapper;

@Data
@Entity
@DiscriminatorValue("MONITOR")
public class MonitorEntity extends DispositivoEntity {

    private String fabricante;
    private String tamanhoTela;

    @Override
    public DispositivoNovoDTO toDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, MonitorNovoDTO.class);
    }
}
