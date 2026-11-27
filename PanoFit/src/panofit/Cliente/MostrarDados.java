package panofit.Cliente;

import static panofit.Cliente.EstiloCliente.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/** Tela "Mostrar Dados": o cliente confere seu cadastro, plano e situação do pagamento. */
public class MostrarDados extends JInternalFrame {

    public MostrarDados(int idCliente) {
        semMoldura(this);
        JPanel raiz = new JPanel(new BorderLayout(0, 20));
        raiz.setBackground(FUNDO);
        raiz.setBorder(new EmptyBorder(28, 32, 28, 32));
        setContentPane(raiz);
        setPreferredSize(new Dimension(720, 620));

        JPanel topo = new JPanel();
        topo.setLayout(new BoxLayout(topo, BoxLayout.Y_AXIS));
        topo.setOpaque(false);
        topo.add(titulo("Meus dados"));
        topo.add(Box.createVerticalStrut(4));
        topo.add(subtitulo("Confira se suas informações estão corretas. Se algo estiver errado, fale com a recepção."));
        raiz.add(topo, BorderLayout.NORTH);

        ClienteInfo info;
        try {
            info = ClienteInfo.carregar(idCliente);
        } catch (Exception e) {
            raiz.add(subtitulo("Erro ao carregar dados: " + e.getMessage()), BorderLayout.CENTER);
            pack();
            return;
        }
        if (info == null) {
            raiz.add(subtitulo("Cliente não encontrado."), BorderLayout.CENTER);
            pack();
            return;
        }

        JPanel pessoais = card("Dados pessoais");
        pessoais.add(tabela(
                new String[]{"Nome", "CPF", "Telefone", "Data de nascimento", "Gênero"},
                new JLabel[]{
                    valor(info.nome),
                    valor(formatarCpf(info.cpf)),
                    valor(info.telefone),
                    valor(info.dataNascimento),
                    valor(info.genero)}));

        JLabel status = valor(info.status);
        status.setForeground(corStatus(info.status));
        JPanel plano = card("Plano e pagamento");
        plano.add(tabela(
                new String[]{"Plano", "Valor", "Duração", "Status"},
                new JLabel[]{
                    valor(info.assinatura),
                    valor(formatarValor(info.valor)),
                    valor(info.duracao),
                    status}));
        plano.add(Box.createVerticalStrut(10));
        plano.add(rotulo(mensagemStatus(info.status)));

        JPanel grade = new JPanel(new GridLayout(1, 2, 16, 0));
        grade.setOpaque(false);
        grade.add(pessoais);
        grade.add(plano);

        // Mantém os cartões no topo em vez de esticá-los até o fim da tela
        JPanel centro = new JPanel(new BorderLayout());
        centro.setOpaque(false);
        centro.add(grade, BorderLayout.NORTH);
        raiz.add(centro, BorderLayout.CENTER);

        pack();
    }

    /** Linhas "rótulo  valor" alinhadas em duas colunas. */
    private static JPanel tabela(String[] rotulos, JLabel[] valores) {
        JPanel p = new JPanel(new GridBagLayout());
        p.setOpaque(false);
        GridBagConstraints g = new GridBagConstraints();
        g.anchor = GridBagConstraints.WEST;
        for (int i = 0; i < rotulos.length; i++) {
            g.gridy = i;
            g.gridx = 0;
            g.weightx = 0;
            g.insets = new Insets(6, 0, 6, 16);
            p.add(rotulo(rotulos[i]), g);
            g.gridx = 1;
            g.weightx = 1;
            g.insets = new Insets(6, 0, 6, 0);
            p.add(valores[i], g);
        }
        return esq(p);
    }
}
