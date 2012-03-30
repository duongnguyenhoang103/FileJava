/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb32.ui.panel;

import javax.swing.JTextArea;

/**
 *
 * @author DONGTAM
 */
public class SB32_W43Tutorial {
    private JTextArea TongTienThu;
    private JTextArea TongLoiNhuan;
    private JTextArea DanhGia;
    private JTextArea TongTienChi;
    private  JTextArea TySuatLoiNhuan;
    public  SB32_W43Tutorial()
    {
        TongTienThu=new JTextArea();
        TongTienThu.setText("Hiển thị tổng tiền thu");
        TongLoiNhuan=new JTextArea();
        TongLoiNhuan.setText("Hiển thị tổng lợi nhuận");
        DanhGia=new JTextArea();
        DanhGia.setText("đánh giá ");
        TongTienChi=new JTextArea();
        TongTienChi.setText("Hiển thị tổng tiền chi");
        TySuatLoiNhuan=new JTextArea();
        TySuatLoiNhuan.setText("Hiển thị tỷ suất lợi nhuận");
    }

    public JTextArea getDanhGia() {
        return DanhGia;
    }

    public JTextArea getTongLoiNhuan() {
        return TongLoiNhuan;
    }

    public JTextArea getTongTienChi() {
        return TongTienChi;
    }

    public JTextArea getTongTienThu() {
        return TongTienThu;
    }

    public JTextArea getTySuatLoiNhuan() {
        return TySuatLoiNhuan;
    }
    
}
