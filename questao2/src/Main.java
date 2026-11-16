import comissao.ComissaoEletronicos;
import fatura.CertificadoAutenticidade;
import fatura.EmbalagemEspecial;
import fatura.EntregaExpressa;
import fatura.Fatura;
import fatura.SeguroTransporte;
import leilao.AcertoVendedor;
import leilao.Categoria;
import leilao.Lote;
import observador.Auditor;
import observador.PainelLeiloeiro;
import observador.Participante;

public class Main {

    public static void main(String[] args) {
        Lote quadro = new Lote("Quadro Abaporu", Categoria.ARTE, 10000.00, 15000.00);
        Lote fusca = new Lote("Fusca 1970", Categoria.VEICULOS, 20000.00, 60000.00);

        Participante ana = new Participante("Ana");
        Participante bruno = new Participante("Bruno");
        Participante carla = new Participante("Carla");
        PainelLeiloeiro painel = new PainelLeiloeiro();
        Auditor auditor = new Auditor();

        System.out.println("===== ACOMPANHAMENTO =====");
        for (Lote lote : new Lote[] { quadro, fusca }) {
            lote.adicionarObservador(painel);
            lote.adicionarObservador(auditor);
            ana.acompanhar(lote);
            bruno.acompanhar(lote);
            carla.acompanhar(lote);
        }
        System.out.println();

        System.out.println("===== LANCES - " + quadro.getNome() + " =====");
        ana.darLance(quadro, 10500.00);
        bruno.darLance(quadro, 10800.00);
        bruno.darLance(quadro, 11500.00);
        carla.deixarDeAcompanhar(quadro);
        carla.darLance(quadro, 12500.00);
        ana.darLance(quadro, 14000.00);
        bruno.darLance(quadro, 15500.00);
        System.out.println();

        System.out.println("===== LANCES - " + fusca.getNome() + " =====");
        bruno.darLance(fusca, 21000.00);
        carla.darLance(fusca, 22000.00);
        carla.darLance(fusca, 23000.00);
        System.out.println();

        System.out.println("===== CAMPANHA =====");
        quadro.setRegraComissao(new ComissaoEletronicos());
        System.out.println();

        System.out.println("===== ENCERRAMENTO =====");
        quadro.encerrar();
        fusca.encerrar();
        ana.darLance(quadro, 20000.00);
        System.out.println();

        new AcertoVendedor(quadro).imprimir();

        Fatura fatura = new Fatura(quadro);
        fatura.contratar(new SeguroTransporte());
        fatura.contratar(new CertificadoAutenticidade());
        fatura.contratar(new EmbalagemEspecial());
        fatura.contratar(new EmbalagemEspecial());
        fatura.contratar(new EntregaExpressa());
        fatura.imprimir();

        auditor.imprimirRegistros();
    }
}
