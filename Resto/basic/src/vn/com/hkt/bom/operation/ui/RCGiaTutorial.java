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
public class RCGiaTutorial {
    private JTextArea MaSanPhamDichVu;
    private JTextArea TenSanPhamDichVu;
    private JTextArea SoLuong;
    private JTextArea DonGia;
    private JTextArea ChietKhau;
    private JTextArea ThanhTien;
    private  JTextArea GhiChu;

    public RCGiaTutorial()
    {
        MaSanPhamDichVu=new JTextArea();
        MaSanPhamDichVu.setText("Chọn mã sản phẩm dịch vụ từ combobox");
        TenSanPhamDichVu=new JTextArea();
        TenSanPhamDichVu.setText("Nhập tên sản phẩm dịch vụ");
        SoLuong=new JTextArea();
        SoLuong.setText("Nhập số lượng, chỉ được nhập số nguyên ");
        DonGia=new JTextArea();
        DonGia.setText("Nhập đơn giá cho 1 sản phẩm, chỉ được nhập kiểu số");
        ChietKhau=new JTextArea();
        ChietKhau.setText("Chọn chiết khấu");
        ThanhTien=new JTextArea();
        ThanhTien.setText("Thành tiền");
        GhiChu=new JTextArea();
        GhiChu.setText("Nhập ghi chú");
    }
    public JTextArea getChietKhau() {
        return ChietKhau;
    }

    public JTextArea getDonGia() {
        return DonGia;
    }

    public JTextArea getGhiChu() {
        return GhiChu;
    }

    public JTextArea getMaSanPhamDichVu() {
        return MaSanPhamDichVu;
    }

    public JTextArea getSoLuong() {
        return SoLuong;
    }

    public JTextArea getTenSanPhamDichVu() {
        return TenSanPhamDichVu;
    }

    public JTextArea getThanhTien() {
        return ThanhTien;
    }
    
}
