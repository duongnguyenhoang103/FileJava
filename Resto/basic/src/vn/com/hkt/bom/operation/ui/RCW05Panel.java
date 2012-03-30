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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.Installer;
import vn.com.hkt.basic.toolbar.BasicToolbarManager;
import vn.com.hkt.bom.operation.dao.OperationBN;
import vn.com.hkt.erm.department.dao.DepartmentBN;
import vn.com.hkt.erm.enterprise.dao.EnterpriseBN;
import vn.com.hkt.hrm.person.dao.PersonBN;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Operation;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.entities.system.dao.SystemSotfwareDAO;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.operation.viewer.api.CreateKeyOperation;
import vn.com.hkt.pilot.operation.viewer.api.HelpTutorialOperation;
import vn.com.hkt.pilot.operation.viewer.api.IGetObjectOperation;
import vn.com.hkt.pilot.operation.viewer.api.IOperationCreater;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.ui.main.ui.api.creater.ISaveBasic;

/**
 *
 * @author longnt
 */
@ServiceProvider(service = IOperationCreater.class)
public class RCW05Panel extends javax.swing.JPanel implements IOperationCreater,
        IResetFontSize, ISaveBasic, IUserInterface,
        CreateKeyOperation , IGetObjectOperation{

    private int size;
    private String font;
    private SystemSotfwareDAO sotfwareBN = new SystemSotfwareDAO();
    private List<SystemSoftware> listS = new ArrayList<SystemSoftware>();
    private RCW05Cell rCW05Cell = new RCW05Cell();
    private Operation operation = null;
    private OperationBN operationBN = new OperationBN();
    private EnterpriseBN enterpriseBN = new EnterpriseBN() ;
    private DepartmentBN departmentBN = new DepartmentBN();
    private PersonBN personBN = new PersonBN();
    /**
     * Creates new form ExtensionEnterprisePanel
     */
    public RCW05Panel() {
        initComponents();
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tableW05.setTableHeader(null);
        tableW05.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableW05.getColumnModel().getColumn(0).setMaxWidth(100);
        tableW05.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableW05.getColumnModel().getColumn(2).setMaxWidth(100);
        tableW05.setRowSelectionAllowed(true);
        tableW05.setColumnSelectionAllowed(false);
        tableW05.getColumnModel().getColumn(1).setCellEditor(rCW05Cell);
        tableW05.getColumnModel().getColumn(3).setCellEditor(rCW05Cell);
        StripedTableCellRenderer.installInColumn(tableW05, colorL, null, colorD, null);
        tableW05.setForeground(color);

        panelTong.removeAll();
        panelTong.setBackground(new Color(242, 241, 240));
        panelTong.setLayout(new BorderLayout());

        panelTong.add(tableW05, BorderLayout.NORTH);
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(jPanel1, BorderLayout.CENTER);
        this.setBackground(new Color(242, 241, 240));
        JLabel label = new JLabel("             Thông tin cơ bản");
        label.setBackground(new Color(242, 241, 240));
        label.setFont((new Font(" ", Font.BOLD, 14)));
        label.setPreferredSize(new Dimension(WIDTH, 30));
        this.add(label, BorderLayout.NORTH);
        resetColorMouse();
        this.resetSize();

        tableW05.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
        Enterprise enterprise1 = BasicToolbarManager.getBasicToolbar().getEnterprise();
        if (enterprise1 != null) {
            tableW05.setValueAt(enterprise1, 1, 3);
            rCW05Cell.resetCombo(enterprise1);
        }
    }

    public JTable getTableO5() {
        return this.tableW05;
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tableW05 = new javax.swing.JTable();

        panelTong.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTong.setPreferredSize(new java.awt.Dimension(827, 165));

        tableW05.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Ngày tháng năm", " ", " ", " "},
                {"Tên nghiệp vụ", " ", "Công ty", " "},
                {"Người chịu trách nhiệm", " ", "Bộ phận ,dự án", " "}
            },
            new String [] {
                "", "", "", ""
            }
        ));
        tableW05.setRowHeight(26);
        tableW05.setShowHorizontalLines(false);
        tableW05.setShowVerticalLines(false);
        jScrollPane2.setViewportView(tableW05);
        tableW05.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(RCW05Panel.class, "RCW05Panel.tableW05.columnModel.title0")); // NOI18N
        tableW05.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(RCW05Panel.class, "RCW05Panel.tableW05.columnModel.title1")); // NOI18N
        tableW05.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(RCW05Panel.class, "RCW05Panel.tableW05.columnModel.title2")); // NOI18N
        tableW05.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(RCW05Panel.class, "RCW05Panel.tableW05.columnModel.title3")); // NOI18N

        javax.swing.GroupLayout panelTongLayout = new javax.swing.GroupLayout(panelTong);
        panelTong.setLayout(panelTongLayout);
        panelTongLayout.setHorizontalGroup(
            panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongLayout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(249, Short.MAX_VALUE))
        );
        panelTongLayout.setVerticalGroup(
            panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTongLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
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
                .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(417, Short.MAX_VALUE))
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
    private javax.swing.JTable tableW05;
    // End of variables declaration//GEN-END:variables

    @Override
    public String toString() {
        return "Thông tin cơ bản";
    }

    @Override
    public double getLevelNumber() {
        return 5.05;
    }

    @Override
    public JPanel getOperationCreater() {
        return this;
    }

    @Override
    public Lookup getOperationLookup() {
        return null;
    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = tableW05.getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        tableW05.setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(tableW05, colorL, null, colorD, null);
        tableW05.repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = tableW05.getFont().getFontName();
        size = listS.get(0).getSizeWord();
        tableW05.setFont(new Font(font, 0, size));

    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tableW05.setForeground(color);
        tableW05.repaint();
    }

    @Override
    public void resetColorTitle() {
//        listS = sotfwareBN.selectAll();
//        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
//        tableW05.getTableHeader().setForeground(color);
//        tableW05.repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        tableW05.setSelectionBackground(color);
        tableW05.repaint();
    }

    @Override
    public IEntity save() {
        return Save();
    }

    @Override
    public void reset() {
        try {
            operation = null;
            tableW05.setValueAt(" ", 0, 1);
            tableW05.setValueAt(" ", 1, 1);
            tableW05.setValueAt(" ", 2, 1);
            tableW05.setValueAt(" ", 2, 3);
            rCW05Cell.getTxtNameO().setText(" ");
            tableW05.setRowHeight(26);
            Enterprise enterprise1 = BasicToolbarManager.getBasicToolbar().getEnterprise();
            if (enterprise1 != null) {
                tableW05.setValueAt(enterprise1, 1, 3);
                rCW05Cell.resetCombo(enterprise1);
            }
            RCW58Panel.getRCW58Panel().resetCookie();
            RCGiaPanel.getRCGiaPanel().resetCookie();
            RCTongTienPanel.getRCTongTienPanel().resetCookie();
            resetColorMouse();
            resetColorWord();
            resetFont();
            resetSize();
            resetColorRowTable();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public void createKey() {
        Enterprise enterprise1 = BasicToolbarManager.getBasicToolbar().getEnterprise();
        rCW05Cell.loadCboEnterprise();
        if (enterprise1 != null) {
            tableW05.setValueAt(enterprise1, 1, 3);
            rCW05Cell.resetCombo(enterprise1);
        }
    }

    protected IEntity Save() {
        int idEnterprise;
        int idPerson;
        int idDepartment;
        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date d1 = new Date();
        try {
            String date = tableW05.getValueAt(0, 1).toString();
            try {
                d1 = sdf.parse(date);
            } catch (ParseException ex) {
                d1 = d1;
            }
            c1.setTime(d1);
        } catch (Exception e) {
            d1 = null;
        }
        String name = tableW05.getValueAt(1, 1).toString().trim();
        if (tableW05.getValueAt(1, 3) != null && tableW05.getValueAt(1, 3).toString().trim().length() != 0) {
            Enterprise enterprise = (Enterprise) tableW05.getValueAt(1, 3);
            idEnterprise = enterprise.getId();
        } else {
            idEnterprise = 0;
        }
        if (tableW05.getValueAt(2, 1) != null && tableW05.getValueAt(2, 1).toString().trim().length() != 0) {
            Person person = (Person) tableW05.getValueAt(2, 1);
            idPerson = person.getId();
        } else {
            idPerson = 0;
        }
        if (tableW05.getValueAt(2, 3) != null && tableW05.getValueAt(2, 3).toString().trim().length() != 0) {
            Department department = (Department) tableW05.getValueAt(2, 3);
            idDepartment = department.getId();
        } else {
            idDepartment = 0;
        }

        Operation opertion = new Operation();
        if (d1 != null) {
            opertion.setDateTime(c1);
        } else {
            opertion.setDateTime(null);
        }

        opertion.setOperationName(name);
        opertion.setDepartmentIdActual(idDepartment);
        opertion.setEnterpriseIdActual(idEnterprise);
        opertion.setPersonIdActual(idPerson);
        operationBN.insert(opertion);
//        RCW58Panel.getRCW58Panel().Save(opertion.getId());
//        RCGiaPanel.getRCGiaPanel().Save(opertion.getId());
//        RCTongTienPanel.getRCTongTienPanel().Save(opertion.getId());
        return opertion;
    }

    private void tableMousePressed(MouseEvent evt) {
        OperationTutorial operationTutorial = new OperationTutorial();
        JTable table = (JTable) evt.getSource();
        if (table == tableW05) {
            if (tableW05.getSelectedRow() == 0) {
                addFormEditID(0.1, operationTutorial.getTxtDate().getText());
            }
            if (tableW05.getSelectedRow() == 1 && tableW05.getSelectedColumn() == 1) {
                addFormEditID(0.2, operationTutorial.getTxtNameOperation().getText());
            }
            if (tableW05.getSelectedRow() == 1 && tableW05.getSelectedColumn() == 3) {
                addFormEditID(0.3, operationTutorial.getTxtEnterprise().getText());
            }
            if (tableW05.getSelectedRow() == 2 && tableW05.getSelectedColumn() == 1) {
                addFormEditID(0.4, operationTutorial.getTxtPerson().getText());
            }
            if (tableW05.getSelectedRow() == 2 && tableW05.getSelectedColumn() == 3) {
                addFormEditID(0.5, operationTutorial.getTxtDepartment().getText());
            }

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
        return "Giao diện trách nhiệm thực hiện nghiệp vụ";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }

    @Override
    public List<JTable> getTables() {
        List<JTable> lt = new ArrayList<JTable>();
        lt.add(tableW05);
        return lt;
    }

    @Override
    public void getObject(int id) {
        reset();
        Operation op = operationBN.getById(id);
        if (op != null){
            Enterprise enterprise = enterpriseBN.getById(op.getEnterpriseIdActual());
            Department department = departmentBN.getById(op.getDepartmentIdActual());
            Person person = personBN.getById(op.getPersonIdActual());
            
            Calendar c = op.getDateTime();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = "";
            if (c != null) date = sdf.format(c.getTime());
            
            tableW05.setValueAt(date, 0, 1);
            tableW05.setValueAt(op, 1, 1);
            tableW05.setValueAt(enterprise , 1, 3);
            tableW05.setValueAt(person, 2, 1);
            tableW05.setValueAt(department, 2, 3);
        }
    }
}
