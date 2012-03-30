/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.ui.panel;

import javax.swing.JTextArea;

/**
 *
 * @author longnt-dongtam
 */
public class SB12Tutorial {

    private JTextArea txtTell, txtFax, txtEmail, txtWeb, txtAddr;

    public JTextArea getTxtAddr() {
        return txtAddr;
    }

    public JTextArea getTxtEmail() {
        return txtEmail;
    }

    public JTextArea getTxtFax() {
        return txtFax;
    }

    public JTextArea getTxtTell() {
        return txtTell;
    }

    public JTextArea getTxtWeb() {
        return txtWeb;
    }

    public SB12Tutorial() {
        txtTell = new JTextArea();
        txtTell.setText("Nhập số điện thoại ");

        txtEmail = new JTextArea();
        txtEmail.setText("Nhập địa chỉ mail ");
        txtFax = new JTextArea();
        txtFax.setText("Nhập số fax ");
        txtWeb = new JTextArea();
        txtWeb.setText("Nhập địa chỉ website của công ty");

        txtAddr = new JTextArea();
        txtAddr.setText("Nhập địa chỉ của công ty");


    }
}
