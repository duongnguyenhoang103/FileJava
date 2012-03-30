/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.bom.product.ui;

import javax.swing.JTextArea;

/**
 *
 * @author longnt
 */
public class ProductTutorial {

    private JTextArea txtDirector, txtNameProduct, txtEnterprise, txtDepartment, txtProductParent;

    public JTextArea getTxtDepartment() {
        return txtDepartment;
    }

    public JTextArea getTxtDirector() {
        return txtDirector;
    }

    public JTextArea getTxtEnterprise() {
        return txtEnterprise;
    }

    public JTextArea getTxtNameProduct() {
        return txtNameProduct;
    }

    public JTextArea getTxtProductParent() {
        return txtProductParent;
    }

     

    public ProductTutorial() {
        txtDirector = new JTextArea();
        txtDirector.setText("Chọn người phụ trách từ combobox");

        txtEnterprise = new JTextArea();
        txtEnterprise.setText("Chọn công ty từ combobox");
        txtNameProduct = new JTextArea();
        txtNameProduct.setText("Nhập tên sản phẩm");
        txtDepartment = new JTextArea();
        txtDepartment.setText("Chọn bộ phận dự án từ combobox");


        txtProductParent = new JTextArea();
        txtProductParent.setText("Chọn sản phẩm mẹ từ combobox hoặc để trống");
    }

}
