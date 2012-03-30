/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExtensionEnterprisePanel.java
 *
 * Created on Dec 21, 2011, 4:35:59 PM
 */
package vn.com.hkt.bom.operation.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.Installer;
import vn.com.hkt.basic.api.IClassificationBN;
import vn.com.hkt.basic.cookie.api.IClassificationCookie;
import vn.com.hkt.bom.operation.dao.OperationBN;
import vn.com.hkt.bom.operation.dao.OperationPartnerBN;
import vn.com.hkt.erm.department.dao.DepartmentBN;
import vn.com.hkt.erm.enterprise.dao.EnterpriseBN;
import vn.com.hkt.hrm.person.dao.PersonBN;
import vn.com.hkt.pilot.enterprise.viewer.api.ResetCookie;
import vn.com.hkt.pilot.entities.*;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.entities.system.dao.SystemSotfwareDAO;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.operation.viewer.api.HelpTutorialOperation;
import vn.com.hkt.pilot.operation.viewer.api.IGetObjectOperation;
import vn.com.hkt.pilot.operation.viewer.api.IOperationCreater;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;

/**
 *
 * @author longnt
 */
@ServiceProvider(service = IOperationCreater.class)
public class RCW58Panel extends javax.swing.JPanel implements IOperationCreater, IResetFontSize, ResetCookie,
        IUserInterface, ItemListener, IClassificationCookie , IGetObjectOperation {

    private int size;
    private String font;
    private SystemSotfwareDAO sotfwareBN = new SystemSotfwareDAO();
    private List<SystemSoftware> listS = new ArrayList<SystemSoftware>();
    private JLabel label;
    private JPanel panelBorder;
    private RCW58Cell rCW58Cell = new RCW58Cell();
    private int idOperation = -1;
    private static RCW58Panel rCW58Panel;
    private JComboBox cboClassification = null;
    private DefaultComboBoxModel modelClass;
    private static Classification classification;
    private OperationBN operationBN = new OperationBN();
    private EnterpriseBN enterpriseBN = new EnterpriseBN() ;
    private DepartmentBN departmentBN = new DepartmentBN();
    private PersonBN personBN = new PersonBN();
    private OperationPartnerBN dao = new OperationPartnerBN();

    public static RCW58Panel getRCW58Panel() {
        if (rCW58Panel == null) {
            rCW58Panel = new RCW58Panel();
        }
        return rCW58Panel;
    }

    public JComboBox getCboClassification() {
        return cboClassification;
    }

    /**
     * Creates new form ExtensionEnterprisePanel
     */
    public RCW58Panel() {
        initComponents();
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tableW58.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableW58.getColumnModel().getColumn(0).setMaxWidth(100);
        tableW58.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableW58.getColumnModel().getColumn(2).setMaxWidth(100);
        tableW58.setRowSelectionAllowed(true);
        tableW58.setColumnSelectionAllowed(false);
        tableW58.setSelectionBackground(new Color(192, 210, 224));
        tableW58.getColumnModel().getColumn(0).setCellEditor(rCW58Cell);
        tableW58.getColumnModel().getColumn(1).setCellEditor(rCW58Cell);
        tableW58.getColumnModel().getColumn(0).setCellRenderer(new TableCell());
        tableW58.getColumnModel().getColumn(1).setCellRenderer(new TableCell());
        tableW58.getColumnModel().getColumn(2).setCellRenderer(new TableCell());
        StripedTableCellRenderer.installInColumn(tableW58, colorL, null, colorD, null);
        tableW58.setTableHeader(null);
        tableW58.setForeground(color);
        jScrollPane1.setViewportBorder(null);

        panelTong.removeAll();
        panelTong.setBackground(new Color(242, 241, 240));
        panelTong.setLayout(new GridLayout());
        panelTong.add(tableW58);
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(jPanel1, BorderLayout.CENTER);
        this.setBackground(new Color(242, 241, 240));
        panelBorder = new JPanel();
        panelBorder.setBackground(new Color(242, 241, 240));
        panelBorder.setPreferredSize(new Dimension(WIDTH, 30));
        panelBorder.setLayout(new BorderLayout());
        label = new JLabel("      Thông tin chi tiết");
        label.setBackground(new Color(242, 241, 240));
        label.setFont((new Font(" ", Font.BOLD, 14)));
        label.setPreferredSize(new Dimension(300, 29));
        this.add(panelBorder, BorderLayout.NORTH);


        //
        tableW58.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                tableExtensionProductMousePressed(e);
            }
        });
        modelClass = new DefaultComboBoxModel();
        loadCombo();

        //cboClassification = new JComboBox(modelClass);
        cboClassification = new JComboBox(modelClass);

        cboClassification.setPreferredSize(new Dimension(100, 29));

        panelBorder.add(label, BorderLayout.WEST);
        panelBorder.add(cboClassification, BorderLayout.EAST);
        cboClassification.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseExited(MouseEvent e) {
                addFormEditID(0, " ");
            }
        });
        cboClassification.addItemListener(this);

    }

    @Override
    public String toString() {
        return "Thông tin chi tiết";
    }

    public JTable getTableW58() {
        return this.tableW58;
    }

    private void loadCombo() {
        List<Classification> list = new ArrayList<Classification>();
        IClassificationBN classificationBN = Lookup.getDefault().lookup(IClassificationBN.class);
        modelClass.removeAllElements();
        list = classificationBN.selectAll();
        if (!list.isEmpty()) {
            for (Classification bean : list) {
                modelClass.addElement(bean);
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
        panelTong = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableW58 = new javax.swing.JTable();

        panelTong.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTong.setPreferredSize(new java.awt.Dimension(827, 161));

        tableW58.setBackground(new java.awt.Color(242, 241, 240));
        tableW58.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{
                {"Khách hàng", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "}
            },
            new String[]{
                "", "", ""
            }) {

                boolean[] canEdit = new boolean[]{
                    true, true, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });
            tableW58.setIntercellSpacing(new java.awt.Dimension(0, 0));
            tableW58.setRowHeight(26);
            tableW58.setShowHorizontalLines(false);
            tableW58.setShowVerticalLines(false);
            jScrollPane1.setViewportView(tableW58);

            javax.swing.GroupLayout panelTongLayout = new javax.swing.GroupLayout(panelTong);
            panelTong.setLayout(panelTongLayout);
            panelTongLayout.setHorizontalGroup(
                panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
            );
            panelTongLayout.setVerticalGroup(
                panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTongLayout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                    .addGap(96, 96, 96))
            );

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private javax.swing.JTable tableW58;
    // End of variables declaration//GEN-END:variables

    @Override
    public double getLevelNumber() {
        return 5.58;
    }

    @Override
    public JPanel getOperationCreater() {
        RCW58Panel.getRCW58Panel().loadCombo();
        return RCW58Panel.getRCW58Panel();
    }

    @Override
    public Lookup getOperationLookup() {
        return null;
    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = tableW58.getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        tableW58.setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(tableW58, colorL, null, colorD, null);
        tableW58.repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = tableW58.getFont().getFontName();
        size = listS.get(0).getSizeWord();
        tableW58.setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tableW58.setForeground(color);
        tableW58.repaint();
    }

    @Override
    public void resetColorTitle() {
//        listS = sotfwareBN.selectAll();
//        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
//        tableW58.getTableHeader().setForeground(color);
//        label.setBackground(color);
//        tableW58.repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        tableW58.setSelectionBackground(color);
        tableW58.repaint();
    }

    public void Save(int id) {
        if (tableW58.getCellEditor() != null) {
            tableW58.getCellEditor().stopCellEditing();
        }
        tableW58.clearSelection();
        OperationPartnerBN dao = new OperationPartnerBN();
        OperationPartner bean = this.getOperation(true, id);
        dao.insert(bean);

    }

    public OperationPartner getOperation(boolean isInsert, int id) {
        idOperation = id;
        String parnershipType;
        String nameCustommer;
        OperationPartner bean = new OperationPartner();
        if (idOperation == -1) {
            return new OperationPartner();
        }
        if (!isInsert) {
            //  bean = dao.getByObjectId(id);
        }
        bean.setOperationIdActual(idOperation);
        boolean isCustomer = rCW58Cell.isIsCustommer();
        // Neu la doi tac
        if (isCustomer == true) {
            bean.setSupplierType(OperationPartner.SUPPLIER_TYPE_PARNER);
            // Nếu đối tác là tổ chức - công ty
            if (tableW58.getValueAt(0, 1).toString().contains("Tổ Chức")) {
                parnershipType = OperationPartner.PARNERSHIP_TYPE_ENTERPRISE;
                Enterprise e = (Enterprise) tableW58.getValueAt(1, 1);
                bean.setEnterpriseIdActual(e.getId());
                bean.setParnershipType(parnershipType);

            } else if (tableW58.getValueAt(0, 1).toString().contains("Cá Nhân")) {
                // Nếu đối tác là cá nhân
                parnershipType = OperationPartner.PARNERSHIP_TYPE_PERSON;
                Person p = (Person) tableW58.getValueAt(1, 1);
                bean.setEnterpriseIdActual(p.getId());
                bean.setParnershipType(parnershipType);
            } else {
                bean.setEnterpriseIdActual(0);
                bean.setParnershipType("");
            }
        } else { // Neu la vang lai
            bean.setSupplierType(OperationPartner.FIELD_SUPPLIER_IRREGULAR_NAME);
            nameCustommer = tableW58.getValueAt(2, 1).toString();
            bean.setSupplierIrregularName(nameCustommer);

        }
        return bean;
    }

    @Override
    public void resetCookie() throws IOException {
        tableW58.setValueAt(" ", 0, 1);
        tableW58.setValueAt(" ", 1, 1);
        tableW58.setValueAt(" ", 2, 1);
        tableW58.getColumnModel().getColumn(0).setCellEditor(rCW58Cell);
        tableW58.getColumnModel().getColumn(1).setCellEditor(rCW58Cell);
        tableW58.getColumnModel().getColumn(0).setCellRenderer(new TableCell());
        tableW58.getColumnModel().getColumn(1).setCellRenderer(new TableCell());
        tableW58.getColumnModel().getColumn(2).setCellRenderer(new TableCell());
        tableW58.setRowHeight(26);
    }

    @Override
    public void resetSelectTable(double d) throws IOException {
        tableW58.clearSelection();
    }

    private void tableExtensionProductMousePressed(MouseEvent evt) {
        RCW58Tutorail rCW58Tutorail = new RCW58Tutorail();
        if (tableW58.getSelectedColumn() == 0) {
            if (tableW58.getSelectedRow() == 1) {
                tableW58.repaint();
            }
            if (tableW58.getSelectedRow() == 2) {
                tableW58.repaint();
            }
        }
        tableW58.repaint();
        if (tableW58.getSelectedRow() == 1 && tableW58.getSelectedColumn() == 1) {
            addFormEditID(6.11, rCW58Tutorail.getDoiTac().getText());

        }
        if (tableW58.getSelectedRow() == 2 && tableW58.getSelectedColumn() == 1) {
            addFormEditID(6.21, rCW58Tutorail.getVangLai().getText());
        }
    }

    private void addFormEditID(double i, String str) {
        Collection<? extends HelpTutorialOperation> allSave = Lookup.getDefault().lookupAll(HelpTutorialOperation.class);
        for (HelpTutorialOperation editCookie : allSave) {
            editCookie.getTutorial(i, str);
        }
    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện thông khách hàng, đối tác";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cboClassification) {
            if (cboClassification.getSelectedItem() != null) {
                classification = (Classification) cboClassification.getSelectedItem();
            }
        }
    }

    @Override
    public Classification getClassification() {

        return classification;

    }

    @Override
    public void getObject(int id) {        
        Operation op = operationBN.getById(id);
        if (op != null) {
            OperationPartner bean = dao.select(OperationPartner.FIELD_OPERATION_ID_ACTUAL, String.valueOf(op.getId())).get(0);
            int idEnterprise = bean.getEnterpriseIdActual();
            int idPerson = bean.getPersonIdActual();
            // Neu co enterprise
            if (idEnterprise != 0) {
                Enterprise e = enterpriseBN.getById(idEnterprise);
                tableW58.setValueAt("Tổ Chức", 0, 1);
                tableW58.setValueAt(e, 1, 1);
                rCW58Cell.getRdb1().setSelected(true);
                tableW58.repaint();
            } else if (idPerson != 0) {
                Person p = personBN.getById(idPerson);
                tableW58.setValueAt("Cá Nhân", 0, 1);
                tableW58.setValueAt(p, 1, 1);
                rCW58Cell.getRdb1().setSelected(true);
                tableW58.repaint();
            } else {
                tableW58.setValueAt(" ", 0, 1);
                tableW58.setValueAt(bean.getSupplierIrregularName(), 2, 1);
                rCW58Cell.getRdb2().setSelected(true);
            }

        }
    }

    public class TableCell extends JLabel implements TableCellRenderer {

        public TableCell() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            listS = sotfwareBN.selectAll();
            Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
            Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
            if (column == 0) {
                if (row == 0) {
                    this.setText("Khách hàng");
                    this.setBackground(colorL);
                    return this;
                }
                if (row == 1) {
                    rCW58Cell.getRdb1().setBackground(colorD);
                    return rCW58Cell.getRdb1();
                }
                if (row == 2) {
                    rCW58Cell.getRdb2().setBackground(colorL);
                    return rCW58Cell.getRdb2();
                }
                if (row == 3) {
                    this.setBackground(colorD);
                    this.setText(" ");
                    return this;
                }
                if (row == 4) {
                    this.setBackground(colorL);
                    this.setText(" ");
                    return this;
                }
            }
            if (column == 1) {
                if (row == 0) {

                    this.setBackground(colorL);
                    this.setText(table.getValueAt(0, 1).toString());
                    return this;
                }
                if (row == 1) {
                    this.setBackground(colorD);
                    this.setText(table.getValueAt(1, 1).toString());
                    return this;
                }
                if (row == 2) {
                    this.setBackground(colorL);
                    this.setText(table.getValueAt(2, 1).toString());
                    return this;
                }
                if (row == 3) {
                    this.setBackground(colorD);
                    this.setText(" ");
                    return this;
                }
                if (row == 4) {
                    this.setBackground(colorL);
                    this.setText(" ");
                    return this;
                }
            }
            if (column == 2) {
                if (row == 0) {

                    this.setBackground(colorL);
                    this.setText(" ");
                    return this;
                }
                if (row == 1) {
                    this.setBackground(colorD);
                    this.setText(" ");
                    return this;
                }
                if (row == 2) {
                    this.setBackground(colorL);
                    this.setText(" ");
                    return this;
                }
                if (row == 3) {
                    this.setBackground(colorD);
                    this.setText(" ");
                    return this;
                }
                if (row == 4) {
                    this.setBackground(colorL);
                    this.setText(" ");
                    return this;
                }
            }
            this.setBackground(colorD);
            this.setText(" ");
            return this;
        }
    }

    public void saveOperation(int id) {
        OperationBN operationBN = new OperationBN();
        Operation operation = operationBN.getById(id);
        Classification classification = (Classification) cboClassification.getSelectedItem();
        operation.setClassificationIdActual(classification.getId());
    }

    @Override
    public List<JTable> getTables() {
        List<JTable> lt = new ArrayList<JTable>();
        lt.add(tableW58);
        return lt;
    }
}
