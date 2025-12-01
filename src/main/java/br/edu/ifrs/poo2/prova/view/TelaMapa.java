package br.edu.ifrs.poo2.prova.view;

import br.edu.ifrs.poo2.prova.dao.RotaDAO;
import br.edu.ifrs.poo2.prova.entity.PontoDeRota;
import br.edu.ifrs.poo2.prova.entity.Rota;
import br.edu.ifrs.poo2.prova.util.ImportadorGPX;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

public class TelaMapa extends javax.swing.JFrame {

    private JMapViewer mapViewer;
    private RotaDAO rotaDAO;
    private Preferences prefs;

    public TelaMapa() {
        initComponents(); // Inicia componentes visuais
        this.rotaDAO = new RotaDAO();
        this.prefs = Preferences.userNodeForPackage(TelaMapa.class); 
        
        configurarMapa();       
        carregarListaRotas();   
        setLocationRelativeTo(null); 
    }

    private void configurarMapa() {
        mapViewer = new JMapViewer();
        pnMapa.setLayout(new BorderLayout());
        pnMapa.add(mapViewer, BorderLayout.CENTER);
    }

    private void carregarListaRotas() {
        DefaultListModel<Rota> model = new DefaultListModel<>();
        List<Rota> rotas = rotaDAO.listarTodas();
        if (rotas != null) {
            for (Rota r : rotas) {
                model.addElement(r);
            }
        }
        lstRotas.setModel(model);
    }

    private void desenharRota(Rota rota) {
        mapViewer.removeAllMapPolygons(); 
        if (rota.getPontos().isEmpty()) return;

        List<Coordinate> coordenadas = new ArrayList<>();
        double distTotal = 0;
        double ganhoElev = 0;
        double perdaElev = 0;
        PontoDeRota anterior = null;

        for (PontoDeRota p : rota.getPontos()) {
            coordenadas.add(new Coordinate(p.getLatitude(), p.getLongitude()));

            if (anterior != null) {
                double dist = Math.sqrt(Math.pow(p.getLatitude() - anterior.getLatitude(), 2) 
                                      + Math.pow(p.getLongitude() - anterior.getLongitude(), 2));
                distTotal += dist;

                double diffEle = p.getElevacao() - anterior.getElevacao();
                if (diffEle > 0) ganhoElev += diffEle;
                else perdaElev += Math.abs(diffEle);
            }
            anterior = p;
        }

        if (coordenadas.size() > 1) {
            MapPolygonImpl poly = new MapPolygonImpl(coordenadas);
            poly.setColor(Color.RED);
            poly.setStroke(new java.awt.BasicStroke(4)); 
            mapViewer.addMapPolygon(poly);
            mapViewer.setDisplayToFitMapPolygons(); 
        }
        
        lblDistancia.setText(String.format("Distância (aprox): %.2f km", distTotal * 111)); 
        lblGanho.setText(String.format("Ganho Elev: %.2f m", ganhoElev));
        lblPerda.setText(String.format("Perda Elev: %.2f m", perdaElev));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnImportar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstRotas = new javax.swing.JList<>();
        lblDistancia = new javax.swing.JLabel();
        lblGanho = new javax.swing.JLabel();
        lblPerda = new javax.swing.JLabel();
        pnMapa = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnImportar.setBackground(new java.awt.Color(204, 255, 204));
        btnImportar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnImportar.setText("Importar");
        btnImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarActionPerformed(evt);
            }
        });

        lstRotas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstRotasValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstRotas);

        lblDistancia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDistancia.setText("Distancia:");

        lblGanho.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGanho.setText("Ganho:");

        lblPerda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPerda.setText("Perda:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnImportar)
                    .addComponent(lblPerda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGanho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDistancia, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDistancia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGanho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPerda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addComponent(btnImportar)
                .addContainerGap())
        );

        pnMapa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnMapaLayout = new javax.swing.GroupLayout(pnMapa);
        pnMapa.setLayout(pnMapaLayout);
        pnMapaLayout.setHorizontalGroup(
            pnMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 671, Short.MAX_VALUE)
        );
        pnMapaLayout.setVerticalGroup(
            pnMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("MS PRO");

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("CÁLCULO DE ROTA ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(7, 7, 7)
                .addComponent(pnMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarActionPerformed
       String lastPath = prefs.get("LAST_GPX_PATH", System.getProperty("user.home"));
        JFileChooser chooser = new JFileChooser(lastPath);
        chooser.setFileFilter(new FileNameExtensionFilter("Arquivos GPX", "gpx"));
        
        int res = chooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            File arquivo = chooser.getSelectedFile();
            prefs.put("LAST_GPX_PATH", arquivo.getParent());
            try {
                ImportadorGPX.importar(arquivo);
                JOptionPane.showMessageDialog(this, "Sucesso!");
                carregarListaRotas();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnImportarActionPerformed

    private void lstRotasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstRotasValueChanged
        if (!evt.getValueIsAdjusting()) {
            Rota selecionada = lstRotas.getSelectedValue();
            if (selecionada != null) {
                desenharRota(selecionada);
            }
        }
    }//GEN-LAST:event_lstRotasValueChanged

    /**
     * @param args the command line arguments
     */
  public static void main(String args[]) {
        try { FlatLightLaf.setup(); } catch( Exception ex ) {}
        java.awt.EventQueue.invokeLater(() -> new TelaMapa().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImportar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDistancia;
    private javax.swing.JLabel lblGanho;
    private javax.swing.JLabel lblPerda;
    private javax.swing.JList<Rota> lstRotas;
    private javax.swing.JPanel pnMapa;
    // End of variables declaration//GEN-END:variables
}
