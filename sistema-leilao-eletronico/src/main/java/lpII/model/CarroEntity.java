package lpII.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lpII.dto.veiculo.CarroNovoDTO;
import lpII.dto.veiculo.VeiculoNovoDTO;
import org.modelmapper.ModelMapper;

@Data
@Entity
@DiscriminatorValue("CARRO")
public class CarroEntity extends VeiculoEntity {

    public String tipo;
    public Integer qtdPorta;

    public CarroEntity() {
    }

    @Override
    public VeiculoNovoDTO toDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, CarroNovoDTO.class);
    }
}
