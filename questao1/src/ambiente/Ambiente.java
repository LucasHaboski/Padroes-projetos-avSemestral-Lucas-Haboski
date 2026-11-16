package ambiente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fabrica.TabelaPrecos;
import recurso.Armazenamento;
import recurso.Fila;
import recurso.MaquinaVirtual;
import recurso.Recurso;

public final class Ambiente {

    private final String nome;
    private final String provedor;
    private final String regiao;
    private final List<MaquinaVirtual> maquinasVirtuais;
    private final Armazenamento armazenamento;
    private final Fila fila;

    public Ambiente(String nome, String regiao, List<MaquinaVirtual> maquinasVirtuais, Armazenamento armazenamento,
            Fila fila) {
        this.nome = nome;
        this.regiao = regiao;
        this.maquinasVirtuais = Collections.unmodifiableList(new ArrayList<>(maquinasVirtuais));
        this.armazenamento = armazenamento;
        this.fila = fila;
        this.provedor = validarProvedorUnico();
    }

    private String validarProvedorUnico() {
        List<Recurso> recursos = new ArrayList<>(maquinasVirtuais);
        recursos.add(armazenamento);
        recursos.add(fila);
        String provedorEsperado = recursos.get(0).getProvedor();
        for (Recurso recurso : recursos) {
            if (!provedorEsperado.equals(recurso.getProvedor())) {
                throw new IllegalArgumentException("Recursos de provedores diferentes no ambiente " + nome + ": "
                        + provedorEsperado + " e " + recurso.getProvedor());
            }
        }
        return provedorEsperado;
    }

    public String getNome() {
        return nome;
    }

    public String getProvedor() {
        return provedor;
    }

    public String getRegiao() {
        return regiao;
    }

    public List<MaquinaVirtual> getMaquinasVirtuais() {
        return maquinasVirtuais;
    }

    public Armazenamento getArmazenamento() {
        return armazenamento;
    }

    public Fila getFila() {
        return fila;
    }

    public double getCustoMensalEstimado() {
        double custoMaquinas = 0;
        for (MaquinaVirtual maquina : maquinasVirtuais) {
            custoMaquinas += maquina.getCustoPorHora() * TabelaPrecos.HORAS_NO_MES;
        }
        double custoArmazenamento = armazenamento.getCapacidadeGb() * armazenamento.getCustoPorGbMes();
        return custoMaquinas + custoArmazenamento + fila.getCustoFixoMensal();
    }

    public String gerarResumo() {
        StringBuilder resumo = new StringBuilder();
        resumo.append("----- Ambiente provisionado -----").append(System.lineSeparator());
        resumo.append("Nome: ").append(nome).append(System.lineSeparator());
        resumo.append("Provedor: ").append(provedor).append(System.lineSeparator());
        resumo.append("Regiao: ").append(regiao).append(System.lineSeparator());
        for (MaquinaVirtual maquina : maquinasVirtuais) {
            resumo.append("Maquina virtual: ").append(maquina.getIdentificador()).append(System.lineSeparator());
        }
        resumo.append("Armazenamento: ").append(armazenamento.getIdentificador())
                .append(" (").append(armazenamento.getCapacidadeGb()).append(" GB)").append(System.lineSeparator());
        resumo.append("Fila: ").append(fila.getIdentificador()).append(System.lineSeparator());
        resumo.append(String.format("Custo mensal estimado: R$ %.2f", getCustoMensalEstimado()))
                .append(System.lineSeparator());
        return resumo.toString();
    }
}
