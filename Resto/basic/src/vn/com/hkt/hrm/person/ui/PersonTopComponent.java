/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.hrm.person.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.IOException;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.basic.api.IPersonBN;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.JTable;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.api.IDepartmentBN;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.IMission;
import vn.com.hkt.basic.toolbar.BasicToolbarManager;
import vn.com.hkt.pilot.dialog.dao.CountryBN;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnableTable;
import vn.com.hkt.pilot.enterprise.viewer.api.IGetObject;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.enterprise.viewer.api.ResetCookie;
import vn.com.hkt.pilot.entities.Country;
import vn.com.hkt.pilot.entities.KeyGenerate;
import vn.com.hkt.pilot.entities.Mission;
import vn.com.hkt.pilot.entities.system.SystemSoftware;
import vn.com.hkt.pilot.entities.system.dao.SystemSotfwareDAO;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;
import vn.com.hkt.pilot.keymanager.api.CreateKey;
import vn.com.hkt.pilot.person.viewer.api.CreateKeyPerson;
import vn.com.hkt.pilot.person.viewer.api.HelpTutorialPerson;
import vn.com.hkt.pilot.person.viewer.api.IPersonCreater;
import vn.com.hkt.pilot.toobar.api.IResetFontSize;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;
import vn.com.hkt.ui.main.ui.api.creater.ISaveBasic;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.hrm.person.ui//Person//EN",
autostore = false)
@TopComponent.Description(preferredID = "PersonTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.hrm.person.ui.PersonTopComponent")
@ActionReference(path = " " /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_PersonAction",
preferredID = "PersonTopComponent")
@ServiceProvider(service = IPersonCreater.class)
public final class PersonTopComponent extends TopComponent implements IPersonCreater, IEnableTable,
        ISaveBasic, CreateKeyPerson, IResetFontSize, IGetObject {

    private PersonPanel personPanel;
    private String key = null;
    private int size;
    private String font;
    private SystemSotfwareDAO sotfwareBN = new SystemSotfwareDAO();
    private List<SystemSoftware> listS = new ArrayList<SystemSoftware>();
    private PersonCell personCell;
    private Person person = null;
    private PersonEditIDPanel editIDPanel;
    
    private KeyGenerate keyGenerate = null;

    public PersonTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(PersonTopComponent.class, "CTL_PersonTopComponent"));
        setToolTipText(NbBundle.getMessage(PersonTopComponent.class, "HINT_PersonTopComponent"));
        personPanel = PersonPanel.getPersonPanel();
        personCell = personPanel.getPersonCell();


        personPanel.getTabelPerson().addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
    }

    public void setPersonPanel(PersonPanel personPanel) {
        this.personPanel = personPanel;
    }

    public PersonPanel getPersonPanel() {
        return personPanel;
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
            .addGap(0, 930, Short.MAX_VALUE)
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
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

    // reset lại table mỗi khi save hoặc muốn điền mới
    @Override
    public void reset() {
        person = null;
        Enterprise enterprise1 = BasicToolbarManager.getBasicToolbar().getEnterprise();
        if (enterprise1 != null) {
            personPanel.getTabelPerson().setValueAt(enterprise1, 3, 1);
            personCell.resetCombobox(enterprise1);
        } else {
            personPanel.getTabelPerson().setValueAt("", 3, 1);
        }
        personCell.reset();
        personPanel.getTabelPerson().setValueAt("", 0, 1);
        // personPanel.getTabelPerson().setValueAt("", 1, 1);
        personPanel.getTabelPerson().setValueAt("", 2, 1);
        personPanel.getTabelPerson().setValueAt("", 4, 1);
        personPanel.getTabelPerson().setValueAt("", 5, 1);
        personPanel.getTabelPerson().getColumnModel().getColumn(1).setCellEditor(personCell);
        personPanel.getTabelPerson().getColumnModel().getColumn(0).setPreferredWidth(100);
        personPanel.getTabelPerson().getColumnModel().getColumn(0).setMaxWidth(100);
        personPanel.getTabelPerson().setRowSelectionAllowed(true);
        personPanel.getTabelPerson().setColumnSelectionAllowed(false);
        personPanel.getTabelPerson().setSelectionBackground(new Color(192, 210, 224));
        personPanel.getTabelPerson().setRowHeight(26);
        personPanel.setDataImages(null);
        personPanel.showImage();

    }

    public Person getTable() {
        Person bean = new Person();
        if (person != null) {
            bean = person;
        }

        String personName = personPanel.getTabelPerson().getValueAt(0, 1).toString();
        String idPerson = personPanel.getTabelPerson().getValueAt(1, 1).toString();
        int idCountry = 0;
        int idMission = 0;
        byte[] image = personPanel.getDataImages();
        int idEnterprise;
        int idDepartment;

        if (personPanel.getTabelPerson().getValueAt(5, 1) != null) {
            if (personPanel.getTabelPerson().getValueAt(5, 1).toString().trim().length() != 0) {
                Mission m = (Mission) personPanel.getTabelPerson().getValueAt(5, 1);
                idMission = m.getId();
            } else {
                idMission = 0;
            }
        } else {
            idMission = 0;
        }


        // Lay id Country
        try {
            Country country = (Country) personPanel.getTabelPerson().getValueAt(2, 1);
            idCountry = country.getId();
        } catch (Exception ex) {
            idCountry = 0;
        }


        if (personPanel.getTabelPerson().getValueAt(3, 1) != null) {
            if (personPanel.getTabelPerson().getValueAt(3, 1).toString().trim().length() != 0) {
                Enterprise enterprise = (Enterprise) personPanel.getTabelPerson().getValueAt(3, 1);
                idEnterprise = enterprise.getId();
            } else {
                idEnterprise = 0;
            }
        } else {
            idEnterprise = 0;
        }

        // Lấy mã và lưu Department xuống csdl
        if (personPanel.getTabelPerson().getValueAt(4, 1) != null) {
            if (personPanel.getTabelPerson().getValueAt(4, 1).toString().trim().length() != 0) {
                Department department = (Department) personPanel.getTabelPerson().getValueAt(4, 1);
                idDepartment = department.getId();
            } else {
                idDepartment = 0;
            }
        } else {
            idDepartment = 0;
        }


        bean.setPersonId(idPerson);
        bean.setPersonName(personName);
        bean.setEnterpriseIdActual(idEnterprise);
        bean.setDepartmentIdActual(idDepartment);
        bean.setMissionIdActual(idMission);
        bean.setCountryIdActual(idCountry);
        bean.setImage(image);
        return bean;
    }

    @Override
    public JPanel getPersonCreater() {
        return this.personPanel;
    }

    @Override
    public Lookup getPersonLookup() {
        return null;
    }

    @Override
    public double getLevelNumber() {
        personCell.loadMission();
        personCell.loadNationality();
        return 3.41;
    }

    private void tableMousePressed(MouseEvent evt) {
        PersonTutorial personTutorial = new PersonTutorial();
        JTable table = (JTable) evt.getSource();
        if (table == personPanel.getTabelPerson()) {
            if (personPanel.getTabelPerson().getSelectedRow() == 1) {
                addFormEditID(0.1, "");
            }
            if (personPanel.getTabelPerson().getSelectedRow() == 5) {
                addFormEditID(0.5, personTutorial.getTxtMisstion().getText());
            }
            if (personPanel.getTabelPerson().getSelectedRow() == 0) {
                addFormEditID(0, personTutorial.getTxtNameP().getText());
            }
            if (personPanel.getTabelPerson().getSelectedRow() == 3) {
                addFormEditID(0.3, personTutorial.getTxtEnterprise().getText());
            }
            if (personPanel.getTabelPerson().getSelectedRow() == 2) {
                addFormEditID(0.2, personTutorial.getTxtCountry().getText());
            }
            if (personPanel.getTabelPerson().getSelectedRow() == 4) {
                addFormEditID(0.4, personTutorial.getTxtDepartmentParent().getText());
            }
        }
        this.resetSelectTable();
    }

    private void resetSelectTable() {
        // Reset select table
        Collection<? extends ResetCookie> allResetCookie = Lookup.getDefault().lookupAll(ResetCookie.class);
        for (ResetCookie rc : allResetCookie) {
            try {
                rc.resetSelectTable(this.getLevelNumber());
            } catch (IOException ex) {
            }
        }
    }

    private void addFormEditID(double i, String str) {
        Collection<? extends HelpTutorialPerson> allSave = Lookup.getDefault().lookupAll(HelpTutorialPerson.class);
        for (HelpTutorialPerson editCookie : allSave) {
            editCookie.getTutorial(i, str);
        }
    }

    @Override
    public void createKey() {
        IPersonBN personBN = Lookup.getDefault().lookup(IPersonBN.class);
        CreateKey createKey = new CreateKey();
        boolean change = false;
        editIDPanel = PersonEditIDPanel.getPanel();
        change = editIDPanel.isGetBoolean();

        List<Person> listD = personBN.selectAll();
        for (Person beans : listD) {
            if (key == beans.getPersonId()) {
                key = null;
            }
        }
        if (key == null) {
            keyGenerate = new KeyGenerate();
            keyGenerate = createKey.createAKeyByParent(Person.class, editIDPanel.getKeyManager().getLastPrefix());
            key = keyGenerate.toString();
        }
        if (change == true) {
            keyGenerate.setPrefix(editIDPanel.getKeyManager().getLastPrefix());
            keyGenerate.getAccessDataOfEntity().update(keyGenerate);
            key = keyGenerate.toString();
        }
        personPanel.getTabelPerson().setValueAt(key, 1, 1);
        personCell.getTxtIdP().setText(key);
        personPanel.getTabelPerson().getColumnModel().getColumn(1).setCellEditor(personCell);
        Enterprise enterprise1 = BasicToolbarManager.getBasicToolbar().getEnterprise();
        if (enterprise1 != null) {
            personPanel.getTabelPerson().setValueAt(enterprise1, 3, 1);
            personCell.resetCombobox(enterprise1);
            personCell.loadEnterprise();
        }
    }

    // Liên thông font cỡ chữ, màu
    @Override
    public void resetFont() {
        listS = sotfwareBN.selectAll();
        size = personPanel.getTabelPerson().getFont().getSize();
        font = listS.get(0).getFont().getFontName();
        personPanel.getTabelPerson().setFont(new Font(font, 0, size));
    }

    @Override
    public void resetColorRowTable() {
        listS = sotfwareBN.selectAll();
        Color colorL = new Color(listS.get(0).getColorLight().getRed(), listS.get(0).getColorLight().getGreen(), listS.get(0).getColorLight().getBlue());
        Color colorD = new Color(listS.get(0).getColorDark().getRed(), listS.get(0).getColorDark().getGreen(), listS.get(0).getColorDark().getBlue());
        StripedTableCellRenderer.installInColumn(personPanel.getTabelPerson(), colorL, null, colorD, null);
        personPanel.getTabelPerson().repaint();
        createKey();
    }

    @Override
    public void resetSize() {
        listS = sotfwareBN.selectAll();
        font = personPanel.getTabelPerson().getFont().getFontName();
        size = listS.get(0).getSizeWord();
        personPanel.getTabelPerson().setFont(new Font(font, 0, size));
        for (int i = 0; i < personPanel.getTabelPerson().getRowCount(); i++) {
            personPanel.getTabelPerson().setRowHeight(i, size + 10);
        }
    }

    @Override
    public void resetColorWord() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorWord().getRed(), listS.get(0).getColorWord().getGreen(), listS.get(0).getColorWord().getBlue());
        personPanel.getTabelPerson().setForeground(color);
        personPanel.getTabelPerson().repaint();
    }

    @Override
    public void resetColorTitle() {
//        listS = sotfwareBN.selectAll();
//        Color color = new Color(listS.get(0).getColorTitle().getRed(), listS.get(0).getColorTitle().getGreen(), listS.get(0).getColorTitle().getBlue());
//        personPanel.getTabelPerson().getTableHeader().setForeground(color);
//        personPanel.getTabelPerson().repaint();
    }

    @Override
    public void resetColorMouse() {
        listS = sotfwareBN.selectAll();
        Color color = new Color(listS.get(0).getColorMouseClick().getRed(), listS.get(0).getColorMouseClick().getGreen(), listS.get(0).getColorMouseClick().getBlue());
        personPanel.getTabelPerson().setSelectionBackground(color);
        personPanel.getTabelPerson().repaint();
    }

    @Override
    public void getObject(String id) {
        IPersonBN personBN = Lookup.getDefault().lookup(IPersonBN.class);
        IEnterpriseBN enterpriseBN = Lookup.getDefault().lookup(IEnterpriseBN.class);
        IDepartmentBN departmentBN = Lookup.getDefault().lookup(IDepartmentBN.class);
        IMission missionBN = Lookup.getDefault().lookup(IMission.class);
        Person bean = personBN.getByObjectId(id);
        try {
            if (bean != null) {
                person = bean;
                Enterprise enterprise2 = enterpriseBN.getById(bean.getEnterpriseIdActual());
                Department department = departmentBN.getById(bean.getDepartmentIdActual());
                Mission mission = missionBN.getById(bean.getMissionIdActual());
                Country country = (new CountryBN()).getById(bean.getCountryIdActual());
                personPanel.getTabelPerson().setValueAt(bean, 0, 1);
                personPanel.getTabelPerson().setValueAt(bean.getPersonId(), 1, 1);
                personPanel.getTabelPerson().setValueAt(country, 2, 1);
                personPanel.getTabelPerson().setValueAt(enterprise2, 3, 1);
                personPanel.getTabelPerson().setValueAt(department, 4, 1);
                personPanel.getTabelPerson().setValueAt(mission, 5, 1);
                personPanel.setDataImages(bean.getImage());
                personPanel.showImage();
            }
        } catch (Exception ex) {
        }
    }

    @Override
    public void enableTable(boolean ok) throws IOException {
        personPanel.getTabelPerson().setEnabled(ok);
        //personPanel.getLabelImage().setEnabled(ok);
    }

    @Override
    public List<JTable> getTables() {
        List<JTable> lt = new ArrayList<JTable>();
        lt.add(personPanel.getTabelPerson());
        return lt;
    }

    @Override
    public IEntity save() {
        IPersonBN personBN = Lookup.getDefault().lookup(IPersonBN.class);
        Person bean = this.getTable();
        if (personPanel.getTabelPerson().getValueAt(0, 1).toString().trim().length() != 0
                && personPanel.getTabelPerson().getValueAt(1, 1).toString().trim().length() != 0) {
            personBN.update(bean);
        }
        return bean;
    }
}