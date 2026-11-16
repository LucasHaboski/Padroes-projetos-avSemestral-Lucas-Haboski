package comissao;

public class ComissaoImoveis implements RegraComissao {

    private static final double PERCENTUAL = 0.03;
    private static final double VALOR_FIXO = 1500.00;

    @Override
    public String getDescricao() {
        return "Imoveis: 3% do valor de venda mais R$ 1.500,00 fixos";
    }

    @Override
    public double calcular(double valorVenda) {
        return valorVenda * PERCENTUAL + VALOR_FIXO;
    }
}
