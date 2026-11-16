package fabrica;

public final class TabelaPrecos {

    public static final int HORAS_NO_MES = 730;

    private final double custoHoraMaquinaVirtual;
    private final double custoGbArmazenamento;
    private final double custoFixoFila;

    public TabelaPrecos(double custoHoraMaquinaVirtual, double custoGbArmazenamento, double custoFixoFila) {
        this.custoHoraMaquinaVirtual = custoHoraMaquinaVirtual;
        this.custoGbArmazenamento = custoGbArmazenamento;
        this.custoFixoFila = custoFixoFila;
    }

    public double getCustoHoraMaquinaVirtual() {
        return custoHoraMaquinaVirtual;
    }

    public double getCustoGbArmazenamento() {
        return custoGbArmazenamento;
    }

    public double getCustoFixoFila() {
        return custoFixoFila;
    }

    public double estimarCustoMensal(int quantidadeMaquinas, int gbArmazenamento) {
        return quantidadeMaquinas * custoHoraMaquinaVirtual * HORAS_NO_MES
                + gbArmazenamento * custoGbArmazenamento
                + custoFixoFila;
    }
}
