package lpII.utils;

import lpII.enuns.Status;
import lpII.model.LeilaoEntity;

import java.time.LocalDateTime;

public class StatusLeilao {

    public static Status statusLeilao(LocalDateTime agora, LeilaoEntity leilao) {
        if (agora.isBefore(leilao.getDataInicio())) {
            leilao.setStatus(Status.ABERTO);
            return Status.ABERTO;
        } else if (agora.isAfter(leilao.getDataInicio()) && agora.isBefore(leilao.getDataFim())) {
            leilao.setStatus(Status.ANDAMENTO);
            return Status.ANDAMENTO;
        } else if (agora.isAfter(leilao.getDataFim())) {
            leilao.setStatus(Status.FINALIZADO);
            return Status.FINALIZADO;
        }
        return null;
    }

}
