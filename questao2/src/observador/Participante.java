package observador;

import leilao.Lance;
import leilao.LanceRecusadoException;
import leilao.Lote;

public class Participante implements ObservadorLote {

    private final String nome;

    public Participante(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void acompanhar(Lote lote) {
        lote.adicionarObservador(this);
        System.out.println("[" + nome + "] passou a acompanhar o lote " + lote.getNome());
    }

    public void deixarDeAcompanhar(Lote lote) {
        lote.removerObservador(this);
        System.out.println("[" + nome + "] deixou de acompanhar o lote " + lote.getNome());
    }

    public void darLance(Lote lote, double valor) {
        try {
            lote.receberLance(this, valor);
        } catch (LanceRecusadoException e) {
            System.out.println(String.format("[%s] lance de R$ %.2f RECUSADO no lote %s: %s", nome, valor,
                    lote.getNome(), e.getMessage()));
        }
    }

    @Override
    public void atualizar(Lote lote, Lance lance) {
        System.out.println(String.format("    -> [%s] novo lance no lote %s: R$ %.2f", nome, lote.getNome(),
                lance.getValor()));
    }

    @Override
    public String toString() {
        return nome;
    }
}
