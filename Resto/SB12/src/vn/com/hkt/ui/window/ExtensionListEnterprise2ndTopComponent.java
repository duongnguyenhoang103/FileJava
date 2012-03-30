/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.ui.window;

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
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
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
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.WindowManager;
import vn.com.hkt.basic.api.ICityBN;
import vn.com.hkt.basic.api.ICountryBN;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.ISystemSotfwareBN;
import vn.com.hkt.basic.api.IWidthTableBN;
import vn.com.hkt.basic.toolbar.BasicToolbarManager;
import vn.com.hkt.enterprise.ext.dao.AddressInfoEnterpriseBN;
import vn.com.hkt.enterprise.ext.dao.EnterpriseExtBN;
import vn.com.hkt.enterprise.ext.entity.AddressInfoEnterprise;
import vn.com.hkt.enterprise.ext.entity.EnterpriseExt;
import vn.com.hkt.pilot.enterprise.viewer.api.EditCookieList;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnableButton;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseExtViewer;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseViewer;
import vn.com.hkt.pilot.enterprise.viewer.api.IGetObject;
import vn.com.hkt.pilot.enterprise.viewer.api.RemoveCookie;
import vn.com.hkt.pilot.enterprise.viewer.api.SaveCookieList;
import vn.com.hkt.pilot.enterprise.viewer.api.ViewCookieList;
import vn.com.hkt.pilot.entities.City;
import vn.com.hkt.pilot.entities.Country;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.report.api.IReportListGUI;
import vn.com.hkt.pilot.report.api.IReportManager;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.ui.panel.ExtensionListEnterprisePanel2nd;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.ui.window//ExtensionListEnterprise2nd//EN",
autostore = false)
@TopComponent.Description(preferredID = "ExtensionListEnterprise2ndTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.ui.window.ExtensionListEnterprise2ndTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ExtensionListEnterprise2ndAction",
preferredID = "ExtensionListEnterprise2ndTopComponent")
@ServiceProvider(service = IEnterpriseExtViewer.class)
public final class ExtensionListEnterprise2ndTopComponent extends TopComponent implements IEnterpriseExtViewer, ViewCookieList, RemoveCookie,
        FilterCokieTable, MouseMotionListener, SaveCookieList, IResetFontSize, EditCookieList, IReportListGUI {

    private ExtensionListEnterprisePanel2nd listEnterprisePanel;
    private DefaultTableModel model;
    private EnterpriseExtBN enterpriseExtDao;
    private ICountryBN countryBN;
    private ICityBN cityBN;
    private int size;
    private String font;
    private ISystemSotfwareBN sotfwareBN = Lookup.getDefault().lookup(ISystemSotfwareBN.class);
    private List<SystemSoftware> listS = new ArrayList<SystemSoftware>();
    private int itsRow = 0;
    private int itsColumn = 0;
    private boolean isEdit = false;
    private TableRowSorter<TableModel> tableRowSorter;

    public ExtensionListEnterprise2ndTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ExtensionListEnterprise2ndTopComponent.class, "CTL_ExtensionListEnterprise2ndTopComponent"));
        setToolTipText(NbBundle.getMessage(ExtensionListEnterprise2ndTopComponent.class, "HINT_ExtensionListEnterprise2ndTopComponent"));

        enterpriseExtDao = new EnterpriseExtBN();
        countryBN = Lookup.getDefault().lookup(ICountryBN.class);
        cityBN = Lookup.getDefault().lookup(ICityBN.class);
        listEnterprisePanel = new ExtensionListEnterprisePanel2nd();
        listEnterprisePanel.getTblEnterpriseExt().addMouseMotionListener(this);

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
        setupTable();
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
    public JPanel getEnterpriseExtViewer() {
        return this.listEnterprisePanel;
    }

    @Override
    public Lookup getEnterpriseExtLookup() {
        return null;
    }

    @Override
    public void remove() throws IOException {
        int row = listEnterprisePanel.getTblEnterpriseExt().getSelectedRow();
        if (row >= 0) {
            Enterprise bean = (Enterprise) listEnterprisePanel.getTblEnterpriseExt().getValueAt(row, 2);
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
        List<EnterpriseExt> list = enterpriseExtDao.select(EnterpriseExt.FIELD_ENTERPRISEEXT_ID_ACTUAL, String.valueOf(id));
        EnterpriseExt bean = list.get(0);
        enterpriseExtDao.delete(bean.getId());
    }


    @Override
    public String toString() {
        return "Thông tin chi tiết";
    }

    @Override
    public Lookup getEnterpriseLookup() {
        return null;
    }

    protected DefaultTableModel getTableMode() {
        IEnterpriseBN enterpriseDao = Lookup.getDefault().lookup(IEnterpriseBN.class);
        String[] header = {"Mã Cty", "Logo", "Tên thường gọi",
            "Tell", "Fax", "Email", "Web", "Quốc kỳ", "Thành phố"};
        model = new DefaultTableModel(header, 0){
             boolean[] canEdit = new boolean[]{
                false, false, false, true, true, true, true, true, true
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        List<EnterpriseExt> list = new ArrayList<EnterpriseExt>();
        list = enterpriseExtDao.selectAll();

        int n = list.size();
        for (int i = 0; i < n; i++) {
            EnterpriseExt bean = list.get(i);
            Enterprise enterprise;
            try {
                enterprise = enterpriseDao.getById(bean.getEnterpriseIdActual());
            } catch (Exception e) {
                enterprise = null;
            }
            Country c;
            City city;

            // Lay ra danh sach cac dia chi ( diachi + qgia+ tpho)
            List<Integer> listAddress = bean.getEnterpriseAddressIdActual();
            try {
                // Lay dia chi dau tien
                AddressInfoEnterprise aie = (new AddressInfoEnterpriseBN()).getById(listAddress.get(0));
                c = countryBN.getById(aie.getCountryIdActual());
            } catch (Exception e) {
                c = null;
            }

            try {
                AddressInfoEnterprise aie = (new AddressInfoEnterpriseBN()).getById(listAddress.get(0));
                city = cityBN.getById(aie.getCityIdActual());
            } catch (Exception e) {
                city = null;
            }
            if (enterprise != null) {
                Object[] rows = {enterprise.getEnterpriseId(), " Anh ", enterprise,
                    bean.getEnterpriseTel(), bean.getEnterpriseFax(), bean.getEnterpriseEmail(), bean.getEnterpriseWeb(), c, city};
                model.addRow(rows);
            }

        }
        return model;
    }

    @Override
    public void ViewCookieList() {
        if (isEdit == true) {
            loadDataEdit();
        } else {
            loadData();
        }
    }
//

    protected void setupTable() {
        IWidthTableBN widthTableBN = Lookup.getDefault().lookup(IWidthTableBN.class);
        listEnterprisePanel.getTblEnterpriseExt().setSelectionBackground(new Color(192, 210, 224));
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(listEnterprisePanel.getTblEnterpriseExt(), colorL, null, colorD, null);
        int sizeId = widthTableBN.getWidth1();
        int sizeLogo = widthTableBN.getWidth2();
        int sizeName = widthTableBN.getWidth3();


        listEnterprisePanel.getTblEnterpriseExt().getColumnModel().getColumn(0).setMaxWidth(sizeId);
        listEnterprisePanel.getTblEnterpriseExt().getColumnModel().getColumn(0).setPreferredWidth(sizeId);

        listEnterprisePanel.getTblEnterpriseExt().getColumnModel().getColumn(1).setMaxWidth(sizeLogo);
        listEnterprisePanel.getTblEnterpriseExt().getColumnModel().getColumn(1).setPreferredWidth(sizeLogo);

        listEnterprisePanel.getTblEnterpriseExt().getColumnModel().getColumn(2).setMaxWidth(sizeName);
        listEnterprisePanel.getTblEnterpriseExt().getColumnModel().getColumn(2).setPreferredWidth(sizeName);

        for (int i=0;i<=8;i++){
            listEnterprisePanel.getTblEnterpriseExt().getColumnModel().getColumn(i).setResizable(false);
        }

        listEnterprisePanel.getTblEnterpriseExt().getTableHeader().setReorderingAllowed(false);
        listEnterprisePanel.getTblEnterpriseExt().setRowHeight(26);
    }

    @Override
    public double getLevelNumber() {
        return 0.0;
    }

    @Override
    public void SaveCookieList() throws IOException {
        AddressInfoEnterpriseBN addressBN = new AddressInfoEnterpriseBN();
        int n = listEnterprisePanel.getTblEnterpriseExt().getRowCount();
        Country country;
        City city;
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                String nameEnterprise = listEnterprisePanel.getTblEnterpriseExt().getValueAt(i, 2).toString();
                String tel = listEnterprisePanel.getTblEnterpriseExt().getValueAt(i, 3).toString();
                String fax = listEnterprisePanel.getTblEnterpriseExt().getValueAt(i, 4).toString();
                String email = listEnterprisePanel.getTblEnterpriseExt().getValueAt(i, 5).toString();
                String web = listEnterprisePanel.getTblEnterpriseExt().getValueAt(i, 6).toString();

                Enterprise e = (Enterprise) listEnterprisePanel.getTblEnterpriseExt().getValueAt(i, 2);
                EnterpriseExt bean = enterpriseExtDao.getByObjectId(String.valueOf(e.getId()));

                try {
                    List<Integer> listAddress = bean.getEnterpriseAddressIdActual();
                    int idAddress = listAddress.get(0);
                    AddressInfoEnterprise aie = addressBN.getById(idAddress);
                    try {
                        country = (Country) listEnterprisePanel.getTblEnterpriseExt().getValueAt(i, 7);
                        aie.setCountryIdActual(country.getId());
                        addressBN.update(aie);
                    } catch (Exception ex1) {
                        aie.setCountryIdActual(0);
                        addressBN.update(aie);
                    }
                } catch (Exception ex) {
                }

                try {
                    List<Integer> listAddress = bean.getEnterpriseAddressIdActual();
                    int idAddress = listAddress.get(0);
                    AddressInfoEnterprise aie = addressBN.getById(idAddress);
                    try {
                        city = (City) listEnterprisePanel.getTblEnterpriseExt().getValueAt(i, 8);
                        aie.setCityIdActual(city.getId());
                        addressBN.update(aie);
                    } catch (Exception ex1) {
                        aie.setCityIdActual(0);
                        addressBN.update(aie);
                    }
                } catch (Exception ex) {
                }

                bean.setEnterpriseName(nameEnterprise);
                bean.setEnterpriseEmail(email);
                bean.setEnterpriseFax(fax);
                bean.setEnterpriseTel(tel);
                bean.setEnterpriseWeb(web);
                enterpriseExtDao.update(bean);
            }
        }
        loadDataEdit();
    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = listEnterprisePanel.getTblEnterpriseExt().getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        listEnterprisePanel.getTblEnterpriseExt().setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(listEnterprisePanel.getTblEnterpriseExt(), colorL, null, colorD, null);
        listEnterprisePanel.getTblEnterpriseExt().repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = listEnterprisePanel.getTblEnterpriseExt().getFont().getFontName();
        size = listS.get(0).getSizeWord();
        listEnterprisePanel.getTblEnterpriseExt().setFont(new Font(font, 0, size));

    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        listEnterprisePanel.getTblEnterpriseExt().setForeground(color);
        listEnterprisePanel.getTblEnterpriseExt().repaint();
    }

    @Override
    public void resetColorTitle() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
        listEnterprisePanel.getTblEnterpriseExt().getTableHeader().setForeground(color);
        listEnterprisePanel.getTblEnterpriseExt().repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        listEnterprisePanel.getTblEnterpriseExt().setSelectionBackground(color);
        listEnterprisePanel.getTblEnterpriseExt().repaint();
    }

    @Override
    public JTable getTable() {
        return listEnterprisePanel.getTblEnterpriseExt();
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

    private void resetTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        TableCell tableCell = new TableCell(colorL, colorD);
        listEnterprisePanel.getTblEnterpriseExt().getColumnModel().getColumn(2).setCellRenderer(tableCell);

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

    private void resetTableEdit() {
        DefaultComboBoxModel modelCountry = new DefaultComboBoxModel();
        modelCountry.addElement("");
        List<Country> listCountry = countryBN.selectAll();
        for (Country p : listCountry) {
            modelCountry.addElement(p);
        }

        DefaultComboBoxModel modelCity = new DefaultComboBoxModel();
        modelCity.addElement("");
        List<City> list = new ArrayList<City>();
        list = cityBN.selectAll();
        for (City bean : list) {
            modelCity.addElement(bean);
        }
        JComboBox cbxContry = new JComboBox(modelCountry);
        JComboBox cbxCity = new JComboBox(modelCity);

        listEnterprisePanel.getTblEnterpriseExt().getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(cbxContry));
        listEnterprisePanel.getTblEnterpriseExt().getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(cbxCity));
    }

    private void loadDataEdit() {
        listEnterprisePanel.getTblEnterpriseExt().removeAll();
        listEnterprisePanel.getTblEnterpriseExt().setModel(this.getTableMode());
        resetTableEdit();
        setupTable();
    }

    private void loadData() {
        listEnterprisePanel.getTblEnterpriseExt().removeAll();
        listEnterprisePanel.getTblEnterpriseExt().setModel(this.getTableMode());
        resetTable();
        setupTable();
        filterTable(getTable(), model);
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
    public Hashtable<Integer, String> getTableHeader() {
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
                    if(tc instanceof  IEnableButton){
                        try {
                            ((IEnableButton)tc).enableButton();
                        } catch (IOException ex) {
                            Exceptions.printStackTrace(ex);
                        }
                    }                
                    toolbarEnterprise(p1);
                    getEnterprise(p1.getEnterpriseId());

                }

            }
            aTable.clearSelection();
        }

        private void toolbarEnterprise(Enterprise p1) {
            BasicToolbarManager.getBasicToolbar().loadEnterprise();
            BasicToolbarManager.getBasicToolbar().setEnterprise(p1);
        }

        private void getEnterprise(String id) {
            Collection<? extends IGetObject> allSave = Lookup.getDefault().lookupAll(IGetObject.class);
            for (IGetObject saveCookie : allSave) {
                saveCookie.getObject(id);
            }
        }        
    }
}