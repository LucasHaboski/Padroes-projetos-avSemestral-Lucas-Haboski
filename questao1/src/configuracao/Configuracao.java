package configuracao;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public final class Configuracao {

    private static final String ARQUIVO = "config.properties";

    private static final Configuracao INSTANCIA = new Configuracao();

    private final String regiaoPadrao;
    private final double orcamentoMensalMaximo;

    private Configuracao() {
        Properties propriedades = carregarArquivo();
        this.regiaoPadrao = propriedades.getProperty("regiao.padrao", "us-east-1");
        this.orcamentoMensalMaximo = Double.parseDouble(propriedades.getProperty("orcamento.mensal.maximo", "0"));
    }

    public static Configuracao getInstancia() {
        return INSTANCIA;
    }

    private static Properties carregarArquivo() {
        Properties propriedades = new Properties();
        try (InputStream entrada = abrirArquivo()) {
            propriedades.load(entrada);
        } catch (IOException e) {
            throw new IllegalStateException("Nao foi possivel ler o arquivo " + ARQUIVO, e);
        }
        return propriedades;
    }

    private static InputStream abrirArquivo() throws IOException {
        InputStream entrada = Configuracao.class.getClassLoader().getResourceAsStream(ARQUIVO);
        if (entrada != null) {
            return entrada;
        }
        return Files.newInputStream(Paths.get(ARQUIVO));
    }

    public String getRegiaoPadrao() {
        return regiaoPadrao;
    }

    public double getOrcamentoMensalMaximo() {
        return orcamentoMensalMaximo;
    }
}
