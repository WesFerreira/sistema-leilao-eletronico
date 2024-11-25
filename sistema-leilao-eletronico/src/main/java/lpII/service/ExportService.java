package lpII.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import lpII.dto.leilao.DetalheExportacaoLeilaoDTO;
import lpII.exception.ApiException;
import lpII.model.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ExportService {

    public void exportarLeilao(Long idLeilao, String filePath) throws IOException {

        LeilaoEntity leilao = LeilaoEntity.findById(idLeilao);
        if (leilao == null) {
            throw new ApiException("Leilão não encontrado!", Response.Status.NOT_FOUND);
        }

        List<VeiculoEntity> listVeiculos = VeiculoEntity.findByIdLeilaoOrdered(idLeilao);
        List<DispositivoEntity> listDispositivo = DispositivoEntity.findByIdLeilaoOrdered(idLeilao);
        List<LanceEntity> listLances = LanceEntity.findLancesByIdLeilao(idLeilao);
        List<ClienteEntity> listClientes = LanceEntity.findDistinctClientesByLeilaoId(idLeilao);

        List<Long> idInstituicoes = leilao.getInstituicaoFinId();
        List<InstituicaoFinanceiraEntity> listInstituicoes = new ArrayList<>();

        for (Long instituicaoId : idInstituicoes) {
            InstituicaoFinanceiraEntity instituicaoFinanceira = InstituicaoFinanceiraEntity.findById(instituicaoId);

            listInstituicoes.add(instituicaoFinanceira);
        }

        DetalheExportacaoLeilaoDTO detalheLeilao = new DetalheExportacaoLeilaoDTO();
        detalheLeilao.setLeilao(leilao);
        detalheLeilao.setVeiculos(listVeiculos);
        detalheLeilao.setDispositivos(listDispositivo);
        detalheLeilao.setClientes(listClientes);
        detalheLeilao.setInstituicoes(listInstituicoes);
        detalheLeilao.setLances(listLances);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(detalheLeilao);

        File file = new File(filePath);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(json);
        }

    }

}
