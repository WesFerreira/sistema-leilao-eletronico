package lpII.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import lpII.dto.lance.DetalheLanceVeiculoEspecificoDTO;
import lpII.dto.lance.LanceNovoDispositivoDTO;
import lpII.dto.lance.LanceNovoVeiculoDTO;
import lpII.exception.ApiException;
import lpII.model.ClienteEntity;
import lpII.model.DispositivoEntity;
import lpII.model.LanceEntity;
import lpII.model.VeiculoEntity;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class LanceService {

    private ModelMapper modelMapper;

    public LanceService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Transactional
    public LanceNovoVeiculoDTO lanceNovoVeiculo(LanceNovoVeiculoDTO lanceNovoVeiculoDTO) {
        VeiculoEntity veiculo = VeiculoEntity.findById(lanceNovoVeiculoDTO.getVeiculoId());
        ClienteEntity cliente = ClienteEntity.findById(lanceNovoVeiculoDTO.getClienteId());

        validaDadosLance(veiculo == null, "Veículo com o id: ", lanceNovoVeiculoDTO.getVeiculoId());
        validaDadosLance(cliente == null, "Cliente com o id: ", lanceNovoVeiculoDTO.getClienteId());

        LanceEntity lance = new LanceEntity();
        lance.setDataHora(LocalDateTime.now());
        lance.setLanceInicial(lanceNovoVeiculoDTO.getLanceInicial());
        lance.setLanceAdicional(lanceNovoVeiculoDTO.getLanceAdicional());
        lance.setCliente(cliente);
        lance.setVeiculo(veiculo);

        lance.persist();

        return modelMapper.map(lance, LanceNovoVeiculoDTO.class);
    }

    @Transactional
    public LanceNovoDispositivoDTO lanceNovoDispositivo(LanceNovoDispositivoDTO lanceNovoDispositivoDTO) {
        DispositivoEntity dispositivo = DispositivoEntity.findById(lanceNovoDispositivoDTO.getDispositivoId());
        ClienteEntity cliente = ClienteEntity.findById(lanceNovoDispositivoDTO.getClienteId());

        validaDadosLance(dispositivo == null, "Dispositivo com o id: ", lanceNovoDispositivoDTO.getDispositivoId());
        validaDadosLance(cliente == null, "Cliente com o id: ", lanceNovoDispositivoDTO.getClienteId());

        LanceEntity lance = new LanceEntity();
        lance.setDataHora(LocalDateTime.now());
        lance.setLanceInicial(lanceNovoDispositivoDTO.getLanceInicial());
        lance.setLanceAdicional(lanceNovoDispositivoDTO.getLanceAdicional());
        lance.setCliente(cliente);
        lance.setDispositivo(dispositivo);

        lance.persist();

        return modelMapper.map(lance, LanceNovoDispositivoDTO.class);
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

    public List<DetalheLanceVeiculoEspecificoDTO> detalheLanceVeiculoEspecifico(Long idVeiculo) {
        List<DetalheLanceVeiculoEspecificoDTO> listDetalhes = new ArrayList<>();
        List<LanceEntity> lances = LanceEntity.findByVeiculoId(idVeiculo);
        if (lances.isEmpty()) {
            throw new ApiException("Ainda não teve lances para esse veiculo", Response.Status.NOT_FOUND);
        }

        for (LanceEntity lance : lances) {
            DetalheLanceVeiculoEspecificoDTO detalhe = new DetalheLanceVeiculoEspecificoDTO();
            detalhe.setVeiculo(lance.getVeiculo());
            detalhe.setCliente(lance.getCliente());
            detalhe.setLanceInicial(lance.getLanceInicial());

            listDetalhes.add(detalhe);
        }

        return listDetalhes;
    }
}
