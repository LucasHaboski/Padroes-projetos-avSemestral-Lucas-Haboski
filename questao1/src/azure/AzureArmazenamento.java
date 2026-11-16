package azure;

import recurso.Armazenamento;

public class AzureArmazenamento implements Armazenamento {

    public static final String PREFIXO = "blob://";
    public static final double CUSTO_POR_GB_MES = 0.02;

    private final String identificador;
    private final int capacidadeGb;

    public AzureArmazenamento(int sequencial, int capacidadeGb) {
        this.identificador = PREFIXO + sequencial;
        this.capacidadeGb = capacidadeGb;
    }

    @Override
    public String getIdentificador() {
        return identificador;
    }

    @Override
    public String getProvedor() {
        return AzureFactory.NOME;
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
