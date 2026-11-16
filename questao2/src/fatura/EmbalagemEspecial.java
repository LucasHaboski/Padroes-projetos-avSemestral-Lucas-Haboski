package fatura;

public class EmbalagemEspecial implements ServicoAdicional {

    private static final double CUSTO = 180.00;

    @Override
    public String getDescricao() {
        return "Embalagem especial";
    }

    @Override
    public double calcularCusto(double valorVenda) {
        return CUSTO;
    }
}
