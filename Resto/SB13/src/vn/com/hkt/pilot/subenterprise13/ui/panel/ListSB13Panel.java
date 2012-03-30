/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ListSB13Panel.java
 *
 * Created on Feb 15, 2012, 10:19:58 AM
 */
package vn.com.hkt.pilot.subenterprise13.ui.panel;

import com.vn.hkt.core.cookie.api.FilterCokieTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.japura.gui.LinkLabel;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.ISystemSotfwareBN;
import vn.com.hkt.basic.api.IWidthTableBN;
import vn.com.hkt.basic.toolbar.BasicToolbarManager;
import vn.com.hkt.pilot.enterprise.viewer.api.EditCookieList;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnableButton;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseCreator;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseExtCreator;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseExtViewer;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseViewer;
import vn.com.hkt.pilot.enterprise.viewer.api.IGetObject;
import vn.com.hkt.pilot.enterprise.viewer.api.RemoveCookie;
import vn.com.hkt.pilot.enterprise.viewer.api.SaveCookieList;
import vn.com.hkt.pilot.enterprise.viewer.api.ViewCookieList;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.report.api.IReportListGUI;
import vn.com.hkt.pilot.report.api.IReportManager;
import vn.com.hkt.pilot.subenterprise13.Installer;
import vn.com.hkt.pilot.subenterprise13.dao.SubEnterprise13BN;
import vn.com.hkt.pilot.subenterprise13.entity.SubEnterprise13;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.setup.StripedTableCellRenderer;

/**
 *
 * @author longnt
 */
