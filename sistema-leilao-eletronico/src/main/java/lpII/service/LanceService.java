package lpII.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import lpII.dto.lance.LanceNovoVeiculoDTO;
import lpII.exception.ApiException;
import lpII.model.ClienteEntity;
import lpII.model.LanceEntity;
import lpII.model.VeiculoEntity;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class LanceService {

    private ModelMapper modelMapper;

    public LanceService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Transactional
    public LanceNovoVeiculoDTO vincularLanceAoVeiculo(LanceNovoVeiculoDTO lanceNovoVeiculoDTO) {
        VeiculoEntity veiculo = VeiculoEntity.findById(lanceNovoVeiculoDTO.getVeiculoId());
        ClienteEntity cliente = ClienteEntity.findById(lanceNovoVeiculoDTO.getClienteId());

        validaDadosLance(veiculo == null, "Veículo com o id:", lanceNovoVeiculoDTO.getVeiculoId());
        validaDadosLance(cliente == null, "Cliente com o id:", lanceNovoVeiculoDTO.getClienteId());

        LanceEntity lance = new LanceEntity();
        lance.setDataHora(LocalDateTime.now());
        lance.setLanceInicial(lanceNovoVeiculoDTO.getLanceInicial());
        lance.setLanceAdicional(lanceNovoVeiculoDTO.getLanceAdicional());
        lance.setVeiculo(veiculo);
        lance.setCliente(cliente);

        lance.persist();

        return modelMapper.map(lance, LanceNovoVeiculoDTO.class);
    }

    private void validaDadosLance(boolean dadosLance, String x, Long lanceNovoVeiculoDTO) {
        if (dadosLance) {
            throw new ApiException(x + lanceNovoVeiculoDTO + " não encontrado",
                    Response.Status.NOT_FOUND);
        }
    }


    public List<LanceEntity> listarLances() {
        return LanceEntity.listAll();
    }
}
