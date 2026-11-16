package observador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import leilao.Lance;
import leilao.Lote;

public class Auditor implements ObservadorLote {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final List<String> registros = new ArrayList<>();

    @Override
    public void atualizar(Lote lote, Lance lance) {
        String registro = String.format("[%s] lote=%s participante=%s valor=R$ %.2f", FORMATO.format(LocalDateTime.now()),
                lote.getNome(), lance.getParticipante().getNome(), lance.getValor());
        registros.add(registro);
        System.out.println("    -> [Auditor] lance registrado");
    }

    public List<String> getRegistros() {
        return Collections.unmodifiableList(registros);
    }

    public void imprimirRegistros() {
        System.out.println("===== REGISTROS DO AUDITOR (" + registros.size() + " lances aceitos) =====");
        for (String registro : registros) {
            System.out.println(registro);
        }
    }
}
