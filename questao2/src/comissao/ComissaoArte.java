package comissao;

public class ComissaoArte implements RegraComissao {

    private static final double PERCENTUAL = 0.20;

    @Override
    public String getDescricao() {
        return "Arte: 20% do valor de venda";
    }

    @Override
    public double calcular(double valorVenda) {
        return valorVenda * PERCENTUAL;
    }
}
