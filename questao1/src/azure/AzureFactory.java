package azure;

import java.util.concurrent.atomic.AtomicInteger;

import fabrica.ProvedorFactory;
import fabrica.TabelaPrecos;
import recurso.Armazenamento;
import recurso.Fila;
import recurso.MaquinaVirtual;

public class AzureFactory implements ProvedorFactory {

    public static final String NOME = "Azure";

    private static final AtomicInteger SEQUENCIA_MAQUINAS = new AtomicInteger();
    private static final AtomicInteger SEQUENCIA_ARMAZENAMENTOS = new AtomicInteger();
    private static final AtomicInteger SEQUENCIA_FILAS = new AtomicInteger();

    private final TabelaPrecos tabelaPrecos = new TabelaPrecos(
            AzureMaquinaVirtual.CUSTO_POR_HORA,
            AzureArmazenamento.CUSTO_POR_GB_MES,
            AzureFila.CUSTO_FIXO_MENSAL);

    @Override
    public String getNomeProvedor() {
        return NOME;
    }

    @Override
    public TabelaPrecos getTabelaPrecos() {
        return tabelaPrecos;
    }

    @Override
    public MaquinaVirtual criarMaquinaVirtual() {
        return new AzureMaquinaVirtual(SEQUENCIA_MAQUINAS.incrementAndGet());
    }

    @Override
    public Armazenamento criarArmazenamento(int capacidadeGb) {
        return new AzureArmazenamento(SEQUENCIA_ARMAZENAMENTOS.incrementAndGet(), capacidadeGb);
    }

    @Override
    public Fila criarFila() {
        return new AzureFila(SEQUENCIA_FILAS.incrementAndGet());
    }
}
