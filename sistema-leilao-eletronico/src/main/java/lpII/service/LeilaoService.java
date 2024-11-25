package lpII.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import lpII.dto.leilao.DetalheLeilaoEspecificoDTO;
import lpII.dto.leilao.LeilaoNovoDTO;
import lpII.exception.ApiException;
import lpII.model.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
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
            throw new ApiException("Deve ser informada ao menos uma instituição financeira.", Response.Status.NOT_FOUND);
        }

        for (Long instituicaoFinId : idInstituicoes) {
            InstituicaoFinanceiraEntity instituicao = InstituicaoFinanceiraEntity.findById(instituicaoFinId);
            if (instituicao == null) {
                throw new ApiException("Não foi encontrado Instituição com esse id: " + instituicaoFinId, Response.Status.NOT_FOUND);
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

    public List<LeilaoEntity> leilaoOrdenadoDataIninio() {
        return LeilaoEntity.findAllOrderedByDataInicio();
    }

    public DetalheLeilaoEspecificoDTO buscarDetalhesLeilaoEspecifico(Long idLeilao) {
        LeilaoEntity leilao = LeilaoEntity.findById(idLeilao);
        List<VeiculoEntity> listVeiculos = VeiculoEntity.findByIdLeilaoOrdered(idLeilao);
        List<DispositivoEntity> listDispositivo = DispositivoEntity.findByIdLeilaoOrdered(idLeilao);
        Long qtdVeiculo = (long) listVeiculos.size();
        Long qtdDispositivos = (long) listDispositivo.size();
        Long qtdTotal = (qtdVeiculo + qtdDispositivos);
        List<Long> idInstituicoes = leilao.getInstituicaoFinId();
        List<InstituicaoFinanceiraEntity> listInstituicoes = new ArrayList<>();

        for (Long instituicaoId : idInstituicoes) {
            InstituicaoFinanceiraEntity instituicaoFinanceira = InstituicaoFinanceiraEntity.findById(instituicaoId);

            listInstituicoes.add(instituicaoFinanceira);
        }

        DetalheLeilaoEspecificoDTO detalheLeilao = new DetalheLeilaoEspecificoDTO();
        detalheLeilao.setLeilao(leilao);
        detalheLeilao.setDispositivos(listDispositivo);
        detalheLeilao.setInstituicoes(listInstituicoes);
        detalheLeilao.setVeiculos(listVeiculos);
        detalheLeilao.setQtdDispositivos(qtdDispositivos);
        detalheLeilao.setQtdVeiculos(qtdVeiculo);
        detalheLeilao.setQtdTotal(qtdTotal);

        return detalheLeilao;
    }
}
