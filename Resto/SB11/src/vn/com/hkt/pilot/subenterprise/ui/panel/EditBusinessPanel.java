/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EditChucVuPanel.java
 *
 * Created on Feb 20, 2012, 3:35:33 PM
 */
package vn.com.hkt.pilot.subenterprise.ui.panel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseExtCreator;
import vn.com.hkt.pilot.enterprise.viewer.api.IEnterpriseHelp;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;
import vn.com.hkt.pilot.sbenterprise.Installer;
import vn.com.hkt.pilot.sbenterprise.entity.BusinessArea;
import vn.com.hkt.pilot.subenterprise.dao.BusinessAreaBN;

/**
 *
 * @author longnt
 */
@ServiceProvider(service = IEnterpriseHelp.class)
public class EditBusinessPanel extends javax.swing.JPanel implements IEnterpriseHelp, IUserInterface {

    private BusinessArea BusinessArea;
    private BusinessAreaBN BusinessAreaBN;
    private DefaultTableModel model;
    private List<BusinessArea> list = new ArrayList<BusinessArea>();
    private Collection<? extends IEnterpriseExtCreator> ieec;

    /** Creates new form EditChucVuPanel */
    public EditBusinessPanel() {
        initComponents();
        BusinessAreaBN = new BusinessAreaBN();
        loadData();
        resetData();
        ieec = Lookup.getDefault().lookupAll(IEnterpriseExtCreator.class);
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
        txtBusiness = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableObject = new javax.swing.JTable();
        btnCreate = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        lbDescription1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();

        lbTitle.setFont(new java.awt.Font("SansSerif", 1, 18));
        lbTitle.setForeground(new java.awt.Color(0, 51, 102));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText(org.openide.util.NbBundle.getMessage(EditBusinessPanel.class, "EditBusinessPanel.lbTitle.text")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(EditBusinessPanel.class, "EditBusinessPanel.jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(0, 51, 102))); // NOI18N

        lbDescription.setFont(new java.awt.Font("SansSerif", 0, 12));
        lbDescription.setText(org.openide.util.NbBundle.getMessage(EditBusinessPanel.class, "EditBusinessPanel.lbDescription.text")); // NOI18N

        txtBusiness.setFont(new java.awt.Font("SansSerif", 0, 12));
        txtBusiness.setText(org.openide.util.NbBundle.getMessage(EditBusinessPanel.class, "EditBusinessPanel.txtBusiness.text")); // NOI18N

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
        btnCreate.setText(org.openide.util.NbBundle.getMessage(EditBusinessPanel.class, "EditBusinessPanel.btnCreate.text")); // NOI18N
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("SansSerif", 0, 12));
        btnEdit.setText(org.openide.util.NbBundle.getMessage(EditBusinessPanel.class, "EditBusinessPanel.btnEdit.text")); // NOI18N
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("SansSerif", 0, 12));
        btnDelete.setText(org.openide.util.NbBundle.getMessage(EditBusinessPanel.class, "EditBusinessPanel.btnDelete.text")); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("SansSerif", 0, 12));
        btnReset.setText(org.openide.util.NbBundle.getMessage(EditBusinessPanel.class, "EditBusinessPanel.btnReset.text")); // NOI18N
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        lbDescription1.setFont(new java.awt.Font("SansSerif", 0, 12));
        lbDescription1.setText(org.openide.util.NbBundle.getMessage(EditBusinessPanel.class, "EditBusinessPanel.lbDescription1.text")); // NOI18N

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(lbDescription1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane2))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(lbDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtBusiness, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDescription)
                    .addComponent(txtBusiness, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDescription1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset)
                    .addComponent(btnCreate)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbDescription;
    private javax.swing.JLabel lbDescription1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTable tableObject;
    private javax.swing.JTextField txtBusiness;
    private javax.swing.JTextArea txtDescription;
    // End of variables declaration//GEN-END:variables

    private void tabelSelectedRow() {
        if (tableObject.getSelectedRow() < 0) {
            return;
        }
        BusinessArea = list.get(tableObject.getSelectedRow());
        txtBusiness.setText(BusinessArea.getBusinessCode());
        txtDescription.setText(BusinessArea.getDescription());
        btnCreate.setEnabled(false);
        btnDelete.setEnabled(true);
        btnEdit.setEnabled(true);
    }

    private void resetData() {
        txtBusiness.setText("");
        txtDescription.setText("");
        btnCreate.setEnabled(true);
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);
        BusinessArea = null;
    }

    public void loadCombo() {
        if (ieec != null) {
            for (IEnterpriseExtCreator extCreator : ieec) {
                if(extCreator instanceof ExtensionEnterpriseSB11Panel){
                    ((ExtensionEnterpriseSB11Panel)extCreator).loadCombo();
                }
//                double index = extCreator.getLevelNumber();
//                if (index == 1.11) {
//                    extCreator.getEnterpriseExtLookup();
//                }
            }
        }
    }

    private void removeData() {
        BusinessAreaBN.delete(BusinessArea.getId());
        model.removeRow(tableObject.getSelectedRow());
        resetData();
        loadData();
    }

    private void loadData() {

        list = BusinessAreaBN.selectAll();
        String[] header = {"Thông số", " "};
        model = new DefaultTableModel(header, 0);
        for (BusinessArea bean : list) {
            String[] rows = {bean.getBusinessCode(), bean.getDescription()};
            model.addRow(rows);
        }
        tableObject.setModel(model);
    }

    private void editData() {
        BusinessArea.setBusinessCode(txtBusiness.getText());
        BusinessArea.setDescription(txtDescription.getText());
        BusinessAreaBN.update(BusinessArea);
        resetData();
        loadData();
    }

    private void createData() {
        BusinessArea m = new BusinessArea();
        m.setBusinessCode(txtBusiness.getText());
        m.setDescription(txtDescription.getText());
        BusinessAreaBN.insert(m);
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
        return 1.4;
    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện chỉnh sủa kinh doanh";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }
}