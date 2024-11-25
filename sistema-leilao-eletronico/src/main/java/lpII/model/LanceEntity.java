package lpII.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public static List<LanceEntity> findByDispositivoId(Long dispositivoId) {
        return find("dispositivo.id", dispositivoId).list();
    }

    public static List<LanceEntity> findLancesByIdLeilao(Long idLeilao) {
        List<LanceEntity> lancesVeiculo = find("veiculo.idLeilao = ?1", idLeilao).list();

        List<LanceEntity> lancesDispositivo = find("dispositivo.idLeilao = ?1", idLeilao).list();

        List<LanceEntity> lances = new ArrayList<>();
        lances.addAll(lancesVeiculo);
        lances.addAll(lancesDispositivo);

        return lances;
    }

    public static List<ClienteEntity> findDistinctClientesByLeilaoId(Long idLeilao) {
        return find("""
            SELECT DISTINCT l.cliente 
            FROM LanceEntity l 
            WHERE l.veiculo.idLeilao = ?1 OR l.dispositivo.idLeilao = ?1
        """, idLeilao).list();
    }


}
