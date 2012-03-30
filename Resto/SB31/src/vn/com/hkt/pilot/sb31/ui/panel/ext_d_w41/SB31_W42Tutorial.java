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
public class SB31_W42Tutorial {

    private JTextArea VangLai;

    public SB31_W42Tutorial() {
        VangLai = new JTextArea();
        VangLai.setText("Nhập loại khách vãng ");

    }

    public JTextArea getVangLai() {
        return VangLai;
    }
}
