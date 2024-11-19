package lpII.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lpII.dto.instituicaoFinanceira.InstituicaoFinanceiraDTO;
import lpII.model.InstituicaoFinanceiraEntity;
import org.modelmapper.ModelMapper;

import java.util.List;

@ApplicationScoped
public class InstituicaoFinanceiraService {

    private ModelMapper modelMapper;

    public InstituicaoFinanceiraService() {
        this.modelMapper = new ModelMapper();
    }

    @Transactional
    public void cadastrarInstituicaoFinanceira(InstituicaoFinanceiraDTO instituicaoFinanceiraDTO) {
        InstituicaoFinanceiraEntity instituicaoFinanceira =
                modelMapper.map(instituicaoFinanceiraDTO,
                                InstituicaoFinanceiraEntity.class);
        instituicaoFinanceira.persist();
    }

    public List<InstituicaoFinanceiraEntity> listarTodasInstituicoesFin() {
        return InstituicaoFinanceiraEntity.listAll();
    }

    public InstituicaoFinanceiraEntity buscarInstituicaoId(Long id) {
        return InstituicaoFinanceiraEntity.findById(id);
    }

    @Transactional
    public InstituicaoFinanceiraDTO atualizarInstituicao(Long id, InstituicaoFinanceiraDTO instituicaoFinanceiraDTO) {
        InstituicaoFinanceiraEntity instituicaoFin = InstituicaoFinanceiraEntity.findById(id);
        if (instituicaoFin != null) {
            modelMapper.map(instituicaoFinanceiraDTO, instituicaoFin);
            instituicaoFin.persist();
            return modelMapper.map(instituicaoFin, InstituicaoFinanceiraDTO.class);
        } else {
            return null;
        }
    }

    @Transactional
    public void deletarInstituicao(Long id) {
        InstituicaoFinanceiraEntity.deleteById(id);
    }

}
