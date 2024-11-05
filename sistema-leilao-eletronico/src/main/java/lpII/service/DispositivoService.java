package lpII.service;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lpII.dto.dispositivo.DispositivoNovoDTO;

@ApplicationScoped
public class DispositivoService {

    @Transactional
    public <T extends PanacheEntityBase> DispositivoNovoDTO cadastrarDispositivo(DispositivoNovoDTO dispositivoNovoDTO,
                                                                             Class<T> entityClass) {
        return null;
    }

}
