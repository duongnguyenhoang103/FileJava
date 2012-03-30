/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.bom.product.ui;

import com.vn.hkt.core.cookie.api.FilterCokieTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.IOException;
import java.util.Hashtable;
import javax.swing.JPanel;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.basic.api.IProductBN;
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
import vn.com.hkt.basic.api.IDepartmentBN;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.bom.product.dao.ProductBN;
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
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.entities.system.dao.SystemSotfwareDAO;
import vn.com.hkt.pilot.person.viewer.api.IPersonCreater;
import vn.com.hkt.pilot.person.viewer.api.IPersonExtCreater;
import vn.com.hkt.pilot.product.viewer.api.IProductCreater;
import vn.com.hkt.pilot.product.viewer.api.IProductExtCreater;
import vn.com.hkt.pilot.product.viewer.api.IProductExtViewer;
import vn.com.hkt.pilot.product.viewer.api.IProductViewer;
import vn.com.hkt.pilot.report.api.IReportListGUI;
import vn.com.hkt.pilot.report.api.IReportManager;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.pilot.ui.setup.WidthTableBN;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.bom.product.ui//ListProduct//EN",
autostore = false)
@TopComponent.Description(preferredID = "ListProductTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.bom.product.ui.ListProductTopComponent")
@ActionReference(path = " " /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ListProductAction",
preferredID = "ListProductTopComponent")
@ServiceProvider(service = IProductViewer.class)
public final class ListProductTopComponent extends TopComponent implements ViewCookieList, RemoveCookieProduct, ResetCookieList,
        FilterCokieTable, IProductViewer, MouseMotionListener, EditCookieList, SaveCookieList, IResetFontSize, IReportListGUI {

    private ListProductPanel listProductPanel = new ListProductPanel();
    private DefaultTableModel model;
    private IProductBN productBN;
    private IPersonBN personBN;
    private IDepartmentBN departmentBN;
    private int itsRow = 0;
    private int itsColumn = 0;
    private boolean isEdit = false;
    private int size;
    private String font;
    private SystemSotfwareDAO sotfwareBN = new SystemSotfwareDAO();
    private List<SystemSoftware> listS = null;
    private TableRowSorter<TableModel> tableRowSorter;

    public ListProductTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ListProductTopComponent.class, "CTL_ListProductTopComponent"));
        setToolTipText(NbBundle.getMessage(ListProductTopComponent.class, "HINT_ListProductTopComponent"));
        panelForm.setLayout(new GridLayout());
        panelForm.add(listProductPanel);

        listProductPanel.getTableListPr().addMouseMotionListener(this);

        this.personBN = new PersonBN();
        this.departmentBN = new DepartmentBN();
        this.productBN = new ProductBN();

    }

    @Override
    public String toString() {
        return "Thông tin chính";
    }

    private void addRow() {
        IEnterpriseBN enterpriseBN = new EnterpriseBN();
        Enterprise enterpriseChoise = null;
        String[] header = {"Mã sản phẩm dịch vụ", "Logo", "Tên sản phẩm dịch vụ", "Công ty", "Bộ phân (Phòng)", "Người chịu trách nhiệm", "Nhóm sản phẩm", "Nước sản xuất", "Thành Phố "};
        model = new DefaultTableModel(header, 0){
           
            boolean[] canEdit = new boolean[]{
                false, false, false, true, true, true, true, true, true
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        enterpriseChoise = BasicToolbarManager.getBasicToolbar().getEnterprise();
        List<Product> list = new ArrayList<Product>();
        if (enterpriseChoise == null) {
            list = productBN.selectAll();
        } else {
            list = productBN.select(Product.FIELD_ENTERPRISE_ID_ACTUAL, String.valueOf(enterpriseChoise.getId()));
        }
        for (Product bean : list) {
            Enterprise enterprise = enterpriseBN.getById(bean.getEnterpriseIdActual());  // Lấy ra 1 Enterprise theo mã
            Product product = productBN.getById(bean.getProductGroupIdActual());
            Department department = departmentBN.getById(bean.getDepartmentIdActual());
            Person person = personBN.getById(bean.getPersonDesignIdActual());
            Object[] rows = {bean.getProductId(), "Anh", bean, enterprise, department, person, product, "Nuoc SX", "Thanh Pho"};
            model.addRow(rows);
        }
        listProductPanel.getTableListPr().removeAll();
        listProductPanel.getTableListPr().setModel(model);
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
            .addGap(0, 597, Short.MAX_VALUE)
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
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

    @Override
    public void componentOpened() {
        panelForm.add(listProductPanel);
        loadData();
        listProductPanel.addMouseMotionListener(this);
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

    @Override
    public void remove() throws IOException {
        int i = listProductPanel.getTableListPr().getSelectedRow();
        if (i >= 0) {
            Product bean = productBN.getByObjectId(listProductPanel.getTableListPr().getValueAt(i, 0).toString());
            Collection<? extends RemoveCookieProduct> allRemoveCookie = Lookup.getDefault().lookupAll(RemoveCookieProduct.class);
            for (RemoveCookieProduct r : allRemoveCookie) {
                if (r instanceof IProductViewer || r instanceof IProductExtViewer) {
                    r.removeObject(bean.getId());
                }
            }
            loadDataEdit();
        }
    }

    @Override
    public void removeObject(int i) throws IOException {
        try {
            productBN.delete(i);
        } catch (Exception e) {
        }
    }

    protected void setupTable() {
        listS = sotfwareBN.selectAll();
        WidthTableBN widthTableBN = new WidthTableBN();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(listProductPanel.getTableListPr(), colorL, null, colorD, null);
        listProductPanel.getTableListPr().setRowHeight(26);

        int sizeId = widthTableBN.getWidth1();
        int sizeLogo = widthTableBN.getWidth2();
        int sizeName = widthTableBN.getWidth3();

        listProductPanel.getTableListPr().getColumnModel().getColumn(0).setMaxWidth(sizeId);
        listProductPanel.getTableListPr().getColumnModel().getColumn(0).setPreferredWidth(sizeId);

        listProductPanel.getTableListPr().getColumnModel().getColumn(1).setMaxWidth(sizeLogo);
        listProductPanel.getTableListPr().getColumnModel().getColumn(1).setPreferredWidth(sizeLogo);

        listProductPanel.getTableListPr().getColumnModel().getColumn(2).setMaxWidth(sizeName);
        listProductPanel.getTableListPr().getColumnModel().getColumn(2).setPreferredWidth(sizeName);

        listProductPanel.getTableListPr().getColumnModel().getColumn(0).setResizable(false);
        listProductPanel.getTableListPr().getColumnModel().getColumn(1).setResizable(false);
        listProductPanel.getTableListPr().getColumnModel().getColumn(2).setResizable(false);
        listProductPanel.getTableListPr().getColumnModel().getColumn(3).setResizable(false);
        listProductPanel.getTableListPr().getColumnModel().getColumn(4).setResizable(false);
        listProductPanel.getTableListPr().getColumnModel().getColumn(5).setResizable(false);
        listProductPanel.getTableListPr().getColumnModel().getColumn(6).setResizable(false);
        listProductPanel.getTableListPr().getColumnModel().getColumn(7).setResizable(false);
        listProductPanel.getTableListPr().getColumnModel().getColumn(8).setResizable(false);

        listProductPanel.getTableListPr().getTableHeader().setReorderingAllowed(false);
    }

    public void loadData() {
        addRow();
        resetTable();
        setupTable();
        filterTable(getTable(), model);
    }

    @Override
    public JPanel getProductViewer() {
        return this.listProductPanel;
    }

    @Override
    public Lookup getProductViewerLookup() {
        return null;
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
    public void resetCookie() throws IOException {
        loadData();
    }

    @Override
    public double getLevelNumber() {
        return -1;
    }

    public void loadDataEdit() {
        addRow();
        resetTableEdit();
        setupTable();
        filterTable(getTable(), model);
    }

    private void resetTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        TableCell tableCell = new TableCell(colorL, colorD);
        listProductPanel.getTableListPr().getColumnModel().getColumn(2).setCellRenderer(tableCell);
        listProductPanel.getTableListPr().getColumnModel().getColumn(3).setCellRenderer(tableCell);
        listProductPanel.getTableListPr().getColumnModel().getColumn(4).setCellRenderer(tableCell);
        listProductPanel.getTableListPr().getColumnModel().getColumn(5).setCellRenderer(tableCell);
        listProductPanel.getTableListPr().getColumnModel().getColumn(6).setCellRenderer(tableCell);


    }

    private void resetTableEdit() {
        DefaultComboBoxModel modelDepartment = new DefaultComboBoxModel();
        modelDepartment.addElement("");
        List<Department> listD = new ArrayList<Department>();
        listD = departmentBN.selectAll();
        for (Department bean : listD) {
            modelDepartment.addElement(bean);
        }

        DefaultComboBoxModel modelPerson = new DefaultComboBoxModel();
        modelPerson.addElement("");
        List<Person> listPerson = personBN.selectAll();
        for (Person p : listPerson) {
            modelPerson.addElement(p);
        }

        DefaultComboBoxModel modelProduct = new DefaultComboBoxModel();
        modelProduct.addElement("");
        List<Product> listProduct = productBN.selectAll();
        for (Product p : listProduct) {
            modelProduct.addElement(p);
        }

        DefaultComboBoxModel modelCountry = new DefaultComboBoxModel();
        modelCountry.addElement("");
        List<Country> listCountry = new CountryBN().selectAll();
        for (Country c : listCountry) {
            modelCountry.addElement(c);
        }

        JComboBox cbxDepartment = new JComboBox(modelDepartment);
        JComboBox cbxPerson = new JComboBox(modelPerson);
        JComboBox cbxProduct = new JComboBox(modelProduct);
        JComboBox cbxCountry = new JComboBox(modelCountry);

        listProductPanel.getTableListPr().getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cbxDepartment));
        listProductPanel.getTableListPr().getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(cbxPerson));
        listProductPanel.getTableListPr().getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(cbxProduct));
        listProductPanel.getTableListPr().getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(cbxCountry));
    }

    private void SaveData() {
        int n = listProductPanel.getTableListPr().getRowCount();
        if (n >= 1) {
            for (int i = 0; i < n; i++) {
                Product p = (Product) listProductPanel.getTableListPr().getValueAt(i, 2);
                String name = null;
                try {
                    name = listProductPanel.getTableListPr().getValueAt(i, 2).toString();
                } catch (Exception e) {
                    name = null;
                }

                int idDepartment = -1;
                try {
                    Department d = (Department) listProductPanel.getTableListPr().getValueAt(i, 4);
                    idDepartment = d.getId();
                } catch (Exception e) {
                    idDepartment = -1;
                }

                int idPerson = -1;
                try {
                    Person person = (Person) listProductPanel.getTableListPr().getValueAt(i, 5);
                    idPerson = person.getId();
                } catch (Exception e) {
                    idPerson = -1;
                }

                int idProduct = -1;
                try {
                    Product product = (Product) listProductPanel.getTableListPr().getValueAt(i, 6);
                    idProduct = product.getId();
                } catch (Exception e) {
                    idProduct = -1;
                }
                if (name != null) {
                    p.setProductName(name);
                }
                p.setDepartmentIdActual(idDepartment);
                p.setPersonDesignIdActual(idPerson);
                if (p.getId() == idProduct) {
                    p.setProductGroupIdActual(-1);
                } else {
                    p.setProductGroupIdActual(idProduct);
                }

                productBN.update(p);
            }
        }
        loadDataEdit();
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

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = listProductPanel.getTableListPr().getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        listProductPanel.getTableListPr().setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(listProductPanel.getTableListPr(), colorL, null, colorD, null);
        listProductPanel.getTableListPr().repaint();

    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = listProductPanel.getTableListPr().getFont().getFontName();
        size = listS.get(0).getSizeWord();
        listProductPanel.getTableListPr().setFont(new Font(font, 0, size));
//        for (int i = 0; i < listProductPanel.getTableListPr().getRowCount(); i++) {
//            listProductPanel.getTableListPr().setRowHeight(i, size + 10);
//        }
    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        listProductPanel.getTableListPr().setForeground(color);
        listProductPanel.getTableListPr().repaint();
    }

    @Override
    public void resetColorTitle() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
        listProductPanel.getTableListPr().getTableHeader().setForeground(color);
        listProductPanel.getTableListPr().repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        listProductPanel.getTableListPr().setSelectionBackground(color);
        listProductPanel.getTableListPr().repaint();
    }

    @Override
    public JTable getTable() {
        return listProductPanel.getTableListPr();
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
        String[] values1 = {"Công Ty Cổ Phần Tư Vấn Quản Trị HKT", "66 Trần Thái Tông- Dịch Vọng,Cầu Giấy, Hà Nội", "Danh Sách Sản Phẩm", "16/03/2012", "Đồng Thị Tâm"};
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
                    if (table.getValueAt(itsRow, 2) != null) {

                        label.setText(table.getValueAt(itsRow, 2).toString());
                    } else {
                        label.setText(" ");
                    }
                }
                if (itsColumn == 3) {
                    if (table.getValueAt(itsRow, 3) != null) {
                        label.setText(table.getValueAt(itsRow, 3).toString());
                    } else {
                        label.setText(" ");
                    }
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
            Collection<? extends IGetObject> allObject = Lookup.getDefault().lookupAll(IGetObject.class);
            if ((selct == itsRow && sectcol == 2) || (selct == itsRow && sectcol == 6)) {
                if (aTable.getValueAt(selct, sectcol) != null) {
                    Product p1 = productBN.getByObjectId(aTable.getValueAt(selct, 0).toString());
                    if (p1 != null) {
                        openCloseComponent("ProductViewerTopComponent", "ProductCreatorTopComponent");
                        for (IGetObject getObject : allObject) {
                            if (getObject instanceof IProductCreater || getObject instanceof IProductExtCreater) {
                                getObject.getObject(p1.getProductId());
                            }
                        }
                    }
                }

            }
            if (selct == itsRow && sectcol == 5) {
                if (aTable.getValueAt(selct, sectcol) != null) {
                    Person p1 = (Person) aTable.getValueAt(selct, sectcol);
                    openCloseComponent("ProductViewerTopComponent", "PersonCreatorTopComponent");
                    for (IGetObject getObject : allObject) {
                        if (getObject instanceof IPersonCreater || getObject instanceof IPersonExtCreater) {
                            getObject.getObject(p1.getPersonId());
                        }
                    }
                    toolbarPerson(p1);
                }

            }

            if (selct == itsRow && sectcol == 3) {
                if (aTable.getValueAt(selct, sectcol) != null) {
                    Enterprise e1 = (Enterprise) aTable.getValueAt(selct, sectcol);
                    openCloseComponent("ProductViewerTopComponent", "EnterpriseCreatorTopComponent");
                    for (IGetObject getObject : allObject) {
                        if (getObject instanceof IEnterpriseCreator || getObject instanceof IEnterpriseExtCreator) {
                            getObject.getObject(e1.getEnterpriseId());
                        }
                    }
                    toolbarEnterprise(e1);
                }
            }

            if (selct == itsRow && sectcol == 4) {
                if (aTable.getValueAt(selct, sectcol) != null) {
                    Department d1 = (Department) aTable.getValueAt(selct, sectcol);
                    openCloseComponent("ProductViewerTopComponent", "DepartmentCreatorTopComponent");
                    for (IGetObject getObject : allObject) {
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
