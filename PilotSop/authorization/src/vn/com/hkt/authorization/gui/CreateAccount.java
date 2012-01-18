/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CreateAccount.java
 *
 * Created on Dec 30, 2011, 10:21:11 AM
 */
package vn.com.hkt.authorization.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import org.openide.util.Lookup;
import vn.com.hkt.CreateAccount.api.ICreateAccount;
import vn.com.hkt.authorization.ComboboxFilter.JComboBoxFilterAPI;
import vn.com.hkt.authorization.ComboboxFilter.Row;
import vn.com.hkt.authorization.ComboboxFilter.RowCellRenderer;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;

/**
 *
 * @author Admin
 */
public class CreateAccount extends javax.swing.JDialog {

    DefaultComboBoxModel modelDepartment, modelPerson, modelCompany;
    private IEnterpriseBN enterpriseDAO;
    private ICreateAccount createAccountDAO;
    private String enterpriseID;

    /** Creates new form CreateAccount */
    public CreateAccount() {
        super();
        initComponents();

        this.enterpriseDAO = Lookup.getDefault().lookup(IEnterpriseBN.class);
        this.createAccountDAO = Lookup.getDefault().lookup(ICreateAccount.class);

        modelCompany = new DefaultComboBoxModel();
        modelCompany.addElement("");

        List<Enterprise> list2 = new ArrayList<Enterprise>();
        list2 = enterpriseDAO.getAllEnterprise();
        for (Enterprise e : list2) {
            modelCompany.addElement(e);
        }
        cbxCompany.setModel(modelCompany);


//        modelPerson = new DefaultComboBoxModel();
//
//        List<Person> list1 = new ArrayList<Person>();
//        list1 = personDAO.getAllPerson();
//        int i;
//        for (i = 0; i < list1.size(); i++) {
//            Row row = new Row(list1.get(i).getPersonID(), list1.get(i).getFirstName() + list1.get(i).getLastName());
//            modelPerson.addElement(row);
//        }
//        cbxPerson.setModel(modelPerson);
//        cbxPerson.setRenderer(new RowCellRenderer());
//        cbxPerson.setEditable(true);
//        new JComboBoxFilterAPI(cbxPerson);

//        List<Department> list = new ArrayList<Department>();
//        list = departmentDAO.getAllDepartment();
//        for (Department d : list) {
//            modelDepartment.addElement(d);
//        }
//        cbxDepartment.setModel(modelDepartment);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        cbxDepartment = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cbxPerson = new javax.swing.JComboBox();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cbxCompany = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText(org.openide.util.NbBundle.getMessage(CreateAccount.class, "CreateAccount.jLabel1.text")); // NOI18N

        txtUserName.setText(org.openide.util.NbBundle.getMessage(CreateAccount.class, "CreateAccount.txtUserName.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(CreateAccount.class, "CreateAccount.jLabel2.text")); // NOI18N

        jPasswordField1.setText(org.openide.util.NbBundle.getMessage(CreateAccount.class, "CreateAccount.jPasswordField1.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(CreateAccount.class, "CreateAccount.jLabel4.text")); // NOI18N

        cbxDepartment.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDepartmentItemStateChanged(evt);
            }
        });

        jLabel3.setText(org.openide.util.NbBundle.getMessage(CreateAccount.class, "CreateAccount.jLabel3.text")); // NOI18N

        cbxPerson.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));

        btnSave.setText(org.openide.util.NbBundle.getMessage(CreateAccount.class, "CreateAccount.btnSave.text")); // NOI18N
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setText(org.openide.util.NbBundle.getMessage(CreateAccount.class, "CreateAccount.btnCancel.text")); // NOI18N
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel5.setText(org.openide.util.NbBundle.getMessage(CreateAccount.class, "CreateAccount.jLabel5.text")); // NOI18N

        cbxCompany.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCompanyItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbxCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbxDepartment, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbxCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbxDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(cbxPerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
// TODO add your handling code here:
    this.dispose();
}//GEN-LAST:event_btnCancelActionPerformed

private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_btnSaveActionPerformed

private void cbxCompanyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCompanyItemStateChanged
// TODO add your handling code here:    
    String str = cbxCompany.getSelectedItem().toString();
    Enterprise e = new Enterprise();
    e = createAccountDAO.getEnterpriseIDByName(str);
    enterpriseID = e.getEnterpriseID();
    List<Department> list = new ArrayList<Department>();
    list = createAccountDAO.getDepartmentByEnterprise(enterpriseID);

    modelDepartment = new DefaultComboBoxModel();
    modelDepartment.addElement("");

    for (Department d : list) {
        modelDepartment.addElement(d);
    }
    cbxDepartment.setModel(modelDepartment);
}//GEN-LAST:event_cbxCompanyItemStateChanged

private void cbxDepartmentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDepartmentItemStateChanged
// TODO add your handling code here:        
    List<Person> list1 = new ArrayList<Person>();
    String str = cbxDepartment.getSelectedItem().toString();
    list1 = createAccountDAO.getPersonByEnterpriseAndDepartment(enterpriseID, str);
    modelPerson = new DefaultComboBoxModel();

    int i;
    for (i = 0; i < list1.size(); i++) {
        Row row = new Row(list1.get(i).getPersonID() + "/t", list1.get(i).getFirstName() + list1.get(i).getLastName());
        modelPerson.addElement(row);
    }
    cbxPerson.setModel(modelPerson);
    cbxPerson.setRenderer(new RowCellRenderer());
    cbxPerson.setEditable(true);
    new JComboBoxFilterAPI(cbxPerson);
}//GEN-LAST:event_cbxDepartmentItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreateAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                CreateAccount dialog = new CreateAccount();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cbxCompany;
    private javax.swing.JComboBox cbxDepartment;
    private javax.swing.JComboBox cbxPerson;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}