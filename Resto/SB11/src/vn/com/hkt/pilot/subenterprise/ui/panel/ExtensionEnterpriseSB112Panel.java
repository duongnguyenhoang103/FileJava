/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExtensionEnterprisePanel.java
 *
 * Created on Dec 21, 2011, 4:35:59 PM
 */
package vn.com.hkt.pilot.subenterprise.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.ISystemSotfwareBN;
import vn.com.hkt.pilot.enterprise.viewer.api.HelpTutorialEnterprise;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnableTable;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseExtCreator;
import vn.com.hkt.pilot.enterprise.viewer.api.IGetObject;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.sbenterprise.Installer;
import vn.com.hkt.pilot.sbenterprise.entity.SubEnterprise;
import vn.com.hkt.pilot.subenterprise.dao.SubEnterpriseBN;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.ui.main.ui.api.creater.ISaveExtention;

/**
 *
 * @author longnt
 */
@ServiceProvider(service = IEnterpriseExtCreator.class)
public class ExtensionEnterpriseSB112Panel extends javax.swing.JPanel implements IEnterpriseExtCreator,
        ISaveExtention, IEnableTable, IResetFontSize, IGetObject, IUserInterface {

    private SubEnterpriseBN dao = new SubEnterpriseBN();
    private int size, idEnterprise = 0, register, c, idTax;
    private String font, nameVN, nameEn, type, state;
    private ISystemSotfwareBN sotfwareBN;
    private List<SystemSoftware> listS;
    private ExtEnterpriseSB112CellEdittor sB112Cell;
    private Date d1, d2;
    private Calendar c1, c2;

    /**
     * Creates new form ExtensionEnterprisePanel
     */
    public ExtensionEnterpriseSB112Panel() {
        initComponents();
        sotfwareBN = Lookup.getDefault().lookup(ISystemSotfwareBN.class);
        listS = new ArrayList<SystemSoftware>();
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        sB112Cell = new ExtEnterpriseSB112CellEdittor();
        StripedTableCellRenderer.installInColumn(tableListSB11, colorL, null, colorD, null);
        StripedTableCellRenderer.installInColumn(tableExtensionE, colorL, null, colorD, null);
        tableExtensionE.setForeground(color);
        tableListSB11.setForeground(color);


        tableListSB11.getColumnModel().getColumn(1).setCellEditor(sB112Cell);
        tableListSB11.getColumnModel().getColumn(3).setCellEditor(sB112Cell);
        tableExtensionE.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableExtensionE.getColumnModel().getColumn(0).setMaxWidth(100);
        tableExtensionE.setRowSelectionAllowed(true);
        tableExtensionE.setColumnSelectionAllowed(false);
        tableExtensionE.setSelectionBackground(new Color(192, 210, 224));
        tableListSB11.setSelectionBackground(new Color(192, 210, 224));
        tableExtensionE.setTableHeader(null);

        tableListSB11.setTableHeader(null);
        tableListSB11.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableListSB11.getColumnModel().getColumn(0).setMaxWidth(100);
        tableListSB11.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableListSB11.getColumnModel().getColumn(2).setMaxWidth(100);
        tableListSB11.setRowSelectionAllowed(true);
        tableListSB11.setColumnSelectionAllowed(false);
        jScrollPane1.setViewportBorder(null);

        panelTong.removeAll();
        panelTong.setBackground(new Color(242, 241, 240));
        panelTong.setLayout(new BorderLayout());
        panelTong.add(tableExtensionE, BorderLayout.NORTH);
        panelTong.add(tableListSB11, BorderLayout.CENTER);
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(jPanel1, BorderLayout.CENTER);
        this.setBackground(new Color(242, 241, 240));
        JLabel label = new JLabel("          Thông tin pháp nhân");
        label.setBackground(new Color(242, 241, 240));
        label.setFont((new Font(" ", Font.BOLD, 14)));
        label.setPreferredSize(new Dimension(WIDTH, 30));
        this.add(label, BorderLayout.NORTH);

        tableListSB11.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
        tableExtensionE.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
    }

    public JTable getTableExtensionE() {
        return this.tableExtensionE;
    }

    public JTable getTableDiaChi() {
        return this.tableListSB11;
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
        panelTong = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableExtensionE = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableListSB11 = new javax.swing.JTable();

        panelTong.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTong.setPreferredSize(new java.awt.Dimension(827, 165));

        tableExtensionE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Tên tiếng Việt", null},
                {"Tên tiếng Anh", null}
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
        tableExtensionE.setRowHeight(26);
        tableExtensionE.setShowHorizontalLines(false);
        tableExtensionE.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tableExtensionE);

        tableListSB11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Loại hình", null, "Lần đăng ký KD", null},
                {"Ngày thành lập", null, "Ngày đăng ký", null},
                {"Số đăng ký KD", null, "Mã số thuế", null},
                {"Tình trạng", null, null, " "}
            },
            new String [] {
                "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {

                if (rowIndex >= 3 )
                if (columnIndex == 2 || columnIndex ==3) return false;
                return canEdit [columnIndex];
            }
        });
        tableListSB11.setRowHeight(25);
        tableListSB11.setShowHorizontalLines(false);
        tableListSB11.setShowVerticalLines(false);
        jScrollPane2.setViewportView(tableListSB11);

        javax.swing.GroupLayout panelTongLayout = new javax.swing.GroupLayout(panelTong);
        panelTong.setLayout(panelTongLayout);
        panelTongLayout.setHorizontalGroup(
            panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );
        panelTongLayout.setVerticalGroup(
            panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongLayout.createSequentialGroup()
                .addGroup(panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTongLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
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
                .addComponent(panelTong, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(420, Short.MAX_VALUE))
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelTong;
    private javax.swing.JTable tableExtensionE;
    private javax.swing.JTable tableListSB11;
    // End of variables declaration//GEN-END:variables

    @Override
    public String toString() {
        return "Thông tin pháp nhân";
    }

    @Override
    public JPanel getEnterpriseExtCreator() {
        return this;
    }

    public ExtEnterpriseSB112CellEdittor getsB112Cell() {
        return sB112Cell;
    }

    @Override
    public Lookup getEnterpriseExtLookup() {
        return null;
    }

    @Override
    public double getLevelNumber() {
        sB112Cell.loadCombo();
        return 1.12;
    }

    @Override
    public void reset() {
        idEnterprise = 0;
        sB112Cell.reset();
        tableExtensionE.setValueAt(" ", 0, 1);
        tableExtensionE.setValueAt(" ", 1, 1);

        tableListSB11.setValueAt(" ", 0, 1);
        tableListSB11.setValueAt(" ", 1, 1);
        tableListSB11.setValueAt(" ", 2, 1);
        tableListSB11.setValueAt(" ", 3, 1);

        tableListSB11.setValueAt(" ", 0, 3);
        tableListSB11.setValueAt(" ", 1, 3);
        tableListSB11.setValueAt(" ", 2, 3);
        tableExtensionE.setRowHeight(26);
        tableListSB11.setRowHeight(26);

    }

    private void saveName() {
        try {
            nameEn = tableExtensionE.getValueAt(1, 1).toString().trim();
        } catch (Exception e) {
            nameEn = " ";
        }
        try {
            nameVN = tableExtensionE.getValueAt(0, 1).toString().trim();
        } catch (Exception e) {
            nameVN = " ";
        }
    }

    private void saveDate() {
        c1 = Calendar.getInstance();
        c2 = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        d1 = new Date();
        d2 = new Date();
        try {
            String date = tableListSB11.getValueAt(1, 1).toString();
            d1 = sdf.parse(date);
            c1.setTime(d1);
        } catch (Exception e) {
            d1 = null;
        }

        try {
            String dateR = tableListSB11.getValueAt(1, 3).toString();
            d2 = sdf.parse(dateR);
            c2.setTime(d2);
        } catch (Exception e) {
            d2 = null;
        }
    }

    private void saveTax() {
        try {
            register = Integer.parseInt(tableListSB11.getValueAt(0, 3).toString().trim());
        } catch (Exception ex) {
            register = 0;
        }
        try {
            c = Integer.parseInt(tableListSB11.getValueAt(2, 1).toString().trim());
        } catch (Exception e) {
            c = 0;
        }
        try {
            idTax = Integer.parseInt(tableListSB11.getValueAt(2, 3).toString().trim());
        } catch (Exception e) {
            idTax = 0;
        }
    }

    private void saveTypeAndState() {
        try {
            type = tableListSB11.getValueAt(0, 1).toString().trim();
        } catch (Exception e) {
            type = " ";
        }
        try {
            state = tableListSB11.getValueAt(3, 1).toString().trim();
        } catch (Exception e) {
            state = " ";
        }

    }

    @Override
    public IEntity save() {
        SubEnterprise bean;
        if (idEnterprise == 0) {
            return null;
        }
        bean = dao.getByObjectId(String.valueOf(idEnterprise));
        if (bean == null) {
            bean = new SubEnterprise();
        }
        bean.setEnterpriseIdActual(idEnterprise);

        saveName();
        saveDate();
        saveTax();
        saveTypeAndState();


        bean.setBookRegisterBussines(c);
        if (d1 != null) {
            bean.setDateRegister(c1);
        } else {
            bean.setDateRegister(null);
        }
        if (d2 != null) {
            bean.setDateStart(c2);
        } else {
            bean.setDateStart(null);
        }
        bean.setIdTax(idTax);
        bean.setNameEnglish(nameEn);
        bean.setRegister(register);
        bean.setType(type);
        bean.setNameVN(nameVN);
        bean.setState(state);
        dao.update(bean);
        return bean;

    }
    // Liên thông font cỡ chữ, màu

    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = tableListSB11.getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        tableListSB11.setFont(new Font(font, 0, size));
        tableExtensionE.setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        SB112CellRedenrer.installInColumn(tableListSB11, colorL, null, colorD, null);
        SB112CellRedenrer.installInColumn(tableExtensionE, colorL, null, colorD, null);
        tableListSB11.repaint();
        tableExtensionE.repaint();

    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = tableListSB11.getFont().getFontName();
        size = listS.get(0).getSizeWord();

        tableListSB11.setFont(new Font(font, 0, size));
        tableExtensionE.setFont(new Font(font, 0, size));

    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tableListSB11.setForeground(color);
        tableExtensionE.setForeground(color);
        tableListSB11.repaint();
        tableExtensionE.repaint();
    }

    @Override
    public void resetColorTitle() {
//        listS = sotfwareBN.selectAll();
//        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
//        tableListSB11.getTableHeader().setForeground(color);
//        tableExtensionE.getTableHeader().setForeground(color);
//        tableListSB11.repaint();
//        tableExtensionE.repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        tableListSB11.setSelectionBackground(color);
        tableExtensionE.setSelectionBackground(color);
        tableListSB11.repaint();
        tableExtensionE.repaint();
    }

    @Override
    public void getObject(String id) {
        IEnterpriseBN enterpriseBN = Lookup.getDefault().lookup(IEnterpriseBN.class);
        Enterprise e = enterpriseBN.getByObjectId(id);
        if (e == null) {
            return;
        }
        try {
            SubEnterprise bean = dao.getByObjectId(String.valueOf(e.getId()));
            tableExtensionE.setValueAt(bean.getNameVN(), 0, 1);
            tableExtensionE.setValueAt(bean.getNameEnglish(), 1, 1);
            tableListSB11.setValueAt(bean.getType(), 0, 1);
            tableListSB11.setValueAt(bean.getRegister(), 0, 3);

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String dateStart = "";
            String dateRegister = "";
            try {
                Calendar c11 = bean.getDateStart();
                dateStart = sdf.format(c11.getTime());
            } catch (Exception ex) {
                dateStart = "";
            }

            try {
                Calendar c22 = bean.getDateRegister();
                dateRegister = sdf.format(c22.getTime());
            } catch (Exception ex) {
                dateRegister = "";
            }
            tableListSB11.setValueAt(dateStart, 1, 1);
            tableListSB11.setValueAt(dateRegister, 1, 3);
            tableListSB11.setValueAt(bean.getBookRegisterBussines(), 2, 1);
            tableListSB11.setValueAt(bean.getIdTax(), 2, 3);
            tableListSB11.setValueAt(bean.getState(), 3, 1);
            ExtEnterpriseSB112CellEdittor enterpriseSB112Cell = new ExtEnterpriseSB112CellEdittor();
            enterpriseSB112Cell.getTxtBookRegisterBussines().setText(String.valueOf(bean.getBookRegisterBussines()));
            enterpriseSB112Cell.getTxtIdTax().setText(String.valueOf(bean.getIdTax()));
            enterpriseSB112Cell.getTxtRegister().setText(String.valueOf(bean.getRegister()));
            tableListSB11.getColumnModel().getColumn(1).setCellEditor(enterpriseSB112Cell);
            tableListSB11.getColumnModel().getColumn(3).setCellEditor(enterpriseSB112Cell);
        } catch (Exception ex) {
        }
    }

    private void tableMousePressed(MouseEvent evt) {
        SB11Tutorial sB11Tutorial = new SB11Tutorial();
        JTable table = (JTable) evt.getSource();
        if (table == tableListSB11) {
            if (tableListSB11.getSelectedRow() == 0 && tableListSB11.getSelectedColumn() == 1) {
                addFormEditID(4.101, "");
            }
            if (tableListSB11.getSelectedRow() == 0 && tableListSB11.getSelectedColumn() == 3) {
                addFormEditID(4.103, sB11Tutorial.getTxtRegister().getText());
            }
            if (tableListSB11.getSelectedRow() == 1 && tableListSB11.getSelectedColumn() == 1) {
                addFormEditID(4.111, sB11Tutorial.getTxtDateS().getText());
            }
            if (tableListSB11.getSelectedRow() == 1 && tableListSB11.getSelectedColumn() == 3) {
                addFormEditID(4.113, sB11Tutorial.getTxtDateR().getText());
            }
            if (tableListSB11.getSelectedRow() == 2 && tableListSB11.getSelectedColumn() == 1) {
                addFormEditID(4.21, sB11Tutorial.getTxtNumR().getText());
            }
            if (tableListSB11.getSelectedRow() == 2 && tableListSB11.getSelectedColumn() == 3) {
                addFormEditID(4.23, sB11Tutorial.getTxtKindOfTax().getText());
            }
            if (tableListSB11.getSelectedRow() == 3) {
                addFormEditID(4.13, "");
            }
        }
        if (table == tableExtensionE) {
            if (tableExtensionE.getSelectedRow() == 0) {
                addFormEditID(4.01, sB11Tutorial.getTxtNameTV().getText());
            }
            if (tableExtensionE.getSelectedRow() == 1) {
                addFormEditID(4.02, sB11Tutorial.getTxtNameTA().getText());
            }
        }
    }

    public void addFormEditID(double i, String str) {
        Collection<? extends HelpTutorialEnterprise> allSave = Lookup.getDefault().lookupAll(HelpTutorialEnterprise.class);
        for (HelpTutorialEnterprise editCookie : allSave) {
            editCookie.getTutorial(i, str);
        }
    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện thông tin kinh doanh của doanh nghiệp";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }

    @Override
    public void enableTable(boolean ok) throws IOException {
        tableExtensionE.setEnabled(ok);
        tableListSB11.setEnabled(ok);
    }

    @Override
    public List<JTable> getTables() {
        List<JTable> lt = new ArrayList<JTable>();
        lt.add(tableListSB11);
        lt.add(tableExtensionE);
        return lt;
    }

    @Override
    public void setEntity(IEntity entity) {
        idEnterprise = entity.getId();
    }
}
