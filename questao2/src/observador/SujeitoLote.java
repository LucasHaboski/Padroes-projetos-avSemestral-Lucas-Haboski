package observador;

import java.util.ArrayList;
import java.util.List;

import leilao.Lance;
import leilao.Lote;

public abstract class SujeitoLote {

    private final List<ObservadorLote> observadores = new ArrayList<>();

    public void adicionarObservador(ObservadorLote observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void removerObservador(ObservadorLote observador) {
        observadores.remove(observador);
    }

    public List<ObservadorLote> getObservadores() {
        return new ArrayList<>(observadores);
    }

    protected void notificarObservadores(Lote lote, Lance lance, ObservadorLote excluido) {
        for (ObservadorLote observador : getObservadores()) {
            if (observador != excluido) {
                observador.atualizar(lote, lance);
            }
        }
    }
}
