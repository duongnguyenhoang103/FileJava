/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.erm.enterprise.ui;

import com.vn.hkt.core.cookie.api.FilterCokieTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.IOException;
import java.util.Hashtable;
import javax.swing.JPanel;
import vn.com.hkt.pilot.entities.Enterprise;
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
import javax.swing.JOptionPane;
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
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import org.openide.windows.WindowManager;
import vn.com.hkt.basic.api.IWidthTableBN;
import vn.com.hkt.basic.toolbar.BasicToolbarManager;
import vn.com.hkt.erm.enterprise.dao.EnterpriseBN;
import vn.com.hkt.hrm.person.dao.PersonBN;
import vn.com.hkt.pilot.enterprise.viewer.api.EditCookieList;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnableButton;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseCreator;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseExtCreator;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseExtViewer;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseViewer;
import vn.com.hkt.pilot.enterprise.viewer.api.IGetObject;
import vn.com.hkt.pilot.enterprise.viewer.api.RemoveCookie;
import vn.com.hkt.pilot.enterprise.viewer.api.ResetCookieList;
import vn.com.hkt.pilot.enterprise.viewer.api.SaveCookieList;
import vn.com.hkt.pilot.enterprise.viewer.api.ViewCookieList;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.entities.system.dao.SystemSotfwareDAO;
import vn.com.hkt.pilot.person.viewer.api.IPersonCreater;
import vn.com.hkt.pilot.person.viewer.api.IPersonExtCreater;
import vn.com.hkt.pilot.report.api.IReportListGUI;
import vn.com.hkt.pilot.report.api.IReportManager;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.erm.enterprise.ui//ListEnterprise//EN",
autostore = false)
@TopComponent.Description(preferredID = "ListEnterpriseTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.erm.enterprise.ui.ListEnterpriseTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ListEnterpriseAction",
preferredID = "ListEnterpriseTopComponent")
@ServiceProviders({
    @ServiceProvider(service = IEnterpriseViewer.class),
    @ServiceProvider(service = IReportListGUI.class),
    @ServiceProvider(service = RemoveCookie.class)
})
public final class ListEnterpriseTopComponent extends TopComponent implements MouseMotionListener, IReportListGUI,
        IEnterpriseViewer, ViewCookieList, RemoveCookie, FilterCokieTable, EditCookieList, SaveCookieList, IResetFontSize, ResetCookieList {

    private EnterpriseBN enterpriseBN = new EnterpriseBN();
    private ListEnterprisePanel listEnterprisePanel = new ListEnterprisePanel();
    private DefaultTableModel model;
    private int itsRow = 0;
    private int itsColumn = 0;
    private boolean isEdit = false;
    private int size;
    private String font;
    private SystemSotfwareDAO sotfwareBN = new SystemSotfwareDAO();
    private List<SystemSoftware> listS = new ArrayList<SystemSoftware>();
    private TableRowSorter<TableModel> tableRowSorter;

    public ListEnterpriseTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ListEnterpriseTopComponent.class, "CTL_ListEnterpriseTopComponent"));
        setToolTipText(NbBundle.getMessage(ListEnterpriseTopComponent.class, "HINT_ListEnterpriseTopComponent"));

        listEnterprisePanel.getTableListE().addMouseMotionListener(this);

    }

    private void addRow() {
        PersonBN personBN = new PersonBN();
        String[] header = {"Mã công ty", "Logo", "Tên thường gọi", "Slogan", "Đại diện pháp luật", "Công ty mẹ"};
        model = new DefaultTableModel(header, 0){
             boolean[] canEdit = new boolean[]{
                false, false, false, true, true, true
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        List<Enterprise> list = new ArrayList<Enterprise>();
        list = enterpriseBN.selectAll();
        for (Enterprise bean : list) {
            Enterprise enterprise = enterpriseBN.getById(bean.getEnterpriseParentIdActual());  // Lấy ra 1 Enterprise theo mã
            Person person1 = personBN.getById(bean.getDirectorIdActual());

            Object[] rows = {bean.getEnterpriseId(), " ", bean, bean.getSlogan(), person1, enterprise};
            model.addRow(rows);
        }
        listEnterprisePanel.getTableListE().removeAll();
        listEnterprisePanel.getTableListE().setModel(model);
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
            .addGap(0, 676, Short.MAX_VALUE)
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 197, Short.MAX_VALUE)
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
                .addGap(58, 58, 58)
                .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(62, 62, 62))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelForm;
    // End of variables declaration//GEN-END:variables

    public void loadData() {
        addRow();
        resetTable();
        setupTable();
        filterTable(listEnterprisePanel.getTableListE(), model);
    }

    public void loadDataEdit() {
        addRow();
        resetTableEdit();
        setupTable();
        filterTable(getTable(), model);
    }

    @Override
    public void componentOpened() {
        panelForm.setLayout(new GridLayout());
        panelForm.add(listEnterprisePanel);
        loadData();
        listEnterprisePanel.getTableListE().addMouseMotionListener(this);
        listEnterprisePanel.getTableListE().setRowHeight(26);
    }

    @Override
    public void componentClosed() {
        panelForm.removeAll();
        listEnterprisePanel.getTableListE().removeMouseMotionListener(this);
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
    public JPanel getEnterpriseViewer() {
        return listEnterprisePanel;
    }

    @Override
    public Lookup getEnterpriseLookup() {
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
    public void remove() {
        int i = listEnterprisePanel.getTableListE().getSelectedRow(); // chọn hàng để xóa
        if (i >= 0) {
            Enterprise bean = (Enterprise) listEnterprisePanel.getTableListE().getValueAt(i, 2); // Lấy ra 1 enterprise
            Collection<? extends RemoveCookie> allRemoveCookie = Lookup.getDefault().lookupAll(RemoveCookie.class);
            for (RemoveCookie r : allRemoveCookie) {
                if (r instanceof IEnterpriseExtViewer || r instanceof IEnterpriseViewer) {
                    try {
                        r.removeObject(bean.getId());
                    } catch (IOException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }
            loadDataEdit();
        }
    }

    @Override
    public void removeObject(int id) throws IOException {
        enterpriseBN.delete(id);
        BasicToolbarManager.getBasicToolbar().loadEnterprise();
    }

    @Override
    public String toString() {
        return "Thông tin doanh nghiệp";
    }

    protected void setupTable() {
        listEnterprisePanel.getTableListE().setSelectionBackground(new Color(192, 210, 224));
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(listEnterprisePanel.getTableListE(), colorL, null, colorD, null);
        IWidthTableBN widthTableBN = Lookup.getDefault().lookup(IWidthTableBN.class);
        int sizeId = widthTableBN.getWidth1();
        int sizeLogo = widthTableBN.getWidth2();
        int sizeName = widthTableBN.getWidth3();

        listEnterprisePanel.getTableListE().getColumnModel().getColumn(0).setMaxWidth(sizeId);
        listEnterprisePanel.getTableListE().getColumnModel().getColumn(0).setPreferredWidth(sizeId);

        listEnterprisePanel.getTableListE().getColumnModel().getColumn(1).setMaxWidth(sizeLogo);
        listEnterprisePanel.getTableListE().getColumnModel().getColumn(1).setPreferredWidth(sizeLogo);

        listEnterprisePanel.getTableListE().getColumnModel().getColumn(2).setMaxWidth(sizeName);
        listEnterprisePanel.getTableListE().getColumnModel().getColumn(2).setPreferredWidth(sizeName);

        listEnterprisePanel.getTableListE().getColumnModel().getColumn(0).setResizable(false);
        listEnterprisePanel.getTableListE().getColumnModel().getColumn(1).setResizable(false);
        listEnterprisePanel.getTableListE().getColumnModel().getColumn(2).setResizable(false);
        listEnterprisePanel.getTableListE().getColumnModel().getColumn(3).setResizable(false);
        listEnterprisePanel.getTableListE().getColumnModel().getColumn(4).setResizable(false);
        listEnterprisePanel.getTableListE().getColumnModel().getColumn(5).setResizable(false);
        listEnterprisePanel.getTableListE().getTableHeader().setReorderingAllowed(false);
    }

    public void resetTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        TableCell tableCell = new TableCell(colorL, colorD);
        listEnterprisePanel.getTableListE().getColumnModel().getColumn(4).setCellRenderer(tableCell);
        listEnterprisePanel.getTableListE().getColumnModel().getColumn(2).setCellRenderer(tableCell);
        listEnterprisePanel.getTableListE().getColumnModel().getColumn(5).setCellRenderer(tableCell);
        listEnterprisePanel.getTableListE().setRowHeight(26);
    }

    public void resetTableEdit() {
        PersonBN personBN = new PersonBN();
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
        JComboBox cbxPerson = new JComboBox(modelPerson);
        JComboBox cbxEnterprise = new JComboBox(modelEnterprise);

        listEnterprisePanel.getTableListE().getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cbxPerson));
        listEnterprisePanel.getTableListE().getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(cbxEnterprise));
        listEnterprisePanel.getTableListE().setRowHeight(26);
    }

    public void saveData() {
        int n = listEnterprisePanel.getTableListE().getRowCount();
        if (n <= 0) {
            return;
        }
        for (int i = 0; i < n; i++) {
            String idEnterprise = "";
            if (listEnterprisePanel.getTableListE().getValueAt(i, 0) != null) {
                idEnterprise = listEnterprisePanel.getTableListE().getValueAt(i, 0).toString();
            }

            Enterprise e = enterpriseBN.getByObjectId(idEnterprise);
            String nameEnterprise = listEnterprisePanel.getTableListE().getValueAt(i, 2).toString();
            String logo = listEnterprisePanel.getTableListE().getValueAt(i, 3).toString();
            Person p = null;
            int idPerson = 0;
            if (listEnterprisePanel.getTableListE().getValueAt(i, 4) != null && listEnterprisePanel.getTableListE().getValueAt(i, 4).toString().trim().length() != 0) {
                p = (Person) listEnterprisePanel.getTableListE().getValueAt(i, 4);
                idPerson = p.getId();
            }

            Enterprise eParent = null;
            int idEnterpriseParent = 0;
            if (listEnterprisePanel.getTableListE().getValueAt(i, 5) != null && listEnterprisePanel.getTableListE().getValueAt(i, 5) != "") {
                eParent = (Enterprise) listEnterprisePanel.getTableListE().getValueAt(i, 5);
                idEnterpriseParent = eParent.getId();
            }
            String slogan = listEnterprisePanel.getTableListE().getValueAt(i, 3).toString();


            e.setDirectorIdActual(idPerson);
            e.setEnterpriseParentIdActual(idEnterpriseParent);
            e.setSlogan(slogan);
            e.setEnterpriseName(nameEnterprise);

            enterpriseBN.update(e);
        }
        loadDataEdit();
    }

    @Override
    public double getLevelNumber() {
        return 0.0;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (isEdit == false) {
            JTable aTable = (JTable) e.getSource();
            itsRow = aTable.rowAtPoint(e.getPoint());
            itsColumn = aTable.columnAtPoint(e.getPoint());
            aTable.repaint();
        }
    }

    public void toolbarEnterprise(Enterprise bean) {
        BasicToolbarManager.getBasicToolbar().loadEnterprise();
        BasicToolbarManager.getBasicToolbar().setEnterprise(bean);
    }

    public void toolbarPerson(Person bean) {
        BasicToolbarManager.getBasicToolbar().loadPerson();
        BasicToolbarManager.getBasicToolbar().setPerson(bean);
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
        saveData();
    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = listEnterprisePanel.getTableListE().getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        listEnterprisePanel.getTableListE().setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(listEnterprisePanel.getTableListE(), colorL, null, colorD, null);
        listEnterprisePanel.getTableListE().repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = listEnterprisePanel.getTableListE().getFont().getFontName();
        size = listS.get(0).getSizeWord();
        listEnterprisePanel.getTableListE().setFont(new Font(font, 0, size));
//        for (int i = 0; i < listEnterprisePanel.getTableListE().getRowCount(); i++) {
//            listEnterprisePanel.getTableListE().setRowHeight(i, size + 10);
//        }
    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        listEnterprisePanel.getTableListE().setForeground(color);
        listEnterprisePanel.getTableListE().repaint();
    }

    @Override
    public void resetColorTitle() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
        listEnterprisePanel.getTableListE().getTableHeader().setForeground(color);
        listEnterprisePanel.getTableListE().repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        listEnterprisePanel.getTableListE().setSelectionBackground(color);
        listEnterprisePanel.getTableListE().repaint();
    }

    @Override
    public JTable getTable() {
        return listEnterprisePanel.getTableListE();
    }

    public JTable loadData2() {
        addRow();
        resetTable();
        setupTable();
        filterTable(listEnterprisePanel.getTableListE(), model);
        return listEnterprisePanel.getTableListE();
    }

    public TableRowSorter filterTable2(JTable table, DefaultTableModel tableModel) {
        tableRowSorter = new TableRowSorter<TableModel>(tableModel);
        table.setRowSorter(tableRowSorter);
        return tableRowSorter;
    }

    @Override
    public void exportReport() {
        IReportManager reportManager = Lookup.getDefault().lookup(IReportManager.class);
        JTable tempTable = listEnterprisePanel.getTableListE();
        if (reportManager
                == null) {
            return;
        }
        String[] keys1 = {"prTenDoanhNghiep", "prDiaChi", "prTenBaoCao", "prNgayLap", "prNguoiLapBaoCao"};
        String[] values1 = {"Công Ty Cổ Phần Tư Vấn Quản Trị HKT", "66 Trần Thái Tông- Dịch Vọng, Cầu Giấy, Hà Nội", "Danh Sách Doanh Nghiệp", "16/03/2012", "Đồng Thị Tâm"};
        String keys2[] = new String[tempTable.getColumnCount()];
        String values2[] = new String[tempTable.getColumnCount()];
        for (int i = 0; i < tempTable.getColumnCount(); i++) {
            keys2[i] = "prColumn_" + i;
            values2[i] = tempTable.getColumnName(i).toString();
        }
        String[] keys = new String[keys1.length + keys2.length];
        String[] values = new String[values1.length + values2.length];
        System.arraycopy(keys1, 0, keys, 0, keys1.length);
        System.arraycopy(keys2, 0, keys, keys1.length, keys2.length);
        System.arraycopy(values1, 0, values, 0, values1.length);
        System.arraycopy(values2, 0, values, values1.length, values2.length);
        HashMap map = reportManager.getHashMap(keys, values);
        DefaultTableModel md = reportManager.getModel(tempTable);
        reportManager.setReportManager(map, md);
        reportManager.exportReport();
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
            //if(e.getClickCount()<2) return;
            if (isEdit == true) {
                return;
            }
            JTable aTable = (JTable) e.getSource();
            int selct = aTable.getSelectedRow();
            int sectcol = aTable.getSelectedColumn();
            
           // JOptionPane.showMessageDialog(null, allSave.size());
            if (selct == itsRow && sectcol == 2) {
                if (aTable.getValueAt(selct, sectcol) != null) {
                    Enterprise e1 = (Enterprise) aTable.getValueAt(selct, sectcol);
                    openCreatorcomponent("EnterpriseViewerTopComponent", "EnterpriseCreatorTopComponent");
                    Collection<? extends IGetObject> allSave = Lookup.getDefault().lookupAll(IGetObject.class);
                    for (IGetObject getObject : allSave) {
                        if (getObject instanceof IEnterpriseCreator || getObject instanceof IEnterpriseExtCreator) {
                            getObject.getObject(e1.getEnterpriseId());
                        }
                    }
                    toolbarEnterprise(e1);
                }
            }
            if (selct == itsRow && sectcol == 5) {
                if (aTable.getValueAt(selct, sectcol) != null) {
                    Enterprise e1 = (Enterprise) aTable.getValueAt(selct, sectcol);
                    openCreatorcomponent("EnterpriseViewerTopComponent", "EnterpriseCreatorTopComponent");
                    Collection<? extends IGetObject> allSave = Lookup.getDefault().lookupAll(IGetObject.class);
                    for (IGetObject getObject : allSave) {
                        if (getObject instanceof IEnterpriseCreator || getObject instanceof IEnterpriseExtCreator) {
                            getObject.getObject(e1.getEnterpriseId());
                        }
                    }
                    toolbarEnterprise(e1);
                }

            }
            if (selct == itsRow && sectcol == 4) {
                if (aTable.getValueAt(selct, sectcol) != null) {
                    Person p1 = (Person) aTable.getValueAt(selct, sectcol);
                    openCreatorcomponent("EnterpriseViewerTopComponent", "PersonCreatorTopComponent");
                    Collection<? extends IGetObject> allSave = Lookup.getDefault().lookupAll(IGetObject.class);
                    for (IGetObject getObject : allSave) {
                        if (getObject instanceof IPersonCreater || getObject instanceof IPersonExtCreater) {
                            getObject.getObject(p1.getPersonId());
                        }
                    }
                    toolbarPerson(p1);
                }

            }
            aTable.clearSelection();
        }
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
