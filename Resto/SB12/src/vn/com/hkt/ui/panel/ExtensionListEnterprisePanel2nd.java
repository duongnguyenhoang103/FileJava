/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExtensionListEnterprisePanel2nd.java
 *
 * Created on Dec 29, 2011, 9:32:35 AM
 */
package vn.com.hkt.ui.panel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.ISystemSotfwareBN;
import vn.com.hkt.extension.Installer;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;

/**
 *
 * @author khanguct
 */
public class ExtensionListEnterprisePanel2nd extends javax.swing.JPanel implements IUserInterface {

    private IEnterpriseBN enterpriseBN;
    private ISystemSotfwareBN sotfwareBN = Lookup.getDefault().lookup(ISystemSotfwareBN.class);
    private List<SystemSoftware> listS = new ArrayList<SystemSoftware>();

    /** Creates new form ExtensionListEnterprisePanel2nd */
    public ExtensionListEnterprisePanel2nd() {
        initComponents();
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        tblEnterpriseExt.setSelectionBackground(new Color(192, 210, 224));
        StripedTableCellRenderer.installInColumn(tblEnterpriseExt, colorL, null, colorD, null);

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
        tblEnterpriseExt = new javax.swing.JTable();

        tblEnterpriseExt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null,null, null, null, null,null, null, null},
                {null, null, null, null,null, null, null, null,null, null, null},
                {null, null, null, null,null, null, null, null,null, null, null},
                {null, null, null, null,null, null, null, null,null, null, null}
            },
            new String [] {
                "Tên Cty", "Mã Cty", "Cty Mẹ", "Giám Đốc","Logo", "Slogan", "Địa Chỉ", "Tel",
                "Fax", "Email", "Web"
            }
        ));
        jScrollPane1.setViewportView(tblEnterpriseExt);

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
    private javax.swing.JTable tblEnterpriseExt;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the tblEnterpriseExt
     */
    public javax.swing.JTable getTblEnterpriseExt() {
        return tblEnterpriseExt;
    }
//    @Override
//    public void mouseClicked(MouseEvent e) {
//          mouseEvent(e);
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//        mouseEvent(e);
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//        mouseEvent(e);
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//        mouseEvent(e);
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//        mouseEvent(e);
//    }
//
//    public void mouseEvent(MouseEvent e) {
//        int rows = tblEnterpriseExt.getRowCount();
//        int slectedRow = tblEnterpriseExt.getSelectedRow();
//        for (int i = 0; i < rows; i++) {
//            if (i == slectedRow) {
//                if (e.getClickCount() == 2) {
//                    String strRow = tblEnterpriseExt.getValueAt(i, 1).toString().trim();
//                    Enterprise enterprise = enterpriseBN.getEnterpriseByID(strRow);
//                }
//            }
//        }
//    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện danh sách thông tin chi tiết về địa chỉ doanh nghiệp";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }
}
