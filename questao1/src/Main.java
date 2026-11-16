import ambiente.OrcamentoExcedidoException;
import ambiente.ProvisionadorAmbiente;
import ambiente.SolicitacaoAmbiente;
import auditoria.HistoricoAuditoria;
import aws.AwsFactory;
import azure.AzureFactory;
import configuracao.Configuracao;
import fabrica.ProvedorFactory;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Configuracao configuracao = Configuracao.getInstancia();
        System.out.println("Regiao padrao: " + configuracao.getRegiaoPadrao());
        System.out.println(String.format("Orcamento mensal maximo: R$ %.2f", configuracao.getOrcamentoMensalMaximo()));
        System.out.println();

        SolicitacaoAmbiente producaoAws = new SolicitacaoAmbiente("producao", "Aws", 2, 100);
        SolicitacaoAmbiente analyticsAzure = new SolicitacaoAmbiente("analytics", "Azure", 4, 200);
        SolicitacaoAmbiente homologacaoAws = new SolicitacaoAmbiente("homologacao", "Aws", 3, 50);
        SolicitacaoAmbiente dataLakeAzure = new SolicitacaoAmbiente("datalake", "Azure", 10, 500);

        Thread threadProducao = new Thread(() -> provisionar(producaoAws), "thread-producao");
        Thread threadAnalytics = new Thread(() -> provisionar(analyticsAzure), "thread-analytics");

        threadProducao.start();
        threadAnalytics.start();
        threadProducao.join();
        threadAnalytics.join();

        provisionar(homologacaoAws);
        provisionar(dataLakeAzure);

        System.out.println();
        HistoricoAuditoria.getInstancia().imprimir();
    }

    private static void provisionar(SolicitacaoAmbiente solicitacao) {
        ProvedorFactory fabrica = escolherProvedor(solicitacao.getProvedor());
        ProvisionadorAmbiente provisionador = new ProvisionadorAmbiente(fabrica);
        try {
            provisionador.provisionar(solicitacao);
        } catch (OrcamentoExcedidoException e) {
            System.out.println("----- Provisionamento recusado -----");
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    private static ProvedorFactory escolherProvedor(String provedor) {
        switch (provedor) {
            case "Aws":
                return new AwsFactory();
            case "Azure":
                return new AzureFactory();
            default:
                throw new IllegalArgumentException("Provedor nao suportado: " + provedor);
        }
    }
}
