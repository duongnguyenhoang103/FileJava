/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExtensionEnterprisePanel.java
 *
 * Created on Dec 21, 2011, 4:35:59 PM
 */
package vn.com.hkt.pilot.sb22.person.extW23.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import vn.com.hkt.basic.api.ISystemSotfwareBN;
import vn.com.hkt.pilot.enterprise.viewer.api.SaveCookie;
import vn.com.hkt.pilot.entities.City;
import vn.com.hkt.pilot.entities.Country;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.sb22.Installer;
import vn.com.hkt.pilot.sb22.person.extW23.dao.AddressInfoBN;
import vn.com.hkt.pilot.sb22.person.extW23.dao.SubPersonSB22BN;
import vn.com.hkt.pilot.sb22.person.extW23.entity.AddressInfo;
import vn.com.hkt.pilot.sb22.person.extW23.entity.SubPersonSB22;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;

/**
 *
 * @author longnt
 */
public class ExtensionPersonPanel extends javax.swing.JPanel implements SaveCookie,IUserInterface {

    private SubPersonSB22BN subPersonSB22DAO;
    private AddressInfoBN addressInfoDAO;
    private int idPersonActual;
    private List<Integer> listAddressID;
    private PersonExtSB22Cell cell;
    private ISystemSotfwareBN sotfwareBN = Lookup.getDefault().lookup(ISystemSotfwareBN.class);
    private List<SystemSoftware> listS = new ArrayList<SystemSoftware>();

    /**
     * Creates new form ExtensionEnterprisePanel
     */
    public ExtensionPersonPanel() {
        initComponents();
         listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tableExtensionPerson.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableExtensionPerson.getColumnModel().getColumn(0).setMaxWidth(100);
        tableExtensionPerson.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableExtensionPerson.getColumnModel().getColumn(2).setMaxWidth(100);
        tableExtensionPerson.setRowSelectionAllowed(true);
        tableExtensionPerson.setColumnSelectionAllowed(false);
        tableExtensionPerson.setSelectionBackground(new Color(192, 210, 224));
        tableExtensionPerson.setForeground(color);
        StripedTableCellRenderer.installInColumn(tableExtensionPerson, colorL, null, colorD, null);
        tableExtensionPerson.setTableHeader(null);

        tableDiaChi.setTableHeader(null);
        cell = new PersonExtSB22Cell(3);
        tableDiaChi.getColumnModel().getColumn(1).setCellEditor(cell);
        tableDiaChi.getColumnModel().getColumn(2).setCellEditor(cell);
        tableDiaChi.getColumnModel().getColumn(3).setCellEditor(cell);
        tableDiaChi.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableDiaChi.getColumnModel().getColumn(0).setMaxWidth(100);
        tableDiaChi.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableDiaChi.getColumnModel().getColumn(2).setMaxWidth(100);
        tableDiaChi.setRowSelectionAllowed(true);
        tableDiaChi.setColumnSelectionAllowed(false);
        tableDiaChi.setSelectionBackground(new Color(192, 210, 224));
        tableDiaChi.setForeground(color);
        StripedTableCellRenderer.installInColumn(tableDiaChi, colorL, null, colorD, null);

        jScrollPane1.setViewportBorder(null);

        panelTong.removeAll();
        panelTong.setLayout(new BorderLayout());
        panelTong.add(tableExtensionPerson, BorderLayout.NORTH);
        panelTong.add(jScrollPane2, BorderLayout.CENTER);
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(jPanel1, BorderLayout.CENTER);
        JLabel label = new JLabel("          Thông tin nghề nghiệp");
        label.setFont((new Font(" ", Font.BOLD, 12)));
        label.setPreferredSize(new Dimension(WIDTH, 30));
        this.add(label, BorderLayout.NORTH);
    }

    public void reset(int quantity) {
        cell.reset();
        
        tableExtensionPerson.setValueAt("", 0, 1);
        tableExtensionPerson.setValueAt("", 0, 3);
        tableExtensionPerson.setValueAt("", 1, 1);
        tableExtensionPerson.setValueAt("", 1, 3);

        for (int i = 0; i < quantity; i++) {
            tableDiaChi.setValueAt("", i, 1);
            tableDiaChi.setValueAt("", i, 2);
            tableDiaChi.setValueAt("", i, 3);
        }
    }

