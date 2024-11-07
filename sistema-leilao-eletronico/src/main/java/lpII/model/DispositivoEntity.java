package lpII.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lpII.dto.dispositivo.DispositivoNovoDTO;

import java.math.BigDecimal;

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
    public BigDecimal valorInical;

    public DispositivoEntity() {
    }

    public abstract DispositivoNovoDTO toDTO();

}
