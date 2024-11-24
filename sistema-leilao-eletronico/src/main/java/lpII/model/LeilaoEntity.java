package lpII.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lpII.dto.leilao.LeilaoNovoDTO;
import lpII.enuns.Status;
import lpII.utils.StatusLeilao;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class LeilaoEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Status status;
    private String endereco;
    private String cidade;
    private String estado;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private LocalDateTime dataVisita;
    private List<Long> instituicaoFinId;

    public LeilaoEntity(LeilaoNovoDTO leilaoNovo){
        this.dataInicio = leilaoNovo.getDataInicio();
        this.dataFim = leilaoNovo.getDataFim();
        this.dataVisita = leilaoNovo.getDataVisita();
        this.endereco = leilaoNovo.getEndereco();
        this.cidade = leilaoNovo.getCidade();
        this.estado = leilaoNovo.getEstado();
        this.instituicaoFinId = leilaoNovo.getInstituicaoFinId();
        this.status = StatusLeilao.statusLeilao(LocalDateTime.now(), this);
    }

    public static List<LeilaoEntity> findAllOrderedByDataInicio() {
        return find("ORDER BY dataInicio").list();
    }

}
