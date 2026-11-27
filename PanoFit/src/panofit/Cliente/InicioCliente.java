package panofit.Cliente;

import static panofit.Cliente.EstiloCliente.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/** Tela inicial do cliente: resumo do plano, pagamento, treino de hoje e semana. */
public class InicioCliente extends JInternalFrame {

    private static final int MAX_EXERCICIOS_HOJE = 6;

    public InicioCliente(int idCliente) {
        semMoldura(this);
        JPanel raiz = new JPanel(new BorderLayout(0, 20));
        raiz.setBackground(FUNDO);
        raiz.setBorder(new EmptyBorder(28, 32, 28, 32));
        setContentPane(raiz);
        setPreferredSize(new Dimension(720, 620));

        ClienteInfo info;
        try {
            info = ClienteInfo.carregar(idCliente);
        } catch (Exception e) {
            raiz.add(subtitulo("Erro ao carregar seus dados: " + e.getMessage()), BorderLayout.NORTH);
            pack();
            return;
        }
        if (info == null) {
            raiz.add(subtitulo("Não foi possível carregar seus dados."), BorderLayout.NORTH);
            pack();
            return;
        }

        raiz.add(cabecalho(info), BorderLayout.NORTH);

        JPanel grade = new JPanel(new GridLayout(2, 2, 16, 16));
        grade.setOpaque(false);
        grade.add(cardPlano(info));
        grade.add(cardPagamento(info));
        grade.add(cardTreinoHoje(info));
        grade.add(cardSemana(info));
        raiz.add(grade, BorderLayout.CENTER);

        pack();
    }

    private JPanel cabecalho(ClienteInfo info) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);
        String nome = info.primeiroNome();
        p.add(titulo(nome.isEmpty() ? "Olá!" : "Olá, " + nome + "!"));
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy", PT_BR));
        p.add(Box.createVerticalStrut(4));
        p.add(subtitulo("Hoje é " + hoje + ". Confira abaixo o resumo da sua conta."));
        return p;
    }

    private JPanel cardPlano(ClienteInfo info) {
        JPanel c = card("Meu plano");
        c.add(destaque(info.assinatura, TEXTO));
        c.add(Box.createVerticalStrut(6));
        String valor = formatarValor(info.valor);
        c.add(valor(valor == null ? null : valor + (info.duracao == null ? "" : "  •  " + info.duracao)));
        c.add(Box.createVerticalGlue());
        c.add(rotulo("Para trocar de plano, fale com a recepção."));
        return c;
    }

    private JPanel cardPagamento(ClienteInfo info) {
        JPanel c = card("Pagamento");
        c.add(destaque(info.status, corStatus(info.status)));
        c.add(Box.createVerticalStrut(6));
        c.add(valor(mensagemStatus(info.status)));
        c.add(Box.createVerticalGlue());
        String valor = formatarValor(info.valor);
        c.add(rotulo(valor == null ? " " : "Valor da mensalidade: " + valor));
        return c;
    }

    private JPanel cardTreinoHoje(ClienteInfo info) {
        int hoje = ClienteInfo.indiceHoje();
        JPanel c = card("Treino de hoje · " + ClienteInfo.DIAS_NOME[hoje]);
        List<String[]> exercicios = info.treinos.get(ClienteInfo.DIAS[hoje]);
        if (exercicios.isEmpty()) {
            c.add(destaque("Descanso", TEXTO));
            c.add(Box.createVerticalStrut(6));
            c.add(valor("Nenhum exercício para hoje."));
            return c;
        }
        c.add(valor(exercicios.size() + (exercicios.size() == 1 ? " exercício" : " exercícios")));
        c.add(Box.createVerticalStrut(6));
        for (int i = 0; i < exercicios.size() && i < MAX_EXERCICIOS_HOJE; i++) {
            String[] e = exercicios.get(i);
            JLabel l = new JLabel("• " + ouTraco(e[0]) + "   " + ouTraco(e[1]) + " x " + ouTraco(e[2]));
            l.setFont(fonte(Font.PLAIN, 13));
            l.setForeground(TEXTO);
            c.add(esq(l));
        }
        if (exercicios.size() > MAX_EXERCICIOS_HOJE) {
            c.add(rotulo("+ " + (exercicios.size() - MAX_EXERCICIOS_HOJE) + " em \"Treino\""));
        }
        return c;
    }

    private JPanel cardSemana(ClienteInfo info) {
        JPanel c = card("Minha semana");
        int dias = info.diasComTreino();
        c.add(destaque(dias + (dias == 1 ? " dia de treino" : " dias de treino"), TEXTO));
        c.add(Box.createVerticalStrut(4));
        c.add(rotulo(info.totalExercicios() + " exercícios na ficha"));
        c.add(Box.createVerticalStrut(12));

        JPanel chips = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        chips.setOpaque(false);
        int hoje = ClienteInfo.indiceHoje();
        for (int i = 0; i < ClienteInfo.DIAS.length; i++) {
            boolean temTreino = !info.treinos.get(ClienteInfo.DIAS[i]).isEmpty();
            JLabel chip = new JLabel(ClienteInfo.DIAS_CURTO[i], SwingConstants.CENTER);
            chip.setOpaque(true);
            chip.setPreferredSize(new Dimension(36, 30));
            chip.setFont(fonte(Font.BOLD, 12));
            chip.setBackground(temTreino ? VERMELHO : CINZA_CHIP);
            chip.setForeground(temTreino ? Color.WHITE : TEXTO_SUAVE);
            chip.setBorder(i == hoje ? new LineBorder(TEXTO, 2) : BorderFactory.createEmptyBorder());
            chip.setToolTipText(ClienteInfo.DIAS_NOME[i] + (temTreino ? ": tem treino" : ": descanso"));
            chips.add(chip);
        }
        c.add(esq(chips));
        c.add(Box.createVerticalStrut(8));
        c.add(rotulo("Vermelho = dia com treino  •  contorno = hoje"));
        return c;
    }
}
