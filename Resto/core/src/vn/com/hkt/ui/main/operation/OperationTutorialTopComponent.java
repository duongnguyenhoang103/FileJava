/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.ui.main.operation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.*;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.WindowManager;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseHelp;
import vn.com.hkt.pilot.operation.viewer.api.HelpTutorialOperation;
import vn.com.hkt.pilot.operation.viewer.api.IOperationCreater;
import vn.com.hkt.pilot.operation.viewer.api.IOperationExtCreater;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.ui.main.operation//OperationTutorial//EN",
autostore = false)
@TopComponent.Description(preferredID = "OperationTutorialTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "properties", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.ui.main.operation.OperationTutorialTopComponent")
@ActionReference(path = " " /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_OperationTutorialAction",
preferredID = "OperationTutorialTopComponent")
@ServiceProvider(service = HelpTutorialOperation.class)
public final class OperationTutorialTopComponent extends TopComponent implements ActionListener, HelpTutorialOperation {

    private JPanel panel505 = null;
    private JPanel panel51 = null;
    private JPanel panelClassification = null;
    private JPanel panelPayment = null;
    private JPanel panelBillType = null;
    private JPanel panelOperationStatus = null;
    private JPanel panelAccNum = null;
    private JPanel panelKindOfTax = null;
    private Collection<? extends IOperationCreater> operationCreaters;
    private IOperationExtCreater operationExtCreater;
    private JCheckBox checkBoxBasic = null, checkBoxSB = null;
    private JLabel label = new JLabel("           Hướng Dẫn Sử Dụng");
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextArea textArea = new JTextArea();
    private Collection<? extends IEnterpriseHelp> enterpriseHelp = Lookup.getDefault().lookupAll(IEnterpriseHelp.class);

    public JCheckBox getCheckBoxSB() {
        return checkBoxSB;
    }

