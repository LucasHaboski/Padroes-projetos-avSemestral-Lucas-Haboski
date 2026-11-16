package auditoria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class HistoricoAuditoria {

    private static final HistoricoAuditoria INSTANCIA = new HistoricoAuditoria();

    private final List<RegistroAuditoria> registros = new ArrayList<>();

    private HistoricoAuditoria() {
    }

    public static HistoricoAuditoria getInstancia() {
        return INSTANCIA;
    }

    public synchronized void registrar(String nomeAmbiente, String provedor, ResultadoProvisionamento resultado,
            String motivo) {
        registros.add(new RegistroAuditoria(LocalDateTime.now(), nomeAmbiente, provedor, resultado, motivo));
    }

    public synchronized List<RegistroAuditoria> getRegistros() {
        return Collections.unmodifiableList(new ArrayList<>(registros));
    }

    public synchronized void imprimir() {
        System.out.println("===== HISTORICO DE AUDITORIA (" + registros.size() + " registros) =====");
        for (RegistroAuditoria registro : registros) {
            System.out.println(registro);
        }
    }
}
