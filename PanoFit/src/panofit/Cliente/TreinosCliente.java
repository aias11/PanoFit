/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package panofit.Cliente;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import Mysql.ConexaoPF;

public class TreinosCliente extends javax.swing.JInternalFrame {

    private int idCliente;
    
    public TreinosCliente(int idRecebido) {
         initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null); 
        this.idCliente = idRecebido;

        tabelas = new javax.swing.JTable[]{
            tblTreinoCliente, tblTreinoCliente1, tblTreinoCliente2, tblTreinoCliente3,
            tblTreinoCliente4, tblTreinoCliente5, tblTreinoCliente6};
        estilizarTabelas();
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        // Layout que se ajusta à área do dashboard: título no topo, abas ocupando o meio, botão embaixo
        javax.swing.JPanel topo = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        topo.setOpaque(false);
        topo.add(jPanel2);

        jButton1.setPreferredSize(new java.awt.Dimension(200, 50));
        jButton1.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        javax.swing.JPanel rodape = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        rodape.setOpaque(false);
        rodape.add(jButton1);

        javax.swing.JPanel centro = new javax.swing.JPanel(new java.awt.BorderLayout(0, 16));
        centro.setBackground(java.awt.Color.WHITE);
        centro.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 32, 28, 32));
        centro.add(topo, java.awt.BorderLayout.NORTH);
        centro.add(jTabbedPane1, java.awt.BorderLayout.CENTER);
        centro.add(rodape, java.awt.BorderLayout.SOUTH);
        setContentPane(centro);

        carregarTodosOsTreinos();
    }

    private final javax.swing.JTable[] tabelas;
    private ClienteInfo info;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTreinoCliente = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTreinoCliente1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTreinoCliente2 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblTreinoCliente3 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblTreinoCliente4 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblTreinoCliente5 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblTreinoCliente6 = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("MEUS");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(17, 17, 17));
        jLabel2.setText("TREINOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(45, 45, 45))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(21, 21, 21))
        );

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setText("FINALIZAR TREINO");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red));
        jButton1.addActionListener(this::jButton1ActionPerformed);

        tblTreinoCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Exercicios", "Series", "Repetições"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblTreinoCliente);

        jTabbedPane1.addTab("tab8", jScrollPane2);

        tblTreinoCliente1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Exercicios", "Series", "Repetições"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblTreinoCliente1);

        jTabbedPane1.addTab("tab8", jScrollPane3);

        tblTreinoCliente2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Exercicios", "Series", "Repetições"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblTreinoCliente2);

        jTabbedPane1.addTab("tab8", jScrollPane4);

        tblTreinoCliente3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Exercicios", "Series", "Repetições"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblTreinoCliente3);

        jTabbedPane1.addTab("tab8", jScrollPane5);

        tblTreinoCliente4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Exercicios", "Series", "Repetições"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblTreinoCliente4);

        jTabbedPane1.addTab("tab8", jScrollPane6);

        tblTreinoCliente5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Exercicios", "Series", "Repetições"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tblTreinoCliente5);

        jTabbedPane1.addTab("tab8", jScrollPane7);

        tblTreinoCliente6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Exercicios", "Series", "Repetições"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tblTreinoCliente6);

        jTabbedPane1.addTab("tab8", jScrollPane8);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addGap(33, 33, 33)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void estilizarTabelas() {
        for (javax.swing.JTable t : tabelas) {
            t.setRowHeight(30);
            t.setFillsViewportHeight(true);
            t.setBackground(java.awt.Color.WHITE);
            t.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
            t.setShowVerticalLines(false);
            t.setGridColor(new java.awt.Color(230, 230, 230));
            t.setSelectionBackground(new java.awt.Color(250, 220, 222));
            t.setSelectionForeground(java.awt.Color.BLACK);
            t.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
            t.getTableHeader().setReorderingAllowed(false);
            t.getColumnModel().getColumn(0).setPreferredWidth(260);
        }
    }

    /** Carrega a ficha inteira de uma vez, preenche as 7 abas e abre na aba de hoje. */
    private void carregarTodosOsTreinos() {
        try {
            info = ClienteInfo.carregar(this.idCliente);
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erro ao carregar treinos: " + e.getMessage());
            return;
        }
        if (info == null) {
            return;
        }

        int hoje = ClienteInfo.indiceHoje();
        for (int i = 0; i < ClienteInfo.DIAS.length; i++) {
            java.util.List<String[]> exercicios = info.treinos.get(ClienteInfo.DIAS[i]);
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tabelas[i].getModel();
            model.setRowCount(0);
            if (exercicios.isEmpty()) {
                model.addRow(new Object[]{"Descanso - nenhum exercício neste dia", "", ""});
            } else {
                for (String[] e : exercicios) {
                    model.addRow(new Object[]{e[0], e[1], e[2]});
                }
            }

            String titulo = ClienteInfo.DIAS_CURTO[i] + " (" + exercicios.size() + ")";
            jTabbedPane1.setTitleAt(i, i == hoje ? "<html><b>Hoje · " + titulo + "</b></html>" : titulo);
            jTabbedPane1.setToolTipTextAt(i, ClienteInfo.DIAS_NOME[i] + ": " + exercicios.size() + " exercício(s)");
        }
        jTabbedPane1.setSelectedIndex(hoje);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (info == null) {
            return;
        }
        int aba = jTabbedPane1.getSelectedIndex();
        int qtd = info.treinos.get(ClienteInfo.DIAS[aba]).size();
        String dia = ClienteInfo.DIAS_NOME[aba];
        if (qtd == 0) {
            javax.swing.JOptionPane.showMessageDialog(this, dia + " é dia de descanso. Não há treino para finalizar.");
            return;
        }
        javax.swing.JOptionPane.showMessageDialog(this,
                "Treino de " + dia + " finalizado!\n" + qtd + " exercício(s) concluído(s). Bom trabalho!",
                "Treino finalizado", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblTreinoCliente;
    private javax.swing.JTable tblTreinoCliente1;
    private javax.swing.JTable tblTreinoCliente2;
    private javax.swing.JTable tblTreinoCliente3;
    private javax.swing.JTable tblTreinoCliente4;
    private javax.swing.JTable tblTreinoCliente5;
    private javax.swing.JTable tblTreinoCliente6;
    // End of variables declaration//GEN-END:variables
}
