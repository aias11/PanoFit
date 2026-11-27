package panofit.Cliente;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/** Cores, fontes e componentes reutilizados nas telas do cliente. */
final class EstiloCliente {

    static final Color FUNDO = Color.WHITE;
    static final Color CARD = new Color(247, 247, 247);
    static final Color BORDA = new Color(226, 226, 226);
    static final Color TEXTO = new Color(17, 17, 17);
    static final Color TEXTO_SUAVE = new Color(115, 115, 115);
    static final Color VERMELHO = new Color(215, 25, 32);
    static final Color VERDE = new Color(28, 140, 70);
    static final Color CINZA_CHIP = new Color(230, 230, 230);

    static final Locale PT_BR = Locale.forLanguageTag("pt-BR");

    private EstiloCliente() {
    }

    static Font fonte(int estilo, int tamanho) {
        return new Font("Segoe UI", estilo, tamanho);
    }

    /** Remove a barra de título e a borda do JInternalFrame (para ficar "embutido" no dashboard). */
    static void semMoldura(JInternalFrame frame) {
        frame.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI) frame.getUI()).setNorthPane(null);
    }

    static JLabel titulo(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(fonte(Font.BOLD, 24));
        l.setForeground(TEXTO);
        return esq(l);
    }

    static JLabel subtitulo(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(fonte(Font.PLAIN, 13));
        l.setForeground(TEXTO_SUAVE);
        return esq(l);
    }

    static JLabel rotulo(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(fonte(Font.PLAIN, 12));
        l.setForeground(TEXTO_SUAVE);
        return esq(l);
    }

    static JLabel valor(String texto) {
        JLabel l = new JLabel(ouTraco(texto));
        l.setFont(fonte(Font.BOLD, 15));
        l.setForeground(TEXTO);
        return esq(l);
    }

    static JLabel destaque(String texto, Color cor) {
        JLabel l = new JLabel(ouTraco(texto));
        l.setFont(fonte(Font.BOLD, 22));
        l.setForeground(cor);
        return esq(l);
    }

    /** Cartão cinza com faixa vermelha à esquerda e título em caixa alta. Adicione o conteúdo com add(). */
    static JPanel card(String titulo) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(CARD);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(new MatteBorder(0, 4, 0, 0, VERMELHO), new LineBorder(BORDA)),
                new EmptyBorder(14, 16, 14, 16)));
        JLabel t = new JLabel(titulo.toUpperCase(PT_BR));
        t.setFont(fonte(Font.BOLD, 12));
        t.setForeground(TEXTO_SUAVE);
        p.add(esq(t));
        p.add(javax.swing.Box.createVerticalStrut(8));
        return esq(p);
    }

    static <T extends JComponent> T esq(T c) {
        c.setAlignmentX(Component.LEFT_ALIGNMENT);
        return c;
    }

    static String ouTraco(String s) {
        return (s == null || s.isBlank()) ? "—" : s.trim();
    }

    static String formatarCpf(String cpf) {
        if (cpf == null) {
            return null;
        }
        String d = cpf.replaceAll("\\D", "");
        if (d.length() != 11) {
            return cpf;
        }
        return d.substring(0, 3) + "." + d.substring(3, 6) + "." + d.substring(6, 9) + "-" + d.substring(9);
    }

    static String formatarValor(String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }
        try {
            double v = Double.parseDouble(valor.trim().replace("R$", "").trim().replace(",", "."));
            return NumberFormat.getCurrencyInstance(PT_BR).format(v);
        } catch (NumberFormatException e) {
            return valor;
        }
    }

    static Color corStatus(String status) {
        if (status == null) {
            return TEXTO_SUAVE;
        }
        if (status.equalsIgnoreCase("Pago")) {
            return VERDE;
        }
        if (status.equalsIgnoreCase("Pendente")) {
            return VERMELHO;
        }
        return TEXTO;
    }

    static String mensagemStatus(String status) {
        if (status != null && status.equalsIgnoreCase("Pago")) {
            return "Sua mensalidade está em dia.";
        }
        if (status != null && status.equalsIgnoreCase("Pendente")) {
            return "Procure a recepção para regularizar.";
        }
        return "Status não informado.";
    }
}
