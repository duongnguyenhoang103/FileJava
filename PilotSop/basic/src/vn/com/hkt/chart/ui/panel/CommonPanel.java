/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CommonPanel.java
 *
 * Created on Nov 24, 2011, 11:10:13 AM
 */
package vn.com.hkt.chart.ui.panel;

/**
 *
 * @author longnt
 */
public class CommonPanel extends javax.swing.JPanel {

    /** Creates new form CommonPanel */
    public CommonPanel() {
        initComponents();
        
        tableCommon.getColumnModel().getColumn(1).setCellEditor(new CommonCell());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableCommon = new javax.swing.JTable();

        tableCommon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Quốc gia", null},
                {"Vùng", null},
                {"City", null},
                {"Postal code", null},
                {"Quốc tịch", null}
            },
            new String [] {
                "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCommon.setRowHeight(25);
        jScrollPane1.setViewportView(tableCommon);
        tableCommon.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(CommonPanel.class, "CommonPanel.tableCommon.columnModel.title0")); // NOI18N
        tableCommon.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(CommonPanel.class, "CommonPanel.tableCommon.columnModel.title1")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableCommon;
    // End of variables declaration//GEN-END:variables
}
