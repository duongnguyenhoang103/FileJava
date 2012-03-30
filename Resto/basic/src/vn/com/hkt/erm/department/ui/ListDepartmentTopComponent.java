/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.erm.department.ui;

import com.vn.hkt.core.cookie.api.FilterCokieTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.IOException;
import java.util.Hashtable;
import javax.swing.JPanel;
import org.openide.util.Exceptions;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.basic.api.IDepartmentBN;
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
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.WindowManager;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.basic.api.IProductBN;
import vn.com.hkt.basic.api.IWidthTableBN;
import vn.com.hkt.basic.toolbar.BasicToolbarManager;
import vn.com.hkt.pilot.department.viewer.api.IDepartmentCreater;
import vn.com.hkt.pilot.department.viewer.api.IDepartmentExtCreater;
import vn.com.hkt.pilot.department.viewer.api.IDepartmentViewer;
import vn.com.hkt.pilot.enterprise.viewer.api.*;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.entities.system.dao.SystemSotfwareDAO;
import vn.com.hkt.pilot.person.viewer.api.IPersonCreater;
import vn.com.hkt.pilot.person.viewer.api.IPersonExtCreater;
import vn.com.hkt.pilot.product.viewer.api.IProductCreater;
import vn.com.hkt.pilot.product.viewer.api.IProductExtCreater;
import vn.com.hkt.pilot.report.api.IReportListGUI;
import vn.com.hkt.pilot.report.api.IReportManager;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.erm.department.ui//ListDepartment//EN",
autostore = false)
@TopComponent.Description(preferredID = "ListDepartmentTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.erm.department.ui.ListDepartmentTopComponent")
@ActionReference(path = " " /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ListDepartmentAction",
preferredID = "ListDepartmentTopComponent")
@ServiceProvider(service = IDepartmentViewer.class)
public final class ListDepartmentTopComponent extends TopComponent implements MouseMotionListener, ViewCookieList, RemoveCookieDepartment, ResetCookieList,
        IDepartmentViewer, FilterCokieTable, EditCookieList, SaveCookieList, IResetFontSize, IReportListGUI {

    private ListDepartmentPanel listDepartmentPanel = new ListDepartmentPanel();
    private DefaultTableModel model;
    private IDepartmentBN departmentBN;
    private IEnterpriseBN enterpriseBN;
    private IPersonBN personBN;
    private int itsRow = 0;
    private int itsColumn = 0;
    private boolean isEdit = false;
    private int size;
    private String font;
    private SystemSotfwareDAO sotfwareBN = new SystemSotfwareDAO();
    private List<SystemSoftware> listS = new ArrayList<SystemSoftware>();
    private TableRowSorter<TableModel> tableRowSorter;

    public ListDepartmentTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ListDepartmentTopComponent.class, "CTL_ListDepartmentTopComponent"));
        setToolTipText(NbBundle.getMessage(ListDepartmentTopComponent.class, "HINT_ListDepartmentTopComponent"));

        panelForm.setLayout(new GridLayout());
        panelForm.add(listDepartmentPanel);

        listDepartmentPanel.getTableListD().addMouseMotionListener(this);
        this.enterpriseBN = Lookup.getDefault().lookup(IEnterpriseBN.class);
        this.personBN = Lookup.getDefault().lookup(IPersonBN.class);
        this.departmentBN = Lookup.getDefault().lookup(IDepartmentBN.class);


    }

    private void addRow() {
        IProductBN productBN = Lookup.getDefault().lookup(IProductBN.class);
        String[] header = {"Mã dự án", "Logo", "Tên dự án", "Logo Công ty", "Tên công ty", "Phòng phụ thuộc", "Trưởng dự án", "Sản phẩm chính",};
        model = new DefaultTableModel(header, 0);
        List<Department> list = new ArrayList<Department>();
        Enterprise enterprise1 = BasicToolbarManager.getBasicToolbar().getEnterprise();
        if (enterprise1 != null) {
            list = departmentBN.filterDepartmentByEnterprise(enterprise1);
        } else {
            list = departmentBN.selectAll();
        }
        for (Department bean : list) {
            Enterprise enterprise = enterpriseBN.getById(bean.getEnterpriseIdActual());  // Lấy ra 1 Enterprise theo mã
            Product product = productBN.getById(bean.getProductIdActual());
            Department department = departmentBN.getById(bean.getDepartmentParentIdAcutal());
            Person person1 = personBN.getById(bean.getPersonIdActual());
            Object[] rows = {bean.getDepartmentId(), "Logo", bean, "Logo Cty", enterprise, department, person1, product};
            model.addRow(rows);
        }
        listDepartmentPanel.getTableListD().removeAll();
        listDepartmentPanel.getTableListD().setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelForm = new javax.swing.JPanel();

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 713, Short.MAX_VALUE)
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 311, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(53, 53, 53))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelForm;
    // End of variables declaration//GEN-END:variables

    public void loadData() {
        addRow();
        resetTable();
        setupTable();
        filterTable(getTable(), model);
    }

    public void loadDataEdit() {
        addRow();
        resetTableEdit();
        setupTable();
        filterTable(getTable(), model);

    }

    public void resetTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        TableCell tableCell = new TableCell(colorL, colorD);
        listDepartmentPanel.getTableListD().getColumnModel().getColumn(2).setCellRenderer(tableCell);
        listDepartmentPanel.getTableListD().getColumnModel().getColumn(4).setCellRenderer(tableCell);
        listDepartmentPanel.getTableListD().getColumnModel().getColumn(5).setCellRenderer(tableCell);
        listDepartmentPanel.getTableListD().getColumnModel().getColumn(6).setCellRenderer(tableCell);
        listDepartmentPanel.getTableListD().getColumnModel().getColumn(7).setCellRenderer(tableCell);
        listDepartmentPanel.getTableListD().setRowHeight(26);
    }

    public void resetTableEdit() {
        IProductBN productBN = Lookup.getDefault().lookup(IProductBN.class);
        DefaultComboBoxModel modelPerson = new DefaultComboBoxModel();
        modelPerson.addElement("");
        List<Person> listPerson = personBN.selectAll();
        for (Person p : listPerson) {
            modelPerson.addElement(p);
        }

        DefaultComboBoxModel modelEnterprise = new DefaultComboBoxModel();
        modelEnterprise.addElement("");
        List<Enterprise> list = new ArrayList<Enterprise>();
        list = enterpriseBN.selectAll();
        for (Enterprise bean : list) {
            modelEnterprise.addElement(bean);
        }

        DefaultComboBoxModel modelDepartment = new DefaultComboBoxModel();
        modelDepartment.addElement("");
        List<Department> listD = new ArrayList<Department>();
        listD = departmentBN.selectAll();
        for (Department bean : listD) {
            modelDepartment.addElement(bean);
        }


        DefaultComboBoxModel modelProduct = new DefaultComboBoxModel();
        modelProduct.addElement("");
        List<Product> listP = new ArrayList<Product>();
        listP = productBN.selectAll();
        for (Product bean : listP) {
            modelProduct.addElement(bean);
        }

        JComboBox cbxPerson = new JComboBox(modelPerson);
        JComboBox cbxEnterprise = new JComboBox(modelEnterprise);
        JComboBox cbxProduct = new JComboBox(modelProduct);
        JComboBox cbxDepartment = new JComboBox(modelDepartment);

        listDepartmentPanel.getTableListD().getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cbxEnterprise));
        listDepartmentPanel.getTableListD().getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(cbxDepartment));
        listDepartmentPanel.getTableListD().getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(cbxPerson));
        listDepartmentPanel.getTableListD().getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(cbxProduct));
        listDepartmentPanel.getTableListD().setRowHeight(26);

    }

    public void SaveData() {
        int n = listDepartmentPanel.getTableListD().getRowCount();
        if (n <= 0) {
            return;
        }
        for (int i = 0; i < n; i++) {

            Department d = (Department) listDepartmentPanel.getTableListD().getValueAt(i, 2);

            String nameDepartment = listDepartmentPanel.getTableListD().getValueAt(i, 2).toString();

            int idPerson;
            try {
                Person p = (Person) listDepartmentPanel.getTableListD().getValueAt(i, 6);
                idPerson = p.getId();
            } catch (Exception e) {
                idPerson = -1;
            }

            int idEnterprise;
            try {
                Enterprise e = (Enterprise) listDepartmentPanel.getTableListD().getValueAt(i, 4);
                idEnterprise = e.getId();
            } catch (Exception ex) {
                idEnterprise = -1;
            }

            int idProduct;
            try {
                Product product = (Product) listDepartmentPanel.getTableListD().getValueAt(i, 7);
                idProduct = product.getId();
            } catch (Exception ex) {
                idProduct = -1;
            }

            int idDParent = -1;
            try {
                Department dParent = (Department) listDepartmentPanel.getTableListD().getValueAt(i, 5);
                if (dParent.getId() != d.getDepartmentParentIdAcutal()) {
                    idDParent = dParent.getId();
                }
            } catch (Exception e) {
                idDParent = -1;
            }

            d.setDepartmentName(nameDepartment);
            d.setEnterpriseIdActual(idEnterprise);
            d.setPersonIdActual(idPerson);
            d.setProductIdActual(idProduct);
            d.setDepartmentParentIdAcutal(idDParent);
            departmentBN.update(d);
        }
        loadDataEdit();
    }

    private void toolbarEnterprise(Enterprise bean) {
        BasicToolbarManager.getBasicToolbar().loadEnterprise();
        BasicToolbarManager.getBasicToolbar().setEnterprise(bean);
    }

    private void toolbarPerson(Person bean) {
        BasicToolbarManager.getBasicToolbar().loadPerson();
        BasicToolbarManager.getBasicToolbar().setPerson(bean);
    }

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

    public void mouseEvent(MouseEvent e) {
    }

    protected void setupTable() {
        IWidthTableBN widthTableBN = Lookup.getDefault().lookup(IWidthTableBN.class);
        listDepartmentPanel.getTableListD().setSelectionBackground(new Color(192, 210, 224));
        StripedTableCellRenderer.installInColumn(listDepartmentPanel.getTableListD(), new Color(220, 228, 231), null, new Color(235, 239, 242), null);

        int sizeId = widthTableBN.getWidth1();
        int sizeLogo = widthTableBN.getWidth2();
        int sizeName = widthTableBN.getWidth3();

        listDepartmentPanel.getTableListD().getColumnModel().getColumn(0).setMaxWidth(sizeId);
        listDepartmentPanel.getTableListD().getColumnModel().getColumn(0).setPreferredWidth(sizeId);

        listDepartmentPanel.getTableListD().getColumnModel().getColumn(1).setMaxWidth(sizeLogo);
        listDepartmentPanel.getTableListD().getColumnModel().getColumn(1).setPreferredWidth(sizeLogo);

        listDepartmentPanel.getTableListD().getColumnModel().getColumn(2).setMaxWidth(sizeName);
        listDepartmentPanel.getTableListD().getColumnModel().getColumn(2).setPreferredWidth(sizeName);

        listDepartmentPanel.getTableListD().getColumnModel().getColumn(0).setResizable(false);
        listDepartmentPanel.getTableListD().getColumnModel().getColumn(1).setResizable(false);
        listDepartmentPanel.getTableListD().getColumnModel().getColumn(2).setResizable(false);
        listDepartmentPanel.getTableListD().getColumnModel().getColumn(3).setResizable(false);
        listDepartmentPanel.getTableListD().getColumnModel().getColumn(4).setResizable(false);
        listDepartmentPanel.getTableListD().getColumnModel().getColumn(5).setResizable(false);
        listDepartmentPanel.getTableListD().getColumnModel().getColumn(6).setResizable(false);
        listDepartmentPanel.getTableListD().getColumnModel().getColumn(7).setResizable(false);
        listDepartmentPanel.getTableListD().getTableHeader().setReorderingAllowed(false);
    }

    @Override
    public JPanel getDepartmentViewer() {
        return this.listDepartmentPanel;
    }

    @Override
    public Lookup getDepartmentViewerLookup() {
        return null;
    }

    @Override
    public double getLevelNumber() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JTable getTable() {
        return listDepartmentPanel.getTableListD();
    }

    @Override
    public String toString() {
        return "Thông tin cơ bản";
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
        int i = listDepartmentPanel.getTableListD().getSelectedRow(); // chọn hàng để xóa
        if (i >= 0) {
            Department bean = (Department) listDepartmentPanel.getTableListD().getValueAt(i, 2);
            Collection<? extends RemoveCookieDepartment> allRemoveCookie = Lookup.getDefault().lookupAll(RemoveCookieDepartment.class);
            for (RemoveCookieDepartment r : allRemoveCookie) {
                r.removeObject(bean.getId());
            }
            loadDataEdit();
        }
    }

    @Override
    public void removeObject(int i) throws IOException {
        try {
            departmentBN.delete(i);
        } catch (Exception e) {
        }
    }

    @Override
    public void resetCookie() throws IOException {
        loadData();
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
        TopComponent tc = WindowManager.getDefault().findTopComponent("DepartmentViewerTopComponent");
        if (tc.isShowing()) {
            SaveData();
        }
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
        size = listDepartmentPanel.getTableListD().getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        listDepartmentPanel.getTableListD().setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(listDepartmentPanel.getTableListD(), colorL, null, colorD, null);
        listDepartmentPanel.getTableListD().repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = listDepartmentPanel.getTableListD().getFont().getFontName();
        size = listS.get(0).getSizeWord();
        listDepartmentPanel.getTableListD().setFont(new Font(font, 0, size));
//        for (int i = 0; i < listDepartmentPanel.getTableListD().getRowCount(); i++) {
//            listDepartmentPanel.getTableListD().setRowHeight(i, size + 10);
//        }
    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        listDepartmentPanel.getTableListD().setForeground(color);
        listDepartmentPanel.getTableListD().repaint();
    }

    @Override
    public void resetColorTitle() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
        listDepartmentPanel.getTableListD().getTableHeader().setForeground(color);
        listDepartmentPanel.getTableListD().repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        listDepartmentPanel.getTableListD().setSelectionBackground(color);
        listDepartmentPanel.getTableListD().repaint();
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

    @Override
    public void exportReport() {
        IReportManager reportManager = Lookup.getDefault().lookup(IReportManager.class);

        if (reportManager
                == null) {
            return;
        }
        String[] keys1 = {"prTenDoanhNghiep", "prDiaChi", "prTenBaoCao", "prNgayLap", "prNguoiLapBaoCao"};
        String[] values1 = {"Công Ty Cổ Phần Tư Vấn Quản Trị", "66 Trần Thái Tông- Dịch Vọng, Cầu Giấy, Hà Nội", "Danh Sách Phòng Ban", "16/03/2012", "Đồng Thị Tâm"};
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
                    label.setText(table.getValueAt(itsRow, 2).toString());
                }
                if (itsColumn == 4) {
                    if (table.getValueAt(itsRow, 4) != null) {
                        label.setText(table.getValueAt(itsRow, 4).toString());
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
                if (itsColumn == 6) {
                    if (table.getValueAt(itsRow, 6) != null) {
                        label.setText(table.getValueAt(itsRow, 6).toString());
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
                    Department d1 = (Department) aTable.getValueAt(selct, sectcol);
                    openCloseComponent("DepartmentViewerTopComponent", "DepartmentCreatorTopComponent");
                    Collection<? extends IGetObject> allSave = Lookup.getDefault().lookupAll(IGetObject.class);
                    for (IGetObject getObject : allSave) {
                        if (getObject instanceof IDepartmentCreater || getObject instanceof IDepartmentExtCreater) {
                            getObject.getObject(d1.getDepartmentId());
                        }
                    }

                }
            }
            if (selct == itsRow && sectcol == 5) {
                if (aTable.getValueAt(selct, sectcol) != null) {
                    Department d1 = (Department) aTable.getValueAt(selct, sectcol);
                    openCloseComponent("DepartmentViewerTopComponent", "DepartmentCreatorTopComponent");
                    Collection<? extends IGetObject> allSave = Lookup.getDefault().lookupAll(IGetObject.class);
                    for (IGetObject getObject : allSave) {
                        if (getObject instanceof IDepartmentCreater || getObject instanceof IDepartmentExtCreater) {
                            getObject.getObject(d1.getDepartmentId());
                        }
                    }
                }
            }

            if (selct == itsRow && sectcol == 4) {
                if (aTable.getValueAt(selct, sectcol) != null) {
                    Enterprise e1 = (Enterprise) aTable.getValueAt(selct, sectcol);
                    openCloseComponent("DepartmentViewerTopComponent", "EnterpriseCreatorTopComponent");
                    Collection<? extends IGetObject> allSave = Lookup.getDefault().lookupAll(IGetObject.class);
                    for (IGetObject getObject : allSave) {
                        if (getObject instanceof IEnterpriseCreator || getObject instanceof IEnterpriseExtCreator) {
                            getObject.getObject(e1.getEnterpriseId());
                        }
                    }
                    toolbarEnterprise(e1);
                }
            }
            if (selct == itsRow && sectcol == 6) {
                if (aTable.getValueAt(selct, sectcol) != null) {
                    Person p1 = (Person) aTable.getValueAt(selct, sectcol);
                    openCloseComponent("DepartmentViewerTopComponent", "PersonCreatorTopComponent");
                    Collection<? extends IGetObject> allSave = Lookup.getDefault().lookupAll(IGetObject.class);
                    for (IGetObject getObject : allSave) {
                        if (getObject instanceof IPersonCreater || getObject instanceof IPersonExtCreater) {
                            getObject.getObject(p1.getPersonId());
                        }
                    }
                    toolbarPerson(p1);
                }
            }
            if (selct == itsRow && sectcol == 7) {
                if (aTable.getValueAt(selct, sectcol) != null) {
                    Product p1 = (Product) aTable.getValueAt(selct, sectcol);
                    openCloseComponent("DepartmentViewerTopComponent", "ProductCreatorTopComponent");
                    Collection<? extends IGetObject> allSave = Lookup.getDefault().lookupAll(IGetObject.class);
                    for (IGetObject getObject : allSave) {
                        if (getObject instanceof IProductCreater || getObject instanceof IProductExtCreater) {
                            getObject.getObject(p1.getProductId());
                        }
                    }
                }
            }
            aTable.clearSelection();
        }

        public void openCloseComponent(String s1, String s2) {
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
}
