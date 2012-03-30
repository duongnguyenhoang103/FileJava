/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EnterprisePanel.java
 *
 * Created on Nov 23, 2011, 1:56:14 PM
 */
package vn.com.hkt.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.openide.util.Lookup;
import org.openide.util.Lookup.Result;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.IMission;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.basic.api.ISystemSotfwareBN;
import vn.com.hkt.enterprise.ext.dao.EnterpriseExtBN;
import vn.com.hkt.enterprise.ext.dao.ExecutiveBN;
import vn.com.hkt.enterprise.ext.entity.EnterpriseExt;
import vn.com.hkt.enterprise.ext.entity.ExecutiveOffice;
import vn.com.hkt.extension.Installer;
import vn.com.hkt.pilot.enterprise.viewer.api.HelpTutorialEnterprise;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseCreator;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseExtCreator;
import vn.com.hkt.pilot.enterprise.viewer.api.IGetObject;
import vn.com.hkt.pilot.enterprise.viewer.api.ResetCookie;
import vn.com.hkt.pilot.enterprise.viewer.api.SaveCookie;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Mission;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.ui.main.ui.api.creater.ISaveExtention;

/**
 *
 * @author longnt
 */
@ServiceProvider(service = IEnterpriseExtCreator.class)
public class EnterpriseSB122Panel extends javax.swing.JPanel implements IEnterpriseExtCreator,
        ISaveExtention, IResetFontSize, IGetObject, IUserInterface {

    private int idEnterprise = 0;
    private EnterpriseExtBN enterpriseextdao = new EnterpriseExtBN();
    private ExecutiveBN executiveBN = new ExecutiveBN();
    private ExecutiveOfficeCell executiveOfficeCell;
    private int size;
    private String font;
    private ISystemSotfwareBN sotfwareBN;
    private List<SystemSoftware> listS;
    private DefaultTableModel modelCoDOng, modelDieuHanh;
    private int i = 4;
    private int j = 4;

    @Override
    public String toString() {
        return "Thông tin doanh nghiệp";
    }

    /**
     * Creates new form EnterprisePanel
     */
    public EnterpriseSB122Panel() {
        initComponents();
        sotfwareBN = Lookup.getDefault().lookup(ISystemSotfwareBN.class);
        listS = new ArrayList<SystemSoftware>();
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());

        executiveOfficeCell = new ExecutiveOfficeCell(10);

        tableDieuHanh.getColumnModel().getColumn(0).setCellEditor(executiveOfficeCell);
        tableDieuHanh.getColumnModel().getColumn(1).setCellEditor(executiveOfficeCell);

        tableCoDong.getColumnModel().getColumn(0).setCellEditor(executiveOfficeCell);
        tableCoDong.getColumnModel().getColumn(1).setCellEditor(executiveOfficeCell);

        tableDieuHanh.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableDieuHanh.getColumnModel().getColumn(0).setMaxWidth(100);
        tableDieuHanh.setRowSelectionAllowed(true);
        tableDieuHanh.setColumnSelectionAllowed(false);
        tableDieuHanh.setSelectionBackground(new Color(192, 210, 224));
        StripedTableCellRenderer.installInColumn(tableDieuHanh, colorL, null, colorD, null);
        tableDieuHanh.setForeground(color);
        jScrollPane1.setViewportBorder(null);
        jScrollPane1.setBorder(null);
        tableDieuHanh.setTableHeader(null);

        tableCoDong.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableCoDong.getColumnModel().getColumn(0).setMaxWidth(100);
        tableCoDong.setRowSelectionAllowed(true);
        tableCoDong.setColumnSelectionAllowed(false);
        tableCoDong.setSelectionBackground(new Color(192, 210, 224));
        StripedTableCellRenderer.installInColumn(tableCoDong, colorL, null, colorD, null);
        tableCoDong.setForeground(color);
        jScrollPane2.setViewportBorder(null);
        jScrollPane2.setBorder(null);
        tableCoDong.setTableHeader(null);
        this.removeAll();
        this.setLayout(new BorderLayout());
        panelTable.removeAll();
        panelTable.setLayout(new GridLayout());
        panelTable.add(jScrollPane1);

        panlePhoto.removeAll();
        panlePhoto.setLayout(new GridLayout());
        panlePhoto.add(jScrollPane2);
        panelTP.setLayout(new GridLayout());

        JLabel label = new JLabel("  Ban điều hành                               HĐQT & Cổ Đông");
        label.setFont((new Font(" ", Font.BOLD, 14)));
        label.setForeground(new Color(0, 51, 102));
        label.setPreferredSize(new Dimension(WIDTH, 30));
        this.setAutoscrolls(true);
        panelTP.add(panlePhoto);
        panelTP.add(panelTable);
        this.add(panelTong, BorderLayout.CENTER);
        this.add(label, BorderLayout.NORTH);


        tableCoDong.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
        tableDieuHanh.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
    }

    public JTable getTabelEnterprise() {
        return tableDieuHanh;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTong = new javax.swing.JPanel();
        panelTP = new javax.swing.JPanel();
        panlePhoto = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCoDong = new javax.swing.JTable();
        panelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDieuHanh = new javax.swing.JTable();

        panelTP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTP.setPreferredSize(new java.awt.Dimension(827, 165));

        jScrollPane2.setPreferredSize(new java.awt.Dimension(130, 130));
        jScrollPane2.setRequestFocusEnabled(false);

        tableCoDong.setBackground(new java.awt.Color(242, 241, 240));
        tableCoDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Chức danh", "Họ tên"},
                {"",""},
                {"",""},
                {"",""},
                {"",""}
            },
            new String [] {
                "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if (rowIndex == 0) return false ;
                return canEdit [columnIndex];
            }
        });
        tableCoDong.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tableCoDong.setRowHeight(26);
        tableCoDong.setShowHorizontalLines(false);
        tableCoDong.setShowVerticalLines(false);
        jScrollPane2.setViewportView(tableCoDong);

        javax.swing.GroupLayout panlePhotoLayout = new javax.swing.GroupLayout(panlePhoto);
        panlePhoto.setLayout(panlePhotoLayout);
        panlePhotoLayout.setHorizontalGroup(
            panlePhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panlePhotoLayout.setVerticalGroup(
            panlePhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelTableLayout = new javax.swing.GroupLayout(panelTable);
        panelTable.setLayout(panelTableLayout);
        panelTableLayout.setHorizontalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        panelTableLayout.setVerticalGroup(
            panelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );

        jScrollPane1.setPreferredSize(new java.awt.Dimension(130, 130));

        tableDieuHanh.setBackground(new java.awt.Color(242, 241, 240));
        tableDieuHanh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Chức danh", "Họ tên"},
                {"", ""},
                {"", ""},
                {"", ""},
                {"", ""}
            },
            new String [] {
                "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if (rowIndex == 0) return false ;
                return canEdit [columnIndex];
            }
        });
        tableDieuHanh.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tableDieuHanh.setRowHeight(26);
        tableDieuHanh.setShowHorizontalLines(false);
        tableDieuHanh.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tableDieuHanh);

        javax.swing.GroupLayout panelTPLayout = new javax.swing.GroupLayout(panelTP);
        panelTP.setLayout(panelTPLayout);
        panelTPLayout.setHorizontalGroup(
            panelTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTPLayout.createSequentialGroup()
                .addComponent(panlePhoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(207, 207, 207))
        );
        panelTPLayout.setVerticalGroup(
            panelTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTPLayout.createSequentialGroup()
                .addGroup(panelTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panlePhoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelTongLayout = new javax.swing.GroupLayout(panelTong);
        panelTong.setLayout(panelTongLayout);
        panelTongLayout.setHorizontalGroup(
            panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongLayout.createSequentialGroup()
                .addComponent(panelTP, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelTongLayout.setVerticalGroup(
            panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongLayout.createSequentialGroup()
                .addComponent(panelTP, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(363, 363, 363))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelTP;
    private javax.swing.JPanel panelTable;
    private javax.swing.JPanel panelTong;
    private javax.swing.JPanel panlePhoto;
    private javax.swing.JTable tableCoDong;
    private javax.swing.JTable tableDieuHanh;
    // End of variables declaration//GEN-END:variables

    @Override
    public JPanel getEnterpriseExtCreator() {
        executiveOfficeCell.loadCombo();
        return this;
    }

    @Override
    public Lookup getEnterpriseExtLookup() {
        return null;
    }

    @Override
    public double getLevelNumber() {
        return 1.22;
    }

    @Override
    public void reset() {
        idEnterprise = 0;
        tableCoDong.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"Chức danh", "Họ tên"},
                    {"", ""},
                    {"", ""},
                    {"", ""},
                    {"", ""}
                },
                new String[]{
                    "", ""
                }) {

            boolean[] canEdit = new boolean[]{
                true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if (rowIndex == 0) {
                    return false;
                }
                return canEdit[columnIndex];
            }
        });

        tableDieuHanh.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"Chức danh", "Họ tên"},
                    {"", ""},
                    {"", ""},
                    {"", ""},
                    {"", ""}
                },
                new String[]{
                    "", ""
                }) {

            boolean[] canEdit = new boolean[]{
                true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if (rowIndex == 0) {
                    return false;
                }
                return canEdit[columnIndex];
            }
        });

        tableDieuHanh.getColumnModel().getColumn(0).setCellEditor(executiveOfficeCell);
        tableDieuHanh.getColumnModel().getColumn(1).setCellEditor(executiveOfficeCell);

        tableCoDong.getColumnModel().getColumn(0).setCellEditor(executiveOfficeCell);
        tableCoDong.getColumnModel().getColumn(1).setCellEditor(executiveOfficeCell);
        i = 4;
        j = 4;
        resetColorWord();
        resetFont();
        resetSize();
        resetColorRowTable();
    }

    public void addFormEditID(int i) {
        Collection<? extends HelpTutorialEnterprise> allSave = Lookup.getDefault().lookupAll(HelpTutorialEnterprise.class);


        for (HelpTutorialEnterprise editCookie : allSave) {
            editCookie.getTutorial(i, "");
        }
    }