    public OperationTutorialTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(OperationTutorialTopComponent.class, "CTL_OperationTutorialTopComponent"));
        setToolTipText(NbBundle.getMessage(OperationTutorialTopComponent.class, "HINT_OperationTutorialTopComponent"));
        if (panelClassification == null && panelPayment == null) {
            loadFormExtension();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelExtension = new javax.swing.JPanel();
        panelChecbox = new javax.swing.JPanel();
        panelButton = new javax.swing.JPanel();
        buttonAn = new javax.swing.JButton();

        javax.swing.GroupLayout panelChecboxLayout = new javax.swing.GroupLayout(panelChecbox);
        panelChecbox.setLayout(panelChecboxLayout);
        panelChecboxLayout.setHorizontalGroup(
            panelChecboxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
        );
        panelChecboxLayout.setVerticalGroup(
            panelChecboxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 233, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelExtensionLayout = new javax.swing.GroupLayout(panelExtension);
        panelExtension.setLayout(panelExtensionLayout);
        panelExtensionLayout.setHorizontalGroup(
            panelExtensionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelExtensionLayout.createSequentialGroup()
                .addComponent(panelChecbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        panelExtensionLayout.setVerticalGroup(
            panelExtensionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelChecbox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        org.openide.awt.Mnemonics.setLocalizedText(buttonAn, org.openide.util.NbBundle.getMessage(OperationTutorialTopComponent.class, "OperationTutorialTopComponent.buttonAn.text")); // NOI18N
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
                .addContainerGap(277, Short.MAX_VALUE)
                .addComponent(buttonAn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
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
            .addGap(0, 400, Short.MAX_VALUE)
            .addComponent(panelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelExtension, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelExtension, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAnActionPerformed
        OperationCreatorTopComponent tc = (OperationCreatorTopComponent) WindowManager.getDefault().findTopComponent("OperationCreatorTopComponent");
        tc.requestActive();
    }//GEN-LAST:event_buttonAnActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAn;
    private javax.swing.JPanel panelButton;
    private javax.swing.JPanel panelChecbox;
    private javax.swing.JPanel panelExtension;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        operationExtCreater = Lookup.getDefault().lookup(IOperationExtCreater.class);
        operationCreaters = Lookup.getDefault().lookupAll(IOperationCreater.class);
        loadFormExtension();
        checkBoxSB.addActionListener(this);
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

    public void loadFormExtension() {
        panelChecbox.setLayout(new GridLayout(10, 1));
        if (operationCreaters != null) {
            for (IOperationCreater extCreator : operationCreaters) {
                double index = extCreator.getLevelNumber();
                if (index == 5.05) {
                    if (panel505 == null) {
                        panel505 = extCreator.getOperationCreater();
                        checkBoxBasic = new JCheckBox(panel505.toString());
                        checkBoxBasic.setSelected(true);
                        checkBoxBasic.setEnabled(false);
                        panelChecbox.add(checkBoxBasic);
                    }
                }

            }

        }
        if (operationExtCreater != null) {
            if (panel51 == null) {
                panel51 = operationExtCreater.getOperationExtCreater();
                checkBoxSB = new JCheckBox(panel51.toString());
                checkBoxSB.setSelected(true);
                panelChecbox.add(checkBoxSB);
            }
        }
        panelClassification = null;
        panelPayment = null;
        panelBillType = null;
        panelOperationStatus = null;
        panelAccNum = null;
        panelKindOfTax = null;

    }

    public JPanel getPanel505() {
        return panel505;
    }

    private void loadFormClassification() {
        panelChecbox.setLayout(new GridLayout());
        if (enterpriseHelp != null) {
            for (IEnterpriseHelp help : enterpriseHelp) {
                double index = help.getLevelNumber();
                if (index == 5.58) {
                    if (panelClassification == null) {
                        panelClassification = help.getPanel1();
                        panelChecbox.add(panelClassification);

                        panel505 = null;
                        panel51 = null;

                    }
                }
            }
            panelClassification = null;
            panelPayment = null;
            panelBillType = null;
            panelOperationStatus = null;
            panelAccNum = null;
            panelKindOfTax = null;

        }
    }

    private void loadFormBillType() {
        panelChecbox.setLayout(new GridLayout());
        if (enterpriseHelp != null) {
            for (IEnterpriseHelp help : enterpriseHelp) {
                double index = help.getLevelNumber();
                if (index == 5.12) {
                    if (panelBillType == null) {
                        panelBillType = help.getPanel1();
                        panelChecbox.add(panelBillType);

                        panel505 = null;
                        panel51 = null;

                    }
                }
            }
            panelClassification = null;
            panelPayment = null;
            panelBillType = null;
            panelOperationStatus = null;
            panelAccNum = null;
            panelKindOfTax = null;

        }
    }

    private void loadFormPayment() {
        panelChecbox.setLayout(new GridLayout());
        if (enterpriseHelp != null) {
            for (IEnterpriseHelp help : enterpriseHelp) {
                double index = help.getLevelNumber();
                if (index == 5.11) {
                    if (panelPayment == null) {
                        panelPayment = help.getPanel1();
                        panelChecbox.add(panelPayment);

                        panel505 = null;
                        panel51 = null;

                    }
                }
            }
            panelClassification = null;
            panelPayment = null;
            panelOperationStatus = null;
            panelBillType = null;
            panelAccNum = null;
            panelKindOfTax = null;

        }
    }

    private void loadFormOStatus() {
        panelChecbox.setLayout(new GridLayout());
        if (enterpriseHelp != null) {
            for (IEnterpriseHelp help : enterpriseHelp) {
                double index = help.getLevelNumber();
                if (index == 5.13) {
                    if (panelOperationStatus == null) {
                        panelOperationStatus = help.getPanel1();
                        panelChecbox.add(panelOperationStatus);

                        panel505 = null;
                        panel51 = null;

                    }
                }
            }
            panelClassification = null;
            panelPayment = null;
            panelOperationStatus = null;
            panelBillType = null;
            panelAccNum = null;
            panelKindOfTax = null;

        }
    }

    private void loadFormAccNum() {
        panelChecbox.setLayout(new GridLayout());
        if (enterpriseHelp != null) {
            for (IEnterpriseHelp help : enterpriseHelp) {
                double index = help.getLevelNumber();
                if (index == 5.14) {
                    if (panelAccNum == null) {
                        panelAccNum = help.getPanel1();
                        panelChecbox.add(panelAccNum);

                        panel505 = null;
                        panel51 = null;

                    }
                }
            }
            panelClassification = null;
            panelPayment = null;
            panelOperationStatus = null;
            panelBillType = null;
            panelAccNum = null;
            panelKindOfTax = null;

        }
    }

    private void loadFormKindOfTax() {
        panelChecbox.setLayout(new GridLayout());
        if (enterpriseHelp != null) {
            for (IEnterpriseHelp help : enterpriseHelp) {
                double index = help.getLevelNumber();
                if (index == 5.15) {
                    if (panelKindOfTax == null) {
                        panelKindOfTax = help.getPanel1();
                        panelChecbox.add(panelKindOfTax);

                        panel505 = null;
                        panel51 = null;

                    }
                }
            }
            panelClassification = null;
            panelPayment = null;
            panelOperationStatus = null;
            panelBillType = null;
            panelAccNum = null;
            panelKindOfTax = null;

        }
    }

    private void loadFormTutorial(String str) {
        panelChecbox.setLayout(new BorderLayout());
        panelChecbox.add(label, BorderLayout.NORTH);
        label.setPreferredSize(new Dimension(WIDTH, 40));
        textArea.setText(str);
        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(500,WIDTH));
        panelChecbox.add(textArea, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JCheckBox checkBox = (JCheckBox) e.getSource();
        OperationCreatorTopComponent tc = (OperationCreatorTopComponent) WindowManager.getDefault().findTopComponent("OperationCreatorTopComponent");
        if (checkBox == checkBoxSB) {
            tc.getPanelSB().removeAll();
            tc.loadFormSB();
        }
    }

    @Override
    public void getTutorial(double i, String str) {
        OperationTutorialTopComponent tc = (OperationTutorialTopComponent) WindowManager.getDefault().findTopComponent("OperationTutorialTopComponent");
        if (i == 0) {
            if (panelClassification == null) {
                tc.getPanelChecbox().removeAll();
                tc.loadFormClassification();
            }
        }
        if (i == 0.2 || i == 0.1 || i == 0.3 || i == 0.4 || i == 0.5
            ||i==3.23||i==3.31||i==3.33
            ||i==1.01||i==1.02||i==1.03||i==1.04||i==1.05||i==1.06||i==1.07
                ||i==4.01||i==4.11||i==4.13||i==4.21||i==4.23||i==4.31||i==4.33||i==4.41||i==4.43// RC Tong Tien
                ||i==6.11||i==6.21 // RC w 58
                ){
            tc.getPanelChecbox().removeAll();
            tc.loadFormTutorial(str);

        }
        if (i == 3.13) {
            if (panelPayment == null) {
                tc.getPanelChecbox().removeAll();
                tc.loadFormPayment();
            }
        }
        if (i == 3.11) {
            if (panelBillType == null) {
                tc.getPanelChecbox().removeAll();
                tc.loadFormBillType();
            }
        }
        if (i == 3.01) {
            if (panelOperationStatus == null) {
                tc.getPanelChecbox().removeAll();
                tc.loadFormOStatus();
            }
        }
        if (i == 3.21) {
            if (panelAccNum == null) {
                tc.getPanelChecbox().removeAll();
                tc.loadFormAccNum();
            }
        }
        if (i == 5) {// cai nay la cai gi?
            if (panelKindOfTax == null) {
                tc.getPanelChecbox().removeAll();
                tc.loadFormKindOfTax();
            }
        }
       

    }

    public JPanel getPanelChecbox() {
        return panelChecbox;
    }
}