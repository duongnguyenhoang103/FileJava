/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * License.java
 *
 * Created on Jan 30, 2012, 12:01:55 PM
 */
package vn.com.hkt.license.gui.frame;

import java.util.List;
import javax.swing.JOptionPane;
import vn.com.hkt.license.api.IKeyInstallDAO;
import vn.com.hkt.license.api.IKeyLicenseDAO;
import vn.com.hkt.license.encoding.Base32;
import vn.com.hkt.license.encoding.DecryptAES;
import vn.com.hkt.license.encoding.ProductId;
import vn.com.hkt.license.entities.KeyInstall;
import vn.com.hkt.license.entities.KeyLicense;
import vn.com.hkt.license.spi.KeyInstallDAO;
import vn.com.hkt.license.spi.KeyLicenseDAO;

/**
 *
 * giao diện đăng kí, kích hoạt sử dụng
 */
public class License extends javax.swing.JFrame {

    private static License frame;

    public static License getFrame(String moduleName) {
        if (frame == null) {
            frame = new License();
        }
        frame.loadDefault(moduleName);
        return frame;
    }
    private String keyMachine;
    private IKeyLicenseDAO keyLicenseDAO = new KeyLicenseDAO();
    private IKeyInstallDAO keyInstallDAO = new KeyInstallDAO();

    /** Creates new form License */
    private License() {
        initComponents();
        initKeyMachine();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtKeyInstall = new javax.swing.JTextField();
        txtKeyUser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        txtKeyMachine = new javax.swing.JTextField();
        btnCheck = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtModuleName = new javax.swing.JTextField();

        setTitle(org.openide.util.NbBundle.getMessage(License.class, "License.title")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(License.class, "License.jLabel2.text")); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(License.class, "License.jLabel1.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(License.class, "License.jLabel3.text")); // NOI18N

        btnRefresh.setText(org.openide.util.NbBundle.getMessage(License.class, "License.btnRefresh.text")); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnCheck.setText(org.openide.util.NbBundle.getMessage(License.class, "License.btnCheck.text")); // NOI18N
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });

        btnCancel.setText(org.openide.util.NbBundle.getMessage(License.class, "License.btnCancel.text")); // NOI18N
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel4.setText(org.openide.util.NbBundle.getMessage(License.class, "License.jLabel4.text")); // NOI18N

        txtModuleName.setEditable(false);
        txtModuleName.setText(org.openide.util.NbBundle.getMessage(License.class, "License.txtModuleName.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(45, 45, 45)
                        .addComponent(txtModuleName))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtKeyMachine, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(txtKeyUser, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(txtKeyInstall, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRefresh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCheck)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancel)))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtModuleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtKeyMachine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKeyUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtKeyInstall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefresh)
                    .addComponent(btnCheck)
                    .addComponent(btnCancel))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
    loadKeyMachine();
}//GEN-LAST:event_btnRefreshActionPerformed

private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
    checkKey();
}//GEN-LAST:event_btnCheckActionPerformed

