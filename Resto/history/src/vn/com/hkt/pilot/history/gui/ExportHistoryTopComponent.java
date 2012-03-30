/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.history.gui;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import vn.com.hkt.history.apidao.IExportImportHistory;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.pilot.history.gui//ExportHistory//EN",
autostore = false)
@TopComponent.Description(preferredID = "ExportHistoryTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.pilot.history.gui.ExportHistoryTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ExportHistoryAction",
preferredID = "ExportHistoryTopComponent")
public final class ExportHistoryTopComponent extends TopComponent {

    private StringBuilder builder;
    private IExportImportHistory eximHistoryAPI;
    private List<IEntity> list;
    private JCheckBox checkBox[];

    public ExportHistoryTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ExportHistoryTopComponent.class, "CTL_ExportHistoryTopComponent"));
        setToolTipText(NbBundle.getMessage(ExportHistoryTopComponent.class, "HINT_ExportHistoryTopComponent"));
        this.eximHistoryAPI = Lookup.getDefault().lookup(IExportImportHistory.class);
        list = (List<IEntity>) Lookup.getDefault().lookupAll(IEntity.class);
        jPanel1.setLayout(new GridLayout(11, 5));
        checkBox = new JCheckBox[list.size()];
        for (int i = 0; i < list.size(); i++) {
            checkBox[i] = new JCheckBox(list.get(i).getEntityName());
            jPanel1.add(checkBox[i]);
        }
    }

    private void openFileDialog() {
        FileDialog fileDialog = new FileDialog(new Frame(), "Save", FileDialog.SAVE);
        fileDialog.setFilenameFilter(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".xls");
            }
        });
        fileDialog.setFile("Untitled.xls");
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

        jPanel1 = new javax.swing.JPanel();
        lblFromDate = new javax.swing.JLabel();
        lblToDate = new javax.swing.JLabel();
        dchToDate = new com.toedter.calendar.JDateChooser();
        dchFromDate = new com.toedter.calendar.JDateChooser();
        btnBackup = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 106, Short.MAX_VALUE)
        );

        org.openide.awt.Mnemonics.setLocalizedText(lblFromDate, org.openide.util.NbBundle.getMessage(ExportHistoryTopComponent.class, "ExportHistoryTopComponent.lblFromDate.text")); // NOI18N
        lblFromDate.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(lblToDate, org.openide.util.NbBundle.getMessage(ExportHistoryTopComponent.class, "ExportHistoryTopComponent.lblToDate.text")); // NOI18N
        lblToDate.setEnabled(false);

        dchToDate.setDateFormatString(org.openide.util.NbBundle.getMessage(ExportHistoryTopComponent.class, "ExportHistoryTopComponent.dchToDate.dateFormatString")); // NOI18N

        dchFromDate.setDateFormatString(org.openide.util.NbBundle.getMessage(ExportHistoryTopComponent.class, "ExportHistoryTopComponent.dchFromDate.dateFormatString")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnBackup, org.openide.util.NbBundle.getMessage(ExportHistoryTopComponent.class, "ExportHistoryTopComponent.btnBackup.text")); // NOI18N
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBackup, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblToDate)
                            .addComponent(lblFromDate))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dchFromDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dchToDate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dchFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblToDate)
                            .addComponent(dchToDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblFromDate))
                .addGap(18, 18, 18)
                .addComponent(btnBackup, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackupActionPerformed
        openFileDialog();
        eximHistoryAPI.createFileExcel(builder.toString());
        try {
            JTextField jtf = (JTextField) dchFromDate.getDateEditor().getUiComponent();
            JTextField jtf1 = (JTextField) dchToDate.getDateEditor().getUiComponent();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fromDate = sdf.parse(jtf.getText());
            Date toDate = sdf.parse(jtf1.getText());

            for (int i = 0; i < list.size(); i++) {
                if (checkBox[i].isSelected()) {
                    IEntity entity = list.get(i);
                    eximHistoryAPI.exportHistoryToExcel(builder.toString(), entity.getModuleOfEntity(), entity.getClass(), fromDate, toDate);
                }
            }
        } catch (Exception e) {
            Exceptions.printStackTrace(e);
        }
    }//GEN-LAST:event_btnBackupActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackup;
    private com.toedter.calendar.JDateChooser dchFromDate;
    private com.toedter.calendar.JDateChooser dchToDate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFromDate;
    private javax.swing.JLabel lblToDate;
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
