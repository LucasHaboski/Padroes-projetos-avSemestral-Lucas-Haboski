package aws;

import java.util.concurrent.atomic.AtomicInteger;

import fabrica.ProvedorFactory;
import fabrica.TabelaPrecos;
import recurso.Armazenamento;
import recurso.Fila;
import recurso.MaquinaVirtual;

public class AwsFactory implements ProvedorFactory {

    public static final String NOME = "Aws";

    private static final AtomicInteger SEQUENCIA_MAQUINAS = new AtomicInteger();
    private static final AtomicInteger SEQUENCIA_ARMAZENAMENTOS = new AtomicInteger();
    private static final AtomicInteger SEQUENCIA_FILAS = new AtomicInteger();

    private final TabelaPrecos tabelaPrecos = new TabelaPrecos(
            AwsMaquinaVirtual.CUSTO_POR_HORA,
            AwsArmazenamento.CUSTO_POR_GB_MES,
            AwsFila.CUSTO_FIXO_MENSAL);

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
        return new AwsMaquinaVirtual(SEQUENCIA_MAQUINAS.incrementAndGet());
    }

    @Override
    public Armazenamento criarArmazenamento(int capacidadeGb) {
        return new AwsArmazenamento(SEQUENCIA_ARMAZENAMENTOS.incrementAndGet(), capacidadeGb);
    }

    @Override
    public Fila criarFila() {
        return new AwsFila(SEQUENCIA_FILAS.incrementAndGet());
    }
}
