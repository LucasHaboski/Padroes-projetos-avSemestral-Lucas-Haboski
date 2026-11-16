package fatura;

public class SeguroTransporte implements ServicoAdicional {

    private static final double PERCENTUAL = 0.015;

    @Override
    public String getDescricao() {
        return "Seguro de transporte (1,5% do valor de venda)";
    }

    @Override
    public double calcularCusto(double valorVenda) {
        return valorVenda * PERCENTUAL;
    }
}
