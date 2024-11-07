package lpII.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lpII.dto.veiculo.CaminhaoNovoDTO;
import lpII.dto.veiculo.VeiculoNovoDTO;
import org.modelmapper.ModelMapper;

@Data
@Entity
@DiscriminatorValue("CAMINHAO")
public class CaminhaoEntity extends VeiculoEntity {

    public Integer capacidadeCarga;
    public Integer numeroEixos;

    @Override
    public VeiculoNovoDTO toDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, CaminhaoNovoDTO.class);
    }
}
