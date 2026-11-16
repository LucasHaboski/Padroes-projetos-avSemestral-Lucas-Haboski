package fatura;

public class CertificadoAutenticidade implements ServicoAdicional {

    private static final double CUSTO = 300.00;

    @Override
    public String getDescricao() {
        return "Certificado de autenticidade";
    }

    @Override
    public double calcularCusto(double valorVenda) {
        return CUSTO;
    }
}
