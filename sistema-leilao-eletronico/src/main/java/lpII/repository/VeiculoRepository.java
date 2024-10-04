package lpII.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lpII.model.VeiculoEntity;

@ApplicationScoped
public class VeiculoRepository<T extends VeiculoEntity> implements PanacheRepository<T> {
}
