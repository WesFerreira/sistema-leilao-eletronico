package lpII.service;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lpII.dto.veiculo.VeiculoNovoDTO;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class VeiculoService {

    private final ModelMapper modelMapper;

    public VeiculoService() {
        this.modelMapper = new ModelMapper();
    }

    @Transactional
    public <T extends PanacheEntityBase> VeiculoNovoDTO cadastrarVeiculo(VeiculoNovoDTO veiculoNovoDTO,
                                                                         Class<T> entityClass) {
        T veiculo = modelMapper.map(veiculoNovoDTO, entityClass);
        veiculo.persist();
        return modelMapper.map(veiculo, VeiculoNovoDTO.class);
    }

}