private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
    this.dispose();
}//GEN-LAST:event_btnCancelActionPerformed
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(License.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(License.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(License.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(License.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                new License().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCheck;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtKeyInstall;
    private javax.swing.JTextField txtKeyMachine;
    private javax.swing.JTextField txtKeyUser;
    private javax.swing.JTextField txtModuleName;
    // End of variables declaration//GEN-END:variables
    //lấy mã máy
    private void initKeyMachine() {
        keyMachine = ProductId.getSerialNumber("C");
        keyMachine = new Base32().byteArray_2_String32(keyMachine.getBytes());
    }
    //hiển thị mã máy
    private void loadKeyMachine() {
        txtKeyMachine.setText(keyMachine);
    }
    // kiểm tra kí tự theo chuẩn base32
    private boolean checkChar(char c) {
        if ((c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
            return true;
        }
        return false;
    }
    // kiểm tra chuỗi theo chuẩn base32
    private boolean checkString(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (!checkChar(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    // kiểm tra dữ liệu đầu vào
    private boolean checkFormatInput() {
        String keyUser = txtKeyUser.getText();
        String keyInstall = txtKeyInstall.getText();
        if (keyUser.isEmpty() || keyInstall.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Key khong co gi");
            return false;
        }
        if (keyUser.length() != 16) {
            JOptionPane.showMessageDialog(null, "Key user phai du 16 ki tu");
            return false;
        }
        if (!checkString(keyUser) || !checkString(keyInstall)) {
            JOptionPane.showMessageDialog(null, "Key chi co chu hoa va so");
            return false;
        }
        return true;
    }
    // hiển thị mặc định 
    private void loadDefault(String moduleName) {
        txtModuleName.setText(moduleName);
        loadKeyMachine();
        txtKeyUser.setText("");
        txtKeyInstall.setText("");
    }
    // kiểm tra key đăng kí và key cài đặt
    private void checkKey() {
        if (!checkFormatInput()) {
            return;
        }
        String keyUser = txtKeyUser.getText();
        String keyInstall = txtKeyInstall.getText();
        Base32 base32 = new Base32();
        DecryptAES daes = new DecryptAES(keyUser.getBytes(), base32.string32_2_ByteArray(keyInstall));

        byte[] dataDecrypt = daes.decrypt();

        int lengKeyMachineDecrypt = dataDecrypt[0];
        if (dataDecrypt.length <= lengKeyMachineDecrypt) {
            JOptionPane.showMessageDialog(null, "Key sai");
            return;
        }
        byte[] bytesKeyMachineDecrypt = new byte[lengKeyMachineDecrypt];
        for (int i = 0; i < lengKeyMachineDecrypt; i++) {
            bytesKeyMachineDecrypt[i] = dataDecrypt[i + 1];
        }
        String stringKeyMachineDecrypt = new String(bytesKeyMachineDecrypt);

        int lengDateActivate = dataDecrypt[lengKeyMachineDecrypt + 1];
        if (dataDecrypt.length <= lengDateActivate + lengKeyMachineDecrypt + 1) {
            JOptionPane.showMessageDialog(null, "Key sai");
            return;
        }
        byte[] bytesDateActivate = new byte[lengDateActivate];
        for (int i = 0; i < lengDateActivate; i++) {
            bytesDateActivate[i] = dataDecrypt[lengKeyMachineDecrypt + 2 + i];
        }
        String stringDateActivate = new String(bytesDateActivate);

        if (stringKeyMachineDecrypt.equals(keyMachine)) {
            if (extentionTimeActivate(stringDateActivate)) {
                JOptionPane.showMessageDialog(null, "Gia han thanh cong");
                this.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Key sai");
        }
    }
    // thực hiện việc gia hạn, đăng kí
    private boolean extentionTimeActivate(String stringDateActivate) {
        String keyUser = txtKeyUser.getText();
        String keyInstall = txtKeyInstall.getText();
        String moduleName = txtModuleName.getText();

        KeyInstall keyInstallChoise = null;
        List<KeyInstall> keyInstalls = keyInstallDAO.selectAll();
        for (int i = 0; i < keyInstalls.size(); i++) {
            if (keyInstalls.get(i).getModule().equals(moduleName)) {
                keyInstallChoise = keyInstalls.get(i);
                break;
            }
        }
        KeyLicense keyLicenseChoise = null;
        List<KeyLicense> keyLicenses = keyLicenseDAO.selectAll();
        for (int i = 0; i < keyLicenses.size(); i++) {
            if (keyLicenses.get(i).getKeyMachine().equals(keyMachine)
                    && keyLicenses.get(i).getKeyUser().equals(keyUser)) {
                keyLicenseChoise = keyLicenses.get(i);
                break;
            }
        }

        if (keyLicenseChoise == null) {
            keyLicenseChoise = new KeyLicense();
            keyLicenseChoise.setKeyMachine(keyMachine);
            keyLicenseChoise.setKeyUser(keyUser);
            if (!keyLicenseDAO.insert(keyLicenseChoise)) {
                JOptionPane.showMessageDialog(null, "Gia hạn không thành công");
                return false;
            }
        }
        if (keyInstallChoise == null) {
            keyInstallChoise = new KeyInstall();
            keyInstallChoise.setDateActivate(stringDateActivate);
            keyInstallChoise.setKeyInstall(keyInstall);
            keyInstallChoise.setModule(moduleName);
            keyInstallChoise.setKeyLicenseId(keyLicenseChoise.getId());
            if (!keyInstallDAO.insert(keyInstallChoise)) {
                JOptionPane.showMessageDialog(null, "Gia hạn không thành công");
                return false;
            }
        } else {
            keyInstallChoise.setDateActivate(stringDateActivate);
            keyInstallChoise.setKeyInstall(keyInstall);
            keyInstallChoise.setModule(moduleName);
            keyInstallChoise.setKeyLicenseId(keyLicenseChoise.getId());
            if (!keyInstallDAO.update(keyInstallChoise)) {
                JOptionPane.showMessageDialog(null, "Gia hạn không thành công");
                return false;
            }
        }
        return true;
    }
}