/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.bom.operation.ui;

import javax.swing.JTextArea;

/**
 *
 * @author longnt
 */
public class OperationTutorial {
    
    private JTextArea txtDate, txtNameOperation, txtEnterprise, txtDepartment, txtPerson;
    
    public JTextArea getTxtDate() {
        return txtDate;
    }
    
    public JTextArea getTxtDepartment() {
        return txtDepartment;
    }
    
    public JTextArea getTxtEnterprise() {
        return txtEnterprise;
    }
    
    public JTextArea getTxtNameOperation() {
        return txtNameOperation;
    }
    
    public JTextArea getTxtPerson() {
        return txtPerson;
    }
    
    public OperationTutorial() {
        txtDate = new JTextArea();
        txtDate.setText("Nhập ngày bắt đầu nghiệp vu, định dạng ngày/tháng/năm\n "
                + "hoặc ngày-tháng-năm, hoặc bạn có thể chọn từ lịch");
        
        txtEnterprise = new JTextArea();
        txtEnterprise.setText("Chọn công ty từ combobox");
        txtNameOperation = new JTextArea();
        txtNameOperation.setText("Nhập tên nghiệp vụ");
        txtDepartment = new JTextArea();
        txtDepartment.setText("Chọn bộ phận dự án từ combobox ");    
        
        txtPerson = new JTextArea();
        txtPerson.setText("Chọn người chịu trách nhiệm từ combobox");
    }
}
