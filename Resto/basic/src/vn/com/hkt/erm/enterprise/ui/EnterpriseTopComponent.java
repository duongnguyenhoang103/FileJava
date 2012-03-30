/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.erm.enterprise.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.IOException;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.basic.api.IEnterpriseBN;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.JTable;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.TopComponent;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.basic.toolbar.BasicToolbarManager;
import vn.com.hkt.pilot.enterprise.viewer.api.CreateKeyEnterprise;
import vn.com.hkt.pilot.enterprise.viewer.api.HelpTutorialEnterprise;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnableTable;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseCreator;
import vn.com.hkt.pilot.enterprise.viewer.api.IGetObject;
import vn.com.hkt.pilot.enterprise.viewer.api.ResetCookie;
import vn.com.hkt.pilot.entities.KeyGenerate;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.entities.system.dao.SystemSotfwareDAO;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.keymanager.api.CreateKey;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.ui.main.ui.api.creater.ISaveBasic;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//com.vn.hkt.ui//Enterprise//EN",
autostore = false)
@TopComponent.Description(preferredID = "EnterpriseTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.erm.enterprise.ui.EnterpriseTopComponent")
@ActionReference(path = " " /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_EnterpriseAction",
preferredID = "EnterpriseTopComponent")
@ServiceProvider(service = IEnterpriseCreator.class)
public final class EnterpriseTopComponent extends TopComponent implements ActionListener, IEnterpriseCreator, ISaveBasic,
        IEnableTable, CreateKeyEnterprise, IResetFontSize, IGetObject {

    private EnterprisePanel enterprisePanel;
    private IEnterpriseBN enterpriseBN;
    private EnterpriseEditIDPanel editIDPanel = EnterpriseEditIDPanel.getpEditIDPanel();
    private int size, director = 0, parent = 0;
    private String font, id = " ", name = " ", key, slogan = " ";
    private byte[] logo = null;
    private SystemSotfwareDAO sotfwareBN;
    private List<SystemSoftware> listS;
    private EnterpriseCellEditor enterpriseCell;
    private Enterprise enterprise = null;
    private JTable tableBasic;
    private KeyGenerate keyGenerate = null;

    public EnterpriseTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(EnterpriseTopComponent.class, "CTL_EnterpriseTopComponent"));
        setToolTipText(NbBundle.getMessage(EnterpriseTopComponent.class, "HINT_EnterpriseTopComponent"));
        enterprisePanel = new EnterprisePanel();
        panelForm.setLayout(new GridLayout());
        enterpriseCell = enterprisePanel.getEnterpriseCell();
        tableBasic = enterprisePanel.getTabelEnterprise();
        panelForm.add(enterprisePanel);

        this.enterpriseBN = Lookup.getDefault().lookup(IEnterpriseBN.class);

