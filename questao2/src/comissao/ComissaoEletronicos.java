package comissao;

public class ComissaoEletronicos implements RegraComissao {

    private static final double PERCENTUAL = 0.12;
    private static final double MINIMO = 50.00;

    @Override
    public String getDescricao() {
        return "Eletronicos: 12% do valor de venda, minimo de R$ 50,00";
    }

    @Override
    public double calcular(double valorVenda) {
        return Math.max(valorVenda * PERCENTUAL, MINIMO);
    }
}
