/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.promotion.ui.window;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.promotion.Installer;
import vn.com.hkt.pilot.promotion.viewer.api.IPromotionCreator;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.pilot.promotion.ui.window//PromotionCreator//EN",
autostore = false)
@TopComponent.Description(preferredID = "PromotionCreatorTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.pilot.promotion.ui.window.PromotionCreatorTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_PromotionCreatorAction",
preferredID = "PromotionCreatorTopComponent")
public final class PromotionCreatorTopComponent extends TopComponent implements ItemListener,IUserInterface
{

    private Collection<? extends IPromotionCreator> promotionCreators;
    private DefaultComboBoxModel comboBoxModel;

    public PromotionCreatorTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(PromotionCreatorTopComponent.class, "CTL_PromotionCreatorTopComponent"));
        setToolTipText(NbBundle.getMessage(PromotionCreatorTopComponent.class, "HINT_PromotionCreatorTopComponent"));

        comboBoxModel = new DefaultComboBoxModel();
        loadData();
        cboOption.setModel(comboBoxModel);
        cboOption.addItemListener(this);

    }

    public void loadData() {
        comboBoxModel.removeAllElements();
        promotionCreators = Lookup.getDefault().lookupAll(IPromotionCreator.class);
        if (!promotionCreators.isEmpty()) {
            for (IPromotionCreator promotionCreator : promotionCreators) {
                comboBoxModel.addElement(promotionCreator);
            }
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrimeContent = new javax.swing.JPanel();
        panelOption = new javax.swing.JPanel();
        cboOption = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        panelFooter = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnSaveList = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        javax.swing.GroupLayout panelPrimeContentLayout = new javax.swing.GroupLayout(panelPrimeContent);
        panelPrimeContent.setLayout(panelPrimeContentLayout);
        panelPrimeContentLayout.setHorizontalGroup(
            panelPrimeContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 529, Short.MAX_VALUE)
        );
        panelPrimeContentLayout.setVerticalGroup(
            panelPrimeContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 183, Short.MAX_VALUE)
        );

        cboOption.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PromotionCreatorTopComponent.class, "PromotionCreatorTopComponent.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout panelOptionLayout = new javax.swing.GroupLayout(panelOption);
        panelOption.setLayout(panelOptionLayout);
        panelOptionLayout.setHorizontalGroup(
            panelOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOptionLayout.createSequentialGroup()
                .addContainerGap(294, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboOption, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelOptionLayout.setVerticalGroup(
            panelOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOptionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboOption, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(btnNew, org.openide.util.NbBundle.getMessage(PromotionCreatorTopComponent.class, "PromotionCreatorTopComponent.btnNew.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnSave, org.openide.util.NbBundle.getMessage(PromotionCreatorTopComponent.class, "PromotionCreatorTopComponent.btnSave.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnSaveList, org.openide.util.NbBundle.getMessage(PromotionCreatorTopComponent.class, "PromotionCreatorTopComponent.btnSaveList.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnExit, org.openide.util.NbBundle.getMessage(PromotionCreatorTopComponent.class, "PromotionCreatorTopComponent.btnExit.text")); // NOI18N

        javax.swing.GroupLayout panelFooterLayout = new javax.swing.GroupLayout(panelFooter);
        panelFooter.setLayout(panelFooterLayout);
        panelFooterLayout.setHorizontalGroup(
            panelFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFooterLayout.createSequentialGroup()
                .addContainerGap(164, Short.MAX_VALUE)
                .addComponent(btnNew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSaveList, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelFooterLayout.setVerticalGroup(
            panelFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFooterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit)
                    .addComponent(btnNew)
                    .addComponent(btnSave)
                    .addComponent(btnSaveList))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelFooter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelPrimeContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelPrimeContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelFooter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveList;
    private javax.swing.JComboBox cboOption;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelFooter;
    private javax.swing.JPanel panelOption;
    private javax.swing.JPanel panelPrimeContent;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        if (cboOption.getSelectedItem() != null) {
            panelPrimeContent.setLayout(new GridLayout());
            panelPrimeContent.removeAll();
            panelPrimeContent.invalidate();
            IPromotionCreator promotionCreator = (IPromotionCreator) cboOption.getSelectedItem();
            JPanel panel = promotionCreator.getPromotionCreator();
            panelPrimeContent.add(panel);
            panelPrimeContent.revalidate();
            panelPrimeContent.repaint();
        }

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
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cboOption) {
            panelPrimeContent.setLayout(new GridLayout());
            panelPrimeContent.removeAll();
            panelPrimeContent.invalidate();
            IPromotionCreator promotionCreator = (IPromotionCreator) cboOption.getSelectedItem();
            JPanel panel = promotionCreator.getPromotionCreator();
            panelPrimeContent.add(panel);
            panelPrimeContent.revalidate();
            panelPrimeContent.repaint();
        }
    }

     @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện tạo hình thức giảm giá";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }
}
