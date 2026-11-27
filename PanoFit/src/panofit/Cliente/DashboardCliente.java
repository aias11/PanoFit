package panofit.Cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import panofit.Login;

/** Janela principal do cliente: menu lateral + área onde as telas (Início, Treino, Dados) são abertas. */
public class DashboardCliente extends JFrame {

    private static final Color FUNDO_MENU = new Color(17, 17, 17);
    private static final Color ITEM_NORMAL = new Color(17, 17, 17);
    private static final Color ITEM_HOVER = new Color(40, 40, 40);
    private static final Color ITEM_ATIVO = new Color(215, 25, 32);

    private final int idLogado;
    private final JDesktopPane areaConteudo = new JDesktopPane();
    private JPanel itemInicio;
    private JPanel itemTreinos;
    private JPanel itemDados;
    private JPanel itemAtivo;

    public DashboardCliente(int idCliente) {
        this.idLogado = idCliente;
        setTitle("PanoFit - Área do Cliente");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel janela = new JPanel(new BorderLayout());
        janela.add(criarMenu(), BorderLayout.WEST);

        areaConteudo.setBackground(Color.WHITE);
        areaConteudo.setPreferredSize(new Dimension(760, 620));
        // Quando a janela muda de tamanho, a tela aberta acompanha
        areaConteudo.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                for (JInternalFrame f : areaConteudo.getAllFrames()) {
                    f.setBounds(0, 0, areaConteudo.getWidth(), areaConteudo.getHeight());
                }
            }
        });
        janela.add(areaConteudo, BorderLayout.CENTER);

        setContentPane(janela);
        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);

        // Abre o resumo assim que a janela aparece
        SwingUtilities.invokeLater(() -> selecionar(itemInicio, new InicioCliente(idLogado)));
    }

    private JPanel criarMenu() {
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(FUNDO_MENU);
        menu.setPreferredSize(new Dimension(220, 620));
        menu.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        JLabel marca = new JLabel("PANOFIT", SwingConstants.CENTER);
        marca.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
        marca.setForeground(Color.WHITE);
        marca.setAlignmentX(CENTER_ALIGNMENT);
        menu.add(marca);

        JLabel area = new JLabel("Área do cliente", SwingConstants.CENTER);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        area.setForeground(new Color(170, 170, 170));
        area.setAlignmentX(CENTER_ALIGNMENT);
        menu.add(area);
        menu.add(Box.createVerticalStrut(50));

        itemInicio = criarItem("Início", () -> new InicioCliente(idLogado));
        itemTreinos = criarItem("Treino", () -> new TreinosCliente(idLogado));
        itemDados = criarItem("Mostrar Dados", () -> new MostrarDados(idLogado));
        menu.add(itemInicio);
        menu.add(Box.createVerticalStrut(8));
        menu.add(itemTreinos);
        menu.add(Box.createVerticalStrut(8));
        menu.add(itemDados);

        menu.add(Box.createVerticalGlue());

        JButton sair = new JButton("Sair");
        sair.setBackground(new Color(153, 0, 0));
        sair.setForeground(Color.WHITE);
        sair.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        sair.setFocusPainted(false);
        sair.setAlignmentX(CENTER_ALIGNMENT);
        sair.setMaximumSize(new Dimension(120, 42));
        sair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sair.addActionListener(e -> sair());
        menu.add(sair);
        return menu;
    }

    private JPanel criarItem(String texto, java.util.function.Supplier<JInternalFrame> tela) {
        JPanel item = new JPanel(new BorderLayout());
        item.setBackground(ITEM_NORMAL);
        item.setMaximumSize(new Dimension(Integer.MAX_VALUE, 56));
        item.setPreferredSize(new Dimension(220, 56));
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        label.setForeground(Color.WHITE);
        item.add(label, BorderLayout.CENTER);

        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selecionar(item, tela.get());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (item != itemAtivo) {
                    item.setBackground(ITEM_HOVER);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (item != itemAtivo) {
                    item.setBackground(ITEM_NORMAL);
                }
            }
        });
        return item;
    }

    private void selecionar(JPanel item, JInternalFrame tela) {
        for (JPanel p : new JPanel[]{itemInicio, itemTreinos, itemDados}) {
            p.setBackground(ITEM_NORMAL);
        }
        item.setBackground(ITEM_ATIVO);
        itemAtivo = item;

        areaConteudo.removeAll();
        areaConteudo.add(tela);
        tela.setBounds(0, 0, areaConteudo.getWidth(), areaConteudo.getHeight());
        tela.setVisible(true);
        areaConteudo.revalidate();
        areaConteudo.repaint();
    }

    private void sair() {
        Login loginFrame = new Login();
        loginFrame.setVisible(true);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        this.dispose();
    }
}
