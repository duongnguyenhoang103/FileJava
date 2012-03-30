/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EditChucVuPanel.java
 *
 * Created on Feb 20, 2012, 3:35:33 PM
 */
package vn.com.hkt.hrm.person.ui;

import javax.swing.table.DefaultTableModel;
import org.openide.util.Lookup;
import vn.com.hkt.basic.Installer;
import vn.com.hkt.basic.api.ICityBN;
import vn.com.hkt.pilot.entities.City;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;

/**
 *
 * @author longnt
 */
public class EditCodePanel extends javax.swing.JPanel implements IUserInterface {

    private City city;
    private ICityBN cityBN;
    private DefaultTableModel model;

    public EditCodePanel(City city) {
        this.city = city;
        initComponents();
        cityBN = Lookup.getDefault().lookup(ICityBN.class);
        loadData();
        resetData();
    }

    /**
     * Creates new form EditChucVuPanel
     */
//    public EditNationalityPanel(Country country) {
//        initComponents();
//        this.country = country;
//        countryBN = Lookup.getDefault().lookup(ICountryBN.class);
//        loadData();
//        resetData();
//        JOptionPane.showMessageDialog(null, country);
//        JOptionPane.showMessageDialog(null, this.country);
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbDescription = new javax.swing.JLabel();
        txtCountryName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableObject = new javax.swing.JTable();
        btnCreate = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        lbTitle.setFont(new java.awt.Font("SansSerif", 1, 18));
        lbTitle.setForeground(new java.awt.Color(0, 51, 102));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText(org.openide.util.NbBundle.getMessage(EditCodePanel.class, "EditCodePanel.lbTitle.text_1")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(EditCodePanel.class, "EditCodePanel.jPanel1.border.title_1"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(0, 51, 102))); // NOI18N

        lbDescription.setFont(new java.awt.Font("SansSerif", 0, 12));
        lbDescription.setText(org.openide.util.NbBundle.getMessage(EditCodePanel.class, "EditCodePanel.lbDescription.text_1")); // NOI18N

        txtCountryName.setFont(new java.awt.Font("SansSerif", 0, 12));
        txtCountryName.setText(org.openide.util.NbBundle.getMessage(EditCodePanel.class, "EditCodePanel.txtCountryName.text_1")); // NOI18N

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
        btnCreate.setText(org.openide.util.NbBundle.getMessage(EditCodePanel.class, "EditCodePanel.btnCreate.text_1")); // NOI18N
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("SansSerif", 0, 12));
        btnEdit.setText(org.openide.util.NbBundle.getMessage(EditCodePanel.class, "EditCodePanel.btnEdit.text_1")); // NOI18N
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("SansSerif", 0, 12));
        btnDelete.setText(org.openide.util.NbBundle.getMessage(EditCodePanel.class, "EditCodePanel.btnDelete.text_1")); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("SansSerif", 0, 12));
        btnReset.setText(org.openide.util.NbBundle.getMessage(EditCodePanel.class, "EditCodePanel.btnReset.text_1")); // NOI18N
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCountryName, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(txtCountryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
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

    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

         editData();         resetData();         loadData();     }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

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
    private javax.swing.JTextField txtCountryName;
    // End of variables declaration//GEN-END:variables

    private void tabelSelectedRow() {
        if (tableObject.getSelectedRow() < 0) {
            return;
        }
        if (tableObject.getSelectedRow() == 0) {
            txtCountryName.setText(String.valueOf(city.getPostalCode()));
            btnCreate.setEnabled(false);
            btnDelete.setEnabled(true);
            btnEdit.setEnabled(true);
        }
        // country = list.get(tableObject.getSelectedRow());

    }

    private void resetData() {
        txtCountryName.setText("");
        btnCreate.setEnabled(true);
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);
        //country = null;
    }

    private void removeData() {
        city.setPostalCode(0);
        model.removeRow(tableObject.getSelectedRow());
        resetData();
        loadData();
    }

    private void loadData() {
        String[] header = {"Thông số"};
        model = new DefaultTableModel(header, 0);
        String str = String.valueOf(city.getPostalCode());
        String[] rows = {str};
        model.addRow(rows);
        tableObject.setModel(model);
    }

    private void editData() {
        city.setPostalCode(Integer.parseInt(txtCountryName.getText().trim()));
        cityBN.update(city);
        resetData();
        loadData();
    }

    private void createData() {
        city.setPostalCode(Integer.parseInt(txtCountryName.getText().trim()));
        cityBN.update(city);
        resetData();
        loadData();
    }

    @Override
    public String getUserInterfaceName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getUserInterfaceDescription() {
        return "Giao diện chỉnh sửa đất nước";
    }

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }
}