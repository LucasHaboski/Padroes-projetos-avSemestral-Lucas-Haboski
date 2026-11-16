package fatura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import leilao.Lote;

public class Fatura {

    private final Lote lote;
    private final List<ServicoAdicional> servicos = new ArrayList<>();

    public Fatura(Lote lote) {
        if (!lote.isVendido()) {
            throw new IllegalStateException("Nao ha fatura para um lote nao vendido: " + lote.getNome());
        }
        this.lote = lote;
    }

    public Lote getLote() {
        return lote;
    }

    public double getValorVenda() {
        return lote.getValorVenda();
    }

    public void contratar(ServicoAdicional servico) {
        servicos.add(servico);
    }

    public List<ServicoAdicional> getServicos() {
        return Collections.unmodifiableList(servicos);
    }

    public double getCustoServicos() {
        double custo = 0;
        for (ServicoAdicional servico : servicos) {
            custo += servico.calcularCusto(getValorVenda());
        }
        return custo;
    }

    public double getTotal() {
        return getValorVenda() + getCustoServicos();
    }

    public void imprimir() {
        System.out.println("===== FATURA DO COMPRADOR - " + lote.getComprador().getNome() + " =====");
        System.out.println("Lote: " + lote.getNome() + " (" + lote.getCategoria().getDescricao() + ")");
        System.out.println(String.format("Valor de venda: R$ %.2f", getValorVenda()));
        for (ServicoAdicional servico : servicos) {
            System.out.println(String.format("  + %s: R$ %.2f", servico.getDescricao(),
                    servico.calcularCusto(getValorVenda())));
        }
        System.out.println(String.format("Total a pagar: R$ %.2f", getTotal()));
        System.out.println();
    }
}
