/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CreateAccount.java
 *
 * Created on Feb 8, 2012, 2:21:02 PM
 */
package vn.com.hkt.account.manager.gui.panel;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import vn.com.hkt.account.manager.Installer;
import vn.com.hkt.account.manager.api.IAccountDAO;
import vn.com.hkt.account.manager.api.IAccountTypeDAO;
import vn.com.hkt.account.manager.entities.Account;
import vn.com.hkt.account.manager.entities.AccountType;
import vn.com.hkt.account.manager.spi.AccountDAO;
import vn.com.hkt.account.manager.spi.AccountTypeDAO;
import vn.com.hkt.basic.api.IDepartmentBN;
import vn.com.hkt.basic.api.IEnterpriseBN;
import vn.com.hkt.basic.api.IPersonBN;
import vn.com.hkt.pilot.entities.Department;
import vn.com.hkt.pilot.entities.Enterprise;
import vn.com.hkt.pilot.entities.Person;
import vn.com.hkt.pilot.identity.presentation.api.IUserInterface;

/**
 *
 * @author Admin
 */
@ServiceProvider(service = IUserInterface.class)
public class AccountInformation extends javax.swing.JPanel implements IUserInterface {

    public static final String USER_INTERFACE = "create_account";
    public static final String USER_INTERFACE_DESCRIPTION = "Tao moi, chinh sua account";
    private List<Enterprise> enterprises;
    private Enterprise enterpriseChoise;
    private List<Department> departments;
    private Department departmentChoise;
    private List<Person> persons;
    private Person personChoise;
    private List<AccountType> accountTypes;
    private AccountType accountTypeChoise;
    private Account accountChoise;
    private IAccountTypeDAO accountTypeDAO = new AccountTypeDAO();
    private IEnterpriseBN enterpriseBN = Lookup.getDefault().lookup(IEnterpriseBN.class);
    private IDepartmentBN departmentBN = Lookup.getDefault().lookup(IDepartmentBN.class);
    private IPersonBN personBN = Lookup.getDefault().lookup(IPersonBN.class);
    private IAccountDAO accountDAO = new AccountDAO();
    private JScrollPane scrollPane;

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    /**
     * Creates new form CreateAccount
     */
    public AccountInformation() {
        initComponents();
        btnCreate.setVisible(true);
        btnEdit.setVisible(false);
        btnRemove.setVisible(false);
    }

    public AccountInformation(Person person) {
        initComponents();
        personChoise = person;
        loadPersonChoise();
        btnCreate.setVisible(true);
        btnEdit.setVisible(false);
        btnRemove.setVisible(false);
    }

    public AccountInformation(Account account) {
        initComponents();
        accountChoise = account;
        loadAccountChoise();
        btnCreate.setVisible(false);
        btnEdit.setVisible(true);
        btnRemove.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbUsername = new javax.swing.JLabel();
        lbPassword = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cbEnterprise = new javax.swing.JComboBox();
        cbDepartment = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbPerson = new javax.swing.JComboBox();
        btnCreate = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbAccountType = new javax.swing.JComboBox();
        txtPassword = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        lbMessenger = new javax.swing.JLabel();

        lbUsername.setText(org.openide.util.NbBundle.getMessage(AccountInformation.class, "AccountInformation.lbUsername.text")); // NOI18N

        lbPassword.setText(org.openide.util.NbBundle.getMessage(AccountInformation.class, "AccountInformation.lbPassword.text")); // NOI18N

        txtUsername.setText(org.openide.util.NbBundle.getMessage(AccountInformation.class, "AccountInformation.txtUsername.text")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, org.openide.util.NbBundle.getMessage(AccountInformation.class, "AccountInformation.jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(AccountInformation.class, "AccountInformation.jLabel5.text")); // NOI18N

        cbEnterprise.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbEnterprise.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbEnterpriseItemStateChanged(evt);
            }
        });

