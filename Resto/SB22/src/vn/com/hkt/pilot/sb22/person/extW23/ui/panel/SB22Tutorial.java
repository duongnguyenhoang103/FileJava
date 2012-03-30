/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb22.person.extW23.ui.panel;

import javax.swing.JTextArea;

/**
 *
 * @author longnt
 */
public class SB22Tutorial {

    private JTextArea txtTell, txtFax, txtEmail, txtWebName;

    public JTextArea getTxtEmail() {
        return txtEmail;
    }

    public JTextArea getTxtFax() {
        return txtFax;
    }

    public JTextArea getTxtTell() {
        return txtTell;
    }

    public JTextArea getTxtWebName() {
        return txtWebName;
    }


    public SB22Tutorial() {
        txtTell = new JTextArea();
        txtTell.setText("Nhập số điện thoại");
 
        txtFax = new JTextArea();
        txtFax.setText("Nhập fax");
        txtEmail = new JTextArea();

        txtEmail.setText("Nhập địa chỉ email");
      
        txtWebName = new JTextArea();

        txtWebName.setText("Nhập địa chỉ web");
         

    }
}
