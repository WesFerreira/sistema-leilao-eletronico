package lpII.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lpII.dto.cliente.ClienteDTO;
import lpII.model.ClienteEntity;
import org.modelmapper.ModelMapper;

import java.util.List;

@ApplicationScoped
public class ClienteService {

    private ModelMapper modelMapper;

    public ClienteService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void cadastrarCliente (ClienteDTO clienteDTO) {
        ClienteEntity cliente = modelMapper.map(clienteDTO, ClienteEntity.class);
        cliente.persist();
    }

    public List<ClienteEntity> listarTodosClientes() {
        return ClienteEntity.listAll();
    }

    public ClienteEntity buscarClienteId(Long id) {
        return ClienteEntity.findById(id);
    }

    @Transactional
    public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
        ClienteEntity cliente = ClienteEntity.findById(id);
        if (cliente != null) {
            modelMapper.map(clienteDTO, cliente);
            cliente.persist();
            return modelMapper.map(cliente, ClienteDTO.class);
        } else {
            return null;
        }
    }

    @Transactional
    public void deletarCliente(Long id) {
        ClienteEntity.deleteById(id);
    }

}
