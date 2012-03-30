/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExtentionSB23PanelHopDongChiTiet.java
 *
 * Created on Feb 29, 2012, 8:48:37 AM
 */
package vn.com.hkt.pilot.sb23.ui.panel;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.openide.util.Exceptions;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Mission;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.sb23.Installer;
import vn.com.hkt.pilot.sb23.entity.SubPersonSB23;
import vn.com.hkt.pilot.subpersonsb23.dao.SubPersonSB23BN;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;

/**
 *
 * @author DONGTAM
 */
public class ExtentionSB23PanelHopDongChiTiet extends javax.swing.JPanel implements IUserInterface {

    private int personIdActual;
    private SubPersonSB23BN subPersonSB23DAO;
    private ExtentionSB23CellHopDongChiTiet cellHopDongChiTiet;

    /**
     * Creates new form ExtentionSB23PanelHopDongChiTiet
     */
    public ExtentionSB23PanelHopDongChiTiet() {
        initComponents();
        cellHopDongChiTiet = new ExtentionSB23CellHopDongChiTiet();
        tblContractDetail.getColumnModel().getColumn(1).setCellEditor(cellHopDongChiTiet);
        tblContractDetail.getColumnModel().getColumn(3).setCellEditor(cellHopDongChiTiet);
        tblContractDetail.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblContractDetail.getColumnModel().getColumn(0).setMaxWidth(100);
        tblContractDetail.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblContractDetail.getColumnModel().getColumn(2).setMaxWidth(100);
        tblContractDetail.setRowSelectionAllowed(true);
        tblContractDetail.setColumnSelectionAllowed(false);
        tblContractDetail.setSelectionBackground(new Color(192, 210, 224));
        StripedTableCellRenderer.installInColumn(tblContractDetail, new Color(220, 228, 231), null, new Color(235, 239, 242), null);
    }