// Sự kiện load hướng dẫn

    private void tableMousePressed(MouseEvent evt) {
        JTable table = (JTable) evt.getSource();
        if (table == tableCoDong) {
            if (tableCoDong.getSelectedRow() != -1) {
                if (tableCoDong.getSelectedColumn() == 0) {
                    addFormEditID(3);
                }
                if (tableCoDong.getSelectedRow() == i) {
                    modelCoDOng = (DefaultTableModel) tableCoDong.getModel();
                    Object[] rows1 = {" ", " "};
                    modelCoDOng.addRow(rows1);
                    i++;
                    tableCoDong.repaint();
                }
            }
        }
        if (table == tableDieuHanh) {
            if (tableDieuHanh.getSelectedRow() != -1) {
                if (tableDieuHanh.getSelectedColumn() == 0) {
                    addFormEditID(3);
                }
                if (tableDieuHanh.getSelectedRow() == j) {
                    modelDieuHanh = (DefaultTableModel) tableDieuHanh.getModel();
                    Object[] rows1 = {" ", " "};
                    modelDieuHanh.addRow(rows1);
                    j++;
                    tableDieuHanh.repaint();
                }
            }
        }

    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = tableDieuHanh.getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        tableDieuHanh.setFont(new Font(font, 0, size));
        tableCoDong.setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(tableDieuHanh, colorL, null, colorD, null);
        StripedTableCellRenderer.installInColumn(tableCoDong, colorL, null, colorD, null);
        tableDieuHanh.repaint();
        tableCoDong.repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = tableDieuHanh.getFont().getFontName();
        size = listS.get(0).getSizeWord();
        tableDieuHanh.setFont(new Font(font, 0, size));
        tableCoDong.setFont(new Font(font, 0, size));

    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tableDieuHanh.setForeground(color);
        tableCoDong.setForeground(color);
        tableDieuHanh.repaint();
        tableCoDong.repaint();
    }

    @Override
    public void resetColorTitle() {
//        listS = sotfwareBN.selectAll();
//        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
//        tableDieuHanh.getTableHeader().setForeground(color);
//        tableCoDong.getTableHeader().setForeground(color);
//        tableDieuHanh.repaint();
//        tableCoDong.repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        tableDieuHanh.setSelectionBackground(color);
        tableCoDong.setSelectionBackground(color);
        tableCoDong.repaint();
        tableDieuHanh.repaint();
    }

    @Override
    public void getObject(String id) {
        IPersonBN personBN = Lookup.getDefault().lookup(IPersonBN.class);
        IEnterpriseBN enterpriseBN = Lookup.getDefault().lookup(IEnterpriseBN.class);
        IMission missionBN = Lookup.getDefault().lookup((IMission.class));
        Enterprise e = enterpriseBN.getByObjectId(id);
        if (e == null) {
            return;
        }
        try {
            EnterpriseExt bean = enterpriseextdao.getByObjectId(String.valueOf(e.getId()));
            List<Integer> listIdB = bean.getExecutiveOfficesIdActual();
            ExecutiveOffice exeOffice[] = new ExecutiveOffice[listIdB.size()];
            List<ExecutiveOffice> listDH = new ArrayList<ExecutiveOffice>();
            List<ExecutiveOffice> listCD = new ArrayList<ExecutiveOffice>();
            for (int i = 0; i < listIdB.size(); i++) {
                exeOffice[i] = executiveBN.getById(listIdB.get(i));
                if (exeOffice[i].getExecutiveOfficeName() == 0) {
                    listDH.add(exeOffice[i]);
                }
                if (exeOffice[i].getExecutiveOfficeName() == 1) {
                    listCD.add(exeOffice[i]);
                }
            }

            if (listDH.size() < 5) {
                for (int i = 0; i < listDH.size(); i++) {
                    Person person = personBN.getById(listDH.get(i).getPersonIdActual());
                    Mission mission = missionBN.getById(listDH.get(i).getMissionIdActual());
                    tableDieuHanh.setValueAt(person, i + 1, 1);
                    tableDieuHanh.setValueAt(mission, i + 1, 0);
                }
                for (int i = listDH.size(); i < 4; i++) {
                    tableDieuHanh.setValueAt(" ", i + 1, 0);
                    tableDieuHanh.setValueAt(" ", i + 1, 1);
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    Person person = personBN.getById(listDH.get(i).getPersonIdActual());
                    Mission mission = missionBN.getById(listDH.get(i).getMissionIdActual());
                    tableDieuHanh.setValueAt(person, i + 1, 1);
                    tableDieuHanh.setValueAt(mission, i + 1, 0);
                }
                modelDieuHanh = (DefaultTableModel) tableDieuHanh.getModel();
                for (int i = 4; i < listDH.size(); i++) {
                    Person person = personBN.getById(listDH.get(i).getPersonIdActual());
                    Mission mission = missionBN.getById(listDH.get(i).getMissionIdActual());
                    Object[] row = {mission, person};
                    modelDieuHanh.addRow(row);
                }

            }
            if (listCD.size() < 5) {
                for (int i = 0; i < listCD.size(); i++) {
                    Person person = personBN.getById(listCD.get(i).getPersonIdActual());
                    Mission mission = missionBN.getById(listCD.get(i).getMissionIdActual());
                    tableCoDong.setValueAt(person, i + 1, 1);
                    tableCoDong.setValueAt(mission, i + 1, 0);
                }
                for (int i = listCD.size(); i < 4; i++) {
                    tableCoDong.setValueAt(" ", i + 1, 0);
                    tableCoDong.setValueAt(" ", i + 1, 1);
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    Person person = personBN.getById(listCD.get(i).getPersonIdActual());
                    Mission mission = missionBN.getById(listCD.get(i).getMissionIdActual());
                    tableCoDong.setValueAt(person, i + 1, 1);
                    tableCoDong.setValueAt(mission, i + 1, 0);
                }
                modelCoDOng = (DefaultTableModel) tableCoDong.getModel();
                for (int i = 4; i < listCD.size(); i++) {
                    Person person = personBN.getById(listCD.get(i).getPersonIdActual());
                    Mission mission = missionBN.getById(listCD.get(i).getMissionIdActual());
                    Object[] row = {mission, person};
                    modelCoDOng.addRow(row);
                }

            }
        } catch (Exception ex) {
        }

    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện thông tin chi tiết về địa chỉ doanh nghiệp";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }

    @Override
    public List<JTable> getTables() {
        List<JTable> lt = new ArrayList<JTable>();
        lt.add(tableCoDong);
        lt.add(tableDieuHanh);
        return lt;
    }

    @Override
    public void setEntity(IEntity entity) {
        idEnterprise = entity.getId();
    }

    @Override
    public IEntity save() {
        if (idEnterprise == 0) {
            return null;
        }

        for (int k = 1; k < tableDieuHanh.getModel().getRowCount(); k++) {
            if (tableDieuHanh.getValueAt(k, 0) != null && tableDieuHanh.getValueAt(k, 0).toString().trim().length() != 0) {
                try {
                    Mission mission = (Mission) tableDieuHanh.getValueAt(k, 0);
                    Person person = (Person) tableDieuHanh.getValueAt(k, 1);
                    ExecutiveOffice bean = new ExecutiveOffice();
                    bean.setExecutiveOfficeIdActual(idEnterprise);
                    bean.setPersonIdActual(person.getId());
                    bean.setMissionIdActual(mission.getId());
                    bean.setExecutiveOfficeName(0);
                    executiveBN.update(bean);
                } catch (Exception ex) {
                }
            }
        }
        for (int k = 1; k < tableCoDong.getModel().getRowCount(); k++) {
            if (tableCoDong.getValueAt(k, 0) != null && tableCoDong.getValueAt(k, 0).toString().trim().length() != 0) {
                try {
                    Mission mission = (Mission) tableCoDong.getValueAt(k, 0);
                    Person idPerson = (Person) tableCoDong.getValueAt(k, 1);
                    ExecutiveOffice bean = new ExecutiveOffice(idEnterprise, 1, idPerson.getId(), mission.getId());
                    executiveBN.update(bean);
                } catch (Exception ex) {
                }
            }
        }

        List<Integer> lst = new ArrayList<Integer>();
        List<ExecutiveOffice> listEO = executiveBN.filter(ExecutiveOffice.FIELD_EXECUTIVEOFFICE_ID_ACTUAL, String.valueOf(idEnterprise));
        if (!listEO.isEmpty()) {
            for (ExecutiveOffice e : listEO) {
                lst.add(e.getId());
            }
        }
        EnterpriseExt bean = enterpriseextdao.getByObjectId(String.valueOf(idEnterprise));
        if (bean == null) {
            bean = new EnterpriseExt();
        }
        bean.setEnterpriseIdActual(idEnterprise);
        bean.setExecutiveOfficesIdActual(lst);
        enterpriseextdao.update(bean);
        return bean;
    }
}
