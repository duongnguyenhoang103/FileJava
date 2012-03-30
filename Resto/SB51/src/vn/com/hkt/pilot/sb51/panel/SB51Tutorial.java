/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb51.panel;

import javax.swing.JTextArea;

/**
 *
 * @author DONGTAM
 */
public class SB51Tutorial {

    private JTextArea GhiChu;
    private JTextArea SoHoaDon;
    private JTextArea TaiKhoanDoiUng;

    public SB51Tutorial() {

        GhiChu = new JTextArea();
        GhiChu.setText("Ghi chú thông tin liên quan đến thanh toán");
        SoHoaDon = new JTextArea();
        SoHoaDon.setText("Nhập số hóa đơn");
        TaiKhoanDoiUng = new JTextArea();
        TaiKhoanDoiUng.setText("Nhập tài khoản đối ứng");
    }

    public JTextArea getGhiChu() {
        return GhiChu;
    }

    public JTextArea getSoHoaDon() {
        return SoHoaDon;
    }

    public JTextArea getTaiKhoanDoiUng() {
        return TaiKhoanDoiUng;
    }
}
