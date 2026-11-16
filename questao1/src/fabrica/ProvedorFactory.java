package fabrica;

import recurso.Armazenamento;
import recurso.Fila;
import recurso.MaquinaVirtual;

public interface ProvedorFactory {

    String getNomeProvedor();

    TabelaPrecos getTabelaPrecos();

    MaquinaVirtual criarMaquinaVirtual();

    Armazenamento criarArmazenamento(int capacidadeGb);

    Fila criarFila();
}
