package aws;

import recurso.Armazenamento;

public class AwsArmazenamento implements Armazenamento {

    public static final String PREFIXO = "s3://";
    public static final double CUSTO_POR_GB_MES = 0.023;

    private final String identificador;
    private final int capacidadeGb;

    public AwsArmazenamento(int sequencial, int capacidadeGb) {
        this.identificador = PREFIXO + sequencial;
        this.capacidadeGb = capacidadeGb;
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
    public int getCapacidadeGb() {
        return capacidadeGb;
    }

    @Override
    public double getCustoPorGbMes() {
        return CUSTO_POR_GB_MES;
    }
}