//         Bắt sự kiện select row của table 
        tableBasic.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
        sotfwareBN = new SystemSotfwareDAO();
        listS = new ArrayList<SystemSoftware>();
    }

    public JPanel getPanelForm() {
        return panelForm;
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
            .addGap(0, 1039, Short.MAX_VALUE)
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
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
                .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(53, 53, 53))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelForm;
    // End of variables declaration//GEN-END:variables

    public EnterprisePanel getEnterprisePanel() {
        return enterprisePanel;
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    // reset lại table mỗi khi save hoặc muốn điền mới
    @Override
    public void reset() {
        enterprise = null;
        enterprisePanel.reset();
        tableBasic.setBackground(new java.awt.Color(242, 241, 240));
        tableBasic.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableBasic.getColumnModel().getColumn(0).setMaxWidth(100);
        tableBasic.setRowSelectionAllowed(true);
        tableBasic.setColumnSelectionAllowed(false);
        tableBasic.setRowHeight(26);
        resetColorTitle();
        resetColorWord();
        resetFont();
        resetColorRowTable();
    }

    // lấy ID và Name từ table để lưu xuống csdl
    private void saveIdAndName() {
        if (tableBasic.getValueAt(1, 1) != null && tableBasic.getValueAt(1, 1).toString().length() != 0) {
            id = tableBasic.getValueAt(1, 1).toString().trim();
        } else if (enterpriseCell.getTxtIdE().getText().trim().length() != 0) {
            id = enterpriseCell.getTxtIdE().getText().trim();
        }
        if (tableBasic.getValueAt(0, 1) != null && tableBasic.getValueAt(0, 1).toString().length() != 0) {
            name = tableBasic.getValueAt(0, 1).toString().trim();
        } else if (enterpriseCell.getTxtNameE().getText().trim().length() != 0) {
            name = enterpriseCell.getTxtNameE().getText().trim();
        }
    }

    // Lưu Person từ bảng Enterprise xuống CSDL
    private void savePerson() {
        if (tableBasic.getValueAt(3, 1) != null && tableBasic.getValueAt(3, 1).toString().trim().length() != 0) {

            Person person1 = (Person) tableBasic.getValueAt(3, 1);
            director = person1.getId();
        } else if (enterpriseCell.getCboPerson().getSelectedItem() != null) {
            Person person1 = (Person) enterpriseCell.getCboPerson().getSelectedItem();
            director = person1.getId();
        }

    }

    private void saveEnterpriseParent() {

        if (tableBasic.getValueAt(2, 1) != null && tableBasic.getValueAt(2, 1).toString().trim().length() != 0) {
            Enterprise enterprise1 = (Enterprise) tableBasic.getValueAt(2, 1);
            parent = enterprise1.getId();
        } else if (enterpriseCell.getCboEnterprise().getSelectedItem() != null) {
            Enterprise enterprise1 = (Enterprise) enterpriseCell.getCboEnterprise().getSelectedItem();
            parent = enterprise1.getId();
        }
    }

    private void saveLogoAndSlogan() {
        logo = enterprisePanel.getDataImages();

        try {
            slogan = tableBasic.getValueAt(4, 1).toString().trim();
        } catch (NullPointerException ex) {
            slogan = "";
        }
    }

    @Override
    public JPanel getEnterpriseCreator() {
        return this.enterprisePanel;
    }

    @Override
    public Lookup getEnterpriseLookup() {
        return null;
    }

    // Lưu Enterprise sau khi nhập
    @Override
    public IEntity save() {
        saveIdAndName();
        savePerson();
        saveEnterpriseParent();
        saveLogoAndSlogan();

        Enterprise bean = new Enterprise();
        if (enterprise != null) {
            bean = enterprise;
        }
        bean.setEnterpriseId(id);
        bean.setEnterpriseName(name);
        bean.setEnterpriseParentIdActual(parent);
        bean.setDirectorIdActual(director);
        bean.setPicture(logo);
        bean.setSlogan(slogan);
        if (tableBasic.getValueAt(0, 1) != null && tableBasic.getValueAt(0, 1).toString().length() != 0) {
            enterpriseBN.update(bean);
            BasicToolbarManager.getBasicToolbar().loadEnterprise();
        }
        return bean;
    }

    @Override
    public double getLevelNumber() {
        return 1.1;
    }

    @Override
    public void createKey() {
        boolean change = false;
        
        change = editIDPanel.isGetBoolean();
        CreateKey createKey = new CreateKey();
        List<Enterprise> listD = enterpriseBN.selectAll();
        for (Enterprise beans : listD) {
            if (key == beans.getEnterpriseId()) {
                key = null;
            }
        }
        if (key == null) {
            keyGenerate = new KeyGenerate();
            keyGenerate = createKey.createAKeyByParent(Enterprise.class,editIDPanel.getKeyManager().getLastPrefix());
            key = keyGenerate.toString();
        }
        if (change == true) {
            keyGenerate.setPrefix(editIDPanel.getKeyManager().getLastPrefix());
            keyGenerate.getAccessDataOfEntity().update(keyGenerate);
            key = keyGenerate.toString();
        }
        tableBasic.setValueAt(key, 1, 1);
        enterpriseCell.getTxtIdE().setText(key);
        tableBasic.getColumnModel().getColumn(1).setCellEditor(enterpriseCell);

    }

    public void addFormEditID(double i, String str) {
        Collection<? extends HelpTutorialEnterprise> allSave = Lookup.getDefault().lookupAll(HelpTutorialEnterprise.class);
        for (HelpTutorialEnterprise editCookie : allSave) {
            editCookie.getTutorial(i, str);
        }
    }

    //Sự kiện xóa select table
    private void tableMousePressed(MouseEvent evt) {
        EnterpriseTutorial enterpriseTutorial = new EnterpriseTutorial();
        JTable table = (JTable) evt.getSource();
        if (table == tableBasic) {
            if (tableBasic.getSelectedRow() == 1) {

                addFormEditID(0.1, "");
            }
            if (tableBasic.getSelectedRow() == 3) {
                addFormEditID(0.3, enterpriseTutorial.getTxtDirector().getText());
            }
            if (tableBasic.getSelectedRow() == 2) {
                addFormEditID(0.2, enterpriseTutorial.getTxtEnterpriseParent().getText());
            }
            if (tableBasic.getSelectedRow() == 4) {
                addFormEditID(0.4, enterpriseTutorial.getTxtSlogan().getText());
            }
            if (tableBasic.getSelectedRow() == 0) {
                addFormEditID(0, enterpriseTutorial.getTxtNameEnterprise().getText());
            }
        }

        // Reset select table
        Collection<? extends ResetCookie> allResetCookie = Lookup.getDefault().lookupAll(ResetCookie.class);
        for (ResetCookie rc : allResetCookie) {
            try {
                rc.resetSelectTable(this.getLevelNumber());
            } catch (IOException ex) {
            }
        }
    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = tableBasic.getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        tableBasic.setFont(new Font(font, 0, size));

    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        EnterpriseCellRedenrer.installInColumn(tableBasic, colorL, null, colorD, null);
        createKey();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = tableBasic.getFont().getFontName();
        size = listS.get(0).getSizeWord();
        tableBasic.setFont(new Font(font, 0, size));
        tableBasic.repaint();
//        for (int i = 0; i < tableBasic.getRowCount(); i++) {
//            tableBasic.setRowHeight(i, size + 10);
//        }
    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        tableBasic.setForeground(color);
        tableBasic.repaint();
    }

    @Override
    public void resetColorTitle() {
//        listS = sotfwareBN.selectAll();
//        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
//        tableBasic.getTableHeader().setForeground(color);
//        tableBasic.repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        tableBasic.setSelectionBackground(color);
        tableBasic.repaint();
    }

    @Override
    public void getObject(String id) {
        IPersonBN personBN = Lookup.getDefault().lookup(IPersonBN.class);
        Enterprise e = enterpriseBN.getByObjectId(id);
        if (e != null) {
            Enterprise enterprise2 = enterpriseBN.getById(e.getEnterpriseParentIdActual());
            enterprise = e;
            Person person = personBN.getById(e.getDirectorIdActual());
            tableBasic.getModel().setValueAt(e, 0, 1);
            tableBasic.getModel().setValueAt(e.getEnterpriseId(), 1, 1);
            tableBasic.getModel().setValueAt(enterprise2, 2, 1);
            tableBasic.getModel().setValueAt(person, 3, 1);
            tableBasic.getModel().setValueAt(e.getSlogan(), 4, 1);
            enterpriseCell.getTxtNameE().setText(e.getEnterpriseName());
            enterpriseCell.getTxtIdE().setText(e.getEnterpriseId());
            tableBasic.getColumnModel().getColumn(1).setCellEditor(enterpriseCell);
            enterprisePanel.setDataImages(e.getPicture());
            enterprisePanel.showImage();
        }


    }

    @Override
    public void enableTable(boolean ok) {
        tableBasic.setEnabled(ok);
//        enterprisePanel.getLabelEnterprise().setEnabled(ok);
    }

    @Override
    public List<JTable> getTables() {
        List<JTable> lt = new ArrayList<JTable>();
        lt.add(tableBasic);
        return lt;
    }
}