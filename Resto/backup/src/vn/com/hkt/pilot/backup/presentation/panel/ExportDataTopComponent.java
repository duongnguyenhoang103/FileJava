/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.backup.presentation.panel;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import vn.com.hkt.pilot.backup.checkboxNode.CheckBoxNode;
import vn.com.hkt.pilot.backup.checkboxNode.CheckBoxNodeEditor;
import vn.com.hkt.pilot.backup.checkboxNode.CheckBoxNodeRenderer;
import vn.com.hkt.pilot.backup.checkboxNode.NamedVector;
import vn.com.hkt.pilot.backup.tools.ExcelExport;
import vn.com.hkt.pilot.identity.entitiy.api.IEntity;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//vn.com.hkt.pilot.backup.presentation.panel//BackUpData//EN",
autostore = false)
@TopComponent.Description(preferredID = "BackUpDataTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "vn.com.hkt.pilot.backup.presentation.panel.BackUpDataTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_BackUpDataAction",
preferredID = "BackUpDataTopComponent")
public final class ExportDataTopComponent extends TopComponent {

    private StringBuilder builder;
    private List<IEntity> list;
    private JCheckBox checkBox[];
    private ExcelExport exportData;
    private List<String> listModule;

    public ExportDataTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ExportDataTopComponent.class, "CTL_BackUpDataTopComponent"));
        setToolTipText(NbBundle.getMessage(ExportDataTopComponent.class, "HINT_BackUpDataTopComponent"));
        list = (List<IEntity>) Lookup.getDefault().lookupAll(IEntity.class);
        listModule = new ArrayList<String>();
        String lastModuleName = list.get(0).getModuleOfEntity();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getModuleOfEntity().equals(lastModuleName)) {
                listModule.add(lastModuleName);
                lastModuleName = list.get(i).getModuleOfEntity();
            }
        }

        jPanel1.setLayout(new GridLayout(13, 5));
//        JTree tree;
//        CheckBoxNode nodeModule[] = new CheckBoxNode[listModule.size()];
//        CheckBoxNode nodeEntity[] = new CheckBoxNode[list.size()];
//
//        for (int i = 0; i < listModule.size(); i++) {
//            nodeModule[i] = new CheckBoxNode(listModule.get(i), false);
//        }
//        Vector moduleVector = new NamedVector("", nodeModule);
//        tree = new JTree(moduleVector);
//        CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
//        tree.setCellRenderer(renderer);
//        tree.setCellEditor(new CheckBoxNodeEditor(tree));
//        tree.setEditable(true);
//        jPanel1.add(tree);
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
        //fileDialog.setFile("data.xls");
        fileDialog.setVisible(true);
        //String str = fileDialog.getDirectory() + fileDialog.getFile();
        String str = fileDialog.getDirectory();
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

        btnBackUp = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        org.openide.awt.Mnemonics.setLocalizedText(btnBackUp, org.openide.util.NbBundle.getMessage(ExportDataTopComponent.class, "ExportDataTopComponent.btnBackUp.text")); // NOI18N
        btnBackUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackUpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBackUp)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBackUp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void btnBackUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackUpActionPerformed
    exportData = new ExcelExport();
    for (int i = 0; i < list.size(); i++) {
        if (checkBox[i].isSelected()) {
            IEntity entity = list.get(i);
            exportData.export(entity.getModuleOfEntity(), entity.getEntityName());
        }
    }
}//GEN-LAST:event_btnBackUpActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackUp;
    private javax.swing.JPanel jPanel1;
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