/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EditChucVuPanel.java
 *
 * Created on Feb 20, 2012, 3:35:33 PM
 */
package vn.com.hkt.bom.operation.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.basic.Installer;
import vn.com.hkt.pilot.dialog.dao.ClassificationBN;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseExtCreator;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseHelp;
import vn.com.hkt.pilot.entities.Classification;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.operation.viewer.api.IOperationCreater;

/**
 *
 * @author longnt
 */
@ServiceProvider(service = IEnterpriseHelp.class)
public class EditClassificationPanel extends javax.swing.JPanel implements IEnterpriseHelp,IUserInterface {

    private Classification classification;
    private ClassificationBN classificationBN;
    private DefaultTableModel model;
    private List<Classification> list = new ArrayList<Classification>();
    private Collection<? extends IOperationCreater> ieec;

    /** Creates new form EditChucVuPanel */
    public EditClassificationPanel() {
        initComponents();
        classificationBN = new ClassificationBN();
        loadData();
        resetData();
        ieec = Lookup.getDefault().lookupAll(IOperationCreater.class);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbDescription = new javax.swing.JLabel();
        txtnameClassification = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableObject = new javax.swing.JTable();
        btnCreate = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        lbTitle.setFont(new java.awt.Font("SansSerif", 1, 18));
        lbTitle.setForeground(new java.awt.Color(0, 51, 102));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText(org.openide.util.NbBundle.getMessage(EditClassificationPanel.class, "EditClassificationPanel.lbTitle.text")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(EditClassificationPanel.class, "EditClassificationPanel.jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(0, 51, 102))); // NOI18N

        lbDescription.setFont(new java.awt.Font("SansSerif", 0, 12));
        lbDescription.setText(org.openide.util.NbBundle.getMessage(EditClassificationPanel.class, "EditClassificationPanel.lbDescription.text")); // NOI18N

        txtnameClassification.setFont(new java.awt.Font("SansSerif", 0, 12));
        txtnameClassification.setText(org.openide.util.NbBundle.getMessage(EditClassificationPanel.class, "EditClassificationPanel.txtnameClassification.text")); // NOI18N

        tableObject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Thông số"
            }
        ));
        tableObject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableObjectMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableObject);

        btnCreate.setFont(new java.awt.Font("SansSerif", 0, 12));
        btnCreate.setText(org.openide.util.NbBundle.getMessage(EditClassificationPanel.class, "EditClassificationPanel.btnCreate.text")); // NOI18N
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("SansSerif", 0, 12));
        btnEdit.setText(org.openide.util.NbBundle.getMessage(EditClassificationPanel.class, "EditClassificationPanel.btnEdit.text")); // NOI18N
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("SansSerif", 0, 12));
        btnDelete.setText(org.openide.util.NbBundle.getMessage(EditClassificationPanel.class, "EditClassificationPanel.btnDelete.text")); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("SansSerif", 0, 12));
        btnReset.setText(org.openide.util.NbBundle.getMessage(EditClassificationPanel.class, "EditClassificationPanel.btnReset.text")); // NOI18N
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnameClassification, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDescription)
                    .addComponent(txtnameClassification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset)
                    .addComponent(btnCreate)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableObjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableObjectMouseClicked

        tabelSelectedRow();     }//GEN-LAST:event_tableObjectMouseClicked

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed

        createData();
        resetData();
        loadData();
        loadCombo();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        loadCombo();
         editData();         resetData();         loadData();     }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        loadCombo();
        removeData();         resetData();         loadData();     }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed

        resetData();     }//GEN-LAST:event_btnResetActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReset;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDescription;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTable tableObject;
    private javax.swing.JTextField txtnameClassification;
    // End of variables declaration//GEN-END:variables

    private void tabelSelectedRow() {
        if (tableObject.getSelectedRow() < 0) {
            return;
        }
        classification = list.get(tableObject.getSelectedRow());
        txtnameClassification.setText(classification.getClassificationName());
        btnCreate.setEnabled(false);
        btnDelete.setEnabled(true);
        btnEdit.setEnabled(true);
    }

    private void resetData() {
        txtnameClassification.setText("");
        btnCreate.setEnabled(true);
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);
        classification = null;
    }

    public void loadCombo() {
        if (ieec != null) {
            for (IOperationCreater extCreator : ieec) {
                double index = extCreator.getLevelNumber();
                if (index == 5.58) {
                    extCreator.getOperationCreater();
                }
            }
        }
    }

    private void removeData() {
        classificationBN.delete(classification.getId());
        model.removeRow(tableObject.getSelectedRow());
        resetData();
        loadData();
    }

    private void loadData() {

        list = classificationBN.selectAll();
        String[] header = {"Thông số"};
        model = new DefaultTableModel(header, 0);
        for (Classification bean : list) {
            String[] rows = {bean.getClassificationName()};
            model.addRow(rows);
        }
        tableObject.setModel(model);
    }

    private void editData() {
        classification.setClassificationName(txtnameClassification.getText());
        classificationBN.update(classification);
        resetData();
        loadData();
    }

    private void createData() {
        Classification c = new Classification();
        c.setClassificationName(txtnameClassification.getText());
        c.setClassificationId(String.valueOf(Math.random() * 10));
        classificationBN.insert(c);
        resetData();
        loadData();
    }

    @Override
    public JPanel getPanel1() {
        return this;
    }

    @Override
    public JPanel getPanel2() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double getLevelNumber() {
        return 5.58;
    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện chỉnh sửa phân loại";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }
}
