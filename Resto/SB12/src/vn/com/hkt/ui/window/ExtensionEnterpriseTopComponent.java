/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.ui.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.ICityBN;
import vn.com.hkt.basic.api.ICountryBN;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.ISystemSotfwareBN;
import vn.com.hkt.enterprise.ext.dao.AddressInfoEnterpriseBN;
import vn.com.hkt.enterprise.ext.dao.EnterpriseExtBN;
import vn.com.hkt.enterprise.ext.dao.ExecutiveBN;
import vn.com.hkt.enterprise.ext.entity.AddressInfoEnterprise;
import vn.com.hkt.enterprise.ext.entity.EnterpriseExt;
import vn.com.hkt.enterprise.ext.entity.ExecutiveOffice;
import vn.com.hkt.pilot.enterprise.viewer.api.HelpTutorialEnterprise;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnableTable;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseExtCreator;
import vn.com.hkt.pilot.enterprise.viewer.api.IGetObject;
import vn.com.hkt.pilot.enterprise.viewer.api.ResetCookie;
import vn.com.hkt.pilot.entities.City;
import vn.com.hkt.pilot.entities.Country;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.operation.viewer.api.ILoadComboCountry;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.ui.main.ui.api.creater.ISaveExtention;
import vn.com.hkt.ui.panel.EnterpriseExtAddressCell;
import vn.com.hkt.ui.panel.ExtensionEnterprisePanel;
import vn.com.hkt.ui.panel.SB12Tutorial;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.ui.window//ExtensionEnterprise//EN",
autostore = false)
@TopComponent.Description(preferredID = "ExtensionEnterpriseTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.ui.window.ExtensionEnterpriseTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ExtensionEnterpriseAction",
preferredID = "ExtensionEnterpriseTopComponent")
@ServiceProvider(service = IEnterpriseExtCreator.class)
public class ExtensionEnterpriseTopComponent extends TopComponent implements IEnterpriseExtCreator, ISaveExtention,
        IEnableTable, IResetFontSize, IGetObject, ILoadComboCountry {

    private ExtensionEnterprisePanel enterprisePanel;
    private EnterpriseExtBN enterpriseextdao;
    private AddressInfoEnterpriseBN addressInfoBN;
    private int size;
    private String font;
    private ISystemSotfwareBN sotfwareBN;
    private List<SystemSoftware> listS;
    private int i = 3;
    private DefaultTableModel modelDiaChi;
    private EnterpriseExtAddressCell enterpriseExtAddressCell;
    private int idEnterprise = 0;

    public ExtensionEnterpriseTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ExtensionEnterpriseTopComponent.class, "CTL_ExtensionEnterpriseTopComponent"));
        setToolTipText(NbBundle.getMessage(ExtensionEnterpriseTopComponent.class, "HINT_ExtensionEnterpriseTopComponent"));
        sotfwareBN = Lookup.getDefault().lookup(ISystemSotfwareBN.class);
        listS = new ArrayList<SystemSoftware>();
        enterpriseextdao = new EnterpriseExtBN();
        enterprisePanel = new ExtensionEnterprisePanel();
        addressInfoBN = new AddressInfoEnterpriseBN();
        enterpriseExtAddressCell = new EnterpriseExtAddressCell(10);
        enterprisePanel.getTableDiaChi().getColumnModel().getColumn(2).setCellEditor(enterpriseExtAddressCell);
        enterprisePanel.getTableDiaChi().getColumnModel().getColumn(3).setCellEditor(enterpriseExtAddressCell);

        enterprisePanel.getTableExtensionE().addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
        enterprisePanel.getTableDiaChi().addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
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
    }

    @Override
    public void componentClosed() {
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

    private String getValueString(int row, int column) {
        try {
            return enterprisePanel.getTableExtensionE().getValueAt(row, column).toString().trim();
        } catch (Exception ex) {
            return "";
        }
    }

    private List<Integer> getValueAddress() {
        int row = enterprisePanel.getTableDiaChi().getRowCount();
        List<Integer> listAddress = new ArrayList<Integer>();

        for (int i = 0; i < row; i++) {
            AddressInfoEnterprise aie = new AddressInfoEnterprise();
            String address;
            int idCountry, idCity;
            try {
                address = enterprisePanel.getTableDiaChi().getValueAt(i, 1).toString();
            } catch (Exception ex) {
                address = "";
            }
            try {
                Country country = (Country) enterprisePanel.getTableDiaChi().getValueAt(i, 2);
                idCountry = country.getId();
            } catch (Exception ex) {
                idCountry = 0;
            }
            try {
                City city = (City) enterprisePanel.getTableDiaChi().getValueAt(i, 3);
                idCity = city.getId();
            } catch (Exception ex) {
                idCity = 0;
            }

            aie.setAddressInfoName(address);
            aie.setCountryIdActual(idCountry);
            aie.setCityIdActual(idCity);
            addressInfoBN.insert(aie);
            listAddress.add(aie.getId());
        }
        return listAddress;
    }

    @Override
    public void reset() {
        idEnterprise = 0;
        enterprisePanel.getTableExtensionE().setValueAt("", 0, 1);
        enterprisePanel.getTableExtensionE().setValueAt("", 0, 3);
        enterprisePanel.getTableExtensionE().setValueAt("", 1, 1);
        enterprisePanel.getTableExtensionE().setValueAt("", 1, 3);

        enterprisePanel.getTableDiaChi().setValueAt(" ", 0, 1);
        enterprisePanel.getTableDiaChi().setValueAt(" ", 0, 2);
        enterprisePanel.getTableDiaChi().setValueAt(" ", 0, 3);
        enterprisePanel.getTableDiaChi().setValueAt(" ", 1, 1);
        enterprisePanel.getTableDiaChi().setValueAt(" ", 1, 2);
        enterprisePanel.getTableDiaChi().setValueAt(" ", 1, 3);
        enterprisePanel.getTableDiaChi().setValueAt(" ", 2, 1);
        enterprisePanel.getTableDiaChi().setValueAt(" ", 2, 2);
        enterprisePanel.getTableDiaChi().setValueAt(" ", 2, 3);
        enterprisePanel.getTableDiaChi().getColumnModel().getColumn(2).setCellEditor(enterpriseExtAddressCell);
        enterprisePanel.getTableDiaChi().getColumnModel().getColumn(3).setCellEditor(enterpriseExtAddressCell);

        enterprisePanel.getTableExtensionE().setIntercellSpacing(new java.awt.Dimension(0, 0));
        enterprisePanel.getTableExtensionE().setRowHeight(26);
        enterprisePanel.getTableExtensionE().setShowHorizontalLines(false);
        enterprisePanel.getTableExtensionE().setShowVerticalLines(false);
        enterprisePanel.getTableExtensionE().setTableHeader(null);
        enterprisePanel.getTableExtensionE().getColumnModel().getColumn(0).setPreferredWidth(100);
        enterprisePanel.getTableExtensionE().getColumnModel().getColumn(0).setMaxWidth(100);
        enterprisePanel.getTableExtensionE().getColumnModel().getColumn(2).setPreferredWidth(100);
        enterprisePanel.getTableExtensionE().getColumnModel().getColumn(2).setMaxWidth(100);
        enterprisePanel.getTableExtensionE().setRowSelectionAllowed(true);
        enterprisePanel.getTableExtensionE().setColumnSelectionAllowed(false);
        i = 3;
    }

    @Override
    public JPanel getEnterpriseExtCreator() {
        return this.enterprisePanel;
    }

    @Override
    public Lookup getEnterpriseExtLookup() {
        return null;
    }

    @Override
    public double getLevelNumber() {
        return 1.21;
    }

    // Sự kiện load hướng dẫn
    private void tableMousePressed(MouseEvent evt) {
        JTable table = (JTable) evt.getSource();
        if (table == enterprisePanel.getTableDiaChi()) {
            if (enterprisePanel.getTableDiaChi().getSelectedRow() != -1) {
                if (enterprisePanel.getTableDiaChi().getSelectedColumn() == 2) {
                    addFormEditID(1.2, " ");
                }
                if (enterprisePanel.getTableDiaChi().getSelectedColumn() == 3) {
                    addFormEditID(1.3, " ");
                }
                if (enterprisePanel.getTableDiaChi().getSelectedRow() == i - 1) {
                    modelDiaChi = (DefaultTableModel) enterprisePanel.getTableDiaChi().getModel();
                    i++;
                    String str = "                 " + i + "";
                    Object[] rows1 = {str, " ", " "};
                    modelDiaChi.addRow(rows1);

                    enterprisePanel.getTableDiaChi().repaint();
                }

            }
        }
        if (table == enterprisePanel.getTableExtensionE()) {
            SB12Tutorial sB12Tutorial = new SB12Tutorial();
            if (enterprisePanel.getTableExtensionE().getSelectedRow() != -1) {
                if (enterprisePanel.getTableExtensionE().getSelectedColumn() == 1
                        && enterprisePanel.getTableExtensionE().getSelectedRow() == 0) {
                    addFormEditID(2.01, sB12Tutorial.getTxtTell().getText());
                }
                if (enterprisePanel.getTableExtensionE().getSelectedColumn() == 3
                        && enterprisePanel.getTableExtensionE().getSelectedRow() == 0) {
                    addFormEditID(2.00, sB12Tutorial.getTxtFax().getText());
                }
                if (enterprisePanel.getTableExtensionE().getSelectedColumn() == 3
                        && enterprisePanel.getTableExtensionE().getSelectedRow() == 1) {
                    addFormEditID(2.13, sB12Tutorial.getTxtWeb().getText());
                }
                if (enterprisePanel.getTableExtensionE().getSelectedColumn() == 1
                        && enterprisePanel.getTableExtensionE().getSelectedRow() == 1) {
                    addFormEditID(2.11, sB12Tutorial.getTxtEmail().getText());
                }

            }
        }
    }
  

    // Thêm panel sửa ID Enter
    public void addFormEditID(double i, String str) {
        Collection<? extends HelpTutorialEnterprise> allSave = Lookup.getDefault().lookupAll(HelpTutorialEnterprise.class);



        for (HelpTutorialEnterprise editCookie : allSave) {
            editCookie.getTutorial(i, str);
        }
    }
// Liên thông font cỡ chữ, màu

    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = enterprisePanel.getTableDiaChi().getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        enterprisePanel.getTableDiaChi().setFont(new Font(font, 0, size));
        enterprisePanel.getTableExtensionE().setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(enterprisePanel.getTableDiaChi(), colorL, null, colorD, null);
        enterprisePanel.getTableDiaChi().repaint();
        StripedTableCellRenderer.installInColumn(enterprisePanel.getTableExtensionE(), colorL, null, colorD, null);
        enterprisePanel.getTableExtensionE().repaint();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = enterprisePanel.getTableDiaChi().getFont().getFontName();
        size = listS.get(0).getSizeWord();
        enterprisePanel.getTableDiaChi().setFont(new Font(font, 0, size));
        enterprisePanel.getTableExtensionE().setFont(new Font(font, 0, size));

    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        enterprisePanel.getTableDiaChi().setForeground(color);
        enterprisePanel.getTableExtensionE().setForeground(color);
        enterprisePanel.getTableExtensionE().repaint();
        enterprisePanel.getTableDiaChi().repaint();
    }

    @Override
    public void resetColorTitle() {
//        listS = sotfwareBN.selectAll();
//        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
//        enterprisePanel.getTableDiaChi().getTableHeader().setForeground(color);
//        enterprisePanel.getTableExtensionE().getTableHeader().setForeground(color);
//        enterprisePanel.getTableExtensionE().repaint();
//        enterprisePanel.getTableDiaChi().repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        enterprisePanel.getTableDiaChi().setSelectionBackground(color);
        enterprisePanel.getTableExtensionE().setSelectionBackground(color);
        enterprisePanel.getTableExtensionE().repaint();
        enterprisePanel.getTableDiaChi().repaint();
    }

    @Override
    public void getObject(String id) {
        IEnterpriseBN enterpriseBN = Lookup.getDefault().lookup(IEnterpriseBN.class);
        ICityBN cityBN = Lookup.getDefault().lookup(ICityBN.class);
        ICountryBN countryBN = Lookup.getDefault().lookup(ICountryBN.class);
        Enterprise e = enterpriseBN.getByObjectId(id);
        if (e == null) {
            return;
        }
        EnterpriseExt bean = enterpriseextdao.getByObjectId(String.valueOf(e.getId()));
        enterprisePanel.getTableExtensionE().setValueAt(bean.getEnterpriseTel(), 0, 1);
        enterprisePanel.getTableExtensionE().setValueAt(bean.getEnterpriseFax(), 0, 3);
        enterprisePanel.getTableExtensionE().setValueAt(bean.getEnterpriseEmail(), 1, 1);
        enterprisePanel.getTableExtensionE().setValueAt(bean.getEnterpriseWeb(), 1, 3);
        try {
            List<Integer> listAddr = bean.getEnterpriseAddressIdActual();
            if (listAddr.size() != -1) {
                if (listAddr.size() < 3) {
                    AddressInfoEnterprise AddressInfoEnterprise[] = new AddressInfoEnterprise[4];
                    for (int j = 0; j < listAddr.size(); j++) {
                        AddressInfoEnterprise[j] = addressInfoBN.getById(listAddr.get(j));
                        Country country = countryBN.getById(AddressInfoEnterprise[j].getCountryIdActual());
                        City city = cityBN.getById(AddressInfoEnterprise[j].getCityIdActual());
                        enterprisePanel.getTableDiaChi().setValueAt(AddressInfoEnterprise[j].getAddressInfoName(), j + 1, 1);
                        enterprisePanel.getTableDiaChi().setValueAt(country, j, 2);
                        enterprisePanel.getTableDiaChi().setValueAt(city, j, 3);
                    }
                    for (int j = listAddr.size(); j < 3; j++) {

                        enterprisePanel.getTableDiaChi().setValueAt(" ", j, 1);
                        enterprisePanel.getTableDiaChi().setValueAt(" ", j, 2);
                        enterprisePanel.getTableDiaChi().setValueAt(" ", j, 3);
                    }
                } else {
                    AddressInfoEnterprise AddressInfoEnterprise[] = new AddressInfoEnterprise[listAddr.size()];
                    for (int j = 0; j < 3; j++) {
                        AddressInfoEnterprise[j] = addressInfoBN.getById(listAddr.get(j));
                        Country country = countryBN.getById(AddressInfoEnterprise[j].getCountryIdActual());
                        City city = cityBN.getById(AddressInfoEnterprise[j].getCityIdActual());
                        enterprisePanel.getTableDiaChi().setValueAt(AddressInfoEnterprise[j].getAddressInfoName(), j, 1);
                        enterprisePanel.getTableDiaChi().setValueAt(country, j, 2);
                        enterprisePanel.getTableDiaChi().setValueAt(city, j, 3);
                    }
                    modelDiaChi = (DefaultTableModel) enterprisePanel.getTableDiaChi().getModel();
                    for (int j = 3; j < listAddr.size(); j++) {
                        Country country = null;
                        City city = null;
                        boolean ok = true;
                        try {
                            if (AddressInfoEnterprise[j].getCountryIdActual() != -1) {
                                country = countryBN.getById(AddressInfoEnterprise[j].getCountryIdActual());
                            } else {
                                country = new Country();
                            }
                        } catch (Exception ex) {
                            ok = false;
                        }
                        try {
                            if (AddressInfoEnterprise[j].getCityIdActual() != -1) {
                                city = cityBN.getById(AddressInfoEnterprise[j].getCityIdActual());
                            } else {
                                city = new City();
                            }
                        } catch (Exception ex) {
                            ok = false;
                        }

                        try {
                            if (ok) {
                                Object[] row = {AddressInfoEnterprise[j].getAddressInfoName(), country, city};
                                modelDiaChi.addRow(row);
                            } else {
                                Object[] row = {AddressInfoEnterprise[j].getAddressInfoName(), " ", " "};
                                modelDiaChi.addRow(row);
                            }
                        } catch (Exception ex) {
                            Object[] row = {" ", " ", " "};
                            modelDiaChi.addRow(row);
                        }
                    }

                }
            }
        } catch (Exception ex) {
        }

    }

    @Override
    public void enableTable(boolean ok) throws IOException {
        enterprisePanel.getTableDiaChi().setEnabled(ok);
        enterprisePanel.getTableExtensionE().setEnabled(ok);
    }

    @Override
    public void loadCombo() {
        enterpriseExtAddressCell.loadCboCountry();
    }

    @Override
    public List<JTable> getTables() {
        List<JTable> lt = new ArrayList<JTable>();
        lt.add(enterprisePanel.getTableDiaChi());
        lt.add(enterprisePanel.getTableExtensionE());
        return lt;
    }

    @Override
    public void setEntity(IEntity entity) {
        idEnterprise = entity.getId();
    }

    @Override
    public IEntity save() {
        ExecutiveBN executiveBN = new ExecutiveBN();
        String tell, email, fax, web;
        tell = this.getValueString(0, 1);
        email = this.getValueString(1, 1);
        fax = this.getValueString(0, 3);
        web = this.getValueString(1, 3);
        List<Integer> lst = new ArrayList<Integer>();
        EnterpriseExt bean;

        if (idEnterprise == 0) {
            return null;
        }
        bean = enterpriseextdao.getByObjectId(String.valueOf(idEnterprise));
        if (bean == null) {
            bean = new EnterpriseExt();
        }
        bean.setEnterpriseIdActual(idEnterprise);

        List<ExecutiveOffice> listEO = executiveBN.filter(ExecutiveOffice.FIELD_EXECUTIVEOFFICE_ID_ACTUAL, String.valueOf(idEnterprise));


        if (!listEO.isEmpty()) {
            for (ExecutiveOffice e : listEO) {
                lst.add(e.getId());
            }
        }

        bean.setEnterpriseTel(tell);
        bean.setEnterpriseEmail(email);
        bean.setEnterpriseFax(fax);
        bean.setEnterpriseWeb(web);
        //
        List<Integer> listAddress = this.getValueAddress();
        bean.setEnterpriseAddressIdActual(listAddress);

        bean.setPostalCode(" "); // Sau này đưa PostalCode vào Country thì get từ Contry ra
        //
        bean.setExecutiveOfficesIdActual(lst);
        enterpriseextdao.update(bean);
        return bean;
    }
}
