package panofit.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import Mysql.ConexaoPF;

/** Dados do cliente logado (cadastro, plano e ficha de treino), carregados de uma vez do banco. */
final class ClienteInfo {

    /** Chaves dos dias como são gravadas em fichas_treino.dia_semana (sem acento). */
    static final String[] DIAS = {"Segunda", "Terca", "Quarta", "Quinta", "Sexta", "Sabado", "Domingo"};
    static final String[] DIAS_NOME = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo"};
    static final String[] DIAS_CURTO = {"Seg", "Ter", "Qua", "Qui", "Sex", "Sáb", "Dom"};

    String nome;
    String cpf;
    String telefone;
    String dataNascimento;
    String genero;
    String assinatura;
    String valor;
    String status;
    String duracao;

    /** Exercícios por dia (chave de DIAS). Cada item: {exercicio, series, repeticoes}. */
    final Map<String, List<String[]>> treinos = new LinkedHashMap<>();

    private ClienteInfo() {
        for (String dia : DIAS) {
            treinos.put(dia, new ArrayList<>());
        }
    }

    /**
     * Carrega o cliente pelo id. Retorna null se não conseguir conectar (a ConexaoPF já mostra o erro)
     * ou se o cliente não existir.
     */
    static ClienteInfo carregar(int idCliente) throws SQLException {
        Connection conn = new ConexaoPF().conectar();
        if (conn == null) {
            return null;
        }
        try (conn) {
            ClienteInfo info = new ClienteInfo();

            String sqlCliente = "SELECT c.nome, c.cpf, c.telefone, c.data_nascimento, c.genero, c.assinatura, c.valor, c.status, "
                    + "(SELECT a.duracao FROM assinaturas a WHERE a.nome_assinatura = c.assinatura LIMIT 1) AS duracao "
                    + "FROM clientes c WHERE c.id = ?";
            try (PreparedStatement pst = conn.prepareStatement(sqlCliente)) {
                pst.setInt(1, idCliente);
                try (ResultSet rs = pst.executeQuery()) {
                    if (!rs.next()) {
                        return null;
                    }
                    info.nome = rs.getString("nome");
                    info.cpf = rs.getString("cpf");
                    info.telefone = rs.getString("telefone");
                    info.dataNascimento = rs.getString("data_nascimento");
                    info.genero = rs.getString("genero");
                    info.assinatura = rs.getString("assinatura");
                    info.valor = rs.getString("valor");
                    info.status = rs.getString("status");
                    info.duracao = rs.getString("duracao");
                }
            }

            String sqlTreino = "SELECT dia_semana, exercicio, series, repeticoes FROM fichas_treino WHERE cliente_id = ? ORDER BY id";
            try (PreparedStatement pst = conn.prepareStatement(sqlTreino)) {
                pst.setInt(1, idCliente);
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        List<String[]> lista = info.treinos.get(normalizarDia(rs.getString("dia_semana")));
                        if (lista != null) {
                            lista.add(new String[]{rs.getString("exercicio"), rs.getString("series"), rs.getString("repeticoes")});
                        }
                    }
                }
            }
            return info;
        }
    }

    String primeiroNome() {
        if (nome == null || nome.isBlank()) {
            return "";
        }
        return nome.trim().split("\\s+")[0];
    }

    int diasComTreino() {
        int n = 0;
        for (List<String[]> l : treinos.values()) {
            if (!l.isEmpty()) {
                n++;
            }
        }
        return n;
    }

    int totalExercicios() {
        int n = 0;
        for (List<String[]> l : treinos.values()) {
            n += l.size();
        }
        return n;
    }

    /** "Terça", "terca", "Sábado-feira" etc. viram a chave sem acento usada em DIAS. */
    static String normalizarDia(String dia) {
        if (dia == null) {
            return "";
        }
        String s = Normalizer.normalize(dia.trim(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        int traco = s.indexOf('-');
        if (traco > 0) {
            s = s.substring(0, traco);
        }
        if (s.isEmpty()) {
            return s;
        }
        return Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
    }

    /** Índice (0 = segunda ... 6 = domingo) do dia de hoje. */
    static int indiceHoje() {
        return LocalDate.now().getDayOfWeek().getValue() - 1;
    }
}
