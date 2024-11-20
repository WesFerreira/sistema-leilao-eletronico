package lpII.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lpII.dto.leilao.LeilaoNovoDTO;
import lpII.enuns.Status;
import lpII.model.InstituicaoFinanceiraEntity;
import lpII.model.LeilaoEntity;
import org.modelmapper.ModelMapper;

import java.util.List;

@ApplicationScoped
public class LeilaoService {

    private final ModelMapper modelMapper;

    public LeilaoService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Transactional
    public LeilaoNovoDTO cadastrarLeilao(LeilaoNovoDTO leilaoDTO) {
        validandoInstituicaoFinanceira(leilaoDTO);

        LeilaoEntity leilaoNovo = new LeilaoEntity(leilaoDTO);
        LeilaoEntity.persist(leilaoNovo);
        return modelMapper.map(leilaoNovo, LeilaoNovoDTO.class);

    }

    private void validandoInstituicaoFinanceira(LeilaoNovoDTO leilaoDTO) {
        List<Long> idInstituicoes = leilaoDTO.getInstituicaoFinId();
        if (idInstituicoes.isEmpty()) {
            throw new IllegalArgumentException("Deve ser informada ao menos uma instituição financeira.");
        }

        for (Long instituicaoFinId : idInstituicoes) {
            InstituicaoFinanceiraEntity instituicao = InstituicaoFinanceiraEntity.findById(instituicaoFinId);
            if (instituicao == null) {
                throw new IllegalArgumentException("Não foi encontrado Instituição com esse id: " + instituicao);
            }
        }
    }

    public List<LeilaoEntity> listarTodosLeiloes() {
        return LeilaoEntity.listAll();
    }

    public LeilaoEntity buscarLeilaoId(Long id) {
        return LeilaoEntity.findById(id);
    }

    @Transactional
    public void deletarLeilao(Long id) {
        LeilaoEntity.deleteById(id);
    }

}