@ServiceProvider(service = IEnterpriseExtViewer.class)
public class ListSB13Panel extends javax.swing.JPanel implements IEnterpriseExtViewer, MouseMotionListener,
        RemoveCookie, ViewCookieList, FilterCokieTable, EditCookieList, SaveCookieList, IResetFontSize, IUserInterface, IReportListGUI {

    private DefaultTableModel model;
    private SubEnterprise13BN dao = new SubEnterprise13BN();
    private IEnterpriseBN enterpriseBN;
    private IWidthTableBN widthTableBN;
    private boolean isEdit = false;
    private int itsRow = 0;
    private int itsColumn = 0;
    private int size;
    private String font;
    private ISystemSotfwareBN sotfwareBN = Lookup.getDefault().lookup(ISystemSotfwareBN.class);
    private List<SystemSoftware> listS = new ArrayList<SystemSoftware>();
    private TableRowSorter<TableModel> tableRowSorter;

    /**
     * Creates new form ListSB13Panel
     */
    public ListSB13Panel() {
        initComponents();

        enterpriseBN = Lookup.getDefault().lookup(IEnterpriseBN.class);
        table.addMouseMotionListener(this);
        widthTableBN = Lookup.getDefault().lookup(IWidthTableBN.class);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Logo", "Tên thường gọi", "Vốn điều lệ", "Vốn pháp định", "Doanh thu năm nay", "Lợi nhuận năm nay", "Số nhân viên năm nay", "Xếp hạng(Cty con)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        table.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(ListSB13Panel.class, "ListSB13Panel.table.columnModel.title0")); // NOI18N
        table.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(ListSB13Panel.class, "ListSB13Panel.table.columnModel.title1")); // NOI18N
        table.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(ListSB13Panel.class, "ListSB13Panel.table.columnModel.title2")); // NOI18N
        table.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(ListSB13Panel.class, "ListSB13Panel.table.columnModel.title3")); // NOI18N
        table.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(ListSB13Panel.class, "ListSB13Panel.table.columnModel.title4")); // NOI18N
        table.getColumnModel().getColumn(5).setHeaderValue(org.openide.util.NbBundle.getMessage(ListSB13Panel.class, "ListSB13Panel.table.columnModel.title5")); // NOI18N
        table.getColumnModel().getColumn(6).setHeaderValue(org.openide.util.NbBundle.getMessage(ListSB13Panel.class, "ListSB13Panel.table.columnModel.title6")); // NOI18N
        table.getColumnModel().getColumn(7).setHeaderValue(org.openide.util.NbBundle.getMessage(ListSB13Panel.class, "ListSB13Panel.table.columnModel.title7")); // NOI18N
        table.getColumnModel().getColumn(8).setHeaderValue(org.openide.util.NbBundle.getMessage(ListSB13Panel.class, "ListSB13Panel.table.columnModel.title8")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

    @Override
    public JPanel getEnterpriseExtViewer() {
        return this;
    }

    @Override
    public String toString() {
        return "Thông tin quy mô";
    }

    @Override
    public Lookup getEnterpriseExtLookup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Lookup getEnterpriseLookup() {
        return null;
    }

    @Override
    public double getLevelNumber() {
        return 1.3;
    }

    @Override
    public JTable getTable() {
        return table;
    }

    @Override
    public void remove() throws IOException {
        int row = table.getSelectedRow();
        if (row >= 0) {
            Enterprise bean = (Enterprise) table.getValueAt(row, 2);
            Collection<? extends RemoveCookie> allRemoveCookie = Lookup.getDefault().lookupAll(RemoveCookie.class);
            for (RemoveCookie r : allRemoveCookie) {
                if (r instanceof IEnterpriseExtViewer || r instanceof IEnterpriseViewer) {
                    r.removeObject(bean.getId());
                }
            }
        }
    }

    @Override
    public void removeObject(int id) {
        List<SubEnterprise13> list = dao.select(SubEnterprise13.FIELD_ENTERPRISE_ID_ACTUAL, String.valueOf(id));
        SubEnterprise13 bean = list.get(0);
        dao.delete(bean.getId());


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
        TopComponent tc = WindowManager.getDefault().findTopComponent("EnterpriseViewerTopComponent");
        if (tc.isShowing()) {
            int n = table.getRowCount();
            if (n > 0) {
                for (int i = 0; i < n; i++) {
                    Enterprise e = (Enterprise) table.getValueAt(i, 2);
                    SubEnterprise13 bean = dao.getByObjectId(String.valueOf(e.getId()));
                    if (bean == null) {
                        return;
                    }
                    // Doanh thu + loi nhuan ko cho fep sua
                    int charterCapital = this.getValueUpdate(bean.getCharterCapital(), i, 3);
                    int legalCapital = this.getValueUpdate(bean.getLegalCapital(), i, 4);
                    int employeeNum = this.getValueUpdate(bean.getEmployeeNum(), i, 7);
                    bean.setCharterCapital(charterCapital);
                    bean.setLegalCapital(legalCapital);
                    bean.setEmployeeNum(employeeNum);
                    dao.update(bean);
                }
            }
        }
    }

    public int getValueUpdate(int defaultValue, int row, int column) {
        int value;
        try {
            value = Integer.parseInt(table.getValueAt(row, column).toString().trim());
        } catch (Exception ex) {
            value = defaultValue;
        }
        return value;
    }

    protected void setupTable() {
        widthTableBN = Lookup.getDefault().lookup(IWidthTableBN.class);
        table.setSelectionBackground(new Color(192, 210, 224));
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(table, colorL, null, colorD, null);

        int sizeId = widthTableBN.getWidth1();
        int sizeLogo = widthTableBN.getWidth2();
        int sizeName = widthTableBN.getWidth3();


        table.getColumnModel().getColumn(0).setMaxWidth(sizeId);
        table.getColumnModel().getColumn(0).setPreferredWidth(sizeId);

        table.getColumnModel().getColumn(1).setMaxWidth(sizeLogo);
        table.getColumnModel().getColumn(1).setPreferredWidth(sizeLogo);

        table.getColumnModel().getColumn(2).setMaxWidth(sizeName);
        table.getColumnModel().getColumn(2).setPreferredWidth(sizeName);

        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setResizable(false);
        table.getColumnModel().getColumn(4).setResizable(false);
        table.getColumnModel().getColumn(5).setResizable(false);
        table.getColumnModel().getColumn(6).setResizable(false);
        table.getColumnModel().getColumn(7).setResizable(false);
        table.getColumnModel().getColumn(8).setResizable(false);

        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(26);
    }

    private void getModel(){
        String[] header = {"Mã", "Logo", "Tên thường gọi", "Vốn điều lệ", "Vốn pháp định", "Doanh thu năm nay", "Lợi nhuận năm nay", "Số nhân viên năm nay", "Xếp hạng(Cty con)"};
        model = new DefaultTableModel(header, 0){
             boolean[] canEdit = new boolean[]{
                false, false, false, true, true, true, true, true, true
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        List<SubEnterprise13> list = new ArrayList<SubEnterprise13>();
        list = dao.selectAll();
        for (SubEnterprise13 bean : list) {
            Enterprise e = enterpriseBN.getById(bean.getEnterpriseIdActual());
            try {
                Object[] rows = {e.getEnterpriseId(), " Logo ", e, bean.getCharterCapital(), bean.getLegalCapital(), bean.getRevenue(), bean.getProfit(), bean.getEmployeeNum(), bean.getId()};
                model.addRow(rows);
            } catch (Exception ex) {
                System.out.println("Exception : "+ ex.getMessage());
            }

        }
    }
    
    public void loadData() {
        this.getModel();
        this.table.removeAll();
        table.setModel(model);
        resetTable();
        setupTable();
        filterTable(table, model);
    }

    private void resetTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        TableCell tableCell = new TableCell(colorL, colorD);
        table.getColumnModel().getColumn(2).setCellRenderer(tableCell);

    }

    public void loadDataEdit() {
        this.getModel();
        this.table.removeAll();
        table.setModel(model);
        resetTableEdit();
        setupTable();
    }

    private void resetTableEdit() {
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
    public void ViewCookieList() throws IOException {
        if (isEdit == true) {
            loadDataEdit();
        } else {
            loadData();
        }
    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = table.getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        table.setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(table, colorL, null, colorD, null);
        table.repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = table.getFont().getFontName();
        size = listS.get(0).getSizeWord();
        table.setFont(new Font(font, 0, size));

    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        table.setForeground(color);
        table.repaint();
    }

    @Override
    public void resetColorTitle() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
        table.getTableHeader().setForeground(color);
        table.repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        table.setSelectionBackground(color);
        table.repaint();
    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện danh sách thông tin quy mô doanh nghiệp";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
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
        String[] values1 = {"HKT Consultant", "66 Trần Thái Tông Hà Nội", "", "16/03/2012", "Đồng Thị Tâm"};
        String keys2[] = new String[table.getColumnCount()];
        String values2[] = new String[table.getColumnCount()];
        for (int i = 0; i < table.getColumnCount(); i++) {
            keys2[i] = "prColumn_" + i;
            values2[i] = table.getColumnName(i).toString();
        }
        String[] keys = new String[keys1.length + keys2.length];
        String[] values = new String[values1.length + values2.length];
        System.arraycopy(keys1, 0, keys, 0, keys1.length);
        System.arraycopy(keys2, 0, keys, keys1.length, keys2.length);
        System.arraycopy(values1, 0, values, 0, values1.length);
        System.arraycopy(values2, 0, values, values1.length, values2.length);
        HashMap map = reportManager.getHashMap(keys, values);
        DefaultTableModel md = reportManager.getModel(table);
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
                    Enterprise p1 = (Enterprise) aTable.getValueAt(selct, sectcol);
                    TopComponent tc1 = WindowManager.getDefault().findTopComponent("EnterpriseViewerTopComponent");
                    if (tc1.isOpened()) {
                        tc1.close();
                    }
                    TopComponent tc = WindowManager.getDefault().findTopComponent("EnterpriseCreatorTopComponent");
                    if (tc.isOpened() == false) {
                        tc.open();
                        tc.requestActive();
                    } else {
                        tc.requestActive();
                    }
                    getObject(p1.getEnterpriseId());
                    toolbarPerson(p1);
                    if (tc instanceof IEnableButton) {
                        try {
                            ((IEnableButton) tc).enableButton();
                        } catch (IOException ex) {
                            Exceptions.printStackTrace(ex);
                        }
                    }
                }
            }
            aTable.clearSelection();
        }

        private void toolbarPerson(Enterprise p1) {
            BasicToolbarManager.getBasicToolbar().loadEnterprise();
            BasicToolbarManager.getBasicToolbar().setEnterprise(p1);
        }
    }

    private void getObject(String id) {
        Collection<? extends IGetObject> allSave = Lookup.getDefault().lookupAll(IGetObject.class);
        for (IGetObject getObject : allSave) {
            if (getObject instanceof IEnterpriseCreator || getObject instanceof IEnterpriseExtCreator) {
                getObject.getObject(id);
            }
        }
    }
}
