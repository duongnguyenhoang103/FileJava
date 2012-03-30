/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb22.person.extW23.ui.window;

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
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.WindowManager;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.basic.api.ISystemSotfwareBN;
import vn.com.hkt.basic.api.IWidthTableBN;
import vn.com.hkt.basic.toolbar.BasicToolbarManager;
import vn.com.hkt.pilot.sb22.person.extW23.dao.SubPersonSB22BN;
import vn.com.hkt.pilot.sb22.person.extW23.entity.SubPersonSB22;
import vn.com.hkt.pilot.sb22.person.extW23.ui.panel.ExtensionListPersonPanel;
import vn.com.hkt.pilot.enterprise.viewer.api.EditCookieList;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnableButton;
import vn.com.hkt.pilot.enterprise.viewer.api.IGetObject;
import vn.com.hkt.pilot.enterprise.viewer.api.RemoveCookiePerson;
import vn.com.hkt.pilot.enterprise.viewer.api.ResetCookieList;
import vn.com.hkt.pilot.enterprise.viewer.api.SaveCookieList;
import vn.com.hkt.pilot.enterprise.viewer.api.ViewCookieList;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.person.viewer.api.IPersonCreater;
import vn.com.hkt.pilot.person.viewer.api.IPersonExtCreater;
import vn.com.hkt.pilot.person.viewer.api.IPersonExtViewer;
import vn.com.hkt.pilot.person.viewer.api.IPersonViewer;
import vn.com.hkt.pilot.report.api.IReportListGUI;
import vn.com.hkt.pilot.report.api.IReportManager;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.person.extW23.ui.window//ExtensionListPerson//EN",
autostore = false)
@TopComponent.Description(preferredID = "ExtensionListPersonTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.person.extW23.ui.window.ExtensionListPersonTopComponent")
@ActionReference(path = " " /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ExtensionListPersonAction",
preferredID = "ExtensionListPersonTopComponent")
@ServiceProvider(service = IPersonExtViewer.class)
public final class ExtensionListPersonTopComponent extends TopComponent implements MouseMotionListener, IPersonExtViewer, ViewCookieList, RemoveCookiePerson, ResetCookieList,
        FilterCokieTable, EditCookieList, SaveCookieList, IResetFontSize, IReportListGUI {

    private ExtensionListPersonPanel listPersonPanel = new ExtensionListPersonPanel();
    private DefaultTableModel model;
    private SubPersonSB22BN dao = new SubPersonSB22BN();
    private int itsRow = 0;
    private int itsColumn = 0;
    private boolean isEdit = false;
    private int size;
    private String font;
    private ISystemSotfwareBN sotfwareBN = Lookup.getDefault().lookup(ISystemSotfwareBN.class);
    private List<SystemSoftware> listS = new ArrayList<SystemSoftware>();
    private TableRowSorter<TableModel> tableRowSorter;

    public ExtensionListPersonTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ExtensionListPersonTopComponent.class, "CTL_ExtensionListPersonTopComponent"));
        setToolTipText(NbBundle.getMessage(ExtensionListPersonTopComponent.class, "HINT_ExtensionListPersonTopComponent"));
        listPersonPanel.getTableLitsPerson().addMouseMotionListener(this);
    }

    private void addRow() {
        IPersonBN personBN = Lookup.getDefault().lookup(IPersonBN.class);
        String[] header = {"Mã", "Ảnh", "Họ tên", "Tell", "Fax", "Email", "Web", "Quốc kỳ", "Thành Phố"};
        model = new DefaultTableModel(header, 0){
             boolean[] canEdit = new boolean[]{
                false, false, false, true, true, true, true, true, true
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        List<Person> list1 = personBN.selectAll();
        List<SubPersonSB22> list2 = dao.selectAll();

        int m = list1.size();
        int n = list2.size();

        int i, j;
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if (list2.get(i).getPersonIdActual() == (list1.get(j).getId())) {
                    SubPersonSB22 p = list2.get(i);
                    Person person = list1.get(j);
                    Object[] row = {person.getPersonId(), "Anh", person, p.getTel(), p.getFax(), p.getEmail(), p.getWeb(), "QuocKy", "ThanhPho"};
                    model.addRow(row);
                }
            }

        }
        listPersonPanel.getTableLitsPerson().removeAll();
        listPersonPanel.getTableLitsPerson().setModel(model);
        listPersonPanel.getTableLitsPerson().getColumnModel().getColumn(2).setPreferredWidth(200);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
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
    public JPanel getPersonExtViewer() {
        return this.listPersonPanel;
    }

    @Override
    public String toString() {
        return "Thông tin nghề nghiệp";
    }

    @Override
    public Lookup getPersonExtViewerLookup() {
        return null;
    }

    @Override
    public double getLevelNumber() {
        throw new UnsupportedOperationException("Not supported yet.");
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
        int row = listPersonPanel.getTableLitsPerson().getSelectedRow();
        if (row >= 0) {
            Person bean = (Person) listPersonPanel.getTableLitsPerson().getValueAt(row, 2);
            Collection<? extends RemoveCookiePerson> allRemoveCookie = Lookup.getDefault().lookupAll(RemoveCookiePerson.class);
            for (RemoveCookiePerson r : allRemoveCookie) {
                if (r instanceof IPersonExtViewer || r instanceof IPersonViewer) {
                    r.removeObject(bean.getId());
                }
            }
        }
    }

    @Override
    public void removeObject(int i) throws IOException {
        try {
            List<SubPersonSB22> list = dao.select(SubPersonSB22.FIELD_PERSON_ID_ACTUAL, String.valueOf(i));
            SubPersonSB22 bean = list.get(0);
            dao.delete(bean.getId());
        } catch (Exception e) {
        }
    }

    @Override
    public void resetCookie() throws IOException {
        ViewCookieList();
    }

    private void setupTable() {
        IWidthTableBN widthTableBN = Lookup.getDefault().lookup(IWidthTableBN.class);
        listPersonPanel.getTableLitsPerson().setSelectionBackground(new Color(192, 210, 224));
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(listPersonPanel.getTableLitsPerson(), colorL, null, colorD, null);

        int sizeId = widthTableBN.getWidth1();
        int sizeLogo = widthTableBN.getWidth2();
        int sizeName = widthTableBN.getWidth3();


        listPersonPanel.getTableLitsPerson().getColumnModel().getColumn(0).setMaxWidth(sizeId);
        listPersonPanel.getTableLitsPerson().getColumnModel().getColumn(0).setPreferredWidth(sizeId);

        listPersonPanel.getTableLitsPerson().getColumnModel().getColumn(1).setMaxWidth(sizeLogo);
        listPersonPanel.getTableLitsPerson().getColumnModel().getColumn(1).setPreferredWidth(sizeLogo);

        listPersonPanel.getTableLitsPerson().getColumnModel().getColumn(2).setMaxWidth(sizeName);
        listPersonPanel.getTableLitsPerson().getColumnModel().getColumn(2).setPreferredWidth(sizeName);

        listPersonPanel.getTableLitsPerson().getColumnModel().getColumn(0).setResizable(false);
        listPersonPanel.getTableLitsPerson().getColumnModel().getColumn(1).setResizable(false);
        listPersonPanel.getTableLitsPerson().getColumnModel().getColumn(2).setResizable(false);
        listPersonPanel.getTableLitsPerson().getColumnModel().getColumn(3).setResizable(false);
        listPersonPanel.getTableLitsPerson().getColumnModel().getColumn(4).setResizable(false);
        listPersonPanel.getTableLitsPerson().getColumnModel().getColumn(5).setResizable(false);
        listPersonPanel.getTableLitsPerson().getColumnModel().getColumn(6).setResizable(false);
        listPersonPanel.getTableLitsPerson().getColumnModel().getColumn(7).setResizable(false);
        listPersonPanel.getTableLitsPerson().getColumnModel().getColumn(8).setResizable(false);
        listPersonPanel.getTableLitsPerson().getTableHeader().setReorderingAllowed(false);
        listPersonPanel.getTableLitsPerson().setRowHeight(26);
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
        TopComponent tc = WindowManager.getDefault().findTopComponent("ProductViewerTopComponent");
        if (!tc.isShowing()) {
            return;
        }
        int n = listPersonPanel.getTableLitsPerson().getRowCount();
        if (n <= 0) {
            return;
        }
        for (int i = 0; i < n; i++) {
            Person person;
            SubPersonSB22 p;
            try {
                person = (Person) listPersonPanel.getTableLitsPerson().getValueAt(i, 2);
                List<SubPersonSB22> list = dao.select(SubPersonSB22.FIELD_PERSON_ID_ACTUAL, String.valueOf(person.getId()));
                p = list.get(0);
            } catch (Exception e) {
                p = new SubPersonSB22();
            }

            String tell = listPersonPanel.getTableLitsPerson().getValueAt(i, 3).toString();
            String fax = listPersonPanel.getTableLitsPerson().getValueAt(i, 4).toString();
            String email = listPersonPanel.getTableLitsPerson().getValueAt(i, 5).toString();
            String web = listPersonPanel.getTableLitsPerson().getValueAt(i, 6).toString();

            p.setTel(tell);
            p.setFax(fax);
            p.setEmail(email);
            p.setWeb(web);

            dao.update(p);
        }
        loadDataEdit();
    }

    private void resetTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        TableCell tableCell = new TableCell(colorL, colorD);
        listPersonPanel.getTableLitsPerson().getColumnModel().getColumn(2).setCellRenderer(tableCell);

    }

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

    private void resetTableEdit() {
        // Add ComboBox neu co
    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = listPersonPanel.getTableLitsPerson().getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        listPersonPanel.getTableLitsPerson().setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(listPersonPanel.getTableLitsPerson(), colorL, null, colorD, null);
        listPersonPanel.getTableLitsPerson().repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = listPersonPanel.getTableLitsPerson().getFont().getFontName();
        size = listS.get(0).getSizeWord();
        listPersonPanel.getTableLitsPerson().setFont(new Font(font, 0, size));

    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        listPersonPanel.getTableLitsPerson().setForeground(color);
        listPersonPanel.getTableLitsPerson().repaint();
    }

    @Override
    public void resetColorTitle() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
        listPersonPanel.getTableLitsPerson().getTableHeader().setForeground(color);
        listPersonPanel.getTableLitsPerson().repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        listPersonPanel.getTableLitsPerson().setSelectionBackground(color);
        listPersonPanel.getTableLitsPerson().repaint();
    }

    @Override
    public JTable getTable() {
        return listPersonPanel.getTableLitsPerson();
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
        String[] keys1 = {"prTenDoanhNghiep", "prDiaChi", "prTenBaoCao", "prNgayLap"};
        String[] values1 = {"HKT Consultant", "66 Trần Thái Tông Hà Nội", "", "16/03/2012"};
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
                    Person p1 = (Person) aTable.getValueAt(selct, sectcol);
                    TopComponent tc1 = WindowManager.getDefault().findTopComponent("PersonViewerTopComponent");
                    if (tc1.isOpened()) {
                        tc1.close();
                    }
                    TopComponent tc = WindowManager.getDefault().findTopComponent("PersonCreatorTopComponent");
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
                    getObject(p1.getPersonId());
                    toolbarPerson(p1);
                }

            }
            aTable.clearSelection();
        }
    }
     private void toolbarPerson(Person bean) {
        BasicToolbarManager.getBasicToolbar().loadPerson();
        BasicToolbarManager.getBasicToolbar().setPerson(bean);
    }

    private void getObject(String id) {
        Collection<? extends IGetObject> allSave = Lookup.getDefault().lookupAll(IGetObject.class);
        for (IGetObject getObject : allSave) {
            if (getObject instanceof IPersonCreater || getObject instanceof IPersonExtCreater) {
                getObject.getObject(id);
            }
        }
    }  
}
