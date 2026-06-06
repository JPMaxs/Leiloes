import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class vendasVIEW extends javax.swing.JFrame {

    public vendasVIEW() {
        initComponents();
        listarVendidos(); 
    }

    @SuppressWarnings("unchecked")            
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        this.dispose(); 
    }                                         

    private void listarVendidos(){
        try {
            ProdutosDAO produtosdao = new ProdutosDAO();
            
            DefaultTableModel model = (DefaultTableModel) tabelaVendas.getModel();
            model.setNumRows(0);
            
            ArrayList<ProdutosDTO> listagem = produtosdao.listarProdutosVendidos();
            
            for(int i = 0; i < listagem.size(); i++){
                model.addRow(new Object[]{
                    listagem.get(i).getId(),
                    listagem.get(i).getNome(),
                    listagem.get(i).getValor(),
                    listagem.get(i).getStatus()
                });
            }
        } catch (Exception e) {
            // Tratamento silencioso
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new vendasVIEW().setVisible(true);
        });
    }  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
                 
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaVendas;    
}