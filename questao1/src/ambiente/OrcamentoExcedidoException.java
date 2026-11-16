package ambiente;

public class OrcamentoExcedidoException extends Exception {

    private final double custoEstimado;
    private final double orcamentoMaximo;

    public OrcamentoExcedidoException(String nomeAmbiente, double custoEstimado, double orcamentoMaximo) {
        super(String.format("Custo estimado de R$ %.2f ultrapassa o orcamento de R$ %.2f para o ambiente %s",
                custoEstimado, orcamentoMaximo, nomeAmbiente));
        this.custoEstimado = custoEstimado;
        this.orcamentoMaximo = orcamentoMaximo;
    }

    public double getCustoEstimado() {
        return custoEstimado;
    }

    public double getOrcamentoMaximo() {
        return orcamentoMaximo;
    }
}
