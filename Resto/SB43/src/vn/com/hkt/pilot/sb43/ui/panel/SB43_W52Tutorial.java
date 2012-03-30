/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb43.ui.panel;

import javax.swing.JTextArea;

/**
 *
 * @author tamdong
 */
public class SB43_W52Tutorial {

    private JTextArea TongLuongXuat;
    private JTextArea TongTienXuat;
    private JTextArea TongLoiNhuan;
    private JTextArea DanhGia;
    private JTextArea TongLuongNhap;
    private JTextArea TongTienNhap;
    private JTextArea TySuatLoiNhuan;

    public SB43_W52Tutorial() {
        TongLuongXuat = new JTextArea();
        TongLuongXuat.setText("Hiển thị tổng lượng xuất sản phẩm ");

        TongTienXuat = new JTextArea();
        TongTienXuat.setText("Hiển thị tổng tiền xuất cho sản phẩm ");

        TongLoiNhuan = new JTextArea();
        TongLoiNhuan.setText("Hiển thị tổng lợi nhuận");

        DanhGia = new JTextArea();
        DanhGia.setText("Đánh giá");

        TongLuongNhap = new JTextArea();
        TongLuongNhap.setText("Hiển thị tổng lượng nhập ");

        TongTienNhap = new JTextArea();
        TongTienNhap.setText("Hiển thị tổng tiền nhập ");

        TySuatLoiNhuan = new JTextArea();
        TySuatLoiNhuan.setText("Hiển thị tỷ suất lợi nhuận ");

    }

    public JTextArea getDanhGia() {
        return DanhGia;
    }

    public JTextArea getTongLoiNhuan() {
        return TongLoiNhuan;
    }

    public JTextArea getTongLuongNhap() {
        return TongLuongNhap;
    }

    public JTextArea getTongLuongXuat() {
        return TongLuongXuat;
    }

    public JTextArea getTongTienNhap() {
        return TongTienNhap;
    }

    public JTextArea getTongTienXuat() {
        return TongTienXuat;
    }

    public JTextArea getTySuatLoiNhuan() {
        return TySuatLoiNhuan;
    }
}
