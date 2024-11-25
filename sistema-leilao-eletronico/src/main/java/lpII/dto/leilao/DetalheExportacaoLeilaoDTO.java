package lpII.dto.leilao;

import lombok.Data;
import lpII.model.*;

import java.util.List;

@Data
public class DetalheExportacaoLeilaoDTO {

    private LeilaoEntity leilao;
    public List<VeiculoEntity> veiculos;
    public List<DispositivoEntity> dispositivos;
    public List<ClienteEntity> clientes;
    public List<InstituicaoFinanceiraEntity> instituicoes;
    public List<LanceEntity> lances;

}
