package lpII.service;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lpII.dto.dispositivo.DispositivoNovoDTO;
import lpII.exception.DispositivoNotFoundExceptio;
import lpII.model.DispositivoEntity;
import lpII.model.MonitorEntity;
import lpII.model.NotebookEntity;
import org.modelmapper.ModelMapper;

import java.util.List;

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

    public List<DispositivoEntity> listarTodosDispositivos(Integer page, Integer pageSize) {
        return DispositivoEntity.findAll()
                .page(page, pageSize)
                .list();
    }

    public MonitorEntity findMonitorById(Long id) {
        return (MonitorEntity) DispositivoEntity.findByIdOptional(id)
                .orElseThrow(DispositivoNotFoundExceptio::new);
    }
    public NotebookEntity findNotebookById(Long id) {
        return (NotebookEntity) DispositivoEntity.findByIdOptional(id)
                .orElseThrow(DispositivoNotFoundExceptio::new);
    }

}
