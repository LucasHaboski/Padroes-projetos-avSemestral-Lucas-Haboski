package ambiente;

public final class SolicitacaoAmbiente {

    private final String nomeAmbiente;
    private final String provedor;
    private final int quantidadeMaquinasVirtuais;
    private final int gbArmazenamento;

    public SolicitacaoAmbiente(String nomeAmbiente, String provedor, int quantidadeMaquinasVirtuais,
            int gbArmazenamento) {
        if (quantidadeMaquinasVirtuais < 1) {
            throw new IllegalArgumentException("O ambiente precisa de pelo menos uma maquina virtual");
        }
        if (gbArmazenamento < 1) {
            throw new IllegalArgumentException("O ambiente precisa de pelo menos 1 GB de armazenamento");
        }
        this.nomeAmbiente = nomeAmbiente;
        this.provedor = provedor;
        this.quantidadeMaquinasVirtuais = quantidadeMaquinasVirtuais;
        this.gbArmazenamento = gbArmazenamento;
    }

    public String getNomeAmbiente() {
        return nomeAmbiente;
    }

    public String getProvedor() {
        return provedor;
    }

    public int getQuantidadeMaquinasVirtuais() {
        return quantidadeMaquinasVirtuais;
    }

    public int getGbArmazenamento() {
        return gbArmazenamento;
    }
}
