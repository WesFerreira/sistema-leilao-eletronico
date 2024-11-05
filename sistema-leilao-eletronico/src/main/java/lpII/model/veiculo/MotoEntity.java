package lpII.model.veiculo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lpII.dto.veiculo.MotoNovaDTO;
import lpII.dto.veiculo.VeiculoNovoDTO;
import org.modelmapper.ModelMapper;

@Data
@Entity
@DiscriminatorValue("MOTO")
public class MotoEntity extends VeiculoEntity {

    public Integer cilindradas;

    public MotoEntity() {
    }

    @Override
    public VeiculoNovoDTO toDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, MotoNovaDTO.class);
    }
}
