/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExtensionEnterprisePanel.java
 *
 * Created on Dec 21, 2011, 4:35:59 PM
 */
package vn.com.hkt.ui.panel;

import java.awt.Color;
import javax.swing.JTable;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;

/**
 *
 * @author longnt
 */
public class ExtensionEnterprisePanel extends javax.swing.JPanel {

    /** Creates new form ExtensionEnterprisePanel */
    public ExtensionEnterprisePanel() {
        initComponents();
        StripedTableCellRenderer.installInColumn(tableExtensionE, new Color(241, 246, 253), null, null, null);
        tableExtensionE.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableExtensionE.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableExtensionE.getColumnModel().getColumn(2).setMaxWidth(100);
        tableExtensionE.getColumnModel().getColumn(0).setMaxWidth(100);
        tableExtensionE.setColumnSelectionAllowed(false);
        tableExtensionE.setRowSelectionAllowed(true);
        tableExtensionE.setSelectionBackground(new Color(201, 224, 252));
        jScrollPane1.setViewportBorder(null);
        tableExtensionE.setTableHeader(null);
    }

    public JTable getTableExtensionE() {
        return tableExtensionE;
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
        tableExtensionE = new javax.swing.JTable();

        tableExtensionE.setBorder(null);
        tableExtensionE.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{
                {"Địa chỉ", " ", " ", " "},
                {"Tell", " ", "Fax", " "},
                {"Email", " ", "Web", " "},
                {" ", " ", " ", " "},
                {" ", " ", " ", " "}
            },
            new String[]{
                "", "", "", ""
            }) {

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    if (columnIndex == 0) {
                        return false;
                    }
                    if (columnIndex == 2) {
                        return false;
                    }
                    return true;
                }
            });
            tableExtensionE.setRowHeight(26);
            tableExtensionE.setShowHorizontalLines(false);
            tableExtensionE.setShowVerticalLines(false);
            jScrollPane1.setViewportView(tableExtensionE);

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
    private javax.swing.JTable tableExtensionE;
    // End of variables declaration//GEN-END:variables
}
