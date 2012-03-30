/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.hkt.pilot.sb41.product.extW48.ui.panel;

import javax.swing.JTextArea;

/**
 *
 * @author tamdong
 */
public class SB41_W49Tutorial {

    private JTextArea ThongSo;
    private JTextArea MoTa;

    public SB41_W49Tutorial() {
        ThongSo = new JTextArea();
        ThongSo.setText("Nhập thông số sản phẩm ");
         MoTa = new JTextArea();
        MoTa.setText("Nhập mô tả cho thông số ");

    }

    public JTextArea getMoTa() {
        return MoTa;
    }

    public JTextArea getThongSo() {
        return ThongSo;
    }

   
}
