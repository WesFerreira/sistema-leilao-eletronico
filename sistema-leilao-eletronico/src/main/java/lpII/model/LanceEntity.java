package lpII.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class LanceEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    private BigDecimal lanceInicial;

    private BigDecimal lanceAdicional;

    @ManyToOne
    @JoinColumn(name = "Dispositivoid")
    private DispositivoEntity dispositivo;

    @ManyToOne
    @JoinColumn(name = "Clienteid")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "Veiculoid")
    private VeiculoEntity veiculo;

    public static List<LanceEntity> findByVeiculoId(Long veiculoId) {
        return find("veiculo.id", veiculoId).list();
    }

}
