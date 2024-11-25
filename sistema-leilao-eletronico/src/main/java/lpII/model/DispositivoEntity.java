package lpII.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lpII.dto.dispositivo.DispositivoNovoDTO;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dispositivo", discriminatorType = DiscriminatorType.STRING)
public abstract class DispositivoEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String marca;
    private Integer ano;
    private Integer quantidade;
    private Long idLeilao;
    public BigDecimal valorInical;

    public DispositivoEntity() {
    }

    public static List<DispositivoEntity> findByIdLeilaoOrdered(Long idLeilao) {
        return find("idLeilao = ?1 ORDER BY nome ASC", idLeilao).list();
    }

    public abstract DispositivoNovoDTO toDTO();

}
