package aws;

import recurso.MaquinaVirtual;

public class AwsMaquinaVirtual implements MaquinaVirtual {

    public static final String PREFIXO = "i-";
    public static final double CUSTO_POR_HORA = 0.10;

    private final String identificador;

    public AwsMaquinaVirtual(int sequencial) {
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
    public double getCustoPorHora() {
        return CUSTO_POR_HORA;
    }
}
