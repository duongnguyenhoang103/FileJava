/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb31.ui.panel.ext_d_w41;
import vn.com.hkt.pilot.sb31.ui.window.ext_d_w41.*;

import javax.swing.JTextArea;

/**
 *
 * @author tamdong
 */
public class SB31_W43Tutorial {

    private JTextArea HoTen;
    private JTextArea VaiTro;
    private JTextArea TiLe;

    public SB31_W43Tutorial() {
        HoTen = new JTextArea();
        HoTen.setText("Chọn  họ tên thành viên tham gia dự án từ combobox");
         VaiTro = new JTextArea();
        VaiTro.setText("Chọn vai trò của thành viên trong dự án từ combobox");
         TiLe = new JTextArea();
        TiLe.setText("Chọn tỷ lệ đóng góp của thành viên, dạng (%)");
        

    }

    public JTextArea getHoTen() {
        return HoTen;
    }

    public JTextArea getTiLe() {
        return TiLe;
    }

    public JTextArea getVaiTro() {
        return VaiTro;
    }

}
