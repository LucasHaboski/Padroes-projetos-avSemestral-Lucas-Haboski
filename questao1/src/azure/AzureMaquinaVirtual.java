package azure;

import recurso.MaquinaVirtual;

public class AzureMaquinaVirtual implements MaquinaVirtual {

    public static final String PREFIXO = "vm-";
    public static final double CUSTO_POR_HORA = 0.12;

    private final String identificador;

    public AzureMaquinaVirtual(int sequencial) {
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
    public double getCustoPorHora() {
        return CUSTO_POR_HORA;
    }
}
