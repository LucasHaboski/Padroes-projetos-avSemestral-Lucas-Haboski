package auditoria;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class RegistroAuditoria {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

    private final LocalDateTime dataHora;
    private final String nomeAmbiente;
    private final String provedor;
    private final ResultadoProvisionamento resultado;
    private final String motivo;

    public RegistroAuditoria(LocalDateTime dataHora, String nomeAmbiente, String provedor,
            ResultadoProvisionamento resultado, String motivo) {
        this.dataHora = dataHora;
        this.nomeAmbiente = nomeAmbiente;
        this.provedor = provedor;
        this.resultado = resultado;
        this.motivo = motivo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getNomeAmbiente() {
        return nomeAmbiente;
    }

    public String getProvedor() {
        return provedor;
    }

    public ResultadoProvisionamento getResultado() {
        return resultado;
    }

    public String getMotivo() {
        return motivo;
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();
        texto.append("[").append(FORMATO.format(dataHora)).append("] ");
        texto.append("ambiente=").append(nomeAmbiente);
        texto.append(" provedor=").append(provedor);
        texto.append(" resultado=").append(resultado);
        if (motivo != null && !motivo.isBlank()) {
            texto.append(" motivo=").append(motivo);
        }
        return texto.toString();
    }
}
