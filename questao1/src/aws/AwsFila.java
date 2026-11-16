package aws;

import recurso.Fila;

public class AwsFila implements Fila {

    public static final String PREFIXO = "sqs-";
    public static final double CUSTO_FIXO_MENSAL = 0.40;

    private final String identificador;

    public AwsFila(int sequencial) {
        this.identificador = PREFIXO + sequencial;
    }

    @Override
    public String getIdentificador() {
        return identificador;
    }

    @Override
    public String getProvedor() {
        return AwsFactory.NOME;
    }

    @Override
    public double getCustoFixoMensal() {
        return CUSTO_FIXO_MENSAL;
    }
}
