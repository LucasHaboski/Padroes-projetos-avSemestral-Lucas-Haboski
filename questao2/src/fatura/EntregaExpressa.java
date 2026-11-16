package fatura;

public class EntregaExpressa implements ServicoAdicional {

    private static final double CUSTO = 450.00;

    @Override
    public String getDescricao() {
        return "Entrega expressa";
    }

    @Override
    public double calcularCusto(double valorVenda) {
        return CUSTO;
    }
}