    private void saveData() {
        subPersonSB23DAO = new SubPersonSB23BN();
        Enterprise e = (Enterprise) tblContractDetail.getValueAt(0, 1);
        Mission m = (Mission) tblContractDetail.getValueAt(1, 1);

        try {
            SubPersonSB23 beanSubPersonSB23 = subPersonSB23DAO.getByObjectId(String.valueOf(personIdActual));
            if (beanSubPersonSB23 == null) {
                beanSubPersonSB23 = new SubPersonSB23();
            }

            beanSubPersonSB23.setPersonIdActual(personIdActual);
            beanSubPersonSB23.setEnterpriseIdActual(e.getId());
            beanSubPersonSB23.setTypeOfContract(tblContractDetail.getValueAt(0, 3).toString());
            beanSubPersonSB23.setMissionIdActual(m.getId());
            beanSubPersonSB23.setAutoRenewDeadline(Integer.parseInt(tblContractDetail.getValueAt(1, 3).toString()));
            beanSubPersonSB23.setCoefficientMin(Double.parseDouble(tblContractDetail.getValueAt(2, 1).toString()));
            beanSubPersonSB23.setSalaryMin(Double.parseDouble(tblContractDetail.getValueAt(2, 3).toString()));
            beanSubPersonSB23.setCoefficientMax(Double.parseDouble(tblContractDetail.getValueAt(3, 1).toString()));
            beanSubPersonSB23.setSalaryMax(Double.parseDouble(tblContractDetail.getValueAt(3, 3).toString()));
            beanSubPersonSB23.setFrequency(tblContractDetail.getValueAt(4, 1).toString());

            //set hình thức tăng thường niên
            beanSubPersonSB23.setMethodIncreaseAnnually(tblContractDetail.getValueAt(4, 3).toString());
            //set chu kỳ tăng
            beanSubPersonSB23.setCycleIncrease(Integer.parseInt(tblContractDetail.getValueAt(5, 1).toString()));
            beanSubPersonSB23.setClassification(tblContractDetail.getValueAt(5, 2).toString());
            beanSubPersonSB23.setIncreaseValue(Double.parseDouble(tblContractDetail.getValueAt(5, 3).toString()));
            beanSubPersonSB23.setCompensationByFired(Double.parseDouble(tblContractDetail.getValueAt(6, 1).toString()));
            beanSubPersonSB23.setCompensationByQuit(Double.parseDouble(tblContractDetail.getValueAt(6, 3).toString()));

            //set ngày tháng        
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String signedDate = tblContractDetail.getValueAt(7, 1).toString();
            String startDate = tblContractDetail.getValueAt(7, 3).toString();
            String updateDate = tblContractDetail.getValueAt(8, 1).toString();
            String endDate = tblContractDetail.getValueAt(8, 3).toString();

            Date dtSignDate = sdf.parse(signedDate);
            Date dtStartDate = sdf.parse(startDate);
            Date dtUpdateDate = sdf.parse(updateDate);
            Date dtEndDate = sdf.parse(endDate);

            Calendar calSignDate = Calendar.getInstance();
            calSignDate.setTime(dtSignDate);
            Calendar calStartDate = Calendar.getInstance();
            calStartDate.setTime(dtStartDate);
            Calendar calUpdateDate = Calendar.getInstance();
            calUpdateDate.setTime(dtUpdateDate);
            Calendar calEndDate = Calendar.getInstance();
            calEndDate.setTime(dtEndDate);
            beanSubPersonSB23.setSignedDate(calSignDate);
            beanSubPersonSB23.setStartDate(calStartDate);
            beanSubPersonSB23.setUpdatedDate(calUpdateDate);
            beanSubPersonSB23.setFinishedDate(calEndDate);

            subPersonSB23DAO.insert(beanSubPersonSB23);
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public void reset() {
        cellHopDongChiTiet.reset();
        //reset Row Chu kì
        tblContractDetail.setValueAt("", 5, 1);
        tblContractDetail.setValueAt("", 5, 2);
        tblContractDetail.setValueAt("", 5, 3);

        for (int i = 0; i < tblContractDetail.getRowCount(); i++) {
            for (int j = 1; j < tblContractDetail.getColumnCount(); j += 2) {
                tblContractDetail.setValueAt("", i, j);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblContractDetail = new javax.swing.JTable();

        tblContractDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Công ty", " ", "Loại hợp đồng", " "},
                {"Chức vụ", " ", "Auto renew hết hạn", " "},
                {"Hệ số tối thiểu", " ", "Lương tối thiểu", " "},
                {"Hệ số tối đa", " ", "Lương tối đa", " "},
                {"Pay frequency", " ", "Hình thức tăng thường niên", " "},
                {"Chu kì", " ", " ", " "},
                {"Bồi thường đuổi việc", " ", "Bồi thường bỏ việc", " "},
                {"Ngày kí kết", " ", "Ngày bắt đầu", " "},
                {"Ngày thay đổi", " ", null, " "}
            },
            new String [] {
                "", "", "", ""
            }
        ));
        jScrollPane1.setViewportView(tblContractDetail);
        tblContractDetail.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(ExtentionSB23PanelHopDongChiTiet.class, "ExtentionSB23PanelHopDongChiTiet.tblContractDetail.columnModel.title0")); // NOI18N
        tblContractDetail.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(ExtentionSB23PanelHopDongChiTiet.class, "ExtentionSB23PanelHopDongChiTiet.tblContractDetail.columnModel.title1")); // NOI18N
        tblContractDetail.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(ExtentionSB23PanelHopDongChiTiet.class, "ExtentionSB23PanelHopDongChiTiet.tblContractDetail.columnModel.title2")); // NOI18N
        tblContractDetail.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(ExtentionSB23PanelHopDongChiTiet.class, "ExtentionSB23PanelHopDongChiTiet.tblContractDetail.columnModel.title3")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 491, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 168, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblContractDetail;
    // End of variables declaration//GEN-END:variables

   @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện chi tiết hợp đồng";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }
}
