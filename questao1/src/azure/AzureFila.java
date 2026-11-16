package azure;

import recurso.Fila;

public class AzureFila implements Fila {

    public static final String PREFIXO = "sb-";
    public static final double CUSTO_FIXO_MENSAL = 0.50;

    private final String identificador;

    public AzureFila(int sequencial) {
        this.identificador = PREFIXO + sequencial;
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
    public double getCustoFixoMensal() {
        return CUSTO_FIXO_MENSAL;
    }
}
