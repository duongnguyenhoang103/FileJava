/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.erm.enterprise.ui;

import java.awt.Color;
import java.io.IOException;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.basic.api.IEnterpriseBN;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.cookies.SaveCookie;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.bom.operation.ui.OperationTopComponent;
import vn.com.hkt.bom.product.ui.ProductTopComponent;
import vn.com.hkt.erm.department.ui.DepartmentTopComponent;
import vn.com.hkt.hrm.person.ui.PersonTopComponent;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseCreator;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseExtViewer;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseViewer;
import vn.com.hkt.pilot.enterprise.viewer.api.Observable;
import vn.com.hkt.pilot.enterprise.viewer.api.Observer;
import vn.com.hkt.pilot.enterprise.viewer.api.ResetCookie;
import vn.com.hkt.pilot.ui.colortable.StripedTableCellRenderer;

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
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_EnterpriseAction",
preferredID = "EnterpriseTopComponent")
@ServiceProvider(service = IEnterpriseCreator.class)
public final class EnterpriseTopComponent extends TopComponent implements ActionListener,
        IEnterpriseCreator, SaveCookie, ResetCookie, LookupListener, Observer, Observable {

    private static double LEVEL = 0.0;
    private AbstractLookup lookup;
    private InstanceContent content = new InstanceContent();
    private EnterprisePanel enterprisePanel;
    private IEnterpriseBN enterpriseBN;
    private Enterprise enterprise;
    private IEnterpriseViewer enterpriseViewer;
    private IEnterpriseExtViewer enterpriseExtViewer;
    private Lookup.Result<Enterprise> resultbasic = null;
    private Lookup.Result<Enterprise> resultext = null;
    private IPersonBN personBN;
    private Vector obs;
    private boolean change = false;

    public Vector getObs() {
        return obs;
    }

    public EnterpriseTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(EnterpriseTopComponent.class, "CTL_EnterpriseTopComponent"));
        setToolTipText(NbBundle.getMessage(EnterpriseTopComponent.class, "HINT_EnterpriseTopComponent"));
        enterprisePanel = new EnterprisePanel();
        panelForm.setLayout(new GridLayout());

        panelForm.add(enterprisePanel);

        buttonEdit.setEnabled(false);

        buttonSaveDS.addActionListener(this);
        buttonEdit.addActionListener(this);
        buttonExit.addActionListener(this);
        buttonHelp.addActionListener(this);
        buttonReset.addActionListener(this);
        buttonSave.addActionListener(this);
        this.personBN = Lookup.getDefault().lookup(IPersonBN.class);

        this.enterpriseBN = Lookup.getDefault().lookup(IEnterpriseBN.class);
        enterpriseViewer = Lookup.getDefault().lookup(IEnterpriseViewer.class);
        enterpriseExtViewer = Lookup.getDefault().lookup(IEnterpriseExtViewer.class);
        if (enterpriseExtViewer != null) {
            resultext = enterpriseExtViewer.getEnterpriseLookup().lookupResult(Enterprise.class);
            resultext.addLookupListener(this);
        }

        resultbasic = enterpriseViewer.getEnterpriseLookup().lookupResult(Enterprise.class);

        resultbasic.addLookupListener(this);
        lookup = new AbstractLookup(content);
        obs = new Vector();
    }

    public JPanel getPanelForm() {
        return panelForm;
    }

    public JButton getButtonEdit() {
        return buttonEdit;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelButton = new javax.swing.JPanel();
        buttonReset = new javax.swing.JButton();
        buttonEdit = new javax.swing.JButton();
        buttonSave = new javax.swing.JButton();
        buttonExit = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton();
        buttonSaveDS = new javax.swing.JButton();
        panelForm = new javax.swing.JPanel();

        panelButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        org.openide.awt.Mnemonics.setLocalizedText(buttonReset, org.openide.util.NbBundle.getMessage(EnterpriseTopComponent.class, "EnterpriseTopComponent.buttonReset.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(buttonEdit, org.openide.util.NbBundle.getMessage(EnterpriseTopComponent.class, "EnterpriseTopComponent.buttonEdit.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(buttonSave, org.openide.util.NbBundle.getMessage(EnterpriseTopComponent.class, "EnterpriseTopComponent.buttonSave.text_1")); // NOI18N
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(buttonExit, org.openide.util.NbBundle.getMessage(EnterpriseTopComponent.class, "EnterpriseTopComponent.buttonExit.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(buttonHelp, org.openide.util.NbBundle.getMessage(EnterpriseTopComponent.class, "EnterpriseTopComponent.buttonHelp.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(buttonSaveDS, org.openide.util.NbBundle.getMessage(EnterpriseTopComponent.class, "EnterpriseTopComponent.buttonSaveDS.text_1")); // NOI18N

        javax.swing.GroupLayout panelButtonLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonLayout);
        panelButtonLayout.setHorizontalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(buttonSaveDS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
        );
        panelButtonLayout.setVerticalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonReset)
                    .addComponent(buttonEdit)
                    .addComponent(buttonSave)
                    .addComponent(buttonSaveDS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonExit)
                    .addComponent(buttonHelp))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 773, Short.MAX_VALUE)
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
    }//GEN-LAST:event_buttonSaveActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonReset;
    private javax.swing.JButton buttonSave;
    private javax.swing.JButton buttonSaveDS;
    private javax.swing.JPanel panelButton;
    private javax.swing.JPanel panelForm;
    // End of variables declaration//GEN-END:variables

    public EnterprisePanel getEnterprisePanel() {
        return enterprisePanel;
    }

    @Override
    public void componentOpened() {
        if (enterprise == null) {
            reset();
        }
        if (obs.isEmpty()) {
            addObserver();
        }
    }

    @Override
    public void componentClosed() {
        resultbasic.removeLookupListener(this);
        if (resultext != null) {
            resultext.removeLookupListener(this);
        }
        reset();
        obs.removeAllElements();
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
        JButton button = (JButton) e.getSource();
        ListEnterpriseTopComponent listEnterpriseTopComponent = new ListEnterpriseTopComponent();

        if (button == buttonSave) {
            update();
            this.close();
            this.open();
            reset();
        }

        if (button == buttonSaveDS) {
            update();
            reset();
            listEnterpriseTopComponent.open();
            this.close();
        }
        if (button == buttonReset) {
            reset();
            buttonEdit.setEnabled(false);
            enterprisePanel.getTabelEnterprise().setEnabled(true);
        }
        if (button == buttonExit) {
            this.close();
        }
        if (button == buttonEdit) {
            buttonEdit.setEnabled(false);
            enterprisePanel.getTabelEnterprise().setEnabled(true);
        }
    }

    // reset lại table mỗi khi save hoặc muốn điền mới
    public void reset() {
        enterprisePanel.getTabelEnterprise().setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"Tên công ty", " "},
                    {"Mã công ty", " "},
                    {"Công ty mẹ", " "},
                    {"Giám đốc", " "},
                    {"Slogan", " "}
                },
                new String[]{
                    "", ""
                }) {

            boolean[] canEdit = new boolean[]{
                false, true
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        enterprisePanel.getTabelEnterprise().getColumnModel().getColumn(1).setCellEditor(new EnterpriseCell());
        enterprisePanel.getTabelEnterprise().getColumnModel().getColumn(0).setPreferredWidth(100);
        enterprisePanel.getTabelEnterprise().getColumnModel().getColumn(0).setMaxWidth(100);
        enterprisePanel.getTabelEnterprise().setRowSelectionAllowed(true);
        enterprisePanel.getTabelEnterprise().setColumnSelectionAllowed(false);
        enterprisePanel.getTabelEnterprise().setSelectionBackground(new Color(201, 224, 252));
        enterprisePanel.getLbImage().removeAll();
        StripedTableCellRenderer.installInColumn(enterprisePanel.getTabelEnterprise(), new Color(241, 246, 253), null, null, null);
    }

    public void update() {
        String id = enterprisePanel.getTabelEnterprise().getValueAt(1, 1).toString().trim();
        String name = enterprisePanel.getTabelEnterprise().getValueAt(0, 1).toString().trim();
        String parent;
        String director;
        byte[] logo = enterprisePanel.getDataImages();
        String slogan = enterprisePanel.getTabelEnterprise().getValueAt(4, 1).toString().trim();


        if (enterprisePanel.getTabelEnterprise().getValueAt(2, 1) != null) {
            if (enterprisePanel.getTabelEnterprise().getValueAt(2, 1).toString().trim().length() != 0) {
                Enterprise enterprise1 = (Enterprise) enterprisePanel.getTabelEnterprise().getValueAt(2, 1);
                parent = enterprise1.getEnterpriseID().trim();
            } else {
                parent = enterprisePanel.getTabelEnterprise().getValueAt(2, 1).toString().trim();
            }
        } else {
            parent = " ";
        }

        if (enterprisePanel.getTabelEnterprise().getValueAt(3, 1) != null) {
            if (enterprisePanel.getTabelEnterprise().getValueAt(3, 1).toString().trim().length() != 0) {
                Person person1 = (Person) enterprisePanel.getTabelEnterprise().getValueAt(3, 1);
                director = person1.getPersonID().trim();
            } else {
                director = enterprisePanel.getTabelEnterprise().getValueAt(3, 1).toString().trim();
            }

        } else {
            director = " ";
        }
        if (enterprisePanel.getTabelEnterprise().getValueAt(0, 1).toString().trim().length() != 0
                && enterprisePanel.getTabelEnterprise().getValueAt(1, 1).toString().trim().length() != 0) {
            enterprise = new Enterprise(id, name, parent, director, logo, slogan);
            if (enterpriseBN.updateEnterprise(enterprise)) {
                content.set(Collections.singleton(enterprise), null);
                this.setChanged();
                this.notifyObservers(null);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa nhập mã hoặc tên");
        }
    }

    @Override
    public JPanel getEnterpriseCreator() {
        return this.enterprisePanel;
    }

    @Override
    public Lookup getEnterpriseLookup() {
        return this.lookup;
    }

    // Lưu Enterprise sau khi nhập
    @Override
    public void save() throws IOException {
        update();
        this.setChanged();
        this.notifyObservers(null);
    }

    @Override
    public void resetCookie() throws IOException {
        reset();
    }

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result<Enterprise> r;
        r = (Lookup.Result) le.getSource();
        Collection<? extends Enterprise> col = r.allInstances();
        if (!col.isEmpty()) {
            for (Enterprise bean : col) {
                enterprise = bean;
                Enterprise enterprise2 = enterpriseBN.getEnterpriseByID(bean.getEnterpriseParent());
                Person person = personBN.getPersonByID(bean.getDirector());
                enterprisePanel.getTabelEnterprise().setModel(new javax.swing.table.DefaultTableModel(
                        new Object[][]{
                            {"Tên công ty", bean.getEnterpriseName()},
                            {"Mã công ty", bean.getEnterpriseID()},
                            {"Công ty mẹ", enterprise2},
                            {"Giám đốc", person},
                            {"Slogan", bean.getSlogan()}
                        },
                        new String[]{
                            "", ""
                        }) {

                    boolean[] canEdit = new boolean[]{
                        false, true
                    };

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                });
                byte[] logo = bean.getPicture();
                EnterpriseCell enterpriseCell = new EnterpriseCell();
                enterpriseCell.getTxtNameE().setText(bean.getEnterpriseName());
                enterpriseCell.getTxtIdE().setText(bean.getEnterpriseID());
                enterprisePanel.getTabelEnterprise().getColumnModel().getColumn(1).setCellEditor(enterpriseCell);
                enterprisePanel.getTabelEnterprise().setSelectionBackground(new Color(201, 224, 252));
                enterprisePanel.getTabelEnterprise().getColumnModel().getColumn(0).setPreferredWidth(100);
                enterprisePanel.getTabelEnterprise().getColumnModel().getColumn(0).setMaxWidth(100);
                enterprisePanel.getTabelEnterprise().setRowSelectionAllowed(true);
                enterprisePanel.getTabelEnterprise().setColumnSelectionAllowed(false);
                enterprisePanel.setDataImages(logo);
                enterprisePanel.showImage();
                StripedTableCellRenderer.installInColumn(enterprisePanel.getTabelEnterprise(), new Color(241, 246, 253), null, null, null);

            }
        }
    }

    @Override
    public void updateObserver(Observable o, Object arg) {
        reset();
    }

    @Override
    public void notifyObservers(Object arg) {
        Object[] arrLocal;
        synchronized (this) {
            if (!change) {
                return;
            }
            arrLocal = obs.toArray();
            clearChanged();
        }
        for (int i = arrLocal.length - 1; i >= 0; i--) {
            ((Observer) arrLocal[i]).updateObserver(this, arg);
        }
    }

    @Override
    public void setChanged() {
        change = true;
    }

    @Override
    public void clearChanged() {
        change = false;
    }

    public void addObserver() {
        ProductTopComponent tc = (ProductTopComponent) WindowManager.getDefault().findTopComponent("ProductTopComponent");
        if (tc != null) {
            obs.addElement(tc);
        }
        OperationTopComponent tc1 = (OperationTopComponent) WindowManager.getDefault().findTopComponent("OperationTopComponent");
        if (tc1 != null) {
            obs.addElement(tc1);
        }
        DepartmentTopComponent tc2 = (DepartmentTopComponent) WindowManager.getDefault().findTopComponent("DepartmentTopComponent");
        if (tc2 != null) {
            obs.addElement(tc2);
        }
        PersonTopComponent tc3 = (PersonTopComponent) WindowManager.getDefault().findTopComponent("PersonTopComponent");
        if (tc3 != null) {
            obs.addElement(tc3);
        }
    }

    @Override
    public double getLevelNumber() {

        return this.LEVEL;

    }
}