        cbDepartment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbDepartment.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDepartmentItemStateChanged(evt);
            }
        });

        jLabel7.setText(org.openide.util.NbBundle.getMessage(AccountInformation.class, "AccountInformation.jLabel7.text")); // NOI18N

        jLabel8.setText(org.openide.util.NbBundle.getMessage(AccountInformation.class, "AccountInformation.jLabel8.text")); // NOI18N

        cbPerson.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbPerson, 0, 271, Short.MAX_VALUE)
                    .addComponent(cbEnterprise, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbDepartment, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel5, jLabel7, jLabel8});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(cbEnterprise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7)
                    .addComponent(cbDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(cbPerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCreate.setText(org.openide.util.NbBundle.getMessage(AccountInformation.class, "AccountInformation.btnCreate.text")); // NOI18N
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnReset.setText(org.openide.util.NbBundle.getMessage(AccountInformation.class, "AccountInformation.btnReset.text")); // NOI18N
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnCancel.setText(org.openide.util.NbBundle.getMessage(AccountInformation.class, "AccountInformation.btnCancel.text")); // NOI18N
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(org.openide.util.NbBundle.getMessage(AccountInformation.class, "AccountInformation.jLabel4.text")); // NOI18N

        jLabel6.setText(org.openide.util.NbBundle.getMessage(AccountInformation.class, "AccountInformation.jLabel6.text")); // NOI18N

        cbAccountType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbAccountType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbAccountTypeMouseClicked(evt);
            }
        });
        cbAccountType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbAccountTypeItemStateChanged(evt);
            }
        });

        txtPassword.setText(org.openide.util.NbBundle.getMessage(AccountInformation.class, "AccountInformation.txtPassword.text")); // NOI18N

        btnEdit.setText(org.openide.util.NbBundle.getMessage(AccountInformation.class, "AccountInformation.btnEdit.text")); // NOI18N
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnRemove.setText(org.openide.util.NbBundle.getMessage(AccountInformation.class, "AccountInformation.btnRemove.text")); // NOI18N
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        lbMessenger.setText(org.openide.util.NbBundle.getMessage(AccountInformation.class, "AccountInformation.lbMessenger.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbAccountType, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lbUsername)
                                            .addComponent(lbPassword))
                                        .addGap(41, 41, 41)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtUsername)
                                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnReset)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCreate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnRemove)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCancel)))))
                        .addGap(0, 46, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(lbMessenger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lbPassword, lbUsername});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbAccountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnReset)
                    .addComponent(btnCancel)
                    .addComponent(btnEdit)
                    .addComponent(btnRemove))
                .addGap(18, 18, 18)
                .addComponent(lbMessenger)
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        loadDefault();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        createAccount();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        cancel();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void cbEnterpriseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbEnterpriseItemStateChanged
        enterpriseChoise = (Enterprise) cbEnterprise.getSelectedItem();
        if (enterpriseChoise != null) {
            loadDepartment();
        }
    }//GEN-LAST:event_cbEnterpriseItemStateChanged

    private void cbDepartmentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDepartmentItemStateChanged
        departmentChoise = (Department) cbDepartment.getSelectedItem();
        if (departmentChoise != null) {
            loadPerson();
        }
    }//GEN-LAST:event_cbDepartmentItemStateChanged

    private void cbAccountTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbAccountTypeItemStateChanged
        accountTypeChoise = (AccountType) cbAccountType.getSelectedItem();
    }//GEN-LAST:event_cbAccountTypeItemStateChanged

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        editAccount();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        removeAccount();
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void cbAccountTypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbAccountTypeMouseClicked
        if (evt.getClickCount() <= 2) {
            return;
        }
        AccountTypeInfomation ati;
        if (accountTypeChoise == null) {
            ati = new AccountTypeInfomation();
        } else {
            ati = new AccountTypeInfomation(accountTypeChoise);
        }
        ati.setVisible(true);
        scrollPane.setViewportView(ati);
    }//GEN-LAST:event_cbAccountTypeMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox cbAccountType;
    private javax.swing.JComboBox cbDepartment;
    private javax.swing.JComboBox cbEnterprise;
    private javax.swing.JComboBox cbPerson;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbMessenger;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getModuleName() {
        return Installer.MODULE_NAME;
    }

    @Override
    public String getUserInterfaceName() {
        return AccountInformation.USER_INTERFACE;
    }

    @Override
    public String getUserInterfaceDescription() {
        return AccountInformation.USER_INTERFACE_DESCRIPTION;
    }

    private void loadCombobox() {
        loadEnterprise();
        loadAccountType();
    }

    private void loadEnterprise() {
        enterprises = enterpriseBN.selectAll();
        DefaultComboBoxModel model = new DefaultComboBoxModel(enterprises.toArray());
        cbEnterprise.setModel(model);
        if (enterprises.size() > 0) {
            cbEnterprise.setSelectedIndex(0);
            enterpriseChoise = (Enterprise) cbEnterprise.getSelectedItem();
            if (enterpriseChoise != null) {
                loadDepartment();
            }
        }
    }

    private void loadDepartment() {
        departments = departmentBN.filterDepartmentByEnterprise(enterpriseChoise);
        DefaultComboBoxModel model = new DefaultComboBoxModel(departments.toArray());
        cbDepartment.setModel(model);
        if (departments.size() > 0) {
            cbDepartment.setSelectedIndex(0);
            departmentChoise = (Department) cbDepartment.getSelectedItem();
            if (departmentChoise != null) {
                loadPerson();
            }
        }
    }

    private void loadPerson() {
        persons = personBN.filterPersonByDepartment(enterpriseChoise, departmentChoise);
        DefaultComboBoxModel model = new DefaultComboBoxModel(persons.toArray());
        cbPerson.setModel(model);
        if (persons.size() > 0) {
            cbPerson.setSelectedIndex(0);
            personChoise = (Person) cbPerson.getSelectedItem();
        }
    }

    private void loadAccountType() {
        accountTypes = accountTypeDAO.selectAll();
        DefaultComboBoxModel model = new DefaultComboBoxModel(accountTypes.toArray());
        cbAccountType.setModel(model);
        if (accountTypes.size() > 0) {
            cbAccountType.setSelectedIndex(0);
            accountTypeChoise= (AccountType) cbAccountType.getSelectedItem();
        }
    }

    private void loadDefault() {
        loadCombobox();
        txtPassword.setText("");
        txtUsername.setText("");
    }

    private boolean checkCreateAccount() {
        if (txtUsername.getText().isEmpty()) {
            lbMessenger.setVisible(true);
            lbMessenger.setText("Username  trống");
            return false;
        }       
        List<Account> list = accountDAO.select(Account.FIELD_USERNAME, txtUsername.getText());
        if (list.size() > 0) {
            lbMessenger.setVisible(true);
            lbMessenger.setText("Username :" + txtUsername.getText() + " đã được sử dụng");
            return false;
        }
        lbMessenger.setVisible(false);
        return true;
    }

    private void createAccount() {
        if (!checkCreateAccount()) {
            return;
        }
        accountChoise = new Account();
        accountChoise.setUsername(txtUsername.getText());
        accountChoise.setPassword(txtPassword.getText());
        accountChoise.setPersonIdActual(personChoise.getId());
        accountChoise.setAccountTypeIdActual(accountTypeChoise.getId());
        if (!accountDAO.insert(accountChoise)) {
            lbMessenger.setVisible(true);
            lbMessenger.setText("Tạo mới account không thành công");
            return;
        }
        lbMessenger.setVisible(false);
        this.setVisible(false);
    }

    private boolean checkEditAccount() {
        if (txtUsername.getText().isEmpty()) {
            lbMessenger.setVisible(true);
            lbMessenger.setText("Username  trống");
            return false;
        }
        if(accountChoise.getUsername().equals("super admin")){
            if(accountTypeChoise.getId()!=accountChoise.getAccountTypeIdActual()){
                lbMessenger.setVisible(true);
                lbMessenger.setText("Không được phép thay đổi lớp quản lý của account super admin");
                return false;
            }
            if(!accountChoise.getUsername().equals(txtUsername.getText())){
                lbMessenger.setVisible(true);
                lbMessenger.setText("Không được phép thay đổi tên của account super admin");
                return false;
            }
        }
        if (!accountChoise.getUsername().equals(txtUsername.getText())) {
            List<Account> list = accountDAO.select(Account.FIELD_USERNAME, txtUsername.getText());
            if (list.size() > 0) {
                lbMessenger.setVisible(true);
                lbMessenger.setText("Username :" + txtUsername.getText() + " đã được sử dụng");
                return false;
            }
        }
        lbMessenger.setVisible(false);
        return true;
    }

    private void editAccount() {
        if (!checkEditAccount()) {
            return;
        }
        accountChoise.setUsername(txtUsername.getText());
        accountChoise.setPassword(txtPassword.getText());
        accountChoise.setPersonIdActual(personChoise.getId());
        accountChoise.setAccountTypeIdActual(accountTypeChoise.getId());
        if (!accountDAO.update(accountChoise)) {
            lbMessenger.setVisible(true);
            lbMessenger.setText("Chỉnh sửa account không thành công");
            return;
        }
        lbMessenger.setVisible(false);
        this.setVisible(false);
    }

    private void removeAccount() {
        if(accountChoise.getUsername().equals("super admin")){
            lbMessenger.setVisible(true);
            lbMessenger.setText("Không được phép xóa account super admin");
            return;
        }
        if (!accountDAO.delete(accountChoise)) {
            lbMessenger.setVisible(true);
            lbMessenger.setText("Không thể xóa account");
            return;
        }
        lbMessenger.setVisible(false);
        this.setVisible(false);
    }

    private void cancel() {
        this.setVisible(false);
    }

    private void loadPersonChoise() {
        loadEnterprise();
        for (int i = 0; i < enterprises.size(); i++) {
            if (enterprises.get(i).getId()==personChoise.getEnterpriseIdActual()) {
                cbEnterprise.setSelectedItem(enterprises.get(i));
                break;
            }
        }
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getId()==personChoise.getDepartmentIdActual()) {
                cbDepartment.setSelectedItem(departments.get(i));
            }
        }

        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getId()==(personChoise.getId())) {
                cbPerson.setSelectedItem(persons.get(i));
            }
        }
        loadAccountType();
    }

    private void loadAccountChoise() {
        txtUsername.setText(accountChoise.getUsername());
        txtPassword.setText(accountChoise.getPassword());
        loadEnterprise();
        for (int i = 0; i < enterprises.size(); i++) {
            if (enterprises.get(i).getId()==(personChoise.getEnterpriseIdActual())) {
                cbEnterprise.setSelectedItem(enterprises.get(i));
                enterpriseChoise = (Enterprise) cbEnterprise.getSelectedItem();
                break;
            }
        }
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getId()==(personChoise.getDepartmentIdActual())) {
                cbDepartment.setSelectedItem(departments.get(i));
                departmentChoise = (Department) cbDepartment.getSelectedItem();
            }
        }

        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getId()==(personChoise.getId())) {
                cbPerson.setSelectedItem(persons.get(i));
                personChoise = (Person) cbPerson.getSelectedItem();
            }
        }
        loadAccountType();
        for (int i = 0; i < accountTypes.size(); i++) {
            if (accountTypes.get(i).getId() == accountChoise.getAccountTypeIdActual()) {
                cbAccountType.setSelectedItem(accountTypes.get(i));
                accountTypeChoise = (AccountType) cbAccountType.getSelectedItem();
                break;
            }
        }
    }
}
