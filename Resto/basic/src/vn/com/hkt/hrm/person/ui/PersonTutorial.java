/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.hrm.person.ui;

import javax.swing.JTextArea;

/**
 *
 * @author longnt
 */
public class PersonTutorial {

    private JTextArea txtMisstion, txtNameP, txtEnterprise, txtDepartmentParent, txtCountry;

    public JTextArea getTxtCountry() {
        return txtCountry;
    }

    public JTextArea getTxtDepartmentParent() {
        return txtDepartmentParent;
    }

    public JTextArea getTxtEnterprise() {
        return txtEnterprise;
    }

    public JTextArea getTxtMisstion() {
        return txtMisstion;
    }

    public JTextArea getTxtNameP() {
        return txtNameP;
    }

     

    public PersonTutorial() {
        txtMisstion = new JTextArea();
        txtMisstion.setText("Chọn chức vụ từ combobox ");

        txtEnterprise = new JTextArea();
        txtEnterprise.setText("Chọn công ty từ combobox");
        txtNameP = new JTextArea();
        txtNameP.setText("Nhập họ tên đầy đủ cho nhân viên/ cá nhân");
        txtDepartmentParent = new JTextArea();
        txtDepartmentParent.setText("Chọn phòng ban mẹ");

        txtCountry = new JTextArea();
        txtCountry.setText("Chọn quốc tịch từ combobox");

    }

   
}
