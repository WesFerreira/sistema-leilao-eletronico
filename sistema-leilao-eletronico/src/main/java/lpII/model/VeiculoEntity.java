package lpII.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lpII.dto.veiculo.VeiculoNovoDTO;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "veiculo", discriminatorType = DiscriminatorType.STRING)
public abstract class VeiculoEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private String marca;
    private Integer ano;
    private String renavam;
    private String chassi;
    private Long idLeilao;
    private BigDecimal valorInicial;

    public VeiculoEntity() {
    }

    public static List<VeiculoEntity> findByIdLeilaoOrdered(Long idLeilao) {
        return find("idLeilao = ?1 ORDER BY modelo ASC", idLeilao).list();
    }

    public abstract VeiculoNovoDTO toDTO();

}
