package lpII.service;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lpII.dto.dispositivo.DispositivoNovoDTO;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class DispositivoService {

    private final ModelMapper modelMapper;

    public DispositivoService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Transactional
    public <T extends PanacheEntityBase> DispositivoNovoDTO cadastrarDispositivo(DispositivoNovoDTO dispositivoNovoDTO,
                                                                             Class<T> entityClass) {
        T dispositivo = modelMapper.map(dispositivoNovoDTO, entityClass);
        dispositivo.persist();
        return modelMapper.map(dispositivo, DispositivoNovoDTO.class);
    }

}
