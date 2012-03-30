/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.hrm.person.ui;

import com.vn.hkt.core.cookie.api.FilterCokieTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.IOException;
import java.util.Hashtable;
import javax.swing.JPanel;
import vn.com.hkt.pilot.entities.Person;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.japura.gui.LinkLabel;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.WindowManager;
import vn.com.hkt.basic.api.ICountryBN;
import vn.com.hkt.basic.toolbar.BasicToolbarManager;
import vn.com.hkt.erm.department.dao.DepartmentBN;
import vn.com.hkt.erm.enterprise.dao.EnterpriseBN;
import vn.com.hkt.hrm.person.dao.PersonBN;
import vn.com.hkt.pilot.department.viewer.api.IDepartmentCreater;
import vn.com.hkt.pilot.department.viewer.api.IDepartmentExtCreater;
import vn.com.hkt.pilot.dialog.dao.CountryBN;
import vn.com.hkt.pilot.enterprise.viewer.api.*;
import vn.com.hkt.pilot.entities.Country;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.entities.system.dao.SystemSotfwareDAO;
import vn.com.hkt.pilot.person.viewer.api.IPersonCreater;
import vn.com.hkt.pilot.person.viewer.api.IPersonExtCreater;
import vn.com.hkt.pilot.person.viewer.api.IPersonExtViewer;
import vn.com.hkt.pilot.person.viewer.api.IPersonViewer;
import vn.com.hkt.pilot.report.api.IReportListGUI;
import vn.com.hkt.pilot.report.api.IReportManager;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.pilot.ui.setup.WidthTableBN;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.hrm.person.ui//ListPerson//EN",
autostore = false)
@TopComponent.Description(preferredID = "ListPersonTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.hrm.person.ui.ListPersonTopComponent")
@ActionReference(path = " " /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ListPersonAction",
preferredID = "ListPersonTopComponent")
@ServiceProvider(service = IPersonViewer.class)
public final class ListPersonTopComponent extends TopComponent implements MouseMotionListener, ViewCookieList, RemoveCookiePerson, ResetCookieList,
        IPersonViewer, FilterCokieTable, EditCookieList, SaveCookieList, IResetFontSize, IReportListGUI {

    @Override
    public String toString() {
        return "Thông tin chính";
    }
    private ListPersonPanel listPersonPanel;
    private DefaultTableModel model;
    private PersonBN personBN;
    private int itsRow = 0;
    private int itsColumn = 0;
    private boolean isEdit = false;
    private int size;
    private String font;
    private SystemSotfwareDAO sotfwareBN = new SystemSotfwareDAO();
    private List<SystemSoftware> listS = new ArrayList<SystemSoftware>();
    private TableRowSorter<TableModel> tableRowSorter;

    public ListPersonTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ListPersonTopComponent.class, "CTL_ListPersonTopComponent"));
        setToolTipText(NbBundle.getMessage(ListPersonTopComponent.class, "HINT_ListPersonTopComponent"));
        listPersonPanel = new ListPersonPanel();
        personBN = new PersonBN();
        panelForm.setLayout(new GridLayout());
        panelForm.add(listPersonPanel);

        listPersonPanel.getTableListP().addMouseMotionListener(this);
    }

    private void addRow() {
        Enterprise enterpriseChoise = null;
        ICountryBN countryBN = new CountryBN();
        DepartmentBN departmentBN = new DepartmentBN();
        EnterpriseBN enterpriseBN = new EnterpriseBN();
        enterpriseChoise = BasicToolbarManager.getBasicToolbar().getEnterprise();
        String[] header = {"Mã", "Ảnh", "Họ tên", "Quốc kỳ", "Quốc tịch", "Công ty", "Logo Công ty", "Bộ phận"};
        model = new DefaultTableModel(header, 0){
             boolean[] canEdit = new boolean[]{
                false, false, false, true, true, true, true, true
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        List<Person> list = new ArrayList<Person>();
        if (enterpriseChoise == null) {
            list = personBN.selectAll();
        } else {
            list = personBN.select(Person.FIELD_ENTERPRISE_ID_ACTUAl, String.valueOf(enterpriseChoise.getId()));
        }
        for (Person bean : list) {
            Enterprise enterprise = enterpriseBN.getById(bean.getEnterpriseIdActual());  // Lấy ra 1 Enterprise theo mã
            Department department = departmentBN.getById(bean.getDepartmentIdActual());
            Country country = countryBN.getById(bean.getCountryIdActual());
            String flag = "";
            if (country != null) {
                flag = country.getNationalFlag();
            }
            String personId = "";
            if (bean != null) {
                personId = String.valueOf(bean.getPersonId());
            }
            String nationality = "";
            if (country != null) {
                nationality = country.getNationality();
            }
            Object[] rows = {personId, "Anh", bean, flag, nationality, enterprise, "Logo Cty", department};
            model.addRow(rows);
        }
        listPersonPanel.getTableListP().removeAll();
        listPersonPanel.getTableListP().setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelForm = new javax.swing.JPanel();

        setBackground(new java.awt.Color(153, 255, 153));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setForeground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 622, Short.MAX_VALUE)
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(103, 103, 103))
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
    private javax.swing.JPanel panelForm;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        loadData();
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    protected void setupTable() {
        WidthTableBN widthTableBN = new WidthTableBN();
        listPersonPanel.getTableListP().setSelectionBackground(new Color(192, 210, 224));
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(listPersonPanel.getTableListP(), colorL, null, colorD, null);
        listPersonPanel.getTableListP().setRowHeight(26);

        int sizeId = widthTableBN.getWidth1();
        int sizeLogo = widthTableBN.getWidth2();
        int sizeName = widthTableBN.getWidth3();

        listPersonPanel.getTableListP().getColumnModel().getColumn(0).setMaxWidth(sizeId);
        listPersonPanel.getTableListP().getColumnModel().getColumn(0).setPreferredWidth(sizeId);

        listPersonPanel.getTableListP().getColumnModel().getColumn(1).setMaxWidth(sizeLogo);
        listPersonPanel.getTableListP().getColumnModel().getColumn(1).setPreferredWidth(sizeLogo);

        listPersonPanel.getTableListP().getColumnModel().getColumn(2).setMaxWidth(sizeName);
        listPersonPanel.getTableListP().getColumnModel().getColumn(2).setPreferredWidth(sizeName);

        listPersonPanel.getTableListP().getColumnModel().getColumn(0).setResizable(false);
        listPersonPanel.getTableListP().getColumnModel().getColumn(1).setResizable(false);
        listPersonPanel.getTableListP().getColumnModel().getColumn(2).setResizable(false);
        listPersonPanel.getTableListP().getColumnModel().getColumn(3).setResizable(false);
        listPersonPanel.getTableListP().getColumnModel().getColumn(4).setResizable(false);
        listPersonPanel.getTableListP().getColumnModel().getColumn(5).setResizable(false);
        listPersonPanel.getTableListP().getColumnModel().getColumn(6).setResizable(false);
        listPersonPanel.getTableListP().getColumnModel().getColumn(7).setResizable(false);
        listPersonPanel.getTableListP().getTableHeader().setReorderingAllowed(false);
    }

    private void loadDataEdit() {
        addRow();
        resetTableEdit();
        setupTable();
        filterTable(getTable(), model);
    }

    @Override
    public JPanel getPersonViewer() {
        return this.listPersonPanel;
    }

    @Override
    public Lookup getPersonViewerLookup() {
        return null;
    }

    @Override
    public double getLevelNumber() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JTable getTable() {
        return listPersonPanel.getTableListP();
    }

    @Override
    public void ViewCookieList() {
        if (isEdit == true) {
            loadDataEdit();
        } else {
            loadData();
        }
    }

    @Override
    public void remove() throws IOException {
        int i = listPersonPanel.getTableListP().getSelectedRow(); // chọn hàng để xóa
        if (i >= 0) {
            Person bean = (Person) listPersonPanel.getTableListP().getValueAt(i, 2); // Lay object Person
            Collection<? extends RemoveCookiePerson> allRemoveCookie = Lookup.getDefault().lookupAll(RemoveCookiePerson.class);
            for (RemoveCookiePerson r : allRemoveCookie) {
                if (r instanceof IPersonExtViewer || r instanceof IPersonViewer) {
                    r.removeObject(bean.getId());
                }
            }
            loadDataEdit();
        }
    }

    @Override
    public void removeObject(int id) throws IOException {
        personBN.delete(id);
        BasicToolbarManager.getBasicToolbar().loadPerson();
    }

    @Override
    public void resetCookie() throws IOException {
        ViewCookieList();
    }

    @Override
    public void EditCookieList(boolean b) throws IOException {
        isEdit = b;
        if (isEdit == true) {
            loadDataEdit();
        } else {
            loadData();
        }
    }

    @Override
    public void SaveCookieList() throws IOException {
        SaveData();
    }

    private void resetTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        TableCell tableCell = new TableCell(colorL, colorD);
        listPersonPanel.getTableListP().getColumnModel().getColumn(2).setCellRenderer(tableCell);
        listPersonPanel.getTableListP().getColumnModel().getColumn(5).setCellRenderer(tableCell);
        listPersonPanel.getTableListP().getColumnModel().getColumn(7).setCellRenderer(tableCell);
    }

    public void SaveData() {
        int n = listPersonPanel.getTableListP().getRowCount();
        if (n <= 0) {
            return;
        }
        for (int i = 0; i < n; i++) {
            String idPerson = "";
            if (listPersonPanel.getTableListP().getValueAt(i, 0) != null) {
                idPerson = listPersonPanel.getTableListP().getValueAt(i, 0).toString();
            }
            Person p = personBN.getByObjectId(idPerson);
            String namePerson = listPersonPanel.getTableListP().getValueAt(i, 2).toString();

        }
        loadDataEdit();
    }

    public void loadData() {
        addRow();
        resetTable();
        setupTable();
        filterTable(getTable(), model);
    }

    private void resetTableEdit() {
        DepartmentBN departmentBN = new DepartmentBN();
        DefaultComboBoxModel modelDepartment = new DefaultComboBoxModel();
        modelDepartment.addElement("");
        List<Department> listD = new ArrayList<Department>();
        listD = departmentBN.selectAll();
        for (Department bean : listD) {
            modelDepartment.addElement(bean);
        }

        JComboBox cbxDepartment = new JComboBox(modelDepartment);
        listPersonPanel.getTableListP().getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(cbxDepartment));

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        JTable aTable = (JTable) e.getSource();
        itsRow = aTable.rowAtPoint(e.getPoint());
        itsColumn = aTable.columnAtPoint(e.getPoint());
        aTable.repaint();
    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = listPersonPanel.getTableListP().getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        listPersonPanel.getTableListP().setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(listPersonPanel.getTableListP(), colorL, null, colorD, null);
        listPersonPanel.getTableListP().repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = listPersonPanel.getTableListP().getFont().getFontName();
        size = listS.get(0).getSizeWord();
        listPersonPanel.getTableListP().setFont(new Font(font, 0, size));
