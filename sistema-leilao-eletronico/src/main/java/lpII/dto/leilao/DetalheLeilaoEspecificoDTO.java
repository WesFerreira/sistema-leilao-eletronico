package lpII.dto.leilao;

import lombok.Data;
import lpII.model.DispositivoEntity;
import lpII.model.InstituicaoFinanceiraEntity;
import lpII.model.LeilaoEntity;
import lpII.model.VeiculoEntity;

import java.util.List;

@Data
public class DetalheLeilaoEspecificoDTO {

    public List<VeiculoEntity> veiculos;
    public List<DispositivoEntity> dispositivos;
    public List<InstituicaoFinanceiraEntity> instituicoes;
    public LeilaoEntity leilao;
    public Long qtdVeiculos;
    public Long qtdDispositivos;
    public Long qtdTotal;

}
