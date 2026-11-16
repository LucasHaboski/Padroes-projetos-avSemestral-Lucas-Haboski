package fatura;

public interface ServicoAdicional {

    String getDescricao();

    double calcularCusto(double valorVenda);
}
