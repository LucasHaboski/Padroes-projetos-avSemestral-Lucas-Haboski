package leilao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comissao.RegraComissao;
import observador.Participante;
import observador.SujeitoLote;

public class Lote extends SujeitoLote {

    public static final double INCREMENTO_MINIMO = 0.05;

    private final String nome;
    private final Categoria categoria;
    private final double precoInicial;
    private final double precoReserva;
    private final List<Lance> lances = new ArrayList<>();
    private EstadoLote estado = EstadoLote.ABERTO;
    private RegraComissao regraComissao;

    public Lote(String nome, Categoria categoria, double precoInicial, double precoReserva) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoInicial = precoInicial;
        this.precoReserva = precoReserva;
        this.regraComissao = categoria.getRegraComissaoPadrao();
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public double getPrecoInicial() {
        return precoInicial;
    }

    public double getPrecoReserva() {
        return precoReserva;
    }

    public EstadoLote getEstado() {
        return estado;
    }

    public List<Lance> getLances() {
        return Collections.unmodifiableList(lances);
    }

    public Lance getMaiorLance() {
        if (lances.isEmpty()) {
            return null;
        }
        return lances.get(lances.size() - 1);
    }

    public double getLanceAtual() {
        Lance maior = getMaiorLance();
        return maior == null ? precoInicial : maior.getValor();
    }

    public RegraComissao getRegraComissao() {
        return regraComissao;
    }

    public void setRegraComissao(RegraComissao regraComissao) {
        if (estado != EstadoLote.ABERTO) {
            throw new IllegalStateException("Nao e possivel trocar a regra de comissao de um lote encerrado");
        }
        this.regraComissao = regraComissao;
        System.out.println("[Casa de leiloes] lote " + nome + " passa a usar a regra: " + regraComissao.getDescricao());
    }

    public void receberLance(Participante participante, double valor) throws LanceRecusadoException {
        if (estado != EstadoLote.ABERTO) {
            throw new LanceRecusadoException("o lote esta encerrado (" + estado + ")");
        }
        double minimoAceito = getLanceAtual() * (1 + INCREMENTO_MINIMO);
        if (valor < minimoAceito) {
            throw new LanceRecusadoException(String.format(
                    "o lance precisa ser pelo menos 5%% maior que o lance atual de R$ %.2f (minimo R$ %.2f)",
                    getLanceAtual(), minimoAceito));
        }
        Lance lance = new Lance(participante, valor);
        lances.add(lance);
        System.out.println(String.format("[%s] lance de R$ %.2f ACEITO no lote %s", participante.getNome(), valor, nome));
        notificarObservadores(this, lance, participante);
    }

    public void encerrar() {
        if (estado != EstadoLote.ABERTO) {
            throw new IllegalStateException("O lote " + nome + " ja foi encerrado");
        }
        Lance maior = getMaiorLance();
        if (maior != null && maior.getValor() >= precoReserva) {
            estado = EstadoLote.VENDIDO;
            System.out.println(String.format("[Encerramento] lote %s VENDIDO para %s por R$ %.2f", nome,
                    maior.getParticipante().getNome(), maior.getValor()));
        } else {
            estado = EstadoLote.NAO_VENDIDO;
            System.out.println(String.format("[Encerramento] lote %s NAO VENDIDO (maior lance R$ %.2f, reserva R$ %.2f)",
                    nome, getLanceAtual(), precoReserva));
        }
    }

    public boolean isVendido() {
        return estado == EstadoLote.VENDIDO;
    }

    public double getValorVenda() {
        if (!isVendido()) {
            throw new IllegalStateException("O lote " + nome + " nao foi vendido");
        }
        return getMaiorLance().getValor();
    }

    public Participante getComprador() {
        if (!isVendido()) {
            throw new IllegalStateException("O lote " + nome + " nao foi vendido");
        }
        return getMaiorLance().getParticipante();
    }

    public double calcularComissao() {
        return regraComissao.calcular(getValorVenda());
    }
}
