/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb31.ui.panel.ext_d_w41;

import javax.swing.JTextArea;

/**
 *
 * @author DONGTAM
 */
public class SB31_W44Tutorial {
    private JTextArea TenThongSo;
    private JTextArea NoiDung;
    public SB31_W44Tutorial()
    {
        TenThongSo=new JTextArea();
        TenThongSo.setText("Nhập tên thông sô");
        NoiDung=new JTextArea();
        NoiDung.setText("Nhập nội dung");
    }

    public JTextArea getNoiDung() {
        return NoiDung;
    }

    public JTextArea getTenThongSo() {
        return TenThongSo;
    }
    
    
}
