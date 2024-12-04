package lpII.dto.instituicaoFinanceira;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
public class InstituicaoFinanceiraDTO {

    @Schema(description = "Nome da Instituição Financeira", example = "Itau")
    private String cnpj;

    @Schema(description = "Razão Social da Instituição Financeira", example = "000111222000134")
    private String razaoSocial;

}