//        for (int i = 0; i < listPersonPanel.getTableListP().getRowCount(); i++) {
//            listPersonPanel.getTableListP().setRowHeight(i, size + 10);
//        }
    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        listPersonPanel.getTableListP().setForeground(color);
        listPersonPanel.getTableListP().repaint();
    }

    @Override
    public void resetColorTitle() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
        listPersonPanel.getTableListP().getTableHeader().setForeground(color);
        listPersonPanel.getTableListP().repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        listPersonPanel.getTableListP().setSelectionBackground(color);
        listPersonPanel.getTableListP().repaint();
    }

    @Override
    public void exportReport() {
        IReportManager reportManager = Lookup.getDefault().lookup(IReportManager.class);

        if (reportManager
                == null) {
            return;
        }
        String[] keys1 = {"prTenDoanhNghiep", "prDiaChi", "prTenBaoCao", "prNgayLap", "prNguoiLapBaoCao"};
        String[] values1 = {"Công Ty Cổ Phần Tư Vấn Quản Trị HKT", "66 Trần Thái Tông- Dịch Vọng, Cầu Giấy, Hà Nội", "", "16/03/2012", "Đồng Thị Tâm"};
        String keys2[] = new String[getTable().getColumnCount()];
        String values2[] = new String[getTable().getColumnCount()];
        for (int i = 0; i < getTable().getColumnCount(); i++) {
            keys2[i] = "prColumn_" + i;
            values2[i] = getTable().getColumnName(i).toString();
        }
        String[] keys = new String[keys1.length + keys2.length];
        String[] values = new String[values1.length + values2.length];
        System.arraycopy(keys1, 0, keys, 0, keys1.length);
        System.arraycopy(keys2, 0, keys, keys1.length, keys2.length);
        System.arraycopy(values1, 0, values, 0, values1.length);
        System.arraycopy(values2, 0, values, values1.length, values2.length);
        HashMap map = reportManager.getHashMap(keys, values);
        DefaultTableModel md = reportManager.getModel(getTable());
        reportManager.setReportManager(map, md);
        reportManager.exportReport();
    }

    @Override
    public void filterTable(JTable table, DefaultTableModel tableModel) {
        tableRowSorter = new TableRowSorter<TableModel>(tableModel);
        table.setRowSorter(tableRowSorter);
    }

    @Override
    public void filterTable(String stringKey, int column) {
        tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + stringKey, column));
        tableRowSorter.setSortKeys(null);
    }

    @Override
    public Hashtable getTableHeader() {
        Hashtable hashtable = new Hashtable();
        int n = getTable().getColumnCount();
        int i;
        for (i = 0; i < n; i++) {
            hashtable.put(i, getTable().getColumnName(i).toString());
        }

        return hashtable;
    }

    public class TableCell extends JLabel implements TableCellRenderer {

        private Color colorL;
        private Color colorD;

        public TableCell(Color colorL, Color colorD) {
            this.colorL = colorL;
            this.colorD = colorD;
            setOpaque(true);
        }
        private LinkLabel label = new LinkLabel();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            table.addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    mouseEvent(evt);
                }
            });
            if (row == itsRow && column == itsColumn) {

                if (itsColumn == 2) {
                    if (table.getValueAt(itsRow, 2) != null) {
                        label.setText(table.getValueAt(itsRow, 2).toString());
                    } else {
                        label.setText(" ");
                    }
                }
                if (itsColumn == 5) {
                    if (table.getValueAt(itsRow, 5) != null) {
                        label.setText(table.getValueAt(itsRow, 5).toString());
                    } else {
                        label.setText(" ");
                    }
                }
                if (itsColumn == 7) {
                    if (table.getValueAt(itsRow, 7) != null) {
                        label.setText(table.getValueAt(itsRow, 7).toString());
                    } else {
                        label.setText(" ");
                    }
                }


                return label;
            } else {
                if (value != null) {
                    this.setText(value.toString());
                } else {
                    this.setText(" ");
                }
                if (row == 0) {
                    this.setBackground(colorL);
                    return this;
                }
                if (row == 1) {
                    this.setBackground(colorD);
                    return this;
                }
                if (row % 2 == 0) {
                    this.setBackground(colorL);
                    return this;
                }
                if (row % 2 != 0) {
                    this.setBackground(colorD);
                    return this;
                }
                return this;
            }
            //
        }
        // Xử lý click đúp lấy dữ liệu từ list sang form nhap

        public void mouseEvent(MouseEvent e) {
            if (isEdit == true) {
                return;
            }
            JTable aTable = (JTable) e.getSource();
            int selct = aTable.getSelectedRow();
            int sectcol = aTable.getSelectedColumn();            
            if (selct == itsRow && sectcol == 2) {
                if (aTable.getValueAt(selct, sectcol) != null) {
                    Person p1 = (Person) aTable.getValueAt(selct, sectcol);
                    openCreatorcomponent("PersonViewerTopComponent", "PersonCreatorTopComponent");
                    Collection<? extends IGetObject> allSave = Lookup.getDefault().lookupAll(IGetObject.class);
                    for (IGetObject getObject : allSave) {
                       // if (getObject instanceof IPersonCreater || getObject instanceof IPersonExtCreater) {
                            getObject.getObject(p1.getPersonId());
                       // }
                    }
                    toolbarPerson(p1);
                }

            }


            if (selct == itsRow && sectcol == 5) {
                if (aTable.getValueAt(selct, sectcol) != null) {
                    Enterprise e1 = (Enterprise) aTable.getValueAt(selct, sectcol);
                    openCreatorcomponent("PersonViewerTopComponent", "EnterpriseCreatorTopComponent");
                    Collection<? extends IGetObject> allSave = Lookup.getDefault().lookupAll(IGetObject.class);
                    for (IGetObject getObject : allSave) {
                      //  if (getObject instanceof IEnterpriseCreator || getObject instanceof IEnterpriseExtCreator) {
                            getObject.getObject(e1.getEnterpriseId());
                       // }
                    }
                    toolbarEnterprise(e1);
                }

            }

            if (selct == itsRow && sectcol == 7) {
                if (aTable.getValueAt(selct, sectcol) != null) {
                    Department d1 = (Department) aTable.getValueAt(selct, sectcol);
                    openCreatorcomponent("PersonViewerTopComponent", "DepartmentCreatorTopComponent");
                    Collection<? extends IGetObject> allSave = Lookup.getDefault().lookupAll(IGetObject.class);
                    for (IGetObject getObject : allSave) {
                        if (getObject instanceof IDepartmentCreater || getObject instanceof IDepartmentExtCreater) {
                            getObject.getObject(d1.getDepartmentId());
                        }
                    }
                }
            }

            aTable.clearSelection();
        }
    }

    private void toolbarEnterprise(Enterprise bean) {
        BasicToolbarManager.getBasicToolbar().loadEnterprise();
        BasicToolbarManager.getBasicToolbar().setEnterprise(bean);
    }

    private void toolbarPerson(Person bean) {
        BasicToolbarManager.getBasicToolbar().loadPerson();
        BasicToolbarManager.getBasicToolbar().setPerson(bean);
    }

    public void openCreatorcomponent(String s1, String s2) {
        TopComponent tc1 = WindowManager.getDefault().findTopComponent(s1);
        if (tc1.isOpened()) {
            tc1.close();
        }
        TopComponent tc = WindowManager.getDefault().findTopComponent(s2);
        if (tc.isOpened() == false) {
            tc.open();
            tc.requestActive();
        } else {
            tc.requestActive();
        }
        if (tc instanceof IEnableButton) {
            try {
                ((IEnableButton) tc).enableButton();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }   
}
