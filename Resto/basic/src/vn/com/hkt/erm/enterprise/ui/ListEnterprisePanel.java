/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ListEnterprisePanel.java
 *
 * Created on Nov 23, 2011, 2:16:58 PM
 */
package vn.com.hkt.erm.enterprise.ui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import org.openide.util.Lookup;
import vn.com.hkt.basic.Installer;
import vn.com.hkt.basic.api.ISystemSotfwareBN;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;

/**
 *
 * @author longnt
 */
public class ListEnterprisePanel extends javax.swing.JPanel implements IUserInterface {

    private ISystemSotfwareBN sotfwareBN = Lookup.getDefault().lookup(ISystemSotfwareBN.class);
    private List<SystemSoftware> listS = new ArrayList<SystemSoftware>();

    /** Creates new form ListEnterprisePanel */
    public ListEnterprisePanel() {
        initComponents();
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        tableListE.setSelectionBackground(new Color(192, 210, 224));
        StripedTableCellRenderer.installInColumn(tableListE, colorL, null, colorD, null);
    }

    @Override
    public String toString() {
        return "Thông tin cơ bản";
    }

    public JTable getTableListE() {
        return tableListE;
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
        tableListE = new javax.swing.JTable();

        tableListE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã công ty", "Logo", "Tên thường gọi", "Slogan", "Đại diện pháp luật", "Công ty mẹ"
            }
        ));
        jScrollPane1.setViewportView(tableListE);

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
    public javax.swing.JTable tableListE;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện danh sách doanh nghiệp";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }
}
