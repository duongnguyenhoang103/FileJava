/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb41.product.extW48.ui.window;

import javax.swing.JTextArea;

/**
 *
 * @author tamdong
 */
public class SB41_W48Tutorial {

    private JTextArea TenTiengAnh;
    private JTextArea NgaySanXuat;
    private JTextArea HanSuDung;
    private JTextArea MoTaGhiChu;
    private JTextArea NgayChinhSua;

    public SB41_W48Tutorial() {
        TenTiengAnh = new JTextArea();
        TenTiengAnh.setText("Nhập tên tiếng anh của sản phẩm  ");
        NgaySanXuat = new JTextArea();
        NgaySanXuat.setText("Nhập ngày sản xuất sản phẩm, kiểu nhập ngày/tháng/năm\n"
                + " hoặc chọn ở lịch bên \n");
        HanSuDung = new JTextArea();
        HanSuDung.setText("Nhập hạn sử dụng của sản phẩm, kiểu nhập ngày/tháng/năm\n"
                + "hoặc chọn ở lịch bên\n");
        MoTaGhiChu = new JTextArea();
        MoTaGhiChu.setText("Nhập mô tả ghi chú sản phẩm");
        NgayChinhSua = new JTextArea();
        NgayChinhSua.setText("Nhập ngày chỉnh sửa sản phẩm, kiểu nhập ngày/tháng/năm\n"
                + "hoặc chọn ở lịch bên\n ");

    }

    public JTextArea getHanSuDung() {
        return HanSuDung;
    }

    public JTextArea getMoTaGhiChu() {
        return MoTaGhiChu;
    }

    public JTextArea getNgayChinhSua() {
        return NgayChinhSua;
    }

    public JTextArea getNgaySanXuat() {
        return NgaySanXuat;
    }

    public JTextArea getTenTiengAnh() {
        return TenTiengAnh;
    }
}
