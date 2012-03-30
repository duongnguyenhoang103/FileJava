/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExtensionEnterprisePanel.java
 *
 * Created on Dec 21, 2011, 4:35:59 PM
 */
package vn.com.hkt.pilot.sb32.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IDepartmentBN;
import vn.com.hkt.basic.api.ISystemSotfwareBN;
import vn.com.hkt.pilot.department.viewer.api.HelpTutorialDepartment;
import vn.com.hkt.pilot.department.viewer.api.IDepartmentExtCreater;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnableTable;
import vn.com.hkt.pilot.enterprise.viewer.api.IGetObject;
import vn.com.hkt.pilot.enterprise.viewer.api.ResetCookie;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.sb32.Installer;
import vn.com.hkt.pilot.sb32.deparment.extW43.entity.DepartmentExt_W43;
import vn.com.hkt.pilot.sb32.department.extW43.dao.DepartmentExtW43_DAO;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.ui.main.ui.api.creater.ISaveExtention;

/**
 *
 * @author longnt
 */
@ServiceProvider(service = IDepartmentExtCreater.class)
public class SB32Panel extends javax.swing.JPanel implements IDepartmentExtCreater, ISaveExtention,
        IEnableTable, IResetFontSize, IGetObject, IUserInterface {

    private int idDepartment = 0;
    private DepartmentExtW43_DAO dao;
    private int size;
    private String font;
    private ISystemSotfwareBN sotfwareBN;
    private List<SystemSoftware> listS;

    /** Creates new form ExtensionEnterprisePanel */
    public SB32Panel() {
        initComponents();
        dao = new DepartmentExtW43_DAO();
        sotfwareBN = Lookup.getDefault().lookup(ISystemSotfwareBN.class);
        listS = new ArrayList<SystemSoftware>();
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        // bat su kien insert tu department creator sang
        tableSB32.setTableHeader(null);
        tableSB32.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableSB32.getColumnModel().getColumn(0).setMaxWidth(100);
        tableSB32.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableSB32.getColumnModel().getColumn(2).setMaxWidth(100);
        tableSB32.setRowSelectionAllowed(true);
        tableSB32.setColumnSelectionAllowed(false);
        tableSB32.setSelectionBackground(new Color(192, 210, 224));
        tableSB32.setForeground(color);
        StripedTableCellRenderer.installInColumn(tableSB32, colorL, null, colorD, null);


        panelTong.removeAll();
        panelTong.setBackground(new Color(242, 241, 240));
        panelTong.setLayout(new BorderLayout());

        panelTong.add(tableSB32, BorderLayout.CENTER);
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(jPanel1, BorderLayout.CENTER);
        this.setBackground(new Color(242, 241, 240));
        JLabel label = new JLabel("             Phân tích đánh giá");
        label.setBackground(new Color(242, 241, 240));
        label.setFont((new Font(" ", Font.BOLD, 14)));
        label.setPreferredSize(new Dimension(WIDTH, 30));
        this.add(label, BorderLayout.NORTH);


        tableSB32.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }

            public void tableMousePressed(MouseEvent evt) {
                SB32_W43Tutorial sB32_W43Tutorial = new SB32_W43Tutorial();
                Collection<? extends ResetCookie> allResetCookie = Lookup.getDefault().lookupAll(ResetCookie.class);
                for (ResetCookie rc : allResetCookie) {
                    try {
                        rc.resetSelectTable(3.2);
                    } catch (IOException ex) {
                    }
                }
                double k = 0;
                String[] str1 = {sB32_W43Tutorial.getTongTienThu().getText(), sB32_W43Tutorial.getTongLoiNhuan().getText(),
                    sB32_W43Tutorial.getDanhGia().getText()};

                String[] str2 = {sB32_W43Tutorial.getTongTienChi().getText(), sB32_W43Tutorial.getTySuatLoiNhuan().getText(), ""};
                for (int i = 0; i < str1.length; i++) {
                    if (tableSB32.getSelectedRow() == i && tableSB32.getSelectedColumn() == 1) {
                        k = (double) (2 + 0.1 * i + 0.01 * 1);
                        k = Math.round(k * 1000) * 1.0 / 1000;
                        addFormEditID(k, str1[i]);
                    }
                    if (tableSB32.getSelectedRow() == i && tableSB32.getSelectedColumn() == 3) {
                        k = (double) (2 + 0.1 * i + 0.01 * 3);
                        k = Math.round(k * 1000) * 1.0 / 1000;
                        addFormEditID(k, str2[i]);

                    }

                }
            }
        });


    }

    public JTable getTableSB32() {
        return this.tableSB32;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelTong = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSB32 = new javax.swing.JTable();

        panelTong.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTong.setPreferredSize(new java.awt.Dimension(827, 165));

        tableSB32.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Tổng tiền thu", 0, "Tổng tiền chi", 0},
                {"Tổng lợi nhuận", 0, "Tỉ suất lợi nhuận", 0},
                {"Đánh giá", 0, "", 0},
                {"", null, null, null}
            },
            new String [] {
                "", "", "", ""
            }
        ) {

            boolean[] canEdit = new boolean[]{
                false, true , false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if (rowIndex == 1 && columnIndex == 3)
                if (rowIndex >=3) return false ;
                return canEdit[columnIndex];
            }
        }
    );
    tableSB32.setRowHeight(26);
    tableSB32.setShowHorizontalLines(false);
    tableSB32.setShowVerticalLines(false);
    jScrollPane2.setViewportView(tableSB32);

    javax.swing.GroupLayout panelTongLayout = new javax.swing.GroupLayout(panelTong);
    panelTong.setLayout(panelTongLayout);
    panelTongLayout.setHorizontalGroup(
        panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelTongLayout.createSequentialGroup()
            .addGap(376, 376, 376)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(121, Short.MAX_VALUE))
    );
    panelTongLayout.setVerticalGroup(
        panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelTongLayout.createSequentialGroup()
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(20, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(panelTong, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(446, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelTong;
    private javax.swing.JTable tableSB32;
    // End of variables declaration//GEN-END:variables

    @Override
    public String toString() {
        return "Phân tích đánh giá";
    }

    @Override
    public double getLevelNumber() {
        return 3.2;
    }

    @Override
    public JPanel getDepartmentExtViewer() {
        return this;
    }

    @Override
    public Lookup getDepartmentExtViewerLookup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IEntity save() {
        if (idDepartment == 0) {
            return null;
        }
        float sumMoneyImport = Float.parseFloat(tableSB32.getValueAt(0, 1).toString().trim());
        float sumMoneyExport = Float.parseFloat(tableSB32.getValueAt(0, 3).toString().trim());
        float sumProfit = Float.parseFloat(tableSB32.getValueAt(1, 1).toString().trim());
        String proportionProfit = tableSB32.getValueAt(1, 3).toString().trim();
        String estimate = tableSB32.getValueAt(2, 1).toString().trim();

        DepartmentExt_W43 bean = dao.getByObjectId(String.valueOf(idDepartment));
        if (bean == null) {
            bean = new DepartmentExt_W43();
        }
        bean.setDepartmentIdActual(idDepartment);
        bean.setSumMoneyImport(sumMoneyImport);
        bean.setSumMoneyExport(sumMoneyExport);
        bean.setSumprofit(sumProfit);
        bean.setProportionProfit(proportionProfit);
        bean.setEstimate(estimate);

        dao.update(bean);
        reset();

        return bean;
    }

    public void resetSelectTable(double d) throws IOException {
        if (d != this.getLevelNumber()) {
            tableSB32.setColumnSelectionInterval(0, 0);
            this.tableSB32.getSelectionModel().clearSelection();
            this.tableSB32.clearSelection();
        }

    }

    @Override
    public void reset() {
        idDepartment = 0;
        tableSB32.setValueAt(0, 0, 1);
        tableSB32.setValueAt(0, 0, 3);
        tableSB32.setValueAt(0, 1, 1);
        tableSB32.setValueAt(0, 1, 3);
        tableSB32.setValueAt(" ", 2, 1);
        tableSB32.setValueAt(" ", 2, 3);

    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = tableSB32.getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        tableSB32.setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(tableSB32, colorL, null, colorD, null);
        tableSB32.repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = tableSB32.getFont().getFontName();
        size = listS.get(0).getSizeWord();
        tableSB32.setFont(new Font(font, 0, size));

    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tableSB32.setForeground(color);
        tableSB32.repaint();
    }

    @Override
    public void resetColorTitle() {
//        listS = sotfwareBN.selectAll();
//        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
//        tableSB32.getTableHeader().setForeground(color);
//        tableSB32.repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        tableSB32.setSelectionBackground(color);
        tableSB32.repaint();
    }

    @Override
    public void getObject(String id) {
        IDepartmentBN departmentBN = Lookup.getDefault().lookup(IDepartmentBN.class);
        Department department = departmentBN.getByObjectId(id);
        if (department != null) {
            DepartmentExt_W43 bean = dao.getByObjectId(String.valueOf(department.getId()));
            tableSB32.setValueAt(bean.getSumMoneyImport(), 0, 1);
            tableSB32.setValueAt(bean.getSumMoneyExport(), 0, 3);
            tableSB32.setValueAt(bean.getSumprofit(), 1, 1);
            tableSB32.setValueAt(bean.getProportionProfit(), 1, 3);
            tableSB32.setValueAt(" ", 2, 1);
            tableSB32.setValueAt(" ", 2, 3);
        }
    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện phân tích dự án";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }

    public void addFormEditID(double i, String str) {
        Collection<? extends HelpTutorialDepartment> allSave = Lookup.getDefault().lookupAll(HelpTutorialDepartment.class);
        for (HelpTutorialDepartment editCookie : allSave) {
            editCookie.getTutorial(i, str);
        }
    }

    @Override
    public void enableTable(boolean ok) throws IOException {
        tableSB32.setEnabled(ok);
    }

    @Override
    public List<JTable> getTables() {
        List<JTable> lt = new ArrayList<JTable>();
        lt.add(tableSB32);
        return lt;
    }

    @Override
    public void setEntity(IEntity entity) {
        idDepartment = entity.getId();
    }
}
