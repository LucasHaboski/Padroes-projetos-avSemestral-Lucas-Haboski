package leilao;

import observador.Participante;

public final class Lance {

    private final Participante participante;
    private final double valor;

    public Lance(Participante participante, double valor) {
        this.participante = participante;
        this.valor = valor;
    }

    public Participante getParticipante() {
        return participante;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return String.format("%s - R$ %.2f", participante.getNome(), valor);
    }
}
