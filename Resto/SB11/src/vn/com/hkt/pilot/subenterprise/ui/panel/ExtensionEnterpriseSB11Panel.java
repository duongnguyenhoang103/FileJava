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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.ISystemSotfwareBN;
import vn.com.hkt.pilot.enterprise.viewer.api.HelpTutorialEnterprise;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnableTable;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseExtCreator;
import vn.com.hkt.pilot.enterprise.viewer.api.IGetObject;
import vn.com.hkt.pilot.enterprise.viewer.api.ResetCookie;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.sbenterprise.Installer;
import vn.com.hkt.pilot.sbenterprise.entity.BusinessArea;
import vn.com.hkt.pilot.sbenterprise.entity.SubEnterprise;
import vn.com.hkt.pilot.subenterprise.dao.BusinessAreaBN;
import vn.com.hkt.pilot.subenterprise.dao.SubEnterpriseBN;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.ui.main.ui.api.creater.ISaveExtention;

/**
 *
 * @author longnt
 */
@ServiceProvider(service = IEnterpriseExtCreator.class)
public class ExtensionEnterpriseSB11Panel extends javax.swing.JPanel implements IEnterpriseExtCreator,
        ISaveExtention, IEnableTable, IResetFontSize, ActionListener, IGetObject, IUserInterface {

    private SubEnterpriseBN dao = new SubEnterpriseBN();
    private int size;
    private String font;
    private ISystemSotfwareBN sotfwareBN;
    private List<SystemSoftware> listS;
    private JComboBox comboBoxKD;
    private BusinessAreaBN businessAreaBN;
    private DefaultComboBoxModel modelBusiness;
    private DefaultTableModel model;
    private boolean flag1 = false;
    private ExtEnterpriseSB11Cell cell;
    private int quantity = 10;
    private int idEnterprise = 0;
    private SubEnterprise subEnterprise = null;

    /**
     * Creates new form ExtensionEnterprisePanel
     */
    public ExtensionEnterpriseSB11Panel() {
        initComponents();
        cell = new ExtEnterpriseSB11Cell(quantity);
        sotfwareBN = Lookup.getDefault().lookup(ISystemSotfwareBN.class);
        listS = new ArrayList<SystemSoftware>();
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        businessAreaBN = new BusinessAreaBN();

        tableExtensionE.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableExtensionE.getColumnModel().getColumn(0).setMaxWidth(100);
        tableExtensionE.setRowSelectionAllowed(true);
        tableExtensionE.setColumnSelectionAllowed(false);
        tableExtensionE.setSelectionBackground(new Color(192, 210, 224));
        StripedTableCellRenderer.installInColumn(tableExtensionE, colorL, null, colorD, null);
        tableExtensionE.setForeground(color);
        tableExtensionE.setTableHeader(null);

        jScrollPane1.setViewportBorder(null);

        panelTong.removeAll();
        panelTong.setBackground(new Color(242, 241, 240));
        panelTong.setLayout(new BorderLayout());
        panelTong.add(jScrollPane1, BorderLayout.NORTH);
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(jPanel1, BorderLayout.CENTER);
        this.setBackground(new Color(242, 241, 240));


        // Tạo tiêu để cho table
        JPanel panelKD = new JPanel();
        panelKD.setLayout(new GridLayout());
        panelKD.setPreferredSize(new Dimension(WIDTH, 30));
        JPanel panelKD1 = new JPanel();
        JPanel panelKD2 = new JPanel();

        panelKD.add(panelKD1);
        panelKD.add(panelKD2);
        comboBoxKD = new JComboBox();
        panelKD1.setLayout(new GridLayout());
        JLabel label = new JLabel("   Lĩnh vực KD VN");
        label.setBackground(new Color(242, 241, 240));
        label.setFont((new Font(" ", Font.BOLD, 14)));
        label.setPreferredSize(new Dimension(WIDTH, 28));
        panelKD1.setPreferredSize(new Dimension(WIDTH, 29));
        panelKD1.add(label);
        panelKD1.add(comboBoxKD);
        this.add(panelKD, BorderLayout.NORTH);

        comboBoxKD.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboMousePressed(evt);
            }
        });
        tableExtensionE.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
        modelBusiness = new DefaultComboBoxModel();
        loadCombo();
        comboBoxKD.setModel(modelBusiness);
        comboBoxKD.addActionListener(this);
    }

    public JTable getTableExtensionE() {
        return this.tableExtensionE;
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

        panelTong.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTong.setPreferredSize(new java.awt.Dimension(827, 165));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(130, 130));

        tableExtensionE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Business Code", "Desciption"},
                {"", ""},
                {"", ""},
                {"", ""},
                {"", ""}
            },
            new String [] {
                "", ""
            }
        ));
        tableExtensionE.setRowHeight(26);
        tableExtensionE.setShowHorizontalLines(false);
        tableExtensionE.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tableExtensionE);

        javax.swing.GroupLayout panelTongLayout = new javax.swing.GroupLayout(panelTong);
        panelTong.setLayout(panelTongLayout);
        panelTongLayout.setHorizontalGroup(
            panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongLayout.createSequentialGroup()
                .addGap(247, 247, 247)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap(448, Short.MAX_VALUE))
        );
        panelTongLayout.setVerticalGroup(
            panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelTong, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(386, Short.MAX_VALUE))
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
    private javax.swing.JPanel panelTong;
    private javax.swing.JTable tableExtensionE;
    // End of variables declaration//GEN-END:variables

    @Override
    public String toString() {
        return "Lĩnh vực KD VN";
    }

    @Override
    public JPanel getEnterpriseExtCreator() {

        return this;
    }

    @Override
    public Lookup getEnterpriseExtLookup() {
          loadCombo();
        return null;
    }

    @Override
    public double getLevelNumber() {
        return 1.11;
    }

    @Override
    public IEntity save() {
        List<Integer> list = new ArrayList<Integer>();
        int row = 1;
        if (tableExtensionE.getModel().getRowCount() <= 5) {
            BusinessArea b;
            boolean ok = true;
            while (ok) {
                try {
                    b = (BusinessArea) tableExtensionE.getValueAt(row, 0);
                    list.add(b.getId());
                    row++;
                } catch (Exception ex) {
                    ok = false;
                }
            }
        } else {
            row = 1;
            boolean ok = true;
            BusinessArea b;
            while (ok) {
                try {
                    b = (BusinessArea) tableExtensionE.getValueAt(row, 0);
                    list.add(b.getId());
                    row++;
                } catch (Exception ex) {
                    ok = false;
                }
            }
        }


        SubEnterprise bean;
        if (idEnterprise == 0) {
            return null;
        }
        bean = dao.getByObjectId(String.valueOf(idEnterprise));
        if (bean == null) {
            bean = new SubEnterprise();
        }
        if (subEnterprise != null) {
            bean = dao.getById(subEnterprise.getId());
        }

        bean.setBusinessAreasIdActual(list);
        dao.update(bean);
        return bean;
    }

    private void cboMousePressed(MouseEvent evt) {
        addFormTable(4, " ");
    }

    public void loadCombo() {
        flag1 = true;
        modelBusiness.removeAllElements();
        for (BusinessArea bean : businessAreaBN.selectAll()) {
            modelBusiness.addElement(bean);
        }
    }

    @Override
    public void reset() {
        idEnterprise = 0;
        subEnterprise = null;
        cell.reset();

        int total = tableExtensionE.getRowCount();
        for (int i=1;i< total;i++){
            tableExtensionE.setValueAt("", i, 0);
            tableExtensionE.setValueAt("", i, 1);
        }


        
        tableExtensionE.setRowHeight(26);
    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = tableExtensionE.getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        tableExtensionE.setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(tableExtensionE, colorL, null, colorD, null);
        tableExtensionE.repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = tableExtensionE.getFont().getFontName();
        size = listS.get(0).getSizeWord();
        tableExtensionE.setFont(new Font(font, 0, size));
//        for (int i = 0; i < tableExtensionE.getRowCount(); i++) {
//            tableExtensionE.setRowHeight(i, size + 10);
//        }
    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tableExtensionE.setForeground(color);
        tableExtensionE.repaint();
    }

    @Override
    public void resetColorTitle() {
//        listS = sotfwareBN.selectAll();
//        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
//        tableExtensionE.getTableHeader().setForeground(color);
//        tableExtensionE.repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        tableExtensionE.setSelectionBackground(color);
        tableExtensionE.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!tableExtensionE.isEnabled()) {
            return;
        }
        boolean flag = false;
        TopComponent tc = WindowManager.getDefault().findTopComponent("EnterpriseTutorialCreatorTopComponent");
        if (tc.isShowing()) {
            flag1 = true;
        } else {
            flag1 = false;
        }
        BusinessArea bean = (BusinessArea) comboBoxKD.getSelectedItem();
        if (bean != null) {
            if (tableExtensionE.getValueAt(4, 0).toString().trim().length() != 0) {
                model = (DefaultTableModel) tableExtensionE.getModel();
                Object[] rows1 = {bean, bean.getDescription()};
                model.addRow(rows1);
                tableExtensionE.repaint();
                // tableExtensionE.setModel(model);
            } else {
                for (int i = 1; i < 5; i++) {
                    if (flag == false && flag1 == false) {
                        if (tableExtensionE.getValueAt(i, 0).toString().trim().length() == 0) {
                            tableExtensionE.setValueAt(bean, i, 0);
                            tableExtensionE.setValueAt(bean.getDescription(), i, 1);
                            flag = true;
                        }
                    }
                }
            }
        }
    }

    // Lấy dữ liệu liên thông từ các bảng khác sang
    @Override
    public void getObject(String id) {
        IEnterpriseBN enterpriseBN = Lookup.getDefault().lookup(IEnterpriseBN.class);
        Enterprise e = enterpriseBN.getByObjectId(id);
        if (e == null) {
            return;
        }
        SubEnterprise bean = dao.getByObjectId(String.valueOf(e.getId()));
        subEnterprise = bean;
        List<Integer> listIdB = bean.getBusinessAreasIdActual();

        if (listIdB != null && listIdB.size() != -1) {
            if (listIdB.size() < 5) {
                BusinessArea businessArea[] = new BusinessArea[4];
                for (int i = 0; i < listIdB.size(); i++) {
                    businessArea[i] = businessAreaBN.getById(listIdB.get(i));
                    if (businessArea[i] != null) {
                        tableExtensionE.setValueAt(businessArea[i], i + 1, 0);
                        tableExtensionE.setValueAt(businessArea[i].getDescription(), i + 1, 1);
                    }
                }
                for (int i = listIdB.size(); i < 4; i++) {
                    businessArea[i] = new BusinessArea();
                    businessArea[i].setBusinessCode(" ");
                    businessArea[i].setDescription(" ");
                    tableExtensionE.setValueAt(businessArea[i], i + 1, 0);
                    tableExtensionE.setValueAt(businessArea[i].getDescription(), i + 1, 1);
                }
            } else {
                BusinessArea businessArea[] = new BusinessArea[listIdB.size()];
                for (int i = 0; i < 4; i++) {
                    businessArea[i] = businessAreaBN.getById(listIdB.get(i));
                    if (businessArea[i] != null) {
                        tableExtensionE.setValueAt(businessArea[i], i + 1, 0);
                        tableExtensionE.setValueAt(businessArea[i].getDescription(), i + 1, 1);
                    }
                }
                model = (DefaultTableModel) tableExtensionE.getModel();
                for (int i = 4; i < listIdB.size(); i++) {
                    try {
                        Object[] row = {businessArea[i].getBusinessCode(), businessArea[i].getDescription()};
                        model.addRow(row);
                    } catch (NullPointerException ex) {
                        System.out.print(ex);
                    }
                }

            }
        }
    }

    private void tableMousePressed(MouseEvent evt) {
        SB11Tutorial sB11Tutorial = new SB11Tutorial();
        JTable table = (JTable) evt.getSource();
        if (table == tableExtensionE) {
            if (tableExtensionE.getSelectedColumn() == 0) {
                addFormTable(1.01, sB11Tutorial.getTxtBusiness().getText());
            }
            if (tableExtensionE.getSelectedColumn() == 1) {
                addFormTable(1.02, sB11Tutorial.getTxtDesscription().getText());
            }
        }

        // Reset select table
        Collection<? extends ResetCookie> allResetCookie = Lookup.getDefault().lookupAll(ResetCookie.class);
        for (ResetCookie rc : allResetCookie) {
            try {
                rc.resetSelectTable(this.getLevelNumber());
            } catch (IOException ex) {
            }
        }
    }

    private void addFormTable(double i, String str) {
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
        return "Giao diện thông tin kinh doanh chi tiết của doanh nghiệp";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }

    @Override
    public void enableTable(boolean ok) throws IOException {
        tableExtensionE.setEnabled(ok);
    }

    @Override
    public List<JTable> getTables() {
        List<JTable> lt = new ArrayList<JTable>();
        lt.add(tableExtensionE);
        return lt;
    }

    @Override
    public void setEntity(IEntity entity) {
        idEnterprise = entity.getId();
    }
}
