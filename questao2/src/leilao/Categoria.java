package leilao;

import comissao.ComissaoArte;
import comissao.ComissaoEletronicos;
import comissao.ComissaoImoveis;
import comissao.ComissaoVeiculos;
import comissao.RegraComissao;

public enum Categoria {
    ARTE("Arte", new ComissaoArte()),
    VEICULOS("Veiculos", new ComissaoVeiculos()),
    IMOVEIS("Imoveis", new ComissaoImoveis()),
    ELETRONICOS("Eletronicos", new ComissaoEletronicos());

    private final String descricao;
    private final RegraComissao regraComissaoPadrao;

    Categoria(String descricao, RegraComissao regraComissaoPadrao) {
        this.descricao = descricao;
        this.regraComissaoPadrao = regraComissaoPadrao;
    }

    public String getDescricao() {
        return descricao;
    }

    public RegraComissao getRegraComissaoPadrao() {
        return regraComissaoPadrao;
    }
}
