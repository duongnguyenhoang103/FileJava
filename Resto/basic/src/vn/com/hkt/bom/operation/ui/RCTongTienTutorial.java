/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.bom.operation.ui;

import javax.swing.JTextArea;

/**
 *
 * @author DONGTAM
 */
public class RCTongTienTutorial {

    private JTextArea TongTien;
    private JTextArea ChietKhau;
    private JTextArea LoaiThue;
    private JTextArea TongCong;
    private JTextArea TienTraLai;
    private JTextArea GiaTriCK;
    private JTextArea TienThue;
    private JTextArea ThanhToan;
    private JTextArea TienNo;
    public RCTongTienTutorial()
    {
        TongTien=new JTextArea();
        TongTien.setText("Nhập tông tiền, chỉ được nhập số");
        ChietKhau=new JTextArea();
        ChietKhau.setText("Nhập % chiết khấu, chỉ được nhập số");
        LoaiThue=new JTextArea();
        LoaiThue.setText("Chọn loại thuế từ combobox");
        TongCong=new JTextArea();
        TongCong.setText("Tổng cộng");
        TienTraLai=new JTextArea();
        TienTraLai.setText("Nhập số tiền trả lại, chỉ được nhập số");
        GiaTriCK=new JTextArea();
        GiaTriCK.setText("Nhập giá trị chiết khấu, chỉ được nhập số");
        TienThue=new JTextArea();
        TienThue.setText("Nhập tiền thuế, chỉ được nhập số");
        ThanhToan=new JTextArea();
        ThanhToan.setText("Nhập số tiền thanh toán, chỉ được nhập số");
        TienNo=new JTextArea();
        TienNo.setText("Nhập tiền nợ, chỉ được nhập số");
    }

    public JTextArea getTongTien() {
        return TongTien;
    }

    public JTextArea getChietKhau() {
        return ChietKhau;
    }

    public JTextArea getGiaTriCK() {
        return GiaTriCK;
    }

    public JTextArea getLoaiThue() {
        return LoaiThue;
    }

    public JTextArea getThanhToan() {
        return ThanhToan;
    }

    public JTextArea getTienNo() {
        return TienNo;
    }

    public JTextArea getTienThue() {
        return TienThue;
    }

    public JTextArea getTienTraLai() {
        return TienTraLai;
    }

    public JTextArea getTongCong() {
        return TongCong;
    }
    
}