    @Override
    public String toString() {
        return "Thông tin nghề nghiệp";
    }

    public JTable getTablePerson() {
        return this.tableExtensionPerson;
    }

    public JTable getTableDiaChi() {
        return this.tableDiaChi;
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
        tableExtensionPerson = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDiaChi = new javax.swing.JTable();

        panelTong.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTong.setPreferredSize(new java.awt.Dimension(827, 164));

        tableExtensionPerson.setBackground(new java.awt.Color(242, 241, 240));
        tableExtensionPerson.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Tel", " ", "Fax", " "},
                {"Email", " ", "Web", " "}
            },
            new String [] {
                "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableExtensionPerson.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tableExtensionPerson.setRowHeight(26);
        tableExtensionPerson.setShowHorizontalLines(false);
        tableExtensionPerson.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tableExtensionPerson);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(104, 104));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(104, 104));

        tableDiaChi.setBackground(new java.awt.Color(242, 241, 240));
        tableDiaChi.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{
                {"Địa chỉ 1", " ", " ", " "},
                {"        2", " ", " ", " "},
                {"        3", " ", " ", " "},
                {"         ", " ", " ", " "}
            },
            new String[]{
                "", "", "", ""
            }) {

                boolean[] canEdit = new boolean[]{
                    false, true, true, true
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });
            tableDiaChi.setIntercellSpacing(new java.awt.Dimension(0, 0));
            tableDiaChi.setRowHeight(26);
            tableDiaChi.setShowHorizontalLines(false);
            tableDiaChi.setShowVerticalLines(false);
            jScrollPane2.setViewportView(tableDiaChi);

            javax.swing.GroupLayout panelTongLayout = new javax.swing.GroupLayout(panelTong);
            panelTong.setLayout(panelTongLayout);
            panelTongLayout.setHorizontalGroup(
                panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
            );
            panelTongLayout.setVerticalGroup(
                panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTongLayout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap(358, Short.MAX_VALUE))
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
    private javax.swing.JTable tableDiaChi;
    private javax.swing.JTable tableExtensionPerson;
    // End of variables declaration//GEN-END:variables

   
    @Override
    public void SaveCookie() throws IOException {
        TopComponent tc = WindowManager.getDefault().findTopComponent("PersonCreatorTopComponent");
        listAddressID = new ArrayList<Integer>();
        subPersonSB22DAO = new SubPersonSB22BN();
        addressInfoDAO = new AddressInfoBN();

        if (tc.isShowing()) {
            SubPersonSB22 beanSubPersonBN22 = subPersonSB22DAO.getByObjectId(String.valueOf(idPersonActual));
            if (beanSubPersonBN22 == null) {
                beanSubPersonBN22 = new SubPersonSB22();
            }
            beanSubPersonBN22.setPersonIdActual(idPersonActual);
            beanSubPersonBN22.setTel(tableExtensionPerson.getValueAt(0, 1).toString());
            beanSubPersonBN22.setFax(tableExtensionPerson.getValueAt(0, 3).toString());
            beanSubPersonBN22.setEmail(tableExtensionPerson.getValueAt(1, 1).toString());
            beanSubPersonBN22.setWeb(tableExtensionPerson.getValueAt(1, 3).toString());

            AddressInfo beanAddressInfo = null;
            for (int i = 0; i < tableDiaChi.getRowCount(); i++) {
                if (tableDiaChi.getValueAt(i, 1).toString().trim().length() > 0) {
                    beanAddressInfo = new AddressInfo();
                    Country country = (Country) tableDiaChi.getValueAt(i, 2);
                    City city = (City) tableDiaChi.getValueAt(i, 3);
                    beanAddressInfo.setAddressInfoName(tableDiaChi.getValueAt(i, 1).toString());
                    beanAddressInfo.setCountryIdActual(country.getId());
                    beanAddressInfo.setCityIdActual(city.getId());
                    addressInfoDAO.insert(beanAddressInfo);
                }
                listAddressID.add(beanAddressInfo.getId());
            }
            beanSubPersonBN22.setAddressIdActual(listAddressID);
            subPersonSB22DAO.insert(beanSubPersonBN22);
        }
    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện thông tin nghề nghiệp của cá nhân";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }

    @Override
    public boolean checkValidate() {
       return true;
    }
}
