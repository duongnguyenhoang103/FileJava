/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb31.ui.window.ext_d_w41;

import javax.swing.JTextArea;

/**
 *
 * @author tamdong
 */
public class SB31Tutorial {

    private JTextArea DuKienTrienKhai;
    private JTextArea DuKienHoanThanh;
    private JTextArea MoTaGhiChu;
    private JTextArea DaTrienKhai;
    private JTextArea DaHoanThanh;
    private JTextArea TienDo;

    public SB31Tutorial() {
        DuKienTrienKhai = new JTextArea();
        DuKienTrienKhai.setText(" Nhập ngày dự kiến triển khai, kiểu ngày/tháng/năm");
        DuKienHoanThanh = new JTextArea();
        DuKienHoanThanh.setText("Nhập ngày dự kiến hoàn thành,kiểu ngày/tháng/năm        \n" );
        MoTaGhiChu = new JTextArea();
        MoTaGhiChu.setText(" Ghi chú                   \n");
        DaTrienKhai = new JTextArea();
        DaTrienKhai.setText("Nhập ngày đã triển khai,kiểu ngày/tháng/năm                        \n");
        DaHoanThanh = new JTextArea();
        DaHoanThanh.setText("Nhập ngày đã hoàn thành,kiểu ngày/tháng/năm    \n");
        TienDo = new JTextArea();
        TienDo.setText("Nhập tiến độ dự án\n");
    }

    public JTextArea getDaHoanThanh() {
        return DaHoanThanh;
    }

    public JTextArea getDaTrienKhai() {
        return DaTrienKhai;
    }

    public JTextArea getDuKienHoanThanh() {
        return DuKienHoanThanh;
    }

    public JTextArea getDuKienTrienKhai() {
        return DuKienTrienKhai;
    }

    public JTextArea getMoTaGhiChu() {
        return MoTaGhiChu;
    }

    public JTextArea getTienDo() {
        return TienDo;
    }

    

    
}
