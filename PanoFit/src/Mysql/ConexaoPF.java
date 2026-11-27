package Mysql;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 * Conexão com o MySQL.
 *
 * As configurações (host, porta, banco, usuário e senha) ficam no arquivo
 * src/db.properties, que NÃO vai para o git. Use src/db.properties.example
 * como modelo.
 */
public class ConexaoPF {

    private static final String ARQUIVO_CONFIG = "/db.properties";

    private static Properties config;

    private static synchronized Properties carregarConfig() {
        if (config != null) {
            return config;
        }
        Properties p = new Properties();
        // valores padrão (ambiente de teste local)
        p.setProperty("db.host", "localhost");
        p.setProperty("db.porta", "3306");
        p.setProperty("db.banco", "panofit");
        p.setProperty("db.usuario", "root");
        p.setProperty("db.senha", "");

        try (InputStream in = ConexaoPF.class.getResourceAsStream(ARQUIVO_CONFIG)) {
            if (in != null) {
                p.load(in);
            } else {
                System.err.println("Aviso: db.properties não encontrado no classpath; usando valores padrão.");
            }
        } catch (Exception e) {
            System.err.println("Aviso: erro ao ler db.properties: " + e.getMessage());
        }
        config = p;
        return config;
    }

    public Connection conectar() {
        Properties p = carregarConfig();
        String url = "jdbc:mysql://" + p.getProperty("db.host") + ":" + p.getProperty("db.porta")
                + "/" + p.getProperty("db.banco")
                + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Sao_Paulo";

        try {
            Connection conn = DriverManager.getConnection(url, p.getProperty("db.usuario"), p.getProperty("db.senha"));
            System.out.println("Conexão com o banco de dados realizada com sucesso!");
            return conn;
        } catch (SQLException erro) {
            String dica = "";
            if (erro.getErrorCode() == 1045) {
                dica = "\n\nUsuário ou senha incorretos. Confira db.usuario e db.senha em src/db.properties.";
            } else if (erro.getErrorCode() == 1049) {
                dica = "\n\nO banco '" + p.getProperty("db.banco") + "' não existe. Rode database/database.sql no Workbench.";
            } else if (erro.getSQLState() != null && erro.getSQLState().startsWith("08")) {
                dica = "\n\nNão foi possível falar com o MySQL. Verifique se o serviço MySQL84 está rodando.";
            }
            JOptionPane.showMessageDialog(null, "Erro na Conexão com o Banco: " + erro.getMessage() + dica);
            return null;
        }
    }
}
