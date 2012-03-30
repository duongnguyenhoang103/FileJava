/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subenterprise.ui.panel;

import javax.swing.JTextArea;

/**
 *
 * @author longnt
 */
public class SB11Tutorial {

    private JTextArea txtNameTV, txtNameTA, txtType, txtRegister, txtDateS, txtDateR, txtNumR, txtKindOfTax,
            txtStatus, txtBusiness, txtDesscription;

    public JTextArea getTxtBusiness() {
        return txtBusiness;
    }

    public JTextArea getTxtDesscription() {
        return txtDesscription;
    }

    public JTextArea getTxtDateR() {
        return txtDateR;
    }

    public JTextArea getTxtDateS() {
        return txtDateS;
    }

    public JTextArea getTxtKindOfTax() {
        return txtKindOfTax;
    }

    public JTextArea getTxtNameTA() {
        return txtNameTA;
    }

    public JTextArea getTxtNameTV() {
        return txtNameTV;
    }

    public JTextArea getTxtNumR() {
        return txtNumR;
    }

    public JTextArea getTxtRegister() {
        return txtRegister;
    }

    public JTextArea getTxtStatus() {
        return txtStatus;
    }

    public JTextArea getTxtType() {
        return txtType;
    }

    public SB11Tutorial() {
        txtNameTV = new JTextArea();
        txtNameTV.setText("Tên công ty bằng Tiếng Việt");

        txtType = new JTextArea();
        txtType.setText("Nhập loại hình doanh nghiệp ví dụ: công ty cổ phần,công ty trách nhiệm\n"
              +" hữu hạn, vô hạn,công ty hợp danh...");
        txtNameTA = new JTextArea();
        txtNameTA.setText("Nhập tên công ty bằng Tiếng Anh");
        txtRegister = new JTextArea();
        txtRegister.setText("Nhập lần đăng ký kinh doanh thứ mấy , chỉ được nhập các ký tự  số 0->9");

        txtDateS = new JTextArea();
        txtDateS.setText("nhập ngày bắt đầu kinh doanh,kiểu nhập ngày/tháng/năm\n"
                +"hoặc kiểu nhập ngày-tháng-năm... hoặc chọn từ lịch");
        txtDateR = new JTextArea();
        txtDateR.setText("nhập ngày đăng kí kinh doanh, kiểu ngày/tháng/năm\n"
                +"hoặc kiểu nhập ngày-tháng-năm... hoặc chọn từ lịch");

        txtKindOfTax = new JTextArea();
        txtKindOfTax.setText("Nhập mã số thuế, chỉ được nhập các ký tự số từ 0->9\n");
        txtNumR = new JTextArea();
        txtNumR.setText("Nhập số đăng ký kinh doanh,\n"
                +"chỉ được nhập các ký tự số từ 0->9");
        txtStatus = new JTextArea();
        txtStatus.setText("Chọn từ combobox tình trạng kinh doanh");
        
        txtBusiness = new JTextArea();
        txtBusiness.setText("Nhập lĩnh vực kinh doanh của công ty");
        txtDesscription = new JTextArea();
        txtDesscription.setText("Nhập mô tả lĩnh vực kinh doanh");

    }
}
