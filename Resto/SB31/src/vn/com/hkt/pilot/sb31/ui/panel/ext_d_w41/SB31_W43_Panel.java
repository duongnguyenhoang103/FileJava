/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExtensionEnterprisePanel.java
 *
 * Created on Dec 21, 2011, 4:35:59 PM
 */
package vn.com.hkt.pilot.sb31.ui.panel.ext_d_w41;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IDepartmentBN;
import vn.com.hkt.basic.api.IMission;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.basic.api.ISystemSotfwareBN;
import vn.com.hkt.pilot.department.viewer.api.HelpTutorialDepartment;
import vn.com.hkt.pilot.department.viewer.api.IDepartmentExtCreater;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnableTable;
import vn.com.hkt.pilot.enterprise.viewer.api.IGetObject;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Mission;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.sb31.Installer;
import vn.com.hkt.pilot.sb31.deparment.extW41.entity.DepartmentExtSB31_W43;
import vn.com.hkt.pilot.sb31.department.extW41.dao.DepartmentExtSB31W43_DAO;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.ui.main.ui.api.creater.ISaveExtention;

/**
 *
 * @author longnt
 */
@ServiceProvider(service = IDepartmentExtCreater.class)
public class SB31_W43_Panel extends javax.swing.JPanel implements IDepartmentExtCreater,
        ISaveExtention, IEnableTable, IResetFontSize, IGetObject, IUserInterface {

    private int size;
    private String font;
    private ISystemSotfwareBN sotfwareBN;
    private List<SystemSoftware> listS;
    private ExtDepartmentW43Cell departmentW43Cell;
    private int i = 1;
    private int idDepartment = 0;
    private DefaultTableModel modelW43;
    private DepartmentExtSB31W43_DAO dao;
    private IPersonBN personBN;

    /** Creates new form ExtensionEnterprisePanel */
    public SB31_W43_Panel() {
        initComponents();
        sotfwareBN = Lookup.getDefault().lookup(ISystemSotfwareBN.class);
        listS = new ArrayList<SystemSoftware>();
        dao = new DepartmentExtSB31W43_DAO();
        listS = sotfwareBN.selectAll();
        departmentW43Cell = new ExtDepartmentW43Cell(20);
        personBN = Lookup.getDefault().lookup(IPersonBN.class);
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());

        tableW43.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableW43.getColumnModel().getColumn(0).setMaxWidth(100);
        tableW43.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableW43.getColumnModel().getColumn(2).setMaxWidth(100);
        tableW43.setRowSelectionAllowed(true);
        tableW43.setColumnSelectionAllowed(false);
        tableW43.setSelectionBackground(new Color(192, 210, 224));
        tableW43.setForeground(color);
        TableCell tableCell = new TableCell();
        tableW43.getColumnModel().getColumn(2).setCellRenderer(tableCell);

        tableW43.getColumnModel().getColumn(0).setCellEditor(departmentW43Cell);
        tableW43.getColumnModel().getColumn(1).setCellEditor(departmentW43Cell);
        tableW43.getColumnModel().getColumn(2).setCellEditor(departmentW43Cell);
        departmentW43Cell.resetCell();

        StripedTableCellRenderer.installInColumn(tableW43, colorL, null, colorD, null);
        tableW43.setTableHeader(null);

        jScrollPane1.setViewportBorder(null);

        panelTong.removeAll();
        panelTong.setBackground(new Color(242, 241, 240));
        panelTong.setLayout(new GridLayout());
        panelTong.add(jScrollPane1);
        this.removeAll();
        this.setLayout(new BorderLayout());
        this.add(jPanel1, BorderLayout.CENTER);
        this.setBackground(new Color(242, 241, 240));
        JLabel label = new JLabel("  ");
        label.setBackground(new Color(242, 241, 240));
        label.setFont((new Font(" ", Font.BOLD, 14)));
        label.setPreferredSize(new Dimension(WIDTH, 30));
        this.add(label, BorderLayout.NORTH);

        tableW43.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                repainTable(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                repainTable(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                repainTable(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                repainTable(e);
                tableMousePressed(e);
                // if(){}

            }
        });
    }

    @Override
    public String toString() {
        return "  ";
    }

    public JTable getTableW43() {
        return this.tableW43;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tableW43 = new javax.swing.JTable();

        panelTong.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelTong.setPreferredSize(new java.awt.Dimension(827, 161));

        jScrollPane1.setMinimumSize(new java.awt.Dimension(107, 107));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(107, 107));

        tableW43.setBackground(new java.awt.Color(242, 241, 240));
        tableW43.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{
                { "Họ tên", "Vai trò", "Tỷ lệ" },
                {   "", "", "" }, {   "", "", "" }
                , {   "", "", "" } , {   "", "", "" }
            },
            new String[]{
                " ", " "," "
            }) {

                boolean[] canEdit = new boolean[]{
                    true, true,  true
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    if (rowIndex==0) return false;
                    return canEdit[columnIndex];
                }
            });
            tableW43.setIntercellSpacing(new java.awt.Dimension(0, 0));
            tableW43.setRowHeight(26);
            tableW43.setShowHorizontalLines(false);
            tableW43.setShowVerticalLines(false);
            jScrollPane1.setViewportView(tableW43);

            javax.swing.GroupLayout panelTongLayout = new javax.swing.GroupLayout(panelTong);
            panelTong.setLayout(panelTongLayout);
            panelTongLayout.setHorizontalGroup(
                panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTongLayout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(720, Short.MAX_VALUE))
            );
            panelTongLayout.setVerticalGroup(
                panelTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
            );

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(panelTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap(361, Short.MAX_VALUE))
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
    private javax.swing.JTable tableW43;
    // End of variables declaration//GEN-END:variables

    @Override
    public double getLevelNumber() {
        departmentW43Cell.loadCombo();
        return 3.13;
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
    public void reset() {

        idDepartment = 0;
        modelW43 = (DefaultTableModel) tableW43.getModel();
        modelW43.setRowCount(3);
        for (int j = 1; j < 3; j++) {
            tableW43.setValueAt(" ", j, 0);
            tableW43.setValueAt(" ", j, 1);
            tableW43.setValueAt(" ", j, 2);
        }
//        int i = 1;
//        boolean ok = true;
//        try {
//            while (ok) {
//                tableW43.setValueAt(" ", i, 0);
//                tableW43.setValueAt(" ", i, 1);
//                tableW43.setValueAt(" ", i, 2);
//                modelDiaChi.removeRow(i);
//                i++;
//            }
//        } catch (Exception ex) {
//            ok = false;
//        }
        departmentW43Cell.resetCell();
//        modelDiaChi = (DefaultTableModel) tableW43.getModel();
//        Object[] rows1 = {" ", " ", " "};
//        modelDiaChi.addRow(rows1);
        TableCell tableCell = new TableCell();
        tableW43.getColumnModel().getColumn(2).setCellRenderer(tableCell);
        tableW43.repaint();
    }

    private void repainTable(MouseEvent e) {
        tableW43.repaint();
    }

    private void tableMousePressed(MouseEvent e) {
        SB31_W43Tutorial sB31_W43Tutorial = new SB31_W43Tutorial();
        if (tableW43.getSelectedRow() == i) {
            if (i > personBN.selectAll().size()) {
                return;
            }
            modelW43 = (DefaultTableModel) tableW43.getModel();
            i++;
            Object[] rows1 = {" ", " ", " "};
            modelW43.addRow(rows1);
            tableW43.repaint();
        }
        if (tableW43.getSelectedColumn() == 0) {
            addFormEditID(3.0, sB31_W43Tutorial.getHoTen().getText());

        }
        if (tableW43.getSelectedColumn() == 1) {
            addFormEditID(3.1, sB31_W43Tutorial.getVaiTro().getText());
        }
        if (tableW43.getSelectedColumn() == 2) {
            addFormEditID(3.2, sB31_W43Tutorial.getTiLe().getText());

        }

    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = tableW43.getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        tableW43.setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(tableW43, colorL, null, colorD, null);
        tableW43.repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = tableW43.getFont().getFontName();
        size = listS.get(0).getSizeWord();
        tableW43.setFont(new Font(font, 0, size));

    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tableW43.setForeground(color);
        tableW43.repaint();
    }

    @Override
    public void resetColorTitle() {
//        listS = sotfwareBN.selectAll();
//        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
//        tableW43.getTableHeader().setForeground(color);
//        tableW43.repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        tableW43.setSelectionBackground(color);
        tableW43.repaint();
    }

    @Override
    public void getObject(String id) {
        IDepartmentBN departmentBN = Lookup.getDefault().lookup(IDepartmentBN.class);
        reset();
        Department department;
        try {
            department = departmentBN.getByObjectId(id);
            if (department == null) {
                return;
            }
        } catch (Exception ex) {
            return;
        }

        try {
            DepartmentExtSB31_W43 bean = dao.getByObjectId(String.valueOf(department.getId()));
            resultEvent(bean);
        } catch (Exception ex) {
        }
    }

    private void resultEvent(DepartmentExtSB31_W43 bean) {
        IMission missionBN = Lookup.getDefault().lookup(IMission.class);
        List<Integer> listPerson = new ArrayList<Integer>();
        List<Integer> listMisson = new ArrayList<Integer>();
        List<Float> listPercent = new ArrayList<Float>();
        try {
            listPerson = bean.getIdPersonActual();
            listMisson = bean.getIdMissionIdActual();
            listPercent = bean.getPercent();
            modelW43 = new DefaultTableModel();
            for (int i = 0; i < listPerson.size(); i++) {
                tableW43.setValueAt(personBN.getById(listPerson.get(i)), i + 1, 0);
                tableW43.setValueAt(missionBN.getById(listMisson.get(i)), i + 1, 1);
                tableW43.setValueAt((listPercent.get(i)), i + 1, 2);
            }
            departmentW43Cell.setCell(listPerson.size());
        } catch (Exception ex) {
        }
    }

    @Override
    public IEntity save() {
        List<Integer> listPerson = new ArrayList<Integer>();
        List<Integer> listMisson = new ArrayList<Integer>();
        List<Float> listPercent = new ArrayList<Float>();
        if (idDepartment == 0) {
            return null;
        }
        int n;
        try {
            n = modelW43.getRowCount();
        } catch (Exception ex) {
            n = 2;
        }
        int m = personBN.selectAll().size();
        for (int j = 1; j < n - 1; j++) {
            int idPerson;
            int idMission;
            float percent;
            try {
                Person p = (Person) tableW43.getValueAt(j, 0);
                idPerson = p.getId();
            } catch (Exception ex) {
                idPerson = 0;
            }

            try {
                Mission mission = (Mission) tableW43.getValueAt(j, 1);
                idMission = mission.getId();
            } catch (Exception ex) {
                idMission = 0;
            }
            try {
                //percent = Float.parseFloat(tableW43.getValueAt(j, 2).toString().trim()) ;

                percent = 2 * (m - (j - 1)) * 100 / (m * (m + 1));
            } catch (Exception ex) {
                percent = 0;
            }
            listPerson.add(idPerson);
            listMisson.add(idMission);
            listPercent.add(percent);
        }

        DepartmentExtSB31_W43 bean = dao.getByObjectId(String.valueOf(idDepartment));
        if (bean == null) {
            bean = new DepartmentExtSB31_W43();
        }
        bean.setDepartmentIdActual(idDepartment);
        bean.setIdPersonActual(listPerson);
        bean.setIdMissionIdActual(listMisson);
        bean.setPercent(listPercent);
        dao.update(bean);
        reset();
        return bean;
    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện danh sách thành viên dự án";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }

    private void addFormEditID(double d, String string) {
        Collection<? extends HelpTutorialDepartment> allSave = Lookup.getDefault().lookupAll(HelpTutorialDepartment.class);
        for (HelpTutorialDepartment editCookie : allSave) {
            editCookie.getTutorial(d, string);
        }
    }

    @Override
    public void enableTable(boolean ok) throws IOException {
        tableW43.setEnabled(ok);
    }

    @Override
    public List<JTable> getTables() {
        List<JTable> lt = new ArrayList<JTable>();
        lt.add(tableW43);
        return lt;
    }

    @Override
    public void setEntity(IEntity entity) {
        idDepartment = entity.getId();
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
            if (column == 2) {
                if (row == 0) {
                    this.setBackground(colorL);
                    return this;
                }
                if (row == 1) {
                    this.setBackground(colorD);
                    this.setText(table.getValueAt(row, column).toString());
                    return this;
                }
                if (row % 2 == 0) {
                    this.setBackground(colorL);
                    this.setText(table.getValueAt(row, column).toString());
                    return this;
                }
                if (row % 2 != 0) {
                    this.setBackground(colorD);
                    this.setText(table.getValueAt(row, column).toString());
                    return this;
                }
            }

            JLabel lblPercent = new JLabel();
            return lblPercent;
        }
    }
}
