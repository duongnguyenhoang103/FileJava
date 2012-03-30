/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb25.ui.panel;

import javax.swing.JTextArea;

/**
 *
 * @author tamdong
 */
public class SB25Tutorial {

    private JTextArea HocHam;
    private JTextArea XepHangTheoLoiNhuan;
    private JTextArea ChiSoTiemNang;
    private JTextArea HocVi;
    private JTextArea XepHanGTheoDoanhthu;
    private JTextArea DanhGiaChung;

    public SB25Tutorial() {
        HocHam = new JTextArea();
        HocHam.setText(" Hiển thị học hàm cao nhất");
        XepHangTheoLoiNhuan = new JTextArea();
        XepHangTheoLoiNhuan.setText("Kết quả xếp hạng theo lợi nhuận        \n" );
        ChiSoTiemNang = new JTextArea();
        ChiSoTiemNang.setText(" Kết quả chỉ số tiềm năng                    \n");
        HocVi = new JTextArea();
        HocVi.setText("Hiển thị học vị cao nhất                            \n");
        XepHanGTheoDoanhthu = new JTextArea();
        XepHanGTheoDoanhthu.setText("Kết quả xếp hạng theo doanh thu        \n");
        DanhGiaChung = new JTextArea();
        DanhGiaChung.setText("Kết quả đánh giá\n");
    }

    public JTextArea getChiSoTiemNang() {
        return ChiSoTiemNang;
    }

    public JTextArea getDanhGiaChung() {
        return DanhGiaChung;
    }

    public JTextArea getHocHam() {
        return HocHam;
    }

    public JTextArea getHocVi() {
        return HocVi;
    }

    public JTextArea getXepHanGTheoDoanhthu() {
        return XepHanGTheoDoanhthu;
    }

    public JTextArea getXepHangTheoLoiNhuan() {
        return XepHangTheoLoiNhuan;
    }
}
