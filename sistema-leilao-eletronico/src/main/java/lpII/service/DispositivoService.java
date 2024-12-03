package lpII.service;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import lpII.dto.dispositivo.DispositivoNovoDTO;
import lpII.exception.ApiException;
import lpII.exception.DispositivoNotFoundExceptio;
import lpII.exception.DispositivoNotFoundExceptionMapper;
import lpII.exception.VeiculoNotFoundException;
import lpII.model.*;
import org.modelmapper.ModelMapper;

import java.util.List;

@ApplicationScoped
public class DispositivoService {

    @PersistenceContext
    EntityManager entityManager;
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
        DispositivoEntity dispositivo = DispositivoEntity.findById(id);
        validaDispositivoNull(dispositivo == null, "Não foi encontrado dispositivo com o id: " + id);

        validaDispositivoNull(!(dispositivo instanceof MonitorEntity), "O dispositivo encontrado de id: " + id + " não é um monitor.");

        return (MonitorEntity) dispositivo;
    }

    public NotebookEntity findNotebookById(Long id) {
        DispositivoEntity dispositivo = DispositivoEntity.findById(id);
        validaDispositivoNull(dispositivo == null, "Não foi encontrado dispositivo com o id: " + id);

        validaDispositivoNull(!(dispositivo instanceof NotebookEntity), "O dispositivo encontrado de id: " + id + " não é um notebook.");

        return (NotebookEntity) dispositivo;
    }

    public CelularEntity findCelularById(Long id) {
        DispositivoEntity dispositivo = DispositivoEntity.findById(id);
        validaDispositivoNull(dispositivo == null, "Não foi encontrado dispositivo com o id: " + id);

        validaDispositivoNull(!(dispositivo instanceof CelularEntity), "O dispositivo encontrado de id: " + id + " não é um celular.");

        return (CelularEntity) dispositivo;
    }

    private void validaDispositivoNull(boolean dispositivo, String id) {
        if(dispositivo) {
            throw new ApiException(id, Response.Status.NOT_FOUND);
        }
    }

    @Transactional
    public <T extends PanacheEntityBase> DispositivoNovoDTO atualizarDispositivo(Long id,
                                                   DispositivoNovoDTO dispositivoNovoDTO,
                                                   Class<T> entityClass) {

        T dispositivo = entityManager.find(entityClass, id);
        if (dispositivo != null) {
            modelMapper.map(dispositivoNovoDTO, dispositivo);
            dispositivo.persist();
            return modelMapper.map(dispositivo, DispositivoNovoDTO.class);
        } else {
            return null;
        }
    }

    @Transactional
    public void deletarDispositivo(Long id) {
        DispositivoEntity.deleteById(id);
    }
}
