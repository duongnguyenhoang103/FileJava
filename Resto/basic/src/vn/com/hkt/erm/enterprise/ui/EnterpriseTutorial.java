/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.erm.enterprise.ui;

import javax.swing.JTextArea;

/**
 *
 * @author longnt-dongtam
 */
public class EnterpriseTutorial {

    private JTextArea txtDirector, txtNameEnterprise, txtEnterpriseParent, txtSlogan;

    public JTextArea getTxtDirector() {
        return txtDirector;
    }

    public JTextArea getTxtEnterpriseParent() {
        return txtEnterpriseParent;
    }

    public JTextArea getTxtNameEnterprise() {
        return txtNameEnterprise;
    }

    public JTextArea getTxtSlogan() {
        return txtSlogan;
    }

    public EnterpriseTutorial() {
        txtDirector = new JTextArea();
        txtDirector.setText("Chọn tên giám đốc từ combobox \n");

        txtEnterpriseParent = new JTextArea();
        txtEnterpriseParent.setText("Nếu có công ty mẹ thì chọn từ combobox,\n"
                +"nếu không có thì để trống");
        txtNameEnterprise = new JTextArea();
        txtNameEnterprise.setText("Nhập tên của công ty");
        txtSlogan = new JTextArea();
        txtSlogan.setText("Nhập khẩu hiệu của công ty");


    }
}
