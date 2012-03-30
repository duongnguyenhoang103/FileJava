/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb41.product.extW48.ui.window;

import com.vn.hkt.core.cookie.api.FilterCokieTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.japura.gui.LinkLabel;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.LookupListener;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.WindowManager;
import vn.com.hkt.basic.api.*;
import vn.com.hkt.basic.toolbar.BasicToolbarManager;
import vn.com.hkt.pilot.enterprise.viewer.api.EditCookieList;
import vn.com.hkt.pilot.enterprise.viewer.api.IGetObject;
import vn.com.hkt.pilot.enterprise.viewer.api.RemoveCookieProduct;
import vn.com.hkt.pilot.enterprise.viewer.api.ResetCookieList;
import vn.com.hkt.pilot.enterprise.viewer.api.SaveCookieList;
import vn.com.hkt.pilot.enterprise.viewer.api.ViewCookieList;
import vn.com.hkt.pilot.entities.City;
import vn.com.hkt.pilot.entities.Country;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Product;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.product.viewer.api.IProductCreater;
import vn.com.hkt.pilot.product.viewer.api.IProductExtCreater;
import vn.com.hkt.pilot.product.viewer.api.IProductExtViewer;
import vn.com.hkt.pilot.product.viewer.api.IProductViewer;
import vn.com.hkt.pilot.report.api.IReportListGUI;
import vn.com.hkt.pilot.report.api.IReportManager;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.pilot.sb41.product.extW48.dao.ProductExtW48_BN;
import vn.com.hkt.pilot.sb41.product.extW48.dao.ProductStatusBN;
import vn.com.hkt.pilot.sb41.product.extW48.entity.ProductExt_W48;
import vn.com.hkt.pilot.sb41.product.extW48.entity.ProductStatus;
import vn.com.hkt.pilot.sb41.product.extW48.ui.panel.ExtListProductW48Cell;
import vn.com.hkt.pilot.sb41.product.extW48.ui.panel.ExtensionListProductPanel;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.product.extW48.ui.window//ExtensionListProduct//EN",
autostore = false)
@TopComponent.Description(preferredID = "ExtensionListProductTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.product.extW48.ui.window.ExtensionListProductTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ExtensionListProductAction",
preferredID = "ExtensionListProductTopComponent")
@ServiceProvider(service = IProductExtViewer.class)
public final class ExtensionListProductTopComponent extends TopComponent implements IProductExtViewer, ViewCookieList, RemoveCookieProduct, ResetCookieList,
        LookupListener, FilterCokieTable, MouseMotionListener, EditCookieList, SaveCookieList, IResetFontSize, IReportListGUI {

    private ExtensionListProductPanel extensionListProductPanel = new ExtensionListProductPanel();
    private DefaultTableModel model, modelEdit;
    private ProductExtW48_BN dao = new ProductExtW48_BN();
    private int itsRow = 0;
    private int itsColumn = 0;
    private boolean isEdit = false;
    private int size;
    private String font;
    private ISystemSotfwareBN sotfwareBN = Lookup.getDefault().lookup(ISystemSotfwareBN.class);
    private List<SystemSoftware> listS = new ArrayList<SystemSoftware>();
    private TableRowSorter<TableModel> tableRowSorter;

    public ExtensionListProductTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ExtensionListProductTopComponent.class, "CTL_ExtensionListProductTopComponent"));
        setToolTipText(NbBundle.getMessage(ExtensionListProductTopComponent.class, "HINT_ExtensionListProductTopComponent"));
        dao = new ProductExtW48_BN();

        extensionListProductPanel.getTableListExtProduct().addMouseMotionListener(this);
    }

    @Override
    public String toString() {
        return "Thông tin chi tiết";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
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
    public JPanel getProductExtViewer() {
        return this.extensionListProductPanel;
    }

    @Override
    public Lookup getProductExtViewerLookup() {
        return null;
    }

    @Override
    public double getLevelNumber() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void loadData() {
        model = this.getModel();
        extensionListProductPanel.getTableListExtProduct().removeAll();
        extensionListProductPanel.getTableListExtProduct().setModel(model);
        resetTable();
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(2).setPreferredWidth(200);
        setupTable();
        filterTable(getTable(), model);
    }

    public void loadDataEdit() {
        modelEdit = this.getModel();
        extensionListProductPanel.getTableListExtProduct().removeAll();
        extensionListProductPanel.getTableListExtProduct().setModel(modelEdit);
        resetTableEdit(modelEdit.getRowCount());
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(2).setPreferredWidth(200);
        setupTable();
    }

    private String getDateString(Calendar c) {
        String date = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        if (c == null) {
            return "";
        }
        try {
            Date d1 = new Date();
            d1 = c.getTime();
            date = sdf.format(d1);
        } catch (Exception ex) {
            date = "";
        }
        return date;
    }

    public DefaultTableModel getModel() {
        Enterprise enterpriseChoise = null;
        ICityBN cityBN = Lookup.getDefault().lookup(ICityBN.class);
        ICountryBN countryBN = Lookup.getDefault().lookup(ICountryBN.class);
        IProductBN productBN = Lookup.getDefault().lookup(IProductBN.class);
        String[] header = {"Mã", "Logo", "Tên sản phẩm", "Nhà cung cấp", "Quốc kỳ nước sx", "Nước sản xuất", "Thành phố", "Ngày sản xuất", "Ngày sửa",
            "Hạn sử dụng", "Tình trạng"};
        DefaultTableModel m = new DefaultTableModel(header, 0){
             
            boolean[] canEdit = new boolean[]{
                false, false, false, true, true, true, true, true, true, true, true
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        enterpriseChoise = BasicToolbarManager.getBasicToolbar().getEnterprise();

        List<ProductExt_W48> list = new ArrayList<ProductExt_W48>();
        if (enterpriseChoise == null) {
            list = dao.selectAll();
        } else {
            list = dao.getByEnterpriser(enterpriseChoise);
        }
        int n = list.size();
        int i;
        for (i = 0; i < n; i++) {
            ProductExt_W48 p = list.get(i);
            int id = p.getProductIdActual();
            Product product = productBN.getById(id);
            String date1 = this.getDateString(p.getDateOfProduction());
            String date2 = this.getDateString(p.getDateEdit());
            String date3 = this.getDateString(p.getExpiryDate());

            Country c = null;
            try {
                if (p.getCountryIdActual() != -1) {
                    c = countryBN.getById(p.getCountryIdActual());
                }
            } catch (Exception e) {
                c = null;
            }
            City city = null;
            try {
                if (p.getCityIdActual() != -1) {
                    city = cityBN.getById(p.getCityIdActual());
                }
            } catch (Exception e) {
                city = null;
            }

            ProductStatus ps;
            try {
                ps = (new ProductStatusBN()).getById(p.getStatusIdActual());
            } catch (Exception ex) {
                ps = new ProductStatus();
            }
            Object[] row = {product.getProductId(), "Logo", product, "NhaCungCap", "QuocKy", c, city, date1, date2, date3, ps};
            m.addRow(row);
        }
        return m;
    }

    private void resetTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        TableCell tableCell = new TableCell(colorL, colorD);
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(2).setCellRenderer(tableCell);
    }

    private void resetTableEdit(int n) {
        // Sau neu co add ComboBox
        ExtListProductW48Cell cell = new ExtListProductW48Cell(n);
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(5).setCellEditor(cell);
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(6).setCellEditor(cell);
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(7).setCellEditor(cell);
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(8).setCellEditor(cell);
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(9).setCellEditor(cell);

    }

    private void SaveData() {
        int n = extensionListProductPanel.getTableListExtProduct().getRowCount();
        if (n >= 1) {
            for (int i = 0; i < n; i++) {
                Product p = (Product) extensionListProductPanel.getTableListExtProduct().getValueAt(i, 2);
                int idCountry;
                try {
                    Country country = (Country) extensionListProductPanel.getTableListExtProduct().getValueAt(i, 5);
                    idCountry = country.getId();
                } catch (Exception e) {
                    idCountry = -1;
                }


                int idCity = -1;
                try {
                    City city = (City) extensionListProductPanel.getTableListExtProduct().getValueAt(i, 6);
                    idCity = city.getId();
                } catch (Exception e) {
                    idCity = -1;
                }

                Calendar c1 = Calendar.getInstance();
                Calendar c2 = Calendar.getInstance();
                Calendar c3 = Calendar.getInstance();

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date d1 = new Date();
                Date d2 = new Date();
                Date d3 = new Date();

                try {
                    String dateOfProduct = extensionListProductPanel.getTableListExtProduct().getValueAt(i, 7).toString();
                    d1 = sdf.parse(dateOfProduct);
                    c1.setTime(d1);
                } catch (Exception e) {
                    d1 = null;
                }
                try {
                    String dateEdit = extensionListProductPanel.getTableListExtProduct().getValueAt(i, 8).toString();
                    d2 = sdf.parse(dateEdit);
                    c2.setTime(d2);
                } catch (Exception e) {
                    d2 = null;
                }
                try {
                    String daTrienKhai = extensionListProductPanel.getTableListExtProduct().getValueAt(i, 9).toString();
                    d3 = sdf.parse(daTrienKhai);
                    c3.setTime(d3);
                } catch (Exception e) {
                    d3 = null;
                }

                List<ProductExt_W48> list = dao.select(ProductExt_W48.FILED_PRODUCT_ID_ACTUAL, String.valueOf(p.getId()));
                ProductExt_W48 bean;
                if (!list.isEmpty()) {
                    bean = list.get(0);
                } else {
                    bean = new ProductExt_W48();
                }
                bean.setCountryIdActual(idCountry);
                bean.setCityIdActual(idCity);
                if (d1 != null) {
                    bean.setDateOfProduction(c1);
                } else {
                    bean.setDateOfProduction(null);
                }
                if (d2 != null) {
                    bean.setDateEdit(c2);
                } else {
                    bean.setDateEdit(null);
                }
                if (d1 != null) {
                    bean.setExpiryDate(c3);
                } else {
                    bean.setExpiryDate(null);
                }

                dao.update(bean);
            }
            loadDataEdit();
        }
    }

    private void setupTable() {
        IWidthTableBN widthTableBN = Lookup.getDefault().lookup(IWidthTableBN.class);
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(extensionListProductPanel.getTableListExtProduct(), colorL, null, colorD, null);

        int sizeId = widthTableBN.getWidth1();
        int sizeLogo = widthTableBN.getWidth2();
        int sizeName = widthTableBN.getWidth3();


        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(0).setMaxWidth(sizeId);
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(0).setPreferredWidth(sizeId);

        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(1).setMaxWidth(sizeLogo);
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(1).setPreferredWidth(sizeLogo);

        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(2).setMaxWidth(sizeName);
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(2).setPreferredWidth(sizeName);

        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(0).setResizable(false);
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(1).setResizable(false);
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(2).setResizable(false);
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(3).setResizable(false);
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(4).setResizable(false);
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(5).setResizable(false);
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(6).setResizable(false);
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(7).setResizable(false);
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(8).setResizable(false);
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(9).setResizable(false);
        extensionListProductPanel.getTableListExtProduct().getColumnModel().getColumn(10).setResizable(false);
        extensionListProductPanel.getTableListExtProduct().getTableHeader().setReorderingAllowed(false);
        extensionListProductPanel.getTableListExtProduct().setRowHeight(26);
    }

    @Override
    public void remove() throws IOException {
        int i = extensionListProductPanel.getTableListExtProduct().getSelectedRow(); // chọn hàng để xóa
        if (i >= 0) {
            Product bean = (Product) extensionListProductPanel.getTableListExtProduct().getValueAt(i, 2);
            Collection<? extends RemoveCookieProduct> allRemoveCookie = Lookup.getDefault().lookupAll(RemoveCookieProduct.class);
            for (RemoveCookieProduct r : allRemoveCookie) {
                if (r instanceof IProductExtViewer || r instanceof IProductViewer) {
                    r.removeObject(bean.getId());
                }
            }
            loadDataEdit();
        }
    }

    @Override
    public void removeObject(int id) throws IOException {
        List<ProductExt_W48> list = dao.select(ProductExt_W48.FILED_PRODUCT_ID_ACTUAL, String.valueOf(id));
        ProductExt_W48 bean = list.get(0);
        dao.delete(bean.getId());
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
        size = extensionListProductPanel.getTableListExtProduct().getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        extensionListProductPanel.getTableListExtProduct().setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(extensionListProductPanel.getTableListExtProduct(), colorL, null, colorD, null);
        extensionListProductPanel.getTableListExtProduct().repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = extensionListProductPanel.getTableListExtProduct().getFont().getFontName();
        size = listS.get(0).getSizeWord();
        extensionListProductPanel.getTableListExtProduct().setFont(new Font(font, 0, size));
        for (int i = 0; i < extensionListProductPanel.getTableListExtProduct().getRowCount(); i++) {
            extensionListProductPanel.getTableListExtProduct().setRowHeight(i, size + 10);
        }
    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        extensionListProductPanel.getTableListExtProduct().setForeground(color);
        extensionListProductPanel.getTableListExtProduct().repaint();
    }

    @Override
    public void resetColorTitle() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
        extensionListProductPanel.getTableListExtProduct().getTableHeader().setForeground(color);
        extensionListProductPanel.getTableListExtProduct().repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        extensionListProductPanel.getTableListExtProduct().setSelectionBackground(color);
        extensionListProductPanel.getTableListExtProduct().repaint();
    }

    @Override
    public JTable getTable() {
        return extensionListProductPanel.getTableListExtProduct();
    }

    @Override
    public void ViewCookieList() throws IOException {
        if (isEdit == true) {
            loadDataEdit();
        } else {
            loadData();
        }
    }

    @Override
    public void resetCookie() throws IOException {
        ViewCookieList();
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
    public void resultChanged(LookupEvent le) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void exportReport() {
        IReportManager reportManager = Lookup.getDefault().lookup(IReportManager.class);

        if (reportManager
                == null) {
            return;
        }
        String[] keys1 = {"prTenDoanhNghiep", "prDiaChi", "prTenBaoCao", "prNgayLap", "prNguoiLapBaoCao"};
        String[] values1 = {"HKT Consultant", "66 Trần Thái Tông Hà Nội", "", "16/03/2012", "Đồng Thị Tâm"};
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
                    Product p1 = (Product) aTable.getValueAt(selct, sectcol);
                    TopComponent tc1 = WindowManager.getDefault().findTopComponent("ProductViewerTopComponent");
                    if (tc1.isOpened()) {
                        tc1.close();
                    }
                    TopComponent tc = WindowManager.getDefault().findTopComponent("ProductCreatorTopComponent");
                    if (tc.isOpened() == false) {
                        tc.open();
                        tc.requestActive();
                    } else {
                        tc.requestActive();
                    }
                    getObject(p1.getProductId());
                }
            }

            aTable.clearSelection();
        }
    }

    private void getObject(String id) {
        Collection<? extends IGetObject> allObject = Lookup.getDefault().lookupAll(IGetObject.class);
        for (IGetObject getObject : allObject) {
            if (getObject instanceof IProductCreater || getObject instanceof IProductExtCreater) {
                getObject.getObject(id);
            }
        }
    }
}
