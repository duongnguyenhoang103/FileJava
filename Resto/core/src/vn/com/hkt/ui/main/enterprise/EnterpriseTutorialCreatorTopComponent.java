/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.ui.main.enterprise;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import org.openide.util.LookupEvent;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.LookupListener;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.WindowManager;
import vn.com.hkt.pilot.enterprise.viewer.api.HelpTutorialEnterprise;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseCreator;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseExtCreator;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseHelp;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.ui.main.enterprise//EnterpriseTutorialCreator//EN",
autostore = false)
@TopComponent.Description(preferredID = "EnterpriseTutorialCreatorTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "properties", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.ui.main.enterprise.EnterpriseTutorialCreatorTopComponent")
@ActionReference(path = " " /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_EnterpriseTutorialCreatorAction",
preferredID = "EnterpriseTutorialCreatorTopComponent")
@ServiceProvider(service = HelpTutorialEnterprise.class)
public final class EnterpriseTutorialCreatorTopComponent extends TopComponent implements ActionListener, LookupListener, HelpTutorialEnterprise {

    private IEnterpriseCreator iec;
    // private IEnterpriseExtCreator ieec;
    private Collection<? extends IEnterpriseExtCreator> ieec;
    private JPanel panelSB01 = null;
    private JPanel panelSB12 = null;
    private JPanel panelSB11 = null;
    private JPanel panelSB13 = null;
    private JPanel panelSB112 = null;
    private JPanel panelSB122 = null;
    private JPanel panelEditID = null;
    private JPanel panelEditCounty = null;
    private JPanel panelEditCity = null;
    private JPanel panelEditMission = null;
    private JPanel panelEditBusiness = null;
    private JPanel panelState = null;
    private JPanel panelType = null;
    private JLabel label = new JLabel("           Hướng Dẫn Sử Dụng");
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextArea textArea = new JTextArea();
    private Collection<? extends IEnterpriseHelp> enterpriseHelp = Lookup.getDefault().lookupAll(IEnterpriseHelp.class);
    private JCheckBox checkBoxBasic = null, checBoxSB12 = null, checkBoxSB11 = null, checkBoxSB13 = null;

    public EnterpriseTutorialCreatorTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(EnterpriseTutorialCreatorTopComponent.class, "CTL_EnterpriseTutorialCreatorTopComponent"));
        setToolTipText(NbBundle.getMessage(EnterpriseTutorialCreatorTopComponent.class, "HINT_EnterpriseTutorialCreatorTopComponent"));
        putClientProperty(TopComponent.PROP_DRAGGING_DISABLED, Boolean.TRUE);
         iec = Lookup.getDefault().lookup(IEnterpriseCreator.class);
        ieec = Lookup.getDefault().lookupAll(IEnterpriseExtCreator.class);

        if (panelEditCounty == null && panelEditCity == null && panelEditID == null && panelEditMission == null && panelEditBusiness == null) {
            loadFormExtension();
        }



    }

    public void loadFormExtension() {
        panelChecbox.setLayout(new GridLayout(10, 1));
        if (iec != null) {
            if (panelSB01 == null) {
                panelSB01 = iec.getEnterpriseCreator();
                checkBoxBasic = new JCheckBox(panelSB01.toString());
                checkBoxBasic.setSelected(true);
                checkBoxBasic.setEnabled(false);
                panelChecbox.add(checkBoxBasic);
                checkBoxBasic.addActionListener(this);
            }
        } else {
            // panelChecbox.remove(checkBoxBasic);
        }
        if (ieec != null) {
            for (IEnterpriseExtCreator extCreator : ieec) {
                double index = extCreator.getLevelNumber();
                if (index == 1.21) {
                    if (panelSB12 == null) {
                        panelSB12 = extCreator.getEnterpriseExtCreator();
                        checBoxSB12 = new JCheckBox(panelSB12.toString());
                        panelChecbox.add(checBoxSB12);
                        checBoxSB12.setSelected(true);
                        checkBoxBasic.setSelected(true);
                        checBoxSB12.addActionListener(this);
                    }
                }
                if (index == 1.11) {
                    if (panelSB11 == null) {
                        panelSB11 = extCreator.getEnterpriseExtCreator();

                        // checkBoxBasic.setSelected(true);
                    }
                }
                if (index == 1.12) {
                    if (panelSB112 == null) {
                        panelSB112 = extCreator.getEnterpriseExtCreator();
                        checkBoxSB11 = new JCheckBox(panelSB112.toString());
                        panelChecbox.add(checkBoxSB11);
                        checkBoxSB11.setSelected(true);
                        checkBoxSB11.addActionListener(this);
                    }
                }
                if (index == 1.3) {
                    if (panelSB13 == null) {
                        panelSB13 = extCreator.getEnterpriseExtCreator();
                        checkBoxSB13 = new JCheckBox(panelSB13.toString());
                        panelChecbox.add(checkBoxSB13);
                        checkBoxSB13.setSelected(true);
                        checkBoxSB13.addActionListener(this);
                    }
                }
                if (index == 1.22) {
                    if (panelSB122 == null) {
                        panelSB122 = extCreator.getEnterpriseExtCreator();
                    }
                }
            }

        } else {
            // panelChecbox.remove(checBoxEx1);
        }
        panelEditID = null;
        panelEditCity = null;
        panelEditCounty = null;
        panelEditMission = null;
        panelEditBusiness = null;
        panelType = null;
        panelState = null;

    }

    public JPanel getPanelEnterprise1() {
        return panelSB01;
    }

    public JPanel getPanelEnterprise2() {
        return panelSB12;
    }

    public JPanel getPanelChecbox() {
        return panelChecbox;
    }

    public void loadFormID() {
        panelChecbox.setLayout(new GridLayout());
        if (enterpriseHelp != null) {
            for (IEnterpriseHelp help : enterpriseHelp) {
                // help = Lookup.getDefault().lookup(IEnterpriseHelp.class);
                double index = help.getLevelNumber();
                if (index == 1.1) {
                    if (panelEditID == null) {
                        panelEditID = help.getPanel1();

                        panelChecbox.add(panelEditID);
                        panelSB01 = null;
                        panelSB12 = null;
                        panelEditCounty = null;
                        panelEditCity = null;
                        panelSB11 = null;
                        panelSB112 = null;
                        panelSB122 = null;
                        panelSB13 = null;
                        panelEditMission = null;
                        panelEditBusiness = null;
                        panelState = null;
                        panelType = null;
                    }
                }
            }
        }
        panelEditID = null;
    }

    public void loadFormMission() {
        panelChecbox.setLayout(new GridLayout());
        if (enterpriseHelp != null) {
            for (IEnterpriseHelp help : enterpriseHelp) {
                double index = help.getLevelNumber();
                if (index == 1.3) {
                    if (panelEditMission == null) {
                        panelEditMission = help.getPanel1();

                        panelChecbox.add(panelEditMission);
                        panelSB01 = null;
                        panelSB12 = null;
                        panelEditCounty = null;
                        panelEditCity = null;
                        panelSB11 = null;
                        panelSB112 = null;
                        panelSB122 = null;
                        panelSB13 = null;
                        panelEditBusiness = null;
                        panelEditID = null;
                        panelState = null;
                        panelType = null;

                    }
                }
            }
        }
        panelEditMission = null;
    }

    public void loadFormCity() {
        panelChecbox.setLayout(new GridLayout());
        if (enterpriseHelp != null) {
            for (IEnterpriseHelp help : enterpriseHelp) {

                double index = help.getLevelNumber();
                if (index == 1.2) {
                    if (panelEditCity == null) {
                        panelEditCity = help.getPanel1();
                        panelChecbox.add(panelEditCity);
                        panelSB01 = null;
                        panelSB12 = null;
                        panelEditCounty = null;
                        panelEditID = null;
                        panelSB11 = null;
                        panelSB112 = null;
                        panelSB122 = null;
                        panelSB13 = null;
                        panelEditMission = null;
                        panelEditBusiness = null;
                        panelState = null;
                        panelType = null;

                    }
                }
            }
        }
        panelEditCity = null;
    }

    public void loadFormState() {
        panelChecbox.setLayout(new GridLayout());
        if (enterpriseHelp != null) {
            for (IEnterpriseHelp help : enterpriseHelp) {

                double index = help.getLevelNumber();
                if (index == 1.6) {
                    if (panelState == null) {
                        panelState = help.getPanel1();
                        panelChecbox.add(panelState);
                        panelSB01 = null;
                        panelSB12 = null;
                        panelEditCounty = null;
                        panelEditID = null;
                        panelSB11 = null;
                        panelSB112 = null;
                        panelSB122 = null;
                        panelSB13 = null;
                        panelEditMission = null;
                        panelEditBusiness = null;
                        panelState = null;
                        panelType = null;

                    }
                }
            }
        }
        panelEditCity = null;
    }

    public void loadFormType() {
        panelChecbox.setLayout(new GridLayout());
        if (enterpriseHelp != null) {
            for (IEnterpriseHelp help : enterpriseHelp) {

                double index = help.getLevelNumber();
                if (index == 1.5) {
                    if (panelType == null) {
                        panelType = help.getPanel1();
                        panelChecbox.add(panelType);
                        panelSB01 = null;
                        panelSB12 = null;
                        panelEditCounty = null;
                        panelEditID = null;
                        panelSB11 = null;
                        panelSB112 = null;
                        panelSB122 = null;
                        panelSB13 = null;
                        panelEditMission = null;
                        panelEditBusiness = null;
                        panelState = null;
                        panelType = null;

                    }
                }
            }
        }
        panelEditCity = null;
    }

    public void loadFormCountry() {
        panelChecbox.setLayout(new GridLayout());
        panelChecbox.add(tabbedPane);

        if (enterpriseHelp != null) {
            for (IEnterpriseHelp help : enterpriseHelp) {
                double index = help.getLevelNumber();
                if (index == 1.2) {
                    if (panelEditCounty == null) {
                        panelEditCounty = help.getPanel1();
                        tabbedPane.add(panelEditCounty);
                        tabbedPane.setTitleAt(0, "Chỉnh sửa Địa Chỉ");
                        panelSB01 = null;
                        panelSB12 = null;
                        panelEditCounty = null;
                        panelEditID = null;
                        panelSB11 = null;
                        panelSB112 = null;
                        panelSB122 = null;
                        panelSB13 = null;
                        panelEditMission = null;
                        panelEditBusiness = null;
                        panelState = null;
                        panelType = null;
                    }
                }

            }
        }
        tabbedPane.add(textArea);
        tabbedPane.setTitleAt(1, "Hướng dẫn sử dụng");
        panelEditCounty = null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelForm = new javax.swing.JPanel();
        panelExtension = new javax.swing.JPanel();
        panelChecbox = new javax.swing.JPanel();
        panelButton = new javax.swing.JPanel();
        buttonAn = new javax.swing.JButton();

        panelForm.setPreferredSize(new java.awt.Dimension(888, 238));

        javax.swing.GroupLayout panelChecboxLayout = new javax.swing.GroupLayout(panelChecbox);
        panelChecbox.setLayout(panelChecboxLayout);
        panelChecboxLayout.setHorizontalGroup(
            panelChecboxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
        );
        panelChecboxLayout.setVerticalGroup(
            panelChecboxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelExtensionLayout = new javax.swing.GroupLayout(panelExtension);
        panelExtension.setLayout(panelExtensionLayout);
        panelExtensionLayout.setHorizontalGroup(
            panelExtensionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelExtensionLayout.createSequentialGroup()
                .addComponent(panelChecbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(554, Short.MAX_VALUE))
        );
        panelExtensionLayout.setVerticalGroup(
            panelExtensionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelChecbox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelExtension, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelExtension, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        org.openide.awt.Mnemonics.setLocalizedText(buttonAn, org.openide.util.NbBundle.getMessage(EnterpriseTutorialCreatorTopComponent.class, "EnterpriseTutorialCreatorTopComponent.buttonAn.text")); // NOI18N
        buttonAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonLayout);
        panelButtonLayout.setHorizontalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelButtonLayout.createSequentialGroup()
                .addContainerGap(681, Short.MAX_VALUE)
                .addComponent(buttonAn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        panelButtonLayout.setVerticalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelButtonLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(buttonAn)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAnActionPerformed
        EnterpriseCreatorTopComponent tc = (EnterpriseCreatorTopComponent) WindowManager.getDefault().findTopComponent("EnterpriseCreatorTopComponent");
        tc.requestActive();
    }//GEN-LAST:event_buttonAnActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAn;
    private javax.swing.JPanel panelButton;
    private javax.swing.JPanel panelChecbox;
    private javax.swing.JPanel panelExtension;
    private javax.swing.JPanel panelForm;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
       
        if (panelEditCounty == null && panelEditCity == null && panelEditID == null && panelEditMission == null && panelEditBusiness == null) {
            loadFormExtension();
        }
        if (checBoxSB12 != null) {
            checBoxSB12.addActionListener(this);
        }
        if (checkBoxSB11 != null) {
            checkBoxSB11.addActionListener(this);
        }
        if (checkBoxSB13 != null) {
            checkBoxSB13.addActionListener(this);
        }

        checkBoxBasic.addActionListener(this);
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
        JCheckBox checkBox = (JCheckBox) e.getSource();
        EnterpriseCreatorTopComponent tc = (EnterpriseCreatorTopComponent) WindowManager.getDefault().findTopComponent("EnterpriseCreatorTopComponent");
        if (checkBox == checBoxSB12) {
            tc.getPanelForm().removeAll();
            tc.loadForm();
        }
        if (checkBox == checkBoxSB11) {
            tc.getPanelForm().removeAll();
            tc.loadForm();
        }
        if (checkBox == checkBoxSB13) {
            tc.getPanelForm().removeAll();
            tc.loadForm();
        }
    }

    public JCheckBox getCheckSB12() {
        return checBoxSB12;
    }

    public JCheckBox getCheckBoxBasic() {
        return checkBoxBasic;
    }

    public JCheckBox getCheckBoxSB11() {
        return checkBoxSB11;
    }

    public JCheckBox getCheckBoxSB13() {
        return checkBoxSB13;
    }

    @Override
    public void resultChanged(LookupEvent le) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void getTutorial(double i, String txt) {
        EnterpriseTutorialCreatorTopComponent tc =
                (EnterpriseTutorialCreatorTopComponent) WindowManager.getDefault().findTopComponent("EnterpriseTutorialCreatorTopComponent");
        if (i == 0.1) {
            if (panelEditID == null) {
                tc.getPanelChecbox().removeAll();
                tc.loadFormID();
            }

        }
        if (i == 0.3 || i == 0 || i == 0.2 || i == 0.4 || i == 1.01 || i == 1.02 || i == 4.103
                || i == 4.111 || i == 4.113 || i == 4.21 || i == 4.23 || i == 4.01 || i == 4.02
                || i == 2.01 || i == 2.00 || i == 2.11 || i == 2.13 || i == 3.01 || i == 3.13
                || i == 3.21 || i == 3.23 || i == 3.3) {
            tc.getPanelChecbox().removeAll();
            tc.loadFormTutorial(txt);


        }
        if (i == 1.2) {
            if (panelEditCounty == null) {
                tc.getPanelChecbox().removeAll();
                tc.loadFormCountry();
            }

        }
        if (i == 1.3) {
            if (panelEditCity == null) {
                tc.getPanelChecbox().removeAll();
                tc.loadFormCity();
            }
        }
        if (i == 3) {
            if (panelEditMission == null) {
                tc.getPanelChecbox().removeAll();
                tc.loadFormMission();
            }
        }
        if (i == 4) {
            if (panelEditBusiness == null) {
                tc.getPanelChecbox().removeAll();
                tc.loadFormBusiness();
            }
        }
        if (i == 4.101) {
            if (panelState == null) {
                tc.getPanelChecbox().removeAll();
                tc.loadFormState();
            }
        }

        if (i == 4.13) {
            if (panelType == null) {
                tc.getPanelChecbox().removeAll();
                tc.loadFormType();
            }
        }
    }

    private void loadFormBusiness() {
        panelChecbox.setLayout(new GridLayout());
        if (enterpriseHelp != null) {
            for (IEnterpriseHelp help : enterpriseHelp) {
                double index = help.getLevelNumber();
                if (index == 1.4) {
                    if (panelEditBusiness == null) {
                        panelEditBusiness = help.getPanel1();

                        panelChecbox.add(panelEditBusiness);
                        panelSB01 = null;
                        panelSB12 = null;
                        panelEditCounty = null;
                        panelEditCity = null;
                        panelSB11 = null;
                        panelSB112 = null;
                        panelSB122 = null;
                        panelSB13 = null;
                        panelEditMission = null;
                        panelEditID = null;

                    }
                }
            }
        }
        panelEditBusiness = null;
    }

    private void loadFormTutorial(String str) {
        panelChecbox.setLayout(new BorderLayout());
        panelChecbox.add(label, BorderLayout.NORTH);
        label.setPreferredSize(new Dimension(WIDTH, 40));
        textArea.setText(str);
        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(500,WIDTH));
        panelChecbox.add(textArea, BorderLayout.CENTER);

        panelSB01 = null;
        panelSB12 = null;
        panelEditCounty = null;
        panelEditCity = null;
        panelSB11 = null;
        panelSB112 = null;
        panelSB122 = null;
        panelSB13 = null;
        panelEditMission = null;
        panelEditID = null;
        panelEditBusiness = null;
    }
}
