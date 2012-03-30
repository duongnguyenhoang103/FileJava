/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.backup.presentation.panel;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FilenameFilter;
import org.h2.tools.Script;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.pilot.backup.presentation.panel//BackupDatabase//EN",
autostore = false)
@TopComponent.Description(preferredID = "BackupDatabaseTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.pilot.backup.presentation.panel.BackupDatabaseTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_BackupDatabaseAction",
preferredID = "BackupDatabaseTopComponent")
@Messages({
    "CTL_BackupDatabaseAction=BackupDatabase",
    "CTL_BackupDatabaseTopComponent=BackupDatabase Window",
    "HINT_BackupDatabaseTopComponent=This is a BackupDatabase window"
})
public final class BackupDatabaseTopComponent extends TopComponent {

    private StringBuilder builder;

    public BackupDatabaseTopComponent() {
        initComponents();
        setName(Bundle.CTL_BackupDatabaseTopComponent());
        setToolTipText(Bundle.HINT_BackupDatabaseTopComponent());

    }

    private void openFileDialog() {
        FileDialog fileDialog = new FileDialog(new Frame(), "Save", FileDialog.SAVE);
        fileDialog.setFilenameFilter(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".sql");
            }
        });
        fileDialog.setFile("backup.sql");
        fileDialog.setVisible(true);
        String str = fileDialog.getDirectory() + fileDialog.getFile();
        builder = new StringBuilder(str);
        int j = 1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\\') {
                builder.insert(i + j++, '\\');
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBackup = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(btnBackup, org.openide.util.NbBundle.getMessage(BackupDatabaseTopComponent.class, "BackupDatabaseTopComponent.btnBackup.text")); // NOI18N
        btnBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBackup)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(btnBackup)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackupActionPerformed
        openFileDialog();
        try {
            Script.execute("jdbc:h2:tcp://localhost/~/EnterpriseManager", "sa", "", builder.toString());
        } catch (Exception e) {
            Exceptions.printStackTrace(e);
        }
    }//GEN-LAST:event_btnBackupActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackup;
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
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
    }
}