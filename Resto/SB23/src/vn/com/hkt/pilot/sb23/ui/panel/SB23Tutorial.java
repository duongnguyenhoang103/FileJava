/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb23.ui.panel;

import javax.swing.JTextArea;

/**
 *
 * @author DONGTAM
 */
public class SB23Tutorial {
    private JTextArea NgayBatDau;
    private JTextArea LuongToiThieu;
    private JTextArea LuongHienTai;
    private JTextArea TongNhan;
    private JTextArea Thuong;
    private JTextArea NgayHetHan;
    private JTextArea LuongToiDa;
    private JTextArea TongPhuCap;
    private JTextArea DaTra;
    private JTextArea SoDu;

    public SB23Tutorial()
    {
        NgayBatDau=new JTextArea();
        NgayBatDau.setText("Nhập ngàu băt đầu hợp đồng, định dạng kiểu ngày/tháng/năm\n"
                +"hoặc ngày-tháng-năm hoặc có thể chọn từ lịch");
        LuongToiThieu=new JTextArea();
        LuongToiThieu.setText("Nhập lương tối thiểu, chỉ được nhập dạng số ");
        LuongHienTai=new JTextArea();
        LuongHienTai.setText("Nhập mức lương hiện tại, chỉ được nhập số");
        TongNhan=new JTextArea();
        TongNhan.setText("Nhập tổng số tiền được nhận, chỉ được nhập số");
        Thuong=new JTextArea();
        Thuong.setText("Nhập tiền thưởng, chỉ được nhập số");
        NgayHetHan=new JTextArea();
        NgayHetHan.setText("Nhập ngày hết hạn hợp đồng, định dạng ngày/tháng/năm\n"
                             +"hoặc ngày-tháng-năm hoặc bạn có thể chọn từ lịch");
        LuongToiDa=new JTextArea();
        LuongToiDa.setText("Nhập lương tối đa, chỉ được nhập số");
        TongPhuCap=new JTextArea();
        TongPhuCap.setText("Nhập tổng số tiền phụ câp, chỉ được nhập số");
        DaTra=new JTextArea();
        DaTra.setText("Tổng số tiền đã trả, chỉ được nhập số");
        SoDu=new JTextArea();
        SoDu.setText("nhập số dư, chỉ được nhập kiểu số");
        
    }
    public JTextArea getDaTra() {
        return DaTra;
    }

    public JTextArea getLuongHienTai() {
        return LuongHienTai;
    }

    public JTextArea getLuongToiDa() {
        return LuongToiDa;
    }

    public JTextArea getLuongToiThieu() {
        return LuongToiThieu;
    }

    public JTextArea getNgayBatDau() {
        return NgayBatDau;
    }

    public JTextArea getNgayHetHan() {
        return NgayHetHan;
    }

    public JTextArea getSoDu() {
        return SoDu;
    }

    public JTextArea getThuong() {
        return Thuong;
    }

    public JTextArea getTongNhan() {
        return TongNhan;
    }

    public JTextArea getTongPhuCap() {
        return TongPhuCap;
    }
}
