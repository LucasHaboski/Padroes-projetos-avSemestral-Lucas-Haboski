package observador;

import leilao.Lance;
import leilao.Lote;

public interface ObservadorLote {

    void atualizar(Lote lote, Lance lance);
}
