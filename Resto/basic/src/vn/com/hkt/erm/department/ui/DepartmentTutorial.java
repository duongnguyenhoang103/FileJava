/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.erm.department.ui;

import javax.swing.JTextArea;

/**
 *
 * @author longnt- dongtam
 */
public class DepartmentTutorial {

    private JTextArea txtDirector, txtNameD, txtEnterprise, txtDepartmentParent, txtSP;

     

    public DepartmentTutorial() {
        txtDirector = new JTextArea();
        txtDirector.setText("Chọn trưởng dự án từ combobox");

        txtEnterprise = new JTextArea();
        txtEnterprise.setText("Chọn công ty từ combobox");
        txtNameD = new JTextArea();
        txtNameD.setText("Nhập tên dự án");
        txtDepartmentParent = new JTextArea();
        txtDepartmentParent.setText("Chọn phòng ban mẹ");

        txtSP = new JTextArea();
        txtSP.setText("Chọn sản phẩm dịch vụ chính");

    }

    public JTextArea getTxtDepartmentParent() {
        return txtDepartmentParent;
    }

    public JTextArea getTxtDirector() {
        return txtDirector;
    }

    public JTextArea getTxtEnterprise() {
        return txtEnterprise;
    }

    public JTextArea getTxtNameD() {
        return txtNameD;
    }

    public JTextArea getTxtSP() {
        return txtSP;
    }
}
