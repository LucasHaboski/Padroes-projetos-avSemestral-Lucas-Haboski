package leilao;

public class AcertoVendedor {

    private final Lote lote;

    public AcertoVendedor(Lote lote) {
        if (!lote.isVendido()) {
            throw new IllegalStateException("Nao ha acerto para um lote nao vendido: " + lote.getNome());
        }
        this.lote = lote;
    }

    public double getValorVenda() {
        return lote.getValorVenda();
    }

    public double getComissao() {
        return lote.calcularComissao();
    }

    public double getValorLiquido() {
        return getValorVenda() - getComissao();
    }

    public void imprimir() {
        System.out.println("===== ACERTO DO VENDEDOR - " + lote.getNome() + " =====");
        System.out.println(String.format("Valor de venda: R$ %.2f", getValorVenda()));
        System.out.println(String.format("Comissao (%s): R$ %.2f", lote.getRegraComissao().getDescricao(), getComissao()));
        System.out.println(String.format("Valor liquido a repassar: R$ %.2f", getValorLiquido()));
        System.out.println();
    }
}
