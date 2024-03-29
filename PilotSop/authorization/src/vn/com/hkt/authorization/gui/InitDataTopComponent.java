/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.authorization.gui;

import com.vn.hkt.core.Account;
import com.vn.hkt.core.Permission;
import com.vn.hkt.core.Window;
import com.vn.hkt.generic.api.IGenericAPI;
import javax.swing.JOptionPane;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import vn.com.hkt.pilot.entities.Department;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.authorization.gui//InitData//EN",
autostore = false)
@TopComponent.Description(preferredID = "InitDataTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.authorization.gui.InitDataTopComponent")
@ActionReference(path = "Menu/File/Tạo dữ liệu mẫu" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_InitDataAction",
preferredID = "InitDataTopComponent")
public final class InitDataTopComponent extends TopComponent {

    private IGenericAPI aPI;

    public InitDataTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(InitDataTopComponent.class, "CTL_InitDataTopComponent"));
        setToolTipText(NbBundle.getMessage(InitDataTopComponent.class, "HINT_InitDataTopComponent"));
        this.aPI = Lookup.getDefault().lookup(IGenericAPI.class);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnInit = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(btnInit, org.openide.util.NbBundle.getMessage(InitDataTopComponent.class, "InitDataTopComponent.btnInit.text")); // NOI18N
        btnInit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 113, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnInit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnInit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void btnInitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInitActionPerformed
// TODO add your handling code here:
    aPI.insertData(new Account("admin", "admin", "Per01", "Gr01"));
    aPI.insertData(new Account("employer1", "employer1", "Per02", "Gr02"));
    aPI.insertData(new Account("employer2", "employer2", "Per03", "Gr02"));        

    aPI.insertData(new Window("Operation"));
    aPI.insertData(new Window("Person"));
    aPI.insertData(new Window("Enterprise"));
    aPI.insertData(new Window("Product"));
    aPI.insertData(new Window("Department"));    

    aPI.insertData(new Permission(1, 4, "Operation", true, false, false));
    aPI.insertData(new Permission(1, 5, "Person", true, false, false));
    aPI.insertData(new Permission(1, 6, "Enterprise", true, false, false));
    aPI.insertData(new Permission(1, 7, "Product", true, false, false));
    aPI.insertData(new Permission(1, 8, "Department", true, false, false));

    aPI.insertData(new Permission(2, 4, "Operation", false, false, true));
    aPI.insertData(new Permission(2, 5, "Person", false, false, true));
    aPI.insertData(new Permission(2, 6, "Enterprise", false, false, true));
    aPI.insertData(new Permission(2, 7, "Product", false, false, true));
    aPI.insertData(new Permission(2, 8, "Department", false, false, true));

    aPI.insertData(new Permission(3, 4, "Operation", false, false, true));
    aPI.insertData(new Permission(3, 5, "Person", false, false, true));
    aPI.insertData(new Permission(3, 6, "Enterprise", false, false, true));
    aPI.insertData(new Permission(3, 7, "Product", false, false, true));
    aPI.insertData(new Permission(3, 8, "Department", false, false, true));

    JOptionPane.showMessageDialog(null, "Create Default Data Successfully");
}//GEN-LAST:event_btnInitActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInit;
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
}
