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

    public LeilaoEntity(LeilaoNovoDTO cadastro){
        this.dataInicio = cadastro.getDataInicio();
        this.dataFim = cadastro.getDataFim();
        this.dataVisita = cadastro.getDataVisita();
        this.endereco = cadastro.getEndereco();
        this.cidade = cadastro.getCidade();
        this.estado = cadastro.getEstado();
        this.instituicaoFinId = cadastro.getInstituicaoFinId();
        this.status = StatusLeilao.statusLeilao(LocalDateTime.now(), this);
    }
}
