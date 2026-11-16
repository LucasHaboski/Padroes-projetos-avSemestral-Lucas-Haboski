package ambiente;

import java.util.ArrayList;
import java.util.List;

import auditoria.HistoricoAuditoria;
import auditoria.ResultadoProvisionamento;
import configuracao.Configuracao;
import fabrica.ProvedorFactory;
import fabrica.TabelaPrecos;
import recurso.Armazenamento;
import recurso.Fila;
import recurso.MaquinaVirtual;

public class ProvisionadorAmbiente {

    private final ProvedorFactory fabrica;

    public ProvisionadorAmbiente(ProvedorFactory fabrica) {
        this.fabrica = fabrica;
    }

    public Ambiente provisionar(SolicitacaoAmbiente solicitacao) throws OrcamentoExcedidoException {
        Configuracao configuracao = Configuracao.getInstancia();
        HistoricoAuditoria historico = HistoricoAuditoria.getInstancia();
        String provedor = fabrica.getNomeProvedor();

        TabelaPrecos precos = fabrica.getTabelaPrecos();
        double custoEstimado = precos.estimarCustoMensal(solicitacao.getQuantidadeMaquinasVirtuais(),
                solicitacao.getGbArmazenamento());

        if (custoEstimado > configuracao.getOrcamentoMensalMaximo()) {
            OrcamentoExcedidoException excecao = new OrcamentoExcedidoException(solicitacao.getNomeAmbiente(),
                    custoEstimado, configuracao.getOrcamentoMensalMaximo());
            historico.registrar(solicitacao.getNomeAmbiente(), provedor, ResultadoProvisionamento.RECUSADO,
                    excecao.getMessage());
            throw excecao;
        }

        List<MaquinaVirtual> maquinas = new ArrayList<>();
        for (int i = 0; i < solicitacao.getQuantidadeMaquinasVirtuais(); i++) {
            maquinas.add(fabrica.criarMaquinaVirtual());
        }
        Armazenamento armazenamento = fabrica.criarArmazenamento(solicitacao.getGbArmazenamento());
        Fila fila = fabrica.criarFila();

        Ambiente ambiente = new Ambiente(solicitacao.getNomeAmbiente(), configuracao.getRegiaoPadrao(), maquinas,
                armazenamento, fila);

        historico.registrar(ambiente.getNome(), ambiente.getProvedor(), ResultadoProvisionamento.ACEITO,
                String.format("custo estimado R$ %.2f", ambiente.getCustoMensalEstimado()));
        System.out.print(ambiente.gerarResumo());
        return ambiente;
    }
}
