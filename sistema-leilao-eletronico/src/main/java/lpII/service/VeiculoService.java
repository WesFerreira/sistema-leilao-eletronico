package lpII.service;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import lpII.dto.veiculo.VeiculoNovoDTO;
import lpII.exception.ApiException;
import lpII.exception.VeiculoNotFoundException;
import lpII.model.CaminhaoEntity;
import lpII.model.CarroEntity;
import lpII.model.MotoEntity;
import lpII.model.VeiculoEntity;
import org.modelmapper.ModelMapper;

import java.util.List;

@ApplicationScoped
public class VeiculoService {

    @PersistenceContext
    EntityManager entityManager;
    private final ModelMapper modelMapper;

    public VeiculoService(ModelMapper modelMapper) {
        this.modelMapper = new ModelMapper();
    }

    @Transactional
    public <T extends PanacheEntityBase> VeiculoNovoDTO cadastrarVeiculo(VeiculoNovoDTO veiculoNovoDTO,
                                                                         Class<T> entityClass) {
        T veiculo = modelMapper.map(veiculoNovoDTO, entityClass);
        veiculo.persist();
        return modelMapper.map(veiculo, VeiculoNovoDTO.class);
    }

    public List<VeiculoEntity> listarTodosVeiculos(Integer page, Integer pageSize) {
        return VeiculoEntity.findAll()
                .page(page, pageSize)
                .list();
    }

    public CarroEntity findCarroById(Long id) {
        VeiculoEntity veiculo = VeiculoEntity.findById(id);
        validaVeiculoNull(veiculo == null, "Não foi encontrado veiculo com o id: " + id);

        validaVeiculoNull(!(veiculo instanceof CarroEntity), "O veículo encontrado de id: " + id + " não é um carro.");

        return (CarroEntity) veiculo;
    }

    public MotoEntity findMotoById(Long id) {
        VeiculoEntity veiculo = VeiculoEntity.findById(id);
        validaVeiculoNull(veiculo == null, "Não foi encontrado veiculo com o id: " + id);

        validaVeiculoNull(!(veiculo instanceof MotoEntity), "O veículo encontrado de id: " + id + " não é uma moto.");

        return (MotoEntity) veiculo;
    }

    public CaminhaoEntity findCaminhaoById(Long id) {
        VeiculoEntity veiculo = VeiculoEntity.findById(id);
        validaVeiculoNull(veiculo == null, "Não foi encontrado veiculo com o id: " + id);

        validaVeiculoNull(!(veiculo instanceof CaminhaoEntity), "O veículo encontrado de id: " + id + " não é um caminhão.");

        return (CaminhaoEntity) veiculo;
    }

    private void validaVeiculoNull(boolean veiculo, String id) {
        if (veiculo) {
            throw new ApiException(id, Response.Status.NOT_FOUND);
        }
    }


    @Transactional
    public <T extends PanacheEntityBase> VeiculoNovoDTO atualizarVeiculo(Long id,
                                                                         VeiculoNovoDTO veiculoNovoDTO,
                                                                         Class<T> entityClass) {

        T veiculo = entityManager.find(entityClass, id);
        if (veiculo != null) {
            modelMapper.map(veiculoNovoDTO, veiculo);
            veiculo.persist();
            return modelMapper.map(veiculo, VeiculoNovoDTO.class);
        } else {
            return null;
        }

    }

    @Transactional
    public void deletarVeiculo(Long id) {
        VeiculoEntity.deleteById(id);
    }

}
