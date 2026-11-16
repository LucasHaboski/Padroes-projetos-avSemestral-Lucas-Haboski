package observador;

import leilao.Lance;
import leilao.Lote;

public class PainelLeiloeiro implements ObservadorLote {

    @Override
    public void atualizar(Lote lote, Lance lance) {
        System.out.println(String.format("    -> [Painel do leiloeiro] lote %s | maior lance: R$ %.2f (%s) | total de lances: %d",
                lote.getNome(), lote.getLanceAtual(), lote.getMaiorLance().getParticipante().getNome(),
                lote.getLances().size()));
    }
}
