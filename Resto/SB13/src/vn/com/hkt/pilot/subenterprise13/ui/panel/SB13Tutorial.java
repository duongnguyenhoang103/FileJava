/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.subenterprise13.ui.panel;

import javax.swing.JTextArea;

/**
 *
 * @author longnt
 */
public class SB13Tutorial {

    private JTextArea txtDoanhThu, txtLoiNhuan, txtVonDieuLe, txtSoNhanVien, txtVonPhapDinh;

    public JTextArea getTxtDoanhThu() {
        return txtDoanhThu;
    }

    public JTextArea getTxtLoiNhuan() {
        return txtLoiNhuan;
    }

    public JTextArea getTxtVonDieuLe() {
        return txtVonDieuLe;
    }

    public JTextArea getTxtVonPhapDinh() {
        return txtVonPhapDinh;
    }

    public JTextArea getTxtSoNhanVien() {
        return txtSoNhanVien;
    }

    public SB13Tutorial() {
        txtDoanhThu = new JTextArea();
        txtDoanhThu.setText("thông tin về doanh thu");

        txtVonDieuLe = new JTextArea();
        txtVonDieuLe.setText("Vốn điều lệ");
        txtLoiNhuan = new JTextArea();
        txtLoiNhuan.setText("Lợi nhuận");
        txtSoNhanVien = new JTextArea();
        txtSoNhanVien.setText("Số nhân viên của công ty");
        txtVonPhapDinh = new JTextArea();
        txtVonPhapDinh.setText("Vốn pháp định");


    }
}
